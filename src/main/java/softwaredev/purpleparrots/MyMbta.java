package softwaredev.purpleparrots;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import softwaredev.purpleparrots.gui.MbtaMap;
import softwaredev.purpleparrots.gui.Mode;
import softwaredev.purpleparrots.gui.RouteType;
import softwaredev.purpleparrots.gui.Station;
import softwaredev.purpleparrots.gui.TimeOfTrip;

public class MyMbta {

    public static String http = "http://developer.mbta.com/lib/rthr/";
    public static String test = "src/test/resources/mbta-test-data/2012_10_19/";
    public static String def = test;
    public static softwaredev.purpleparrots.TMap tMap = new softwaredev.purpleparrots.TMap();

    private static java.util.Map<Line, TrainCache> lines;

    /**
     * Gets the trains for the given line from the default source from
     * the cache.
     * @param line      the line to get the trains for
     * @return          the trains, if available
     * @author          labichn
     */
    public static List<Train> getTrains(Line line) {
    	return lines.get(line).getTrains(def);
    }
    
    /**
     * Gets the trains for the given line from the source at location from
     * the cache.
     * @param line      the line to get the trains for
     * @param location  the location of the source of the train data
     * @return          the trains, if available
     * @author          labichn
     */
    public static List<Train> getTrains(Line line, String location) {
    	return lines.get(line).getTrains(location);
    }
    
    static {
    	Line redL = new Line("Red");
    	Line blueL = new Line("Blue");
    	Line orangeL = new Line("Orange");
        TrainCache red = new TrainCache(redL);
        TrainCache blue = new TrainCache(blueL);
        TrainCache orange = new TrainCache(orangeL);
    	lines = new HashMap<Line, TrainCache>();
    	lines.put(redL, red);
    	lines.put(blueL, blue);
    	lines.put(orangeL, orange);
    }
    
    /**
     * Forces a cache update from the given location.
     * @param location  the location from which to update the caches.
     * @author          labichn
     */
    public static void updateCache(String location) {
    	if (lines != null && lines.size() > 0) {
    		for (TrainCache cache : lines.values()) {
    			cache.forceUpdate(location == null ? def : location);
    		}
    	}
    }

    /**
     * Gets the current location of all trains so they can be mapped to a station and direction
     * 
     * @param color
     * @param location
     * @return
     * 
     * @author jeffreyguion, labichn
     */
    public static Map<String, Train> getCurrentLocationMap(String color, String location) {
        List<String> stations = new ArrayList<String>();
        Line line = new Line(color, stations);
        List<Train> trains = getTrains(line, location);
        HashMap<String, Train> dict = new HashMap<String, Train>();
        Train t;
        Prediction p = null;
        for(int i = 0; i < trains.size(); i++){
            t = trains.get(i);
            if (t.getPredictions() != null) {
                for (int j = 0; j < t.getPredictions().size(); j++) {
                    p = t.getPredictions().get(j);
                    if (p.seconds < 0) {
                        p = null;
                    } else {
                        break;
                    }
                }
            }
            if (p != null) {
                dict.put(p.stopId, t);
            }
        }

        return dict;
    }

    /**
     * Creates a route object through the stops selected by the user
     * 
     * @param map - instance of the GUI map
     * @return Route object, ordered or unordered, based on on the user's selection
     * 
     * @author leighannastolfi
     */
    public static Route getRoute(MbtaMap map, String location){
        tMap.setTimeOfTrip(map.getTimeOfTrip());
        tMap.setTimeOfTripIndex(map.getTimeOfTripIndex());
        int startTime = 0;
        if(map.getMode().equals(Mode.ORDERED_ROUTE)){
            if(tMap.getTimeOfTripIndex().equals(TimeOfTrip.ARRIVE_BY)){
                Route routeNow = getOrderedRoute(map.getRoute(), location, startTime);
                int routeDuration = (int) (routeNow.getTime() * 1.2); //20% minute buffer
                return getOrderedRoute(map.getRoute(), location, getTimeOfTrip(routeDuration));
            }
            else
                return getOrderedRoute(map.getRoute(), location, getTimeOfTrip(getCurrentTime()));
        }
        else if (map.getMode().equals(Mode.UNORDERED_ROUTE)){
            Station startStation = findStationWithName(map.getStartStation(), map.getRoute());
            Station endStation = findStationWithName(map.getEndStation(), map.getRoute());
            boolean isFewestTransfers = false;
            if(map.getRouteType().equals(RouteType.FEWEST_TRANSFERS)){
                isFewestTransfers = true;
            }
            if(startStation == null){
                if(map.getRouteType().equals(RouteType.EARLIEST_DEPARTURE)){
                    startStation = getEarliestDepartingTrain(map.getRoute(), startTime, location);
                }else{
                    startStation = map.getRoute().get(0);
                }
            }
            if(tMap.getTimeOfTripIndex().equals(TimeOfTrip.ARRIVE_BY)){
                Route routeNow = getBestUnorderedRouteAlgorithm(map.getRoute(), location, startStation, endStation, startTime, isFewestTransfers);
                int routeDuration = (int) (routeNow.getTime() * 1.2); //20% minute buffer
                return getBestUnorderedRouteAlgorithm(map.getRoute(), location, startStation, endStation, getTimeOfTrip(routeDuration), isFewestTransfers);
            }
            else
                return getBestUnorderedRouteAlgorithm(map.getRoute(), location, startStation, endStation, getTimeOfTrip(getCurrentTime()), isFewestTransfers);
        }
        else return new Route();//throw new Exception("Route mode not selected");
    }
    
    /**
     * Compare
     */
    
    private static Route getBestUnorderedRouteAlgorithm(ArrayList<Station> route, String location, Station startStation, Station endStation, int timeOfTrip, boolean isFewestTransfers){
        Route r1 = getUnorderedRouteV1(route, location, startStation, endStation, timeOfTrip, true);
        
        if(isFewestTransfers){
            return r1;
        }
        
        Route r2 = getUnorderedRouteV1(route, location, startStation, endStation, timeOfTrip, false);
        
        if((r1.getTime() < r2.getTime() && r1.getTime() != -1) || r2.getTime() == -1){
            return r1;
        }else{
            return r2;
        }
    }
    
    /**
     * Finds the earliest departing train for an unordered list of trains
     * 
     * @param route
     * @param startTime
     * @param location
     * @return
     */
    private static Station getEarliestDepartingTrain(ArrayList<Station> route, int startTime, String location){
        ArrayList<Train> orangeTrains = JsonData.getTrains(tMap.orangeLine, location);
        ArrayList<Train> redTrains = JsonData.getTrains(tMap.redLine, location);
        ArrayList<Train> blueTrains = JsonData.getTrains(tMap.blueLine, location);
        
        Pair<Integer, Station> orange = getEarliestDepartingTrainForLine(route, orangeTrains, startTime);
        Pair<Integer, Station> red = getEarliestDepartingTrainForLine(route, redTrains, startTime);
        Pair<Integer, Station> blue = getEarliestDepartingTrainForLine(route, blueTrains, startTime);
        
        if((orange.a <= red.a || red.a == -1) && (orange.a <= blue.a || blue.a == -1)){
            return orange.b;
        }else if((red.a <= orange.a || orange.a == -1) && (red.a <= blue.a || blue.a == -1)){
            return red.b;
        }else{
            return blue.b;
        }
    }
    
    /**
     * This method finds the earliest departing train for any train on that line.
     * This is so complicated because it has to look through each prediction for each of the incoming trains and then for 
     * each prediction, it has to check if that stop is in your list of stops you want to stop at, if it is heading in the
     * direction you want to go to, and if it is the earliest arriving train.
     * 
     * @param route
     * @param trains
     * @param startTime
     * @param location
     * @return
     * @author jeffreyguion
     */
    private static Pair<Integer, Station> getEarliestDepartingTrainForLine(ArrayList<Station> route, ArrayList<Train> trains, int startTime){
        int earliestTime = -1;
        Station earliestStation = null;
        List<String> stationNames = getStationNames(route);
        for(int i=0; i< trains.size(); i++){
            Train train = trains.get(i);
            List<Prediction> predictions = train.getPredictions();
            for(int j=0; j < predictions.size(); j++){
                Prediction p = predictions.get(j);
                if(stationNames.contains(p.stop)){
                    if(earliestTime == -1 || p.seconds < earliestTime){
                        if(p.seconds > startTime){
                            Station startStop = findStationWithName(p.stop, route);
                            Pair<Station,String> closestStationInfo = getClosestStation(route, startStop);
                            Station nextStop = closestStationInfo.a;
                            String lineDestination = closestStationInfo.b;
                            if(lineDestination.equals(train.getDestination())){
                                earliestTime = p.seconds;
                                earliestStation = startStop;
                            }
                        }
                    }
                }
            }
        }
        return new Pair<Integer, Station>(earliestTime, earliestStation);
    }
    
    /**
     * From a list of stations, returns a list of strings that represent the station names
     * @param stations
     * @return
     * @author jeffreyguion
     */
    private static List<String> getStationNames(List<Station> stations){
        List<String> names = new ArrayList();
        for(int i=0; i< stations.size(); i++){
            names.add(stations.get(i).getName());
        }
        return names;
    }
   
   
    /**
     * Given a station and a route, finds the closest station on that route and returns 
     * a pair of the closest station and the destination of the train from the start station
     * 
     * @param route
     * @param station
     * @return
     * @author jeffreyguion
     */
    private static Pair<Station,String> getClosestStation(ArrayList<Station> route, Station station){
        ArrayList<String> shortestPath = new ArrayList<String>();
        Station closestStation = null;
        for(int j = 0; j < route.size(); j++){
            Station destinationStation = route.get(j);
            if(station.getName().equals(destinationStation.getName())){
                continue;
            }
            Route r = new Route();
            ArrayList<String> currentPath = getAtoBList(station, destinationStation, r);
            if(shortestPath.size() == 0 || shortestPath.size() > currentPath.size()){
                shortestPath = currentPath;
                closestStation = destinationStation;
            }
        }
        String lineDestination = Route.getTrainDestination(station.getName(), shortestPath.get(1), tMap.getLine(station.getLine()));
        return new Pair<Station,String>(closestStation, lineDestination);
    }

    /**
     * Creates a route object through the stops in the order selected by the user
     * 
     * @param trip - list of stops selected by the user
     * @return Route object with list of stops passed through in order selected by the user and amount of transfers
     * 
     * @author leighannastolfi
     */
    public static Route getOrderedRoute(ArrayList<Station> trip, String location, int timeOfTrip){
        int numberOfStops = trip.size();
        Route route = new Route();
        ArrayList<String> stopsPassed = new ArrayList<String>();
        for(int i = 0; i < numberOfStops - 1; i++){
            getAtoB(trip.get(i), trip.get(i+1), route, stopsPassed);
            if(i < numberOfStops - 2){
                route.addTransfer();
            }
        }
        route.setStops(stopsPassed);
        route.applyJsonToOrderedRoute(tMap, location, timeOfTrip);
        return route;
    }

    /**
     * Creates the list of stops that are passed through getting from one stop to another
     * 
     * @param start - beginning stop
     * @param next - next stop to get to
     * @param route - route being created
     * @param stopsPassed - stops that have been passed on the route
     * 
     * @author leighannastolfi
     */
    public static void getAtoB(Station start, Station next, Route route, ArrayList<String> stopsPassed){
        Line currentLine = tMap.getLine(start.getLine());
        Line endLine = tMap.getLine(next.getLine());
        List<String> stops = currentLine.getStops();
        if(currentLine.equals(endLine)){
            int iStart = stops.indexOf(start.getName());
            int iNext = stops.indexOf(next.getName());
            if(currentLine.getName().equalsIgnoreCase("Red")){
                redLineBranch(stops, start, next, route, stopsPassed);
            }
            else if(iStart < iNext){
                for(int index = iStart; index <= iNext; index++){
                    stopsPassed.add(stops.get(index));
                }
            }
            else{
                for(int index = iStart; index >= iNext; index--){
                    stopsPassed.add(stops.get(index));
                }
            }
        }
        else {
            getRouteBetweenLines(start, currentLine, next, endLine, route, stopsPassed);
        }
    }
    
    /**
     * Same as getAtoB method but returns the list of stops passed
     * 
     * @param start
     * @param next
     * @param route
     * @return
     * 
     * @author jeffreyguion
     */
    public static ArrayList<String> getAtoBList(Station start, Station next, Route route){
        ArrayList<String> stopsPassed = new ArrayList<String>();
        getAtoB(start, next, route, stopsPassed);
        return stopsPassed;
    }

    /**
     * Handles transfers between lines
     * 
     * @param start - beginning stop
     * @param current - line containing the beginning stop
     * @param next - next stop to get to
     * @param end - line containing the next stop
     * @param route - route being created
     * @param stopsPassed - stops that have been passed on the route
     * 
     * @author leighannastolfi
     */
    public static void getRouteBetweenLines(Station start, Line current, Station next, Line end, Route route, ArrayList<String> stopsPassed){
        Station orangeDtnCrossing = new Station("Downtown Crossing", "Orange");
        Station reddtnCrossing = new Station("Downtown Crossing", "Red");
        Station blueStateSt = new Station("State Street", "Blue");
        Station orangeStateSt = new Station("State Street", "Orange");
        
        if(current.getName().equalsIgnoreCase("Red") && end.getName().equalsIgnoreCase("Blue")){
            getAtoB(start, reddtnCrossing, route, stopsPassed);
            getAtoB(orangeDtnCrossing, next, route, stopsPassed);
            route.addTransfer();
        }
        if(current.getName().equalsIgnoreCase("Blue") && end.getName().equalsIgnoreCase("Red")){
            getAtoB(start, orangeDtnCrossing, route, stopsPassed);
            getAtoB(reddtnCrossing, next, route, stopsPassed);
            route.addTransfer();
        }
        else if(current.getName().equalsIgnoreCase("Orange") && end.getName().equalsIgnoreCase("Blue")){
            getAtoB(start, orangeStateSt, route, stopsPassed);
            getAtoB(blueStateSt, next, route, stopsPassed);
            route.addTransfer();
        }
        else if(current.getName().equalsIgnoreCase("Blue") && end.getName().equalsIgnoreCase("Orange")){
            getAtoB(start, blueStateSt, route, stopsPassed);
            getAtoB(orangeStateSt, next, route, stopsPassed);
            route.addTransfer();
        }
        else if(current.getName().equalsIgnoreCase("Orange") && end.getName().equalsIgnoreCase("Red")){
            getAtoB(start, orangeDtnCrossing, route, stopsPassed);
            getAtoB(reddtnCrossing, next, route, stopsPassed);
            route.addTransfer();
        }
        else if(current.getName().equalsIgnoreCase("Red") && end.getName().equalsIgnoreCase("Orange")){
            getAtoB(start, reddtnCrossing, route, stopsPassed);
            getAtoB(orangeDtnCrossing, next, route, stopsPassed);
            route.addTransfer();
        }
    }

    /**
     * 
     * @param stops - stops on the Red line
     * @param start - beginning stop
     * @param next - next stop to get to
     * @param route - route being created
     * @param stopsPassed - stops that have been passed on the route
     * 
     * @author leighannastolfi
     */
    public static void redLineBranch(List<String> stops, Station start, Station next, Route route, ArrayList<String> stopsPassed){
        Station jfk = new Station("JFK/UMass", "Red");
        int jfkIndex = 12;
        int iStart = stops.indexOf(start.getName());
        int iNext = stops.indexOf(next.getName());
        int startDiff = iStart - jfkIndex;
        int nextDiff = iNext - jfkIndex;
        Station branch2 = new Station("North Quincy", "Red");
        //for when both start and end stops are on the same branch or original branch
        if((startDiff > 4 && nextDiff > 4) || (startDiff <= 4 && nextDiff <= 4)){
            if(iStart < iNext){  
                for(int index = iStart; index <= iNext; index++){
                    stopsPassed.add(stops.get(index));
                }
            }
            else{
                for(int index = iStart; index >= iNext; index--){
                    stopsPassed.add(stops.get(index));
                }
            }
        }
        //for when start and end stops are on different branches(original, branch 1, branch 2)
        else {
            if(startDiff > 4){
                redLineBranch(stops, start, branch2, route, stopsPassed);
                if(nextDiff > 0){
                    stopsPassed.add(stops.get(jfkIndex));
                    //transfer to train going other direction
                    route.addTransfer();
                }
                redLineBranch(stops, jfk, next, route, stopsPassed);
            }
            else if(nextDiff > 4){
                redLineBranch(stops, start, jfk, route, stopsPassed);
                if(startDiff > 0){
                    stopsPassed.add(stops.get(jfkIndex));
                    //transfer to train going other direction
                    route.addTransfer();
                }
                redLineBranch(stops, branch2, next, route, stopsPassed);
            }
        }

    }
    
    /**
     * Creates an unordered route object through the stops selected by the user
     * It does this by finding the shortest distance between each stop. 
     * Generates the entire route before applying the JSON data.
     * 
     * @param trip - list of stops selected by the user
     * @return Route object with list of stops passed through and amount of transfers
     * 
     * @author jeffreyguion
     */
    public static Route getUnorderedRouteV1(ArrayList<Station> route, String location, Station startStation, Station endStation, int timeOfTrip, boolean isFewestTransfers){
        HashMap<String,Pair<ArrayList<String>,Integer>> stationToShortestPath = new HashMap<String,Pair<ArrayList<String>,Integer>>();
        Station currentStation = null;
        if(startStation != null){
            currentStation = startStation;
        }else{
            //TODO Something better
            currentStation = route.get(0);
        }
         
        if(endStation != null){
            route.remove(endStation);
        }
        
        //This function can be replaced depending on the goal of the unordered route
        Pair<Station, Integer> pathInformation = buildShortestPathMap(route, startStation, stationToShortestPath, isFewestTransfers);
        currentStation = pathInformation.a;
        int totalTransfers = pathInformation.b;
        
        //Find route to end Station
        if(endStation != null){
            Route r = new Route();
            ArrayList<String> currentPath = getAtoBList(currentStation, endStation, r);
            stationToShortestPath.put(currentStation.getName(), new Pair<ArrayList<String>, Integer>(currentPath, r.getTransfers()));
            totalTransfers += r.getTransfers() + 1;
        }

        ArrayList<String> stopsPassed = generatePath(startStation, stationToShortestPath);

        //Create the route
        Route finalRoute = new Route();
        finalRoute.setStops(stopsPassed);
        finalRoute.setTransfers(totalTransfers - 1);
        finalRoute.applyJsonToOrderedRoute(tMap, location, timeOfTrip);
        return finalRoute;
    }
    
    private Station findReasonableStartStation(ArrayList<Station> route){
        Line blueLine = tMap.getLine("Blue");
        for(int i = 0; i < blueLine.getStops().size(); i++){
            
        }
        return null;
    }
    
    /**
     * Builds the Path Map by building a minimum spanning tree across the stations in the route starting at
     * a given start station.
     * 
     * @param route
     * @param startStation
     * @param stationToShortestPath
     * @return
     * @author jeffreyguion
     */
    private static Pair<Station, Integer> buildShortestPathMap(ArrayList<Station> route, Station startStation, HashMap<String,Pair<ArrayList<String>, Integer>> stationToShortestPath, boolean isFewestTransfers){
        int numStops = route.size();
        Station currentStation = startStation;
        Set<String> visitedStations = new HashSet<String>();
        int totalTransfers = 0;
        //for each stop in the input list mark it as visited
        for(int i = 0; i < numStops - 1; i++){
            int transfers = 0;
            visitedStations.add(currentStation.getName());
            //for each stop, if it has not already been visited, find the closest stop to it
            for(int j = 0; j < numStops; j++){
                Station destinationStation = route.get(j);
                if(visitedStations.contains(destinationStation.getName())){
                    continue;
                }
                Route r = new Route();
                ArrayList<String> currentPath = getAtoBList(currentStation, destinationStation, r);
                Pair<ArrayList<String>,Integer> shortestPath = stationToShortestPath.get(currentStation.getName());
                boolean bestPath = false;
                if(shortestPath == null){
                    bestPath = true;
                }else if(isFewestTransfers){
                    if(r.getTransfers() < shortestPath.b){
                        bestPath = true;
                    }else if(r.getTransfers() == shortestPath.b && currentPath.size() < shortestPath.a.size()){
                        bestPath = true;
                    }
                }else if(currentPath.size() < shortestPath.a.size()){
                    bestPath = true;
                }
                if(bestPath){
                    transfers = r.getTransfers();
                    stationToShortestPath.put(currentStation.getName(), new Pair(currentPath, transfers));
                }
            }
            //Get the last station in a path between stations and mark that as the new current station
            Pair<ArrayList<String>,Integer> shortestPath = stationToShortestPath.get(currentStation.getName());
            String lastStation = shortestPath.a.get(shortestPath.a.size() - 1);
            currentStation = findStationWithName(lastStation, route);
            totalTransfers += shortestPath.b + 1;
        }
        
        return new Pair(currentStation, totalTransfers);
    }
    
    /**
     * Returns the station object that matches the input name
     * 
     * @param name - name of stations
     * @param stations - list of all station objects
     * @return
     * 
     * @author jeffreyguion
     */
    public static Station findStationWithName(String name, ArrayList<Station> stations){
        for(int i = 0; i < stations.size(); i++){
            Station station = stations.get(i);
            if(station.getName().equals(name)){
                return station;
            }
        }
        return null;
    }
    
    /**
     * Generates a list of stops passed from a HashMap of stations to paths given a start station
     * 
     * @param startStation - the station to begin at
     * @param stationToShortestPath - map of stations to path to next station
     * @return
     * 
     * @author jeffreyguion
     */
    public static ArrayList<String> generatePath(Station startStation, HashMap<String,Pair<ArrayList<String>, Integer>> stationToShortestPath){
        ArrayList<String> stops = new ArrayList<String>();
        ArrayList<String> path = stationToShortestPath.get(startStation.getName()).a;
        String lastStop = "";
        while(path != null){
            stops.addAll(path);
            lastStop = path.get(path.size() - 1);
            if(stationToShortestPath.containsKey(lastStop)){
                path = stationToShortestPath.get(lastStop).a;
            }else{
                path = null;
            }
        }
        return stops;
    }

    /**
     * Logs the exception's message and stack trace to STDOUT. Pull into a static utility class if needed
     * elsewhere.
     * @param e  the exception to log
     * @author   labichn
     */
    public static void log(Exception e) {
        System.out.println(e.getClass().getName() + " " + e.getMessage() + "\n" + getStackTrace(e));
    }

    /**
     * Returns a string representation of the stack trace of the given exception.
     * Each element of the stack trace will be printed as follows:
     * SomeClass.someMethod(...) in SomeClass.java: 42             // where 42 is the line number
     * @param e  the exception to get the stack trace from
     * @return   a string representation of the stack trace of the given exception.
     * @author   labichn
     */
    private static String getStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stes = e.getStackTrace();
        StackTraceElement tmp = null;
        if (stes != null) {
            for (int i=0; i<stes.length; i++) {
                tmp = stes[i];
                sb.append(tmp.getClassName() + "." + tmp.getMethodName() + "(...) in " +
                          tmp.getFileName() + ": " + String.valueOf(tmp.getLineNumber()));
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    /**
     * Finds the earliest time in seconds the route can begin
     * 
     * @param time 
     * @return time in seconds
     * @author leighannastolfi
     */
    private static int getTimeOfTrip(int time){
        int travelTime = 0;
        if(tMap.getTimeOfTripIndex().equals(TimeOfTrip.DEPART_AT))
            travelTime = (int) ((tMap.getTimeOfTrip() - time)/1000);
        else if(tMap.getTimeOfTripIndex().equals(TimeOfTrip.ARRIVE_BY)){
            //find the number of seconds from now to when the user wants to leave
            int timeFromNow = (int) ((tMap.getTimeOfTrip() - getCurrentTime())/1000);
            //based off previously found route time, determine when we want to leave
            travelTime = (int) (timeFromNow - time);
        }
        if(travelTime < 0)
            travelTime = 0;

        return travelTime;
    }
    
    /**
     * Finds the current time milliseconds instead of epoch time
     * 
     * @return current time in milliseconds of today only
     * @author leighannastolfi
     */

    public static int getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        return (calendar.get(Calendar.HOUR_OF_DAY)*3600000) + (calendar.get(Calendar.MINUTE) * 60000) 
                + (calendar.get(Calendar.SECOND)*1000) + calendar.get(Calendar.MILLISECOND);
    }
    
    /**
     * Pretty-print seconds to seconds and minutes.
     * @param seconds  the number of seconds
     * @return         A string representation with minutes and seconds
     * @author         labichn
     */
    public static String getMinutesAndSeconds(int seconds) {
        String re = "";
        int rem = seconds%60;
        if (seconds < 60) {
            re = String.valueOf(seconds) + " second" + (rem==1?"":"s");
        } else {
            int min = seconds/60;
            re = String.valueOf(min) + " minute" + (min==1?"":"s") + " and " +
                    String.valueOf(rem) + " second" + (rem==1?"":"s");
        }
        return re;
    }
    
    /**
     * Pretty-print seconds to minutes.
     * @param seconds  the number of seconds
     * @return         A string representation with minutes
     * @author         labichn
     */
    public static String getMinutes(int seconds) {
        return String.valueOf(seconds/60) + " minutes";
    }

}
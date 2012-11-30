package softwaredev.purpleparrots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import softwaredev.purpleparrots.gui.MbtaMap;
import softwaredev.purpleparrots.gui.Mode;
import softwaredev.purpleparrots.gui.RouteType;
import softwaredev.purpleparrots.gui.Station;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class MyMbta {

    public static String http = "http://developer.mbta.com/lib/rthr/";
    public static String test = "src/test/resources/mbta-test-data/2012_10_19/";
    private static String def = test;
    public static Map tMap = new Map();

    public static void getCurrentLocationOfAllTrains() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<String> stations = new ArrayList<String>();
        Line orange = new Line("Orange", stations);
        Line red = new Line("Red", stations);
        Line blue = new Line("Blue", stations);
        List<Train> orange_trains = JsonData.getTrains(orange, test);
        List<Train> red_trains = JsonData.getTrains(red, test);
        List<Train> blue_trains = JsonData.getTrains(blue, test);
        System.out.println(orange_trains);
        System.out.println(red_trains);
        System.out.println(blue_trains);
    }

    /**
     * Gets the trains for this line from the default location.
     * @param line  the line to get train information for
     * @return      the list of current trains on the given line
     * @author      labichn
     */
    private static List<Train> getTrains(Line line) {
        return JsonData.getTrains(line, def);
    }

    /**
     * Gets incoming trains for a line
     * 
     * @param color
     * @return
     * 
     * @author jeffreyguion
     */
    public static String getCurrentLocationOfTrains(String color) {
        ArrayList<String> stations = new ArrayList<String>();
        List<Train> trains = getTrains(new Line(color, stations));
        return trains.toString();
    }

    /**
     * Gets a filtered list of trains--those which currently have predictions for the given stop ID.
     * @param trains  the list of trains to filter
     * @param stopId  the stop ID which the trains must have in their predictions list
     * @return        the list of trains which include predictions toward the given stop ID
     * @author        labichn
     */
    public static List<Train> getTrainsForStop(List<Train> trains, String stopId) {
        List<Train> re = new ArrayList<Train>();
        if (stopId != null && trains != null) {
            Train tmp = null;
            for (int i=0; i<trains.size(); i++) {
                tmp = trains.get(i);
                if (tmp.hasPredFor(stopId)) re.add(tmp);
            }
            re = sort(re, stopId);
        }
        return re;
    }

    /**
     * Slowly sorts the given list of trains. Calls to getPredFor are safe because
     * we've already filtered on the existance of a prediction with the given stop ID.
     * @param trains  the trains to sort
     * @return        the trains, sorted from earliest arrival to latest to the stop
     *                denoted by the given stop ID.
     * @author        labichn
     */
    private static List<Train> sort(List<Train> trains, String stopId) {
        List<Train> re = new ArrayList<Train>();
        Train lowest, test;
        while (re.size()<trains.size()) {
            lowest = null;
            test = null;
            for (int i=0; i<trains.size(); i++) {
                test = trains.get(i);
                if (!re.contains(test)) {
                    if (lowest == null
                     || test.getPredFor(stopId).seconds < lowest.getPredFor(stopId).seconds) {
                        lowest = test;
                    }
                }
            }
            re.add(lowest);
        }
        return re;
    }

    /**
     * Gets the current location of all trains so they can be mapped to a station and direction
     * 
     * @param color
     * @param location
     * @return
     * 
     * @author jeffreyguion
     */
    public static HashMap<String, Train> getCurrentLocationHash(String color, String location) {
        ArrayList<String> stations = new ArrayList<String>();
        Line line = new Line(color, stations);
        ArrayList<Train> trains = JsonData.getTrains(line, location);
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
        if(map.getMode().equals(Mode.ORDERED_ROUTE)){
            return getOrderedRoute(map.getRoute(), location);
        }
        else if (map.getMode().equals(Mode.UNORDERED_ROUTE)){
            Station startStation = map.getStartStation();
            Station endStation = map.getEndStation();
            int startTime = 0;
            if(map.getRouteType().equals(RouteType.EARLIEST_DEPARTURE)){
                startStation = getEarliestDepartingTrain(map.getRoute(), startTime, location);
            }else{
                startStation = map.getRoute().get(0);
            }
            return getUnorderedRouteV1(map.getRoute(), location, startStation, endStation);
        }
        else return new Route();//throw new Exception("Route mode not selected");
    }
    
    private static Station getEarliestDepartingTrain(ArrayList<Station> route, int startTime, String location){
        ArrayList<Train> orangeTrains = JsonData.getTrains(tMap.orangeLine, location);
        ArrayList<Train> redTrains = JsonData.getTrains(tMap.redLine, location);
        ArrayList<Train> blueTrains = JsonData.getTrains(tMap.blueLine, location);
        
        Pair<Integer, Station> orange = getEarliestDepartingTrainForLine(route, orangeTrains, startTime);
        Pair<Integer, Station> red = getEarliestDepartingTrainForLine(route, redTrains, startTime);
        Pair<Integer, Station> blue = getEarliestDepartingTrainForLine(route, blueTrains, startTime);
        
        if(orange.a <= red.a && orange.a <= blue.a){
            return orange.b;
        }else if(red.a <= orange.a && red.a <= blue.a){
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
        ArrayList<String> stationNames = getStationNames(route);
        for(int i=0; i< trains.size(); i++){
            Train train = trains.get(i);
            ArrayList<Prediction> predictions = train.getPredictions();
            for(int j=0; j < predictions.size(); j++){
                Prediction p = predictions.get(j);
                if(stationNames.contains(p.stop)){
                    if(earliestTime == -1 || p.seconds < earliestTime){
                        if(p.seconds > startTime){
                            Station startStop = findStationWithName(p.stop, route);
                            Station nextStop = getClosestStation(route, startStop);
                            String destinationStop = getDirectionToCurrentStation(startStop, nextStop);
                            if(destinationStop.equals(train.getDestination())){
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
    
    private static ArrayList<String> getStationNames(ArrayList<Station> stations){
        ArrayList<String> names = new ArrayList();
        for(int i=0; i< stations.size(); i++){
            names.add(stations.get(i).getName());
        }
        return names;
    }
    
    private static String getDirectionToCurrentStation(Station startStation, Station endStation){
        return Route.getTrainDestination(startStation.getName(), endStation.getName(), tMap.getLine(startStation.getLine()));
    }
    
    private static Station getClosestStation(ArrayList<Station> route, Station station){
        ArrayList<String> shortestPath = new ArrayList<String>();
        Station closestStation = null;
        for(int j = 0; j < route.size(); j++){
            Station destinationStation = route.get(j);
            if(station.getName().equals(destinationStation.getName())){
                continue;
            }
            Route r = new Route();
            ArrayList<String> currentPath = getAtoBList(station, destinationStation, r);
            if(shortestPath == null || shortestPath.size() > currentPath.size()){
                shortestPath = currentPath;
                closestStation = destinationStation;
            }
        }
        return closestStation;
    }

    /**
     * Creates a route object through the stops in the order selected by the user
     * 
     * @param trip - list of stops selected by the user
     * @return Route object with list of stops passed through in order selected by the user and amount of transfers
     * 
     * @author leighannastolfi
     */
    public static Route getOrderedRoute(ArrayList<Station> trip, String location){
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
        route.applyJsonToOrderedRoute(tMap, location);
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
    public static Route getUnorderedRouteV1(ArrayList<Station> route, String location, Station startStation, Station endStation){
        int numStops = route.size();
        HashMap<String,ArrayList<String>> stationToShortestPath = new HashMap<String,ArrayList<String>>();
        Set<String> visitedStations = new HashSet<String>();
        int totalTransfers = 0;
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
                ArrayList<String> shortestPath = stationToShortestPath.get(currentStation.getName());
                if(shortestPath == null || shortestPath.size() > currentPath.size()){
                    stationToShortestPath.put(currentStation.getName(), currentPath);
                    transfers = r.getTransfers();
                }
            }
            //Get the last station in a path between stations and mark that as the new current station
            ArrayList<String> shortestPath = stationToShortestPath.get(currentStation.getName());
            String lastStation = shortestPath.get(shortestPath.size() - 1);
            currentStation = findStationWithName(lastStation, route);
            totalTransfers += transfers + 1;
        }
        
        //Find route to end Station
        if(endStation != null){
            Route r = new Route();
            ArrayList<String> currentPath = getAtoBList(currentStation, endStation, r);
            stationToShortestPath.put(currentStation.getName(), currentPath);
            totalTransfers += r.getTransfers() + 1;
        }

        ArrayList<String> stopsPassed = generatePath(startStation, stationToShortestPath);

        //Create the route
        Route finalRoute = new Route();
        finalRoute.setStops(stopsPassed);
        finalRoute.setTransfers(totalTransfers - 1);
        finalRoute.applyJsonToOrderedRoute(tMap, location);
        return finalRoute;
    }
    
    private Station findReasonableStartStation(ArrayList<Station> route){
        Line blueLine = tMap.getLine("Blue");
        for(int i = 0; i < blueLine.getStops().size(); i++){
            
        }
        return null;
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
            if(station.getName() == name){
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
    public static ArrayList<String> generatePath(Station startStation, HashMap<String,ArrayList<String>> stationToShortestPath){
        ArrayList<String> stops = new ArrayList<String>();
        ArrayList<String> path = stationToShortestPath.get(startStation.getName());
        String lastStop = "";
        while(path != null){
            stops.addAll(path);
            lastStop = path.get(path.size() - 1);
            path = stationToShortestPath.get(lastStop);
        }
        return stops;
    }

}

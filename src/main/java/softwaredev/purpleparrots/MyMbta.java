package softwaredev.purpleparrots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import softwaredev.purpleparrots.gui.MbtaMap;
import softwaredev.purpleparrots.gui.Mode;
import softwaredev.purpleparrots.gui.Station;

public class MyMbta {

    public static String http = "http://developer.mbta.com/lib/rthr/";
    public static String test = "src/test/resources/mbta-test-data/2012_10_19/";
    public static String def = test;
    public static softwaredev.purpleparrots.TMap tMap = new softwaredev.purpleparrots.TMap();

    private static java.util.Map<Line, TrainCache> lines;
    
    public static List<Train> getTrains(Line line) {
    	return lines.get(line).getTrains();
    }
    
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
     * we've already filtered on the existence of a prediction with the given stop ID.
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
     * @author jeffreyguion, labichn
     */
    public static HashMap<String, Train> getCurrentLocationMap(String color, String location) {
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
        if(map.getMode().equals(Mode.ORDERED_ROUTE)){
            return getOrderedRoute(map.getRoute(), location);
        }
        else if (map.getMode().equals(Mode.UNORDERED_ROUTE)){
            return getUnorderedRouteV1(map.getRoute(), location);
        }
        else return new Route();//throw new Exception("Route mode not selected");
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
     * 
     * @param trip - list of stops selected by the user
     * @return Route object with list of stops passed through and amount of transfers
     * 
     * @author jeffreyguion
     */
    public static Route getUnorderedRouteV1(ArrayList<Station> route, String location){
        int numStops = route.size();
        HashMap<String,ArrayList<String>> stationToShortestPath = new HashMap<String,ArrayList<String>>();
        Set<String> visitedStations = new HashSet<String>();
        Station currentStation = route.get(0);
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
        
        //Generate the actual list of stops passed from a HashMap
        Station firstStop = route.get(0);
        ArrayList<String> stopsPassed = generatePath(firstStop, stationToShortestPath);

        //Create the route
        Route finalRoute = new Route();
        finalRoute.setStops(stopsPassed);
        finalRoute.setTransfers(totalTransfers - 1);
        finalRoute.applyJsonToOrderedRoute(tMap, location);
        return finalRoute;
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


    public static void main(String [] args) {
        //        getCurrentLocationOfAllTrains();
        System.out.println(getTrainsForStop(getTrains(new Line("Blue", new ArrayList<String>())), "70040").toString());
    }

}

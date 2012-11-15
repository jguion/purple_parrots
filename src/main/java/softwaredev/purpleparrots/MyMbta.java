package softwaredev.purpleparrots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import softwaredev.purpleparrots.gui.MbtaMap;
import softwaredev.purpleparrots.gui.Mode;
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
            if (t.predictions != null) {
                for (int j = 0; j < t.predictions.size(); j++) {
                    p = t.predictions.get(j);
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
            return getUnorderedRoute(map.getRoute(), location);
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
        else if(start.getName().equalsIgnoreCase("Red") && end.getName().equalsIgnoreCase("Orange")){
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
     */
    //TODO: not yet implemented
    public static Route getUnorderedRoute(List<Station> route, String location){
        return new Route();
    }


    public static void main(String [] args) {
        //        getCurrentLocationOfAllTrains();
        System.out.println(getTrainsForStop(getTrains(new Line("Blue", new ArrayList<String>())), "70040").toString());
    }

}

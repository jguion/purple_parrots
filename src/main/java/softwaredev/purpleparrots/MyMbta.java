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

    private static String http = "http://developer.mbta.com/lib/rthr/";
    private static String test = "src/test/resources/mbta-test-data/2012_10_19/";
    private static String def = test;
    public static Map tMap = new Map();

    public static void getCurrentLocationOfAllTrains() {
        ArrayList<Line> lines = new ArrayList<Line>();
        ArrayList<String> stations = new ArrayList<String>();
        Line orange = new Line("orange", stations);
        Line red = new Line("red", stations);
        Line blue = new Line("blue", stations);
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

    public static String getCurrentLocationOfTrains(String color) {
        ArrayList<String> stations = new ArrayList<String>();
        List<Train> trains = getTrains(new Line(color, stations));
        return trains.toString();
    }

    public static HashMap<String, Train> getCurrentLocationHash(String color) {
        ArrayList<String> stations = new ArrayList<String>();
        Line line = new Line(color, stations);
        ArrayList<Train> trains = JsonData.getTrains(line, test);
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
    
    public static Route getRoute(MbtaMap map){
        if(map.getMode().equals(Mode.ORDERED_ROUTE)){
            return getOrderedRoute(map.getRoute());
        }
        else if (map.getMode().equals(Mode.UNORDERED_ROUTE)){
            return getUnorderedRoute(map.getRoute());
        }
        else return new Route();
    }
    
    public static Route getOrderedRoute(ArrayList<Station> trip){
        int numberOfStops = trip.size();
        Route route = new Route();
        ArrayList<String> stopsPassed = new ArrayList<String>();
        for(int i = 0; i < numberOfStops - 1; i++){
            getAtoB(trip.get(i), trip.get(i+1), route, stopsPassed);
        }
        route.setStops(stopsPassed);
        return route;
    }
    
    public static void getAtoB(Station start, Station next, Route route, ArrayList<String> stopsPassed){
        Line currentLine = tMap.getLine(start.getLine());
        Line endLine = tMap.getLine(next.getLine());
        List<String> stops = currentLine.getStops();
        if(currentLine.equals(endLine)){
            int iStart = stops.indexOf(start.getName());
            int iNext = stops.indexOf(next.getName());
            if(iStart < iNext){
                for(int index = iStart; index <= iNext; index++){
                    stopsPassed.add(stops.get(index));
                }
            }
            else{
                for(int index = iNext; index >= iStart; index--){
                    stopsPassed.add(stops.get(index));
                }
            }
        }
        else {
            getRouteBetweenLines(start, currentLine, next, endLine, route, stopsPassed);
        }
    }
    
    public static void getRouteBetweenLines(Station start, Line current, Station next, Line end, Route route, ArrayList<String> stopsPassed){
        Station orangeDtnCrossing = new Station("Downtown Crossing", "orange");
        Station reddtnCrossing = new Station("Downtown Crossing", "red");
        Station blueStateSt = new Station("State St", "blue");
        Station orangeStateSt = new Station("State St", "orange");
        
        if(current.getName().equalsIgnoreCase("red") && end.getName().equalsIgnoreCase("blue")){
            getAtoB(start, orangeDtnCrossing, route, stopsPassed);
            getAtoB(reddtnCrossing, next, route, stopsPassed);
            route.addTransfers();
        }
        if(current.getName().equalsIgnoreCase("blue") && end.getName().equalsIgnoreCase("red")){
            getAtoB(start, reddtnCrossing, route, stopsPassed);
            getAtoB(orangeDtnCrossing, next, route, stopsPassed);
            route.addTransfers();
        }
        else if(current.getName().equalsIgnoreCase("orange") && end.getName().equalsIgnoreCase("blue")){
            getAtoB(start, orangeStateSt, route, stopsPassed);
            getAtoB(blueStateSt, next, route, stopsPassed);
            route.addTransfers();
        }
        else if(current.getName().equalsIgnoreCase("blue") && end.getName().equalsIgnoreCase("orange")){
            getAtoB(start, blueStateSt, route, stopsPassed);
            getAtoB(orangeStateSt, next, route, stopsPassed);
            route.addTransfers();
        }
        else if(current.getName().equalsIgnoreCase("orange") && end.getName().equalsIgnoreCase("red")){
            getAtoB(start, orangeDtnCrossing, route, stopsPassed);
            getAtoB(reddtnCrossing, next, route, stopsPassed);
            route.addTransfers();
        }
        else if(start.getName().equalsIgnoreCase("red") && end.getName().equalsIgnoreCase("orange")){
            getAtoB(start, reddtnCrossing, route, stopsPassed);
            getAtoB(orangeDtnCrossing, next, route, stopsPassed);
            route.addTransfers();
        }
    }
    
    public static Route getUnorderedRoute(List<Station> route){
        return new Route();
    }


    public static void main(String [] args) {
        getCurrentLocationOfAllTrains();
    }

}

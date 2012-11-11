package softwaredev.purpleparrots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class MyMbta {

    private static String http = "http://developer.mbta.com/lib/rthr/";
    private static String test = "src/test/resources/mbta-test-data/2012_10_19/";
    private static String def = test;

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


    public static void main(String [] args) {
        getCurrentLocationOfAllTrains();
    }

}

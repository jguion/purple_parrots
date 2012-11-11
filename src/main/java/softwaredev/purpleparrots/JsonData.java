package softwaredev.purpleparrots;

import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonData {

    /**
     * Walks the json node for the given line from the given root path, returning an array list
     * of trains. If there is an error it is caught, logged, and an empty list is returned.
     * @param line  the line to get the current trains for
     * @param root  the root of the path of the json strings to parse, expected to be
     *              a string beginning with "http:// or "file:// and ending with "/"
     * @return      the current trains from the path built with the given root and line
     * @author      guionj, modified by labichn
     */
    public static ArrayList<Train> getTrains(Line line, String root) {
        ArrayList<Train> trains = new ArrayList<Train>();
        try {
            JsonNode tripsList = getTData(line, root);
            Iterator<JsonNode> trips = tripsList.iterator();
            while (trips.hasNext()) {
                JsonNode trip = trips.next();
                String id = trip.path("TripID").asText();
                String destination = trip.path("Destination").asText();
                Iterator<JsonNode> predictions_iterator = trip.path("Predictions").iterator();
                List<Prediction> predictions = new ArrayList<Prediction>();
                while(predictions_iterator.hasNext()){
                    JsonNode prediction = predictions_iterator.next();
                    String stopId = prediction.path("StopID").asText();
                    String stop = prediction.path("Stop").asText();
                    int seconds = prediction.path("Seconds").asInt();
                    Prediction p = new Prediction(stopId, stop, seconds);
                    predictions.add(p);
                }
                Train train = new Train(id, line.getName(), destination, predictions);
                trains.add(train);
            }
        } catch (Exception e) {
            log(e);
        }
        return trains;
    }

    /**
     * Returns a json node representation of the given line with the given path root.
     * @param line  the line, used to build the full path of the json string
     * @param root  the root of the path of the json strings to parse, expected to be
     *              a string beginning with "http:// or "file:// and ending with "/"
     * @return      a json node representation of the string at the path built with
     *              the line and the root
     * @author      guionj, modified by labichn
     */
    private static JsonNode getTData(Line line, String root) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String path = getPath(line, root);
        JsonNode rootNode = null;
        if (path.startsWith("http://")) {
            rootNode = mapper.readValue(new URL(path), JsonNode.class);
        } else {
            rootNode = mapper.readValue(readString(new File(path)), JsonNode.class);
        }
        JsonNode trips = null;
        if (rootNode != null) {
            trips = rootNode.path("TripList").path("Trips");
        }
        return trips;
    }

    /**
     * Reads the string content of the given file. May belong in static util class if
     * used elsewhere.
     * @param file  the file to read
     * @return      the string in the file, if any
     * @author      labichn
     */
    private static String readString(File file) {
        String re = "";
        try {
            if (file != null && file.isFile()) {
                re = new String(Files.readAllBytes(Paths.get(file.toURI())));
                System.out.println(re);
            }
        } catch (Exception e) {
            log(e);
        }
        return re;
    }

    /**
     * Gets the string path to get JSON data from based on the given line and
     * root string.
     * @param line  the line to get JSON data for
     * @param root  the root of the path to get JSON data for, either a http
     *              directory or local directory
     * @return      the string path based on the given line and root
     * @author      labichn
     */
    private static String getPath(Line line, String root) {
        if (root != null && root.startsWith("http://")) {
            return root+line.getName()+".json";
        } else {
            return getPath(line, new File(root));
        }
    }

    /**
     * Gets the path of the file located inside the given directory that ends
     * with ".json" and contains the name of the line, without case sensitivity.
     * @param line  the line of the file to get the path for
     * @param dir   the directory to search for json files
     * @return      the path of the file, if found, the empty string otherwise
     * @author      labichn
     */
    private static String getPath(Line line, File dir) {
        String re = "";
        if (line != null && dir != null && dir.isDirectory()) {
            String[] files = dir.list();
            if (files != null) {
                String tmp = "";
                for (int i=0; i< files.length; i++) {
                    tmp = files[i];
                    if (tmp.toLowerCase().contains(line.getName()) && tmp.endsWith(".json")) {
                        re = ""+dir.getPath()+"/"+tmp;
                    }
                }
            }
        }
        System.out.println(re);
        return re;
    }

    /**
     * Logs the exception's message and stack trace to STDOUT. Pull into a static utility class if needed
     * elsewhere.
     * @param e  the exception to log
     * @author   labichn
     */
    private static void log(Exception e) {
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

}

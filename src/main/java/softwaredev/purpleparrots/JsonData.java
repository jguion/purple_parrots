package softwaredev.purpleparrots;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonData {
	
	public static JsonNode getTData(Line line) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		String color = line.getName();
		URL url = new URL("http://developer.mbta.com/lib/rthr/"+color+".json");
				
		JsonNode rootNode = mapper.readValue(url, JsonNode.class);
		
		JsonNode tripList = rootNode.path("TripList");
		JsonNode trips = tripList.path("Trips");
		return trips;
	}
	
	public static ArrayList<Train> getTrains(Line line) throws JsonParseException, JsonMappingException, IOException{
		ArrayList<Train> trains = new ArrayList();
		JsonNode trips_list = getTData(line);
		Iterator<JsonNode> trips = trips_list.iterator();
		while(trips.hasNext()){
			JsonNode trip = trips.next();
			String id = trip.path("TripID").asText();
			String destination = trip.path("Destination").asText();
			Iterator<JsonNode> predictions_iterator = trip.path("Predictions").iterator();
			List<Prediction> predictions = new ArrayList();
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
		return trains;
	}

}

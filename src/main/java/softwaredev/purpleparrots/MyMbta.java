package main.java.softwaredev.purpleparrots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class MyMbta {
	
	public static List<Train> getTrains() throws JsonParseException, JsonMappingException, IOException{
		List<Train> trains = new ArrayList();
		String[] lines = {"orange", "red", "blue"};
		for(int i=0; i<3; i++){
			
			JsonNode trips_list = JsonData.getTData(lines[i]);
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
			
				Train train = new Train(id, lines[i], destination, predictions);
				trains.add(train);
			}
		}
		return trains;
	}
	
	public static void getCurrentLocationOfAllTrains() throws JsonParseException, JsonMappingException, IOException{
		List<Train> trains = getTrains();
		System.out.println(trains);
	}
	
	
	
	public static void main(String [] args) throws JsonParseException, JsonMappingException, IOException {
		getCurrentLocationOfAllTrains();
		
		
	}

}

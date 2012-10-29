package softwaredev.purpleparrots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class MyMbta {
	
	
	public static void getCurrentLocationOfAllTrains() throws JsonParseException, JsonMappingException, IOException{
		ArrayList<Line> lines = new ArrayList();
		ArrayList<String> stations = new ArrayList();
		Line orange = new Line("orange", stations);
		Line red = new Line("red", stations);
		Line blue = new Line("blue", stations);
		List<Train> orange_trains = JsonData.getTrains(orange);
		List<Train> red_trains = JsonData.getTrains(red);
		List<Train> blue_trains = JsonData.getTrains(blue);
		System.out.println(orange_trains);
		System.out.println(red_trains);
		System.out.println(blue_trains);
	}
	
	public static String getCurrentLocationOfTrains(String color) throws JsonParseException, JsonMappingException, IOException{
		ArrayList<String> stations = new ArrayList();
		Line line = new Line(color, stations);
		List<Train> trains = JsonData.getTrains(line);
		return trains.toString();
	}
	
	
	public static void main(String [] args) throws JsonParseException, JsonMappingException, IOException {
		getCurrentLocationOfAllTrains();
	}

}

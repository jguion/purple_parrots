package softwaredev.purpleparrots;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonData {
	
	public static JsonNode getTData(String line) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		URL orangeLineUrl = new URL("http://developer.mbta.com/lib/rthr/"+line+".json");
		JsonNode rootNode = mapper.readValue(orangeLineUrl, JsonNode.class);
		
		JsonNode tripList = rootNode.path("TripList");
		JsonNode trips = tripList.path("Trips");
		return trips;
	}

}

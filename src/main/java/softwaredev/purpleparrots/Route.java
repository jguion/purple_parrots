package softwaredev.purpleparrots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Route {

	private List<String> trainIds;
	private List<String> stops;
	private List<Integer> schedule;
	private Integer transfers;
	private Integer time;
	
	public Route(){
	    this.transfers = 0;
	};
	
	public Route(List<String> trainIds, List<String> stops, List<Integer> schedule, Integer transfers, Integer time) {
		this.trainIds = trainIds;
		this.stops = stops;
		this.schedule = schedule;
		this.transfers = transfers;
		this.time = time;
	}
	
	public void applyJson(Map tMap, String location){
	    List<Train> orange_trains = JsonData.getTrains(tMap.orangeLine, location);
        List<Train> red_trains = JsonData.getTrains(tMap.redLine, location);
        List<Train> blue_trains = JsonData.getTrains(tMap.blueLine, location);
        
        int travelTime = 0;
        int fastestTime;
        ArrayList<ArrayList<String>> possibleTrains = new ArrayList<ArrayList<String>>();
        possibleTrains.add(new ArrayList<String>());
        int numTransfers = 0;
        String lastDestination = "";
        for(int i = 0; i < this.stops.size() - 1; i++){
            HashMap<String, ArrayList<String>> stationToLine = tMap.getStationToLineMap();
            String currentStation = this.stops.get(i);
            String nextStation = this.stops.get(i+1);
            ArrayList<String> currentLines = stationToLine.get(currentStation);
            ArrayList<String> nextLines = stationToLine.get(nextStation);
            String line = "";
            if(currentStation.equals(nextStation)){
                numTransfers++;
                continue;
            }
            if(currentLines.size() == 1){
                line = currentLines.get(0);
            }else {
                line = nextLines.get(0);
            }

            String destination = this.getTrainDestination(currentStation, nextStation, tMap.getLine(line));
            //you have already created a list of possible trains and are headed in the same direction
            if(destination.equals(lastDestination)){
                continue;
            }
            List<Train> trains = getTrainsForColor(line, orange_trains, red_trains, blue_trains);
            for(int j = 0; j<trains.size(); j++){
                Train train = trains.get(j);
                if(train.destination.equals(destination)){
                    for(int k = 0; k < train.predictions.size(); k++){
                        Prediction prediction = train.predictions.get(k);
                        if(prediction.stop.equals(currentStation) && prediction.seconds >= travelTime){
                            possibleTrains.get(numTransfers).add(train.id);
                            break;
                        }
                    }
                }
            }
            lastDestination = destination;

        }
        
        System.out.println(possibleTrains);
        
        
	}
	
	private List<Train> getTrainsForColor(String color, List<Train> orange_trains, List<Train> red_trains, List<Train> blue_trains ){
	   if(color == "Orange"){
	       return orange_trains;
	   }else if(color == "Red"){
	       return red_trains;
	   }else{
	       return blue_trains;
	   }
	}
	
	private String getTrainDestination(String stopA, String stopB, Line line){
	    int numStops = line.getStops().size();
	    for(int i = 0; i<numStops - 1 ; i++){
	        String currentStop = line.getStops().get(i);
	        String nextStop = line.getStops().get(i+1);
	        
	        if(currentStop == stopA && nextStop == stopB){
	            return line.getStops().get(numStops - 1);
	        }else if (currentStop == stopB && nextStop == stopA){
	            return line.getStops().get(0);
	        }
	    }
	    return null;
	}

	public List<String> getTrainIds() {
		return trainIds;
	}

	public void setTrainIds(List<String> trainIds) {
		this.trainIds = trainIds;
	}

	public List<String> getStops() {
		return stops;
	}

	public void setStops(List<String> stops) {
		this.stops = stops;
	}

	public List<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Integer> schedule) {
		this.schedule = schedule;
	}

	public Integer getTransfers() {
		return transfers;
	}

	public void setTransfers(Integer transfers) {
		this.transfers = transfers;
	}
	
	public void addTransfers() {
		this.transfers++;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}

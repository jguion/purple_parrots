package softwaredev.purpleparrots;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Route {

	private ArrayList<String> trainIds;
	private ArrayList<String> stops;
	private ArrayList<Integer> schedule;
	private Integer transfers;
	private Integer time;
	private String errorText;
	private ArrayList<Leg> legs;
	
	public Route(){
	    this.errorText = "";
	    this.transfers = 0;
	    this.legs = new ArrayList<Leg>();
	};
	
	public Route(ArrayList<String> trainIds, ArrayList<String> stops, ArrayList<Integer> schedule, Integer transfers, Integer time) {
		this.trainIds = trainIds;
		this.stops = stops;
		this.schedule = schedule;
		this.transfers = transfers;
		this.time = time;
	}
	
	/**
	 * Prints out route information
	 * 
	 * @author jeffreyguion
	 */
	public String toString(){
	    String directions ="";
	    for(int i = 0; i < this.legs.size(); i++){
	        Leg l = this.legs.get(i);
	        directions += l.toString() +"\n";
	    }
	    return directions;
	}


	
	/**
	 * This method applies Json data to an ordered route to produce the best route
	 * 
	 * @param tMap - Map with helper functions
	 * @param location - location of the data (http or test)
	 * 
	 * @author jeffreyguion
	 */
	public void applyJsonToOrderedRoute(Map tMap, String location){
	    ArrayList<Train> orange_trains = JsonData.getTrains(tMap.orangeLine, location);
	    ArrayList<Train> red_trains = JsonData.getTrains(tMap.redLine, location);
	    ArrayList<Train> blue_trains = JsonData.getTrains(tMap.blueLine, location);
          
	    int travelTime = 0;
	    if(tMap.getTimeOfTripIndex() == 1){
	        Calendar calendar = Calendar.getInstance();
	        int currentTime = (calendar.get(Calendar.HOUR_OF_DAY)*3600000) + (calendar.get(Calendar.MINUTE) * 60000) 
	                + (calendar.get(Calendar.SECOND)*1000) + calendar.get(Calendar.MILLISECOND);

	        travelTime = (int) ((tMap.getTimeOfTrip() - currentTime)/1000);
	    }
	    
        ArrayList<ArrayList<Leg>> possibleTrains = new ArrayList<ArrayList<Leg>>();
        ArrayList<Leg> bestTrains = new ArrayList<Leg>();
        HashMap<String, ArrayList<String>> stationToLine = tMap.getStationToLineMap();
        ArrayList<String> stationsToTravel = this.stops;
        //For each transfer
        for(int i = 0; i <= this.transfers; i++){
            String currentStation = stationsToTravel.get(0);
            if(travelTime == -1){
                this.errorText = "No predicted trains arriving to "+currentStation;
            }
            //find the next transfer station which is either the last stop in a route or
            // the first stop to appear twice in the list of stops
            String transferStation = stationsToTravel.get(stationsToTravel.size() - 1);
            while(stationsToTravel.size() > 1){
                if(stationsToTravel.get(0).equals(stationsToTravel.get(1))){
                    stationsToTravel.remove(0);
                    transferStation = stationsToTravel.get(0);
                    break;
                }else{
                    stationsToTravel.remove(0);
                }
            }
            //find the current line by comparing the lines the current station to the lines that
            // the transfer station has. The line in common is the current line
            ArrayList<String> currentLines = stationToLine.get(currentStation);
            ArrayList<String> transferLines = stationToLine.get(transferStation);
            String line = "";
            for(int j = 0; j<currentLines.size(); j++){
                String startLine = currentLines.get(j);
                if(transferLines.contains(startLine)){
                    line = startLine;
                    break;
                }
            }
            //Get destination the train is heading
            String destination = this.getTrainDestination(currentStation, transferStation, tMap.getLine(line));
            
            //gets all trains for the current line
            ArrayList<Train> trains = getTrainsForColor(line, orange_trains, red_trains, blue_trains);
            
            //creates a list of all potential legs of a route
            ArrayList<Leg> possibleLegs = createLegs(currentStation, transferStation, destination, stationToLine,
                    line, trains, travelTime);
            
            possibleTrains.add(possibleLegs);
            
            //finds the best legs for an ordered route 
            Leg bestLeg;
            if(possibleLegs.isEmpty()){
                bestLeg = new Leg(currentStation, transferStation, line, destination);
            }else{
                bestLeg = findBestLeg(possibleLegs);
            }
            travelTime = bestLeg.endTime;
            bestTrains.add(bestLeg);
        }
        this.legs = bestTrains;
        
    }
	
	/**
	 * Finds the best leg of a list of legs by returning the quickest time to get from the start station
	 * to the end station
	 * 
	 * @param legs - a list of possible legs of trip
	 * @return the best leg
	 * @author jeffreyguion
	 */
	private Leg findBestLeg(ArrayList<Leg> legs){
	    Leg quickestLeg = new Leg();
	    for(int i = 0; i < legs.size(); i++){
	        Leg l = legs.get(i);
	        if(quickestLeg.endTime == -1 && quickestLeg.startTime == -1){
	            quickestLeg = l;
	        }else if(quickestLeg.endTime == -1 && (l.startTime != -1 && l.startTime < quickestLeg.startTime)){
	            quickestLeg = l;
	        }else if(quickestLeg.endTime != -1 && (l.endTime != -1 && l.endTime < quickestLeg.endTime)){
	            quickestLeg = l;
	        }
	    }
	    return quickestLeg;
	}
	
	/**
	 * Creates a list of all possible legs from the start station to the end station given the list of trains and the 
	 * start time of the leg
	 * 
	 * @param startStation - station to start from
	 * @param endStation - startion to go to
	 * @param destination - end stop of the train
	 * @param stationToLine - map from station to line
	 * @param line - current line
	 * @param trains - list of trains from json data
	 * @param startTime - earliest time a user can arrive at a train
	 * @return - list of all possible legs that can be taken
	 * @author jeffreyguion
	 */
	private ArrayList<Leg> createLegs(String startStation, String endStation, String destination, HashMap<String, ArrayList<String>> stationToLine,
	       String line, ArrayList<Train> trains, int startTime){
	    ArrayList<Leg> legs = new ArrayList<Leg>();

	    for(int i = 0; i < trains.size(); i++){
	        Train train = trains.get(i);
            if(train.getDestination().equals(destination)){
                Leg l = new Leg();
                l.setTrainId(train.getId());
                l.setStartStation(startStation);
                l.setEndStation(endStation);
                l.setLine(line);
                l.setLineDestination(destination);
                for(int k = 0; k < train.getPredictions().size(); k++){
                    Prediction prediction = train.getPredictions().get(k);
                    if(prediction.stop.equals(startStation) && prediction.seconds >= startTime){
                        l.setStartTime(prediction.seconds);
                    }else if(prediction.stop.equals(endStation) && prediction.seconds >= l.startTime){
                        l.setEndTime(prediction.seconds);
                    }
                }
                if(l.startTime > 0 && startTime != -1){
                    legs.add(l);
                }
                
            }
	    }
	    return legs;	    
	}

	/**
	 * returns the correct list of trains given a color line
	 * 
	 * @param color - the line of the trains wanted
	 * @param orange_trains
	 * @param red_trains
	 * @param blue_trains
	 * @return - a list of trains from json data
	 * @author jeffreyguion
	 */
	private ArrayList<Train> getTrainsForColor(String color, ArrayList<Train> orange_trains, ArrayList<Train> red_trains, ArrayList<Train> blue_trains ){
	   if(color == "Orange"){
	       return orange_trains;
	   }else if(color == "Red"){
	       return red_trains;
	   }else{
	       return blue_trains;
	   }
	}
	
	/**
	 * Returns the destination of a train on a line given a start and transfer station
	 * 
	 * @param startStation
	 * @param transferStation
	 * @param line
	 * @return
	 * @author jeffreyguion
	 */
	private String getTrainDestination(String startStation, String transferStation, Line line){
        int numStops = line.getStops().size();
        for(int i = 0; i<numStops - 1 ; i++){
            String currentStop = line.getStops().get(i);            
            if(currentStop == startStation){
                return line.getStops().get(numStops - 1);
            }else if (currentStop == transferStation){
                return line.getStops().get(0);
            }
        }
        return null;
    }
	
	/**
	 * Returns the destination of a train on a line given two consecutive stations
	 * 
	 * @param stopA
	 * @param stopB
	 * @param line
	 * @return
	 */
	private String getTrainDestinationFromConsecutiveStops(String stopA, String stopB, Line line){
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

	public ArrayList<String> getTrainIds() {
		return trainIds;
	}

	public void setTrainIds(ArrayList<String> trainIds) {
		this.trainIds = trainIds;
	}

	public ArrayList<String> getStops() {
		return stops;
	}

	public void setStops(ArrayList<String> stops) {
		this.stops = stops;
	}

	public List<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Integer> schedule) {
		this.schedule = schedule;
	}

	public Integer getTransfers() {
		return transfers;
	}

	public void setTransfers(Integer transfers) {
		this.transfers = transfers;
	}
	
	public void addTransfer() {
		this.transfers++;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}

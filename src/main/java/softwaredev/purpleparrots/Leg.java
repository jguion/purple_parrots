package softwaredev.purpleparrots;

/**
 * Leg of a route that goes from a start station to a transfer or end station.
 * It includes the line and direction it is heading as well as the time a train will
 * arrive at the start station and time it will arrive at the end station
 *
 */
public class Leg {
    
    String trainId;
    String startStation;
    String endStation;
    String line;
    String lineDestination;
    int startTime;
    int endTime;
    
    public Leg(String trainId, String startStation, String endStation, String line, String lineDestination, int startTime, int endTime) {
        this.trainId = trainId;
        this.startStation = startStation;
        this.endStation = endStation;
        this.line = line;
        this.lineDestination = lineDestination;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public Leg(String startStation, String endStation, String line, String lineDestination){
        this.trainId = "";
        this.startStation = startStation;
        this.endStation = endStation;
        this.line = line;
        this.lineDestination = lineDestination;
        this.startTime = -1;
        this.endTime = -1;
    }
    
    public Leg(){
        this.trainId = "";
        this.startStation = "";
        this.endStation = "";
        this.line = "";
        this.lineDestination = "";
        this.startTime = -1;
        this.endTime = -1;
    }
    
    /**
     * Returns a message based on the leg
     * 
     *  @author jeffreyguion
     */
    public String toString(){
        if(this.startTime != -1 && this.endTime != -1){
            return "Leave "+this.startStation+" in " + MyMbta.getMinutesAndSeconds(this.startTime) + ". Arrive at "
                    +this.endStation+" in "+ MyMbta.getMinutesAndSeconds(this.endTime) +".";
        }else if(this.startTime != -1 && this.endTime == -1){
            return "Leave "+this.startStation+" in " + MyMbta.getMinutesAndSeconds(this.startTime) + ". Arrival time at "
                    +this.endStation+" is unknown.";
        }else{
            return "Departure time from "+this.startStation+" is unknown. Arrival time at "
                    +this.endStation+" is unknown.";
        }
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLineDestination() {
        return lineDestination;
    }

    public void setLineDestination(String lineDestination) {
        this.lineDestination = lineDestination;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

   
    

}

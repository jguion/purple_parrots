package softwaredev.purpleparrots.gui;


import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;

import softwaredev.purpleparrots.MyMbta;
import softwaredev.purpleparrots.Train;

public class TrainUpdater extends TimerTask{
    
    private String location;
    private ArrayList<UITrain> blueLineTrains;
    private ArrayList<UITrain> redLineTrains;
    private ArrayList<UITrain> orangeLineTrains;
    

    public TrainUpdater(String location) {
        this.location = location;
    }



    @Override
    public void run() {
        showOrangeLineTrains(this.orangeLineTrains);
        showRedLineTrains(this.redLineTrains);
        showBlueLineTrains(this.blueLineTrains);
    }
    
    

    
    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }



    public ArrayList<UITrain> getBlueLineTrains() {
        return blueLineTrains;
    }



    public void setBlueLineTrains(ArrayList<UITrain> blueLineTrains) {
        this.blueLineTrains = blueLineTrains;
    }



    public ArrayList<UITrain> getRedLineTrains() {
        return redLineTrains;
    }



    public void setRedLineTrains(ArrayList<UITrain> redLineTrains) {
        this.redLineTrains = redLineTrains;
    }



    public ArrayList<UITrain> getOrangeLineTrains() {
        return orangeLineTrains;
    }



    public void setOrangeLineTrains(ArrayList<UITrain> orangeLineTrains) {
        this.orangeLineTrains = orangeLineTrains;
    }


    /**
     * updates the orange line trains on the GUI
     * 
     * @param trains
     * @author jeffreyguion
     */
    private void showOrangeLineTrains(ArrayList<UITrain> trains){
        Map<String, Train> orange_line_map = MyMbta.getCurrentLocationMap("Orange", this.location);
        for(int i = 0; i < trains.size(); i++){
            UITrain train = trains.get(i);
            if(orange_line_map.get(train.getStopId()) != null){
                train.setTrain(orange_line_map.get(train.getStopId()));
            }else{
                train.removeTrain();
            }
        }
    }
    
    /**
     * updates the red line trains on the GUI
     * 
     * @param trains
     * @author jeffreyguion
     */
    private void showRedLineTrains(ArrayList<UITrain> trains){
        Map<String, Train> red_line_map = MyMbta.getCurrentLocationMap("Red", this.location);
        for(int i = 0; i < trains.size(); i++){
            UITrain train = trains.get(i);
            if(red_line_map.get(train.getStopId()) != null){
                train.setTrain(red_line_map.get(train.getStopId()));
            }else{
                train.removeTrain();
            }
        }
    }


    /**
     * updates the blue line trains on the GUI
     * 
     * @param trains
     * @author jeffreyguion
     */
    private void showBlueLineTrains(ArrayList<UITrain> trains) {
        Map<String, Train> blue_line_map = MyMbta.getCurrentLocationMap("Blue", this.location);
        for(int i = 0; i < trains.size(); i++){
            UITrain train = trains.get(i);
            if(blue_line_map.get(train.getStopId()) != null){
                train.setTrain(blue_line_map.get(train.getStopId()));
            }else{
                train.removeTrain();
            }
        }
    }

}

package softwaredev.purpleparrots.gui;

import java.awt.Color;

import javax.swing.JPanel;

import softwaredev.purpleparrots.Train;

public class UITrain extends JPanel{

    private boolean hasTrain;
    private String stopId;
    private Train train;
  
    public UITrain(String stopId) {
        this.stopId = stopId;
        this.hasTrain = false;
        this.setBackground(Color.WHITE);
    }
    
    

    public String getStopId() {
        return stopId;
    }



    public void setStopId(String stopId) {
        this.stopId = stopId;
    }



    public boolean hasTrain() {
        return hasTrain;
    }



    public void setHasTrain(boolean hasTrain) {
        this.hasTrain = hasTrain;
    }



    public Train getTrain() {
        return this.train;
    }
    
    /**
     * Removes a train from the GUI that no longer exists
     * 
     * @author jeffreyguion
     */
    public void removeTrain(){
        this.train = null;
        this.hasTrain = false;
        this.setBackground(Color.WHITE);
    }



    /**
     * Sets the Train object from the data and adds a tooltip and a black train to the GUI
     * 
     * @param train
     */
    public void setTrain(Train train) {
        this.train = train;
        this.hasTrain = true;
        this.setToolTipText(train.toString());
        this.setBackground(Color.BLACK);

    }

}

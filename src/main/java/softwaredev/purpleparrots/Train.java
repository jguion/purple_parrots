package softwaredev.purpleparrots;

import java.util.List;

public class Train {
    private String id;
    private String line;
    private String destination;
    private List<Prediction> predictions;

    public Train(String id, String line, String destination,
                 List<Prediction> predictions) {
        this.setId(id);
        this.line = line;
        this.setDestination(destination);
        this.setPredictions(predictions);
    }

    public Train(String id, String line, String destination) {
        this.setId(id);
        this.line = line;
        this.setDestination(destination);
    }

    public String toString(){
        Prediction nextStop = this.getPredictions().get(0);
        if(nextStop.seconds < 0 && this.getPredictions().size() > 1){
            nextStop = this.getPredictions().get(1);
        }
        return "A train will arrive at "+nextStop.stop +" in "+nextStop.seconds+" seconds.";
    }

    /**
     * Determines whether this train has a prediction for the given stop ID.
     * @param stopId  the stop ID of the predictions to look for
     * @return        true if this train contains a prediction with the given
     *                stop ID; false otherwise
     * @author        labichn
     */
    public boolean hasPredFor(String stopId) {
        boolean re = false;
        if (stopId != null && getPredictions() != null) {
            Prediction tmp = null;
            for (int i=0; i<getPredictions().size(); i++) {
                tmp = getPredictions().get(i);
                if (tmp.stopId.equals(stopId)) {
                    re = true;
                    break;
                }
            }
        }
        return re;
    }

    /**
     * Gets the first prediction with the given stop ID, if there is one.
     * Throws a runtime exception otherwise.
     * @param stopId  the stop ID of the prediction to get
     * @return        the prediction, if one exists. an insulting exception
     *                will be thrown otherwise.
     * @author        labichn
     */
    public Prediction getPredFor(String stopId) {
        if (stopId != null) {
            for (int i=0; i< getPredictions().size(); i++) {
                if (getPredictions().get(i).stopId.equals(stopId)) {
                    return getPredictions().get(i);
                }
            }
        }
        throw new RuntimeException("Either you were too stupid to use " +
                                   "hasPredFor, or someone is stealing " +
                                   "your list's predictions.");
    }

    /**
     * Overridden for shallow equality.
     * @param that  the given object, possibly a train
     * @return      true if that object is a train and is shallowly
     *              equal (minus predictions) to this train.
     * @author      labichn
     */
    @Override public boolean equals(Object that) {
        if (that != null && that instanceof Train) {
            return equals((Train)that);
        } else {
            return false;
        }
    }

    /**
     * Tests the given train for shallow equality.
     * @param that  the train to test
     * @return      true if the given train has the same id, line, and
     *              destination as this train.
     * @author      labichn
     */
    public boolean equals(Train that) {
        return that != null
            && (this.getId() == null ? that.getId() == null : this.getId().equals(that.getId()));
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

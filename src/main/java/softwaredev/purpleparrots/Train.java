package softwaredev.purpleparrots;

import java.util.List;

public class Train {
    String id;
    String line;
    String destination;
    List<Prediction> predictions;

    public Train(String id, String line, String destination,
                 List<Prediction> predictions) {
        this.id = id;
        this.line = line;
        this.destination = destination;
        this.predictions = predictions;
    }

    public Train(String id, String line, String destination) {
        this.id = id;
        this.line = line;
        this.destination = destination;
    }

    public String toString(){
        Prediction next_stop = this.predictions.get(0);
        return "Train "+this.id+" is on the "+this.line+" line "+
            next_stop.toString() +
            " headed to "+this.destination+".\n";
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
        if (stopId != null && predictions != null) {
            Prediction tmp = null;
            for (int i=0; i<predictions.size(); i++) {
                tmp = predictions.get(i);
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
            for (int i=0; i< predictions.size(); i++) {
                if (predictions.get(i).stopId.equals(stopId)) {
                    return predictions.get(i);
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
            && (this.id == null ? that.id == null : this.id.equals(that.id));
    }

}

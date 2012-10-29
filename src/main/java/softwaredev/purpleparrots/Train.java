package main.java.softwaredev.purpleparrots;

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
	

}

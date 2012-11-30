package softwaredev.purpleparrots;

public class Prediction {
	public String stopId;
	public String stop;
	public int seconds;
	
	public Prediction(String stopId, String stop, int seconds) {
		this.stopId = stopId;
		this.stop = stop;
		this.seconds = seconds;
	}
	
	public String toString(){
		if(this.seconds > 100){
			int minutes = this.seconds / 60;
			return minutes +" minutes away from "+ this.stop;
		}else{
			return this.seconds+" seconds away from "+this.stop;
		}
	}

}

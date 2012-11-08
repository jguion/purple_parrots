package softwaredev.purpleparrots.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

public class MbtaMap extends JPanel{
	ArrayList<String> route;
	Mode mode;
	
	MbtaMap(){
		this.route = new ArrayList();
		this.mode = Mode.STATION;
	}
	
	ArrayList<String> getRoute(){
		return this.route;
	}
	
	Mode getMode(){
		return this.mode;
	}
	
	void  setMode(Mode mode){
		this.mode = mode;
	}
	
	
	void addStation(String station){
		this.route.add(station);
	}
	
	void removeStation(String station){
		this.route.remove(station);
	}
	
	void clearRoute(){
		this.route.clear();
	}

}

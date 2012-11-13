package softwaredev.purpleparrots.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

public class MbtaMap extends JPanel{
	ArrayList<Station> route;
	Mode mode;
	
	MbtaMap(){
		this.route = new ArrayList<Station>();
		this.mode = Mode.ORDERED_ROUTE;
	}
	
	public ArrayList<Station> getRoute(){
		return this.route;
	}
	
	public ArrayList<String> routeToString(){
		ArrayList<String> stringRoute = new ArrayList<String>();
		for (int i=0; i < this.route.size(); i++){
			stringRoute.add(i, route.get(i).getName());
		}
		return stringRoute;
	}
	
	public Mode getMode(){
		return this.mode;
	}
	
	public void  setMode(Mode mode){
		this.mode = mode;
	}
	
	
	public void addStation(Station station){
		this.route.add(station);
	}
	
	public void removeStation(Station station){
		this.route.remove(station);
	}
	
	public void clearRoute(){
		this.route.clear();
	}

}

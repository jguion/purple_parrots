package softwaredev.purpleparrots.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MbtaMap extends JPanel{
	private ArrayList<Station> route;
	private Mode mode;
	private RouteType routeType;
	private Station startStation;
	private Station endStation;
	Long timeOfTrip;
	int timeOfTripIndex;

    MbtaMap(){
		this.route = new ArrayList<Station>();
		this.mode = Mode.ORDERED_ROUTE;
		this.routeType = RouteType.FASTEST_ROUTE;
		this.startStation = null;
		this.endStation = null;
	}
	
	Image image = null;
	String url = "http://i46.tinypic.com/347xl68.jpg";
	
	public void ImagePanel(String filename) {
        try {
			this.image = new ImageIcon(new URL(filename)).getImage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/**
	 * @author - ryanbigelow
	 */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.ImagePanel(this.url);
        g.drawImage(image, 0, 0, 1100, 700, null);
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
	
	
	public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
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
	   
    public Long getTimeOfTrip() {
        return timeOfTrip;
    }

    public void setTimeOfTrip(Long timeOfTrip) {
        this.timeOfTrip = timeOfTrip;
    }

    public int getTimeOfTripIndex() {
        return timeOfTripIndex;
    }

    public void setTimeOfTripIndex(int timeOfTripIndex) {
        this.timeOfTripIndex = timeOfTripIndex;
    }
    
    /**
	 * @author - ryanbigelow
	 */
	public void removeLastStation(){
		if (this.route.size() == 0) {}
		else 
			this.route.remove(this.route.size() - 1);
	}
	
	/**
	 * @author - ryanbigelow
	 */
	public void clearScreen(){
		
		for(Station s : this.route){
			if(this.route.size() == 0){}
			else {
				if (s == this.route.get(this.route.size() - 1)){
					s.setBackground(Color.yellow);
					}
				else
					s.setBackground(Color.white);
			}
		}
	}
	
	/**
	 * @author - ryanbigelow
	 */
	public void setAllWhite(){
		for(Station s : this.route){
			s.setBackground(Color.white);
		}
	}
}

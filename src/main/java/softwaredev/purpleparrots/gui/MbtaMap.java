package softwaredev.purpleparrots.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MbtaMap extends JPanel{
	ArrayList<Station> route;
	Mode mode;
	
	MbtaMap(){
		this.route = new ArrayList<Station>();
		this.mode = Mode.ORDERED_ROUTE;
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

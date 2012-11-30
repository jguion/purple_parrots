package softwaredev.purpleparrots.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import softwaredev.purpleparrots.Line;
import softwaredev.purpleparrots.MyMbta;
import softwaredev.purpleparrots.Train;

public class Station extends JPanel implements MouseListener{
	private String name;
	private String line;
	
	public Station(String name, String line) {
		this.name = name;
		this.line = line;
		this.setBackground(Color.white);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setToolTipText(name);
		this.addMouseListener(this);
		
	}

	/**
	 * Click event for a station on the gui. 
	 * 
	 * @author jeffreyguion
	 */
	public void mouseClicked(MouseEvent arg0) {
		Mode mode =  ((MbtaMap) this.getParent()).getMode();
		MbtaMap map = (MbtaMap) this.getParent();
		switch(mode){
			case STATION:
				JOptionPane.showMessageDialog(this, this.name +"\n Show Incoming Trains Here");
				break;
			case ORDERED_ROUTE:
				if(this.getBackground() == Color.WHITE){
					this.setBackground(Color.YELLOW);
					map.addStation(this);
					break;
				}else{
					this.setBackground(Color.WHITE);
					map.removeStation(this);
					break;
				}
			case UNORDERED_ROUTE:
				if(this.getBackground() == Color.WHITE){
					this.setBackground(Color.YELLOW);
					map.addStation(this);
					break;
				}else{
					this.setBackground(Color.WHITE);
					map.removeStation(this);
					break;
				}
		}
	}

    /**
     * Gets a filtered list of trains--those which currently have predictions for the given stop name.
     * @param trains  the list of trains to filter
     * @param name    the name of the stop which the trains must have in their predictions list
     * @return        the list of trains which include predictions toward the given stop name
     * @author        labichn
     */
    private List<Train> getTrainsForStop(List<Train> trains, String name) {
        List<Train> re = new ArrayList<Train>();
        if (name != null && trains != null) {
            Train tmp = null;
            for (int i=0; i<trains.size(); i++) {
                tmp = trains.get(i);
                if (tmp.hasPredFor(name)) re.add(tmp);
            }
            re = sort(re, name);
        }
        return re;
    }

    /**
     * Slowly sorts the given list of trains. Calls to getPredFor are safe because
     * we've already filtered on the existance of a prediction with the given stop name.
     * @param trains  the trains to sort
     * @return        the trains, sorted from earliest arrival to latest to the stop
     *                denoted by the given stop name.
     * @author        labichn
     */
    private List<Train> sort(List<Train> trains, String name) {
        List<Train> re = new ArrayList<Train>();
        Train lowest, test;
        while (re.size()<trains.size()) {
            lowest = null;
            test = null;
            for (int i=0; i<trains.size(); i++) {
                test = trains.get(i);
                if (!re.contains(test)) {
                    if (lowest == null
                     || test.getPredFor(name).seconds < lowest.getPredFor(name).seconds) {
                        lowest = test;
                    }
                }
            }
            re.add(lowest);
        }
        return re;
    }
    
    /**
     * Gets a string representation of the predictions for this stop from the given list
     * of trains.
     * @param trains  the trains to get predictions out of
     * @return        the string message for the tooltip.
     * @autho 	      labichn
     */
    private String getTextForTrains(List<Train> trains) {
    	String re = "";
    	if (trains != null && trains.size() > 0) {
    		re = "<html>";
    		for (Train train : trains) {
    			re += "Train " + train.getId() + " is " + String.valueOf(train.getPredFor(this.name).seconds) +
    					" seconds away.<br>";
    		}
    		re = re.substring(0, re.length()-4) + "</html>";
    	} else {
            re = "No train information available for this station.";
        }
    	return re;
    }

	public void mouseEntered(MouseEvent arg0) {
		List<Train> trains = getTrainsForStop(MyMbta.getTrains(new Line(this.line)), this.name);
		this.setToolTipText(getTextForTrains(trains));
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
}

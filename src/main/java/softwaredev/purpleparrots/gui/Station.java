package softwaredev.purpleparrots.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @author jeffreyguion, labichn
     */
    public void mouseClicked(MouseEvent arg0) {
        Mode mode =  ((MbtaMap) this.getParent()).getMode();
        MbtaMap map = (MbtaMap) this.getParent();
        switch(mode){
        case STATION:
            JOptionPane.showMessageDialog(this, getStringForTag(name), name, JOptionPane.PLAIN_MESSAGE);
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
     * Gets a filtered list of trains--those which currently have predictions for the given
     * stop name. Yeah, it's pretty nasty. I'd love suggestions for making it better.
     * @param name    the name of the stop which the trains must have in their predictions list
     * @return        the list of trains which include predictions toward the given stop name
     * @author        labichn
     */
    private String getStringForTag(String name) {
        String nothing = "There are no predictions for this stop.";
        String re = nothing;
        List<Train> trains = MyMbta.getTrains(new Line(line));
        if (name != null && trains != null && trains.size() > 0) {
        	Map<String, List<Train>> map = new HashMap<String, List<Train>>();
        	List<Train> ts;
        	Train tmp;
            for (int i=0; i<trains.size(); i++) {
                tmp = trains.get(i);
                if (tmp.hasPredFor(name) && tmp.getPredFor(name).seconds > 0) {
                	ts = map.get(tmp.getDestination());
                	if (ts == null) {
                		ts = new ArrayList<Train>();
                		map.put(tmp.getDestination(), ts);
                	}
                	ts.add(tmp);
                }
            }
        	String start = "The next ";
        	String mid = "-bound train will arrive in ";
        	String end = ".<br>";
            List<Train> sorted;
            for (List<Train> toSort : map.values()) {
            	if (!re.equals(nothing)) {
            		re += "<br>";
            	} else {
            		re = "";
            	}
            	sorted = sortCut(toSort, name, 5);
            	re += start + sorted.get(0).getDestination() + mid +
            			MyMbta.getMinutesAndSeconds(sorted.get(0).getPredFor(name).seconds) + end;
            	if (sorted.size() > 1) {
            		re += "  Others will follow in ";
            		for (int i=1; i<sorted.size(); i++) {
            			re += MyMbta.getMinutes(sorted.get(i).getPredFor(name).seconds) + ", ";
            		}
            		re = re.substring(0, re.length()-2) + " seconds.<br>";
            	}
            	re = re.substring(0, re.length()-4);
            }
            if (!re.equals(nothing)) {
            	re = "<html>" + re + "</html>";
            }
        }
        return re;
    }


    /**
     * Slowly sorts the given list of trains. Calls to getPredFor are safe because
     * we've already filtered on the existence of a prediction with the given stop name.
     * @param trains  the trains to sort
     * @param name    the name of the stop to look for
     * @param num     the number of trains to return
     * @return        the trains, sorted from earliest arrival to latest to the stop
     *                denoted by the given stop name.
     * @author        labichn
     */
    private List<Train> sortCut(List<Train> trains, String name, int num) {
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
        while (re.size() > num) {
        	re.remove(num);
        }
        return re;
    }

    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
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

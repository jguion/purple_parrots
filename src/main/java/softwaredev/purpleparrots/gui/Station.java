package softwaredev.purpleparrots.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Station extends JPanel implements MouseListener{
	String name;
	int x;
	int y;
	int width;
	int height;
	boolean station_information;
	
	public Station(String name) {
		this.name = name;
		this.setBackground(Color.white);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setToolTipText(name);
		this.addMouseListener(this);
		
	}

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
					map.addStation(this.name);
					break;
				}else{
					this.setBackground(Color.WHITE);
					map.removeStation(this.name);
					break;
				}
			case UNORDERED_ROUTE:
				if(this.getBackground() == Color.WHITE){
					this.setBackground(Color.YELLOW);
					map.addStation(this.name);
					break;
				}else{
					this.setBackground(Color.WHITE);
					map.removeStation(this.name);
					break;
				}
		}
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
	
	
	
}

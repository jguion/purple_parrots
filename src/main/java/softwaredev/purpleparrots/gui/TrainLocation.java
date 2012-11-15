package softwaredev.purpleparrots.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Canvas;
import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JButton;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import softwaredev.purpleparrots.MyMbta;
import softwaredev.purpleparrots.Route;
import softwaredev.purpleparrots.Train;
import java.awt.Label;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainLocation extends JFrame {

	private MbtaMap mbtaMapPanel;
	private String ORANGE = "Orange";
	private String RED = "Red";
	private String BLUE = "Blue";
	private String location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainLocation frame = new TrainLocation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame and places all stations in it. 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * 
	 * @author jeffreyguion
	 */
	public TrainLocation() throws JsonParseException, JsonMappingException, IOException {
	    this.location = MyMbta.http;
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-19, -12, 1100, 700);
		mbtaMapPanel = new MbtaMap();
		mbtaMapPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mbtaMapPanel);
		
		HashMap<String, Train> blue_line_map = MyMbta.getCurrentLocationHash("Blue", this.location);
		mbtaMapPanel.setLayout(null);
		
		// v first blue line stop
		
		Station bowdoin = new Station("Bowdoin", BLUE);
		bowdoin.setBounds(52, 478, 34, 32);
		mbtaMapPanel.add(bowdoin);
		
		Station governmentCenter = new Station("Government Center", BLUE);
		governmentCenter.setBounds(138, 478, 34, 32);
		mbtaMapPanel.add(governmentCenter);
		
		Station stateStBlue = new Station("State Street", BLUE);
		stateStBlue.setBounds(224, 478, 34, 32);
		mbtaMapPanel.add(stateStBlue);
		
		Station aquarium = new Station("Aquarium", BLUE);
		aquarium.setBounds(310, 478, 34, 32);
		mbtaMapPanel.add(aquarium);
		
		Station maverick = new Station("Maverick", BLUE);
		maverick.setBounds(396, 478, 34, 32);
		mbtaMapPanel.add(maverick);
		
		Station airport = new Station("Airport", BLUE);
		airport.setBounds(482, 478, 34, 32);
		mbtaMapPanel.add(airport);
		
		Station woodIsland = new Station("Wood Island", BLUE);
		woodIsland.setBounds(568, 478, 34, 32);
		mbtaMapPanel.add(woodIsland);
		
		Station orientHeight = new Station("Orient Height", BLUE);
		orientHeight.setBounds(654, 478, 34, 32);
		mbtaMapPanel.add(orientHeight);
		
		Station suffolkDowns = new Station("Suffolk Downs", BLUE);
		suffolkDowns.setBounds(740, 478, 34, 32);
		mbtaMapPanel.add(suffolkDowns);
		
		Station beachmont = new Station("Beachmont", BLUE);
		beachmont.setBounds(826, 478, 34, 32);
		mbtaMapPanel.add(beachmont);
		
		Station revereBeach = new Station("Revere Beach", BLUE);
		revereBeach.setBounds(912, 478, 34, 32);
		mbtaMapPanel.add(revereBeach);
		
		Station wonderland = new Station("Wonderland", BLUE);
		wonderland.setBounds(998, 478, 34, 32);
		mbtaMapPanel.add(wonderland);
		
		// ^ last blue line stop
		
		// v first <--- blue line train (yes, I know they're reverse ordered)
		
		Canvas canvas_145 = new Canvas();
		canvas_145.setBounds(98, 425, 20, 20);
		mbtaMapPanel.add(canvas_145);
		if(blue_line_map.get("70037") != null){
			canvas_145.setBackground(Color.BLACK);
		}
		
		Canvas canvas_144 = new Canvas();
		canvas_144.setBounds(184, 425, 20, 20);
		mbtaMapPanel.add(canvas_144);
		if(blue_line_map.get("70039") != null){
			canvas_144.setBackground(Color.BLACK);
		}
		
		Canvas canvas_143 = new Canvas();
		canvas_143.setBounds(273, 425, 20, 20);
		mbtaMapPanel.add(canvas_143);
		if(blue_line_map.get("70041") != null){
			canvas_143.setBackground(Color.BLACK);
		}
		
		Canvas canvas_142 = new Canvas();
		canvas_142.setBounds(360, 425, 20, 20);
		mbtaMapPanel.add(canvas_142);
		if(blue_line_map.get("70043") != null){
			canvas_142.setBackground(Color.BLACK);
		}
		
		Canvas canvas_141 = new Canvas();
		canvas_141.setBounds(443, 425, 20, 20);
		mbtaMapPanel.add(canvas_141);
		if(blue_line_map.get("70045") != null){
			canvas_141.setBackground(Color.BLACK);
		}
		
		Canvas canvas_140 = new Canvas();
		canvas_140.setBounds(529, 425, 20, 20);
		mbtaMapPanel.add(canvas_140);
		if(blue_line_map.get("70047") != null){
			canvas_140.setBackground(Color.BLACK);
		}
		
		Canvas canvas_139 = new Canvas();
		canvas_139.setBounds(615, 425, 20, 20);
		mbtaMapPanel.add(canvas_139);
		if(blue_line_map.get("70049") != null){
			canvas_139.setBackground(Color.BLACK);
		}
		
		Canvas canvas_138 = new Canvas();
		canvas_138.setBounds(701, 425, 20, 20);
		mbtaMapPanel.add(canvas_138);
		if(blue_line_map.get("70051") != null){
			canvas_138.setBackground(Color.BLACK);
		}
		
		Canvas canvas_137 = new Canvas();
		canvas_137.setBounds(784, 425, 20, 20);
		mbtaMapPanel.add(canvas_137);
		if(blue_line_map.get("70053") != null){
			canvas_137.setBackground(Color.BLACK);
		}
		
		Canvas canvas_136 = new Canvas();
		canvas_136.setBounds(873, 425, 20, 20);
		mbtaMapPanel.add(canvas_136);
		if(blue_line_map.get("70055") != null){
			canvas_136.setBackground(Color.BLACK);
		}
		
		Canvas canvas_135 = new Canvas();
		canvas_135.setBounds(959, 425, 20, 20);
		mbtaMapPanel.add(canvas_135);
		if(blue_line_map.get("70057") != null){
			canvas_135.setBackground(Color.BLACK);
		}
		
		// ^ last <--- blue line train
		
		// v first ---> blue line train
		
		Canvas canvas_146 = new Canvas();
		canvas_146.setBounds(98, 525, 20, 20);
		mbtaMapPanel.add(canvas_146);
		if(blue_line_map.get("70040") != null){
			canvas_146.setBackground(Color.BLACK);
		}
		
		Canvas canvas_147 = new Canvas();
		canvas_147.setBounds(184, 525, 20, 20);
		mbtaMapPanel.add(canvas_147);
		if(blue_line_map.get("70042") != null){
			canvas_147.setBackground(Color.BLACK);
		}
		
		Canvas canvas_148 = new Canvas();
		canvas_148.setBounds(273, 525, 20, 20);
		mbtaMapPanel.add(canvas_148);
		if(blue_line_map.get("70044") != null){
			canvas_148.setBackground(Color.BLACK);
		}
		
		Canvas canvas_149 = new Canvas();
		canvas_149.setBounds(360, 525, 20, 20);
		mbtaMapPanel.add(canvas_149);
		if(blue_line_map.get("70046") != null){
			canvas_149.setBackground(Color.BLACK);
		}
		
		Canvas canvas_150 = new Canvas();
		canvas_150.setBounds(443, 525, 20, 20);
		mbtaMapPanel.add(canvas_150);
		if(blue_line_map.get("70048") != null){
			canvas_150.setBackground(Color.BLACK);
		}
		
		Canvas canvas_151 = new Canvas();
		canvas_151.setBounds(529, 525, 20, 20);
		mbtaMapPanel.add(canvas_151);
		if(blue_line_map.get("70050") != null){
			canvas_151.setBackground(Color.BLACK);
		}
		
		Canvas canvas_152 = new Canvas();
		canvas_152.setBounds(615, 525, 20, 20);
		mbtaMapPanel.add(canvas_152);
		if(blue_line_map.get("70052") != null){
			canvas_152.setBackground(Color.BLACK);
		}
		
		Canvas canvas_153 = new Canvas();
		canvas_153.setBounds(701, 525, 20, 20);
		mbtaMapPanel.add(canvas_153);
		if(blue_line_map.get("70054") != null){
			canvas_153.setBackground(Color.BLACK);
		}
		
		Canvas canvas_154 = new Canvas();
		canvas_154.setBounds(784, 525, 20, 20);
		mbtaMapPanel.add(canvas_154);
		if(blue_line_map.get("70056") != null){
			canvas_154.setBackground(Color.BLACK);
		}
		
		Canvas canvas_155 = new Canvas();
		canvas_155.setBounds(873, 525, 20, 20);
		mbtaMapPanel.add(canvas_155);
		if(blue_line_map.get("70058") != null){
			canvas_155.setBackground(Color.BLACK);
		}
		
		Canvas canvas_156 = new Canvas();
		canvas_156.setBounds(959, 525, 20, 20);
		mbtaMapPanel.add(canvas_156);
		if(blue_line_map.get("70060") != null){
			canvas_156.setBackground(Color.BLACK);
		}
		
		// ^ last ---> blue line train
		
		Canvas canvas_119 = new Canvas();
		canvas_119.setBounds(821, 285, 5, 60);
		canvas_119.setBackground(new Color(192, 192, 192));
		mbtaMapPanel.add(canvas_119);
		
		Canvas canvas_118 = new Canvas();
		canvas_118.setBounds(628, 285, 5, 60);
		canvas_118.setBackground(new Color(192, 192, 192));
		mbtaMapPanel.add(canvas_118);
		
		Station oakGrove = new Station("Oak Grove", ORANGE);
		oakGrove.setBounds(19, 63, 34, 32);
		mbtaMapPanel.add(oakGrove);
		
		Station maldenCenter = new Station("Malden Center", ORANGE);
		maldenCenter.setBounds(72, 63, 34, 32);
		mbtaMapPanel.add(maldenCenter);
		
		Station wellington = new Station("Wellington", ORANGE);
		wellington.setBounds(125, 63, 34, 32);
		mbtaMapPanel.add(wellington);
		
		Station sullivanSquare = new Station("Sullivan", ORANGE);
		sullivanSquare.setBounds(178, 63, 34, 32);
		mbtaMapPanel.add(sullivanSquare);
		
		Station communityCollege = new Station("Community College", ORANGE);
		communityCollege.setBounds(231, 63, 34, 32);
		mbtaMapPanel.add(communityCollege);
		
		Station northStaion = new Station("North Station", ORANGE);
		northStaion.setBounds(284, 63, 34, 32);
		mbtaMapPanel.add(northStaion);
		
		Station haymarket = new Station("Haymarket", ORANGE);
		haymarket.setBounds(337, 63, 34, 32);
		mbtaMapPanel.add(haymarket);
		
		Station stateSt = new Station("State Street", ORANGE);
		stateSt.setBounds(390, 63, 34, 32);
		mbtaMapPanel.add(stateSt);
		
		Station downtownCrossing = new Station("Downtown Crossing", ORANGE);
		downtownCrossing.setBounds(443, 63, 34, 32);
		mbtaMapPanel.add(downtownCrossing);
		
		Station chinatown = new Station("Chinatown", ORANGE);
		chinatown.setBounds(496, 63, 34, 32);
		mbtaMapPanel.add(chinatown);
		
		Station tufts = new Station("Tufts Medical", ORANGE);
		tufts.setBounds(549, 63, 34, 32);
		mbtaMapPanel.add(tufts);
		
		Station backBay = new Station("Back Bay", ORANGE);
		backBay.setBounds(602, 63, 34, 32);
		mbtaMapPanel.add(backBay);
		
		Station massAve = new Station("Mass Ave", ORANGE);
		massAve.setBounds(655, 63, 34, 32);
		mbtaMapPanel.add(massAve);
		
		Station ruggles = new Station("Ruggles", ORANGE);
		ruggles.setBounds(708, 63, 34, 32);
		ruggles.setBackground(Color.WHITE);
		mbtaMapPanel.add(ruggles);
		
		Station roxburyCrossing = new Station("Roxbury Crossing", ORANGE);
		roxburyCrossing.setBounds(761, 63, 34, 32);
		mbtaMapPanel.add(roxburyCrossing);
		
		Station jacksonSquare = new Station("Jackson Square", ORANGE);
		jacksonSquare.setBounds(814, 63, 34, 32);
		mbtaMapPanel.add(jacksonSquare);
		
		Station stonyBrook = new Station("Stony Brook", ORANGE);
		stonyBrook.setBounds(867, 63, 34, 32);
		mbtaMapPanel.add(stonyBrook);
		
		Station greenSt = new Station("Green Street", ORANGE);
		greenSt.setBounds(920, 63, 34, 32);
		mbtaMapPanel.add(greenSt);
		
		Station forestHills = new Station("Forest Hills", ORANGE);
		forestHills.setBounds(973, 63, 34, 32);
		mbtaMapPanel.add(forestHills);
		
		Canvas orangeLineEnd = new Canvas();
		orangeLineEnd.setBounds(1026, 63, 34, 32);
		orangeLineEnd.setBackground(Color.WHITE);
		mbtaMapPanel.add(orangeLineEnd);
		
		//ORANGE LINE STOPS 20 - 51
		HashMap<String, Train> orange_line_map = MyMbta.getCurrentLocationHash("Orange", this.location);
		
		JPanel FH_OG_panel = new JPanel();
		FH_OG_panel.setBounds(19, 10, 1041, 38);
		mbtaMapPanel.add(FH_OG_panel);
		FH_OG_panel.setLayout(null);
		
		Canvas FH_OG_end = new Canvas();
		FH_OG_end.setBounds(19, 5, 20, 20);
		FH_OG_panel.add(FH_OG_end);
		if(orange_line_map.get("70036") != null){
			FH_OG_end.setBackground(Color.BLACK);
		}
		
		Canvas canvas_21 = new Canvas();
		canvas_21.setBounds(74, 5, 20, 20);
		FH_OG_panel.add(canvas_21);
		if(orange_line_map.get("70035") != null){
			canvas_21.setBackground(Color.BLACK);
		}
		
		Canvas canvas_22 = new Canvas();
		canvas_22.setBounds(127, 5, 20, 20);
		FH_OG_panel.add(canvas_22);
		if(orange_line_map.get("70033") != null){
			canvas_22.setBackground(Color.BLACK);
		}
		
		Canvas canvas_23 = new Canvas();
		canvas_23.setBounds(180, 5, 20, 20);
		FH_OG_panel.add(canvas_23);
		if(orange_line_map.get("70031") != null){
			canvas_23.setBackground(Color.BLACK);
		}
		
		Canvas canvas_24 = new Canvas();
		canvas_24.setBounds(233, 5, 20, 20);
		FH_OG_panel.add(canvas_24);
		if(orange_line_map.get("70029") != null){
			canvas_24.setBackground(Color.BLACK);
		}
		
		Canvas canvas_25 = new Canvas();
		canvas_25.setBounds(286, 5, 20, 20);
		FH_OG_panel.add(canvas_25);
		if(orange_line_map.get("70027") != null){
			canvas_25.setBackground(Color.BLACK);
		}
		
		Canvas canvas_26 = new Canvas();
		canvas_26.setBounds(339, 5, 20, 20);
		FH_OG_panel.add(canvas_26);
		if(orange_line_map.get("70025") != null){
			canvas_26.setBackground(Color.BLACK);
		}
		
		Canvas canvas_27 = new Canvas();
		canvas_27.setBounds(392, 5, 20, 20);
		FH_OG_panel.add(canvas_27);
		if(orange_line_map.get("70023") != null){
			canvas_27.setBackground(Color.BLACK);
		}
		
		Canvas canvas_28 = new Canvas();
		canvas_28.setBounds(445, 5, 20, 20);
		FH_OG_panel.add(canvas_28);
		if(orange_line_map.get("70021") != null){
			canvas_28.setBackground(Color.BLACK);
		}
		
		Canvas canvas_29 = new Canvas();
		canvas_29.setBounds(498, 5, 20, 20);
		FH_OG_panel.add(canvas_29);
		if(orange_line_map.get("70019") != null){
			canvas_29.setBackground(Color.BLACK);
		}
		
		Canvas canvas_30 = new Canvas();
		canvas_30.setBounds(551, 5, 20, 20);
		FH_OG_panel.add(canvas_30);
		if(orange_line_map.get("70017") != null){
			canvas_30.setBackground(Color.BLACK);
		}
		
		Canvas canvas_31 = new Canvas();
		canvas_31.setBounds(604, 5, 20, 20);
		FH_OG_panel.add(canvas_31);
		if(orange_line_map.get("70015") != null){
			canvas_31.setBackground(Color.BLACK);
		}
		
		Canvas canvas_32 = new Canvas();
		canvas_32.setBounds(657, 5, 20, 20);
		FH_OG_panel.add(canvas_32);
		if(orange_line_map.get("70013") != null){
			canvas_32.setBackground(Color.BLACK);
		}
		
		Canvas canvas_33 = new Canvas();
		canvas_33.setBounds(710, 5, 20, 20);
		FH_OG_panel.add(canvas_33);
		if(orange_line_map.get("70011") != null){
			canvas_33.setBackground(Color.BLACK);
		}
		
		Canvas canvas_34 = new Canvas();
		canvas_34.setBounds(763, 5, 20, 20);
		FH_OG_panel.add(canvas_34);
		if(orange_line_map.get("70009") != null){
			canvas_34.setBackground(Color.BLACK);
		}
		
		Canvas canvas_35 = new Canvas();
		canvas_35.setBounds(816, 5, 20, 20);
		FH_OG_panel.add(canvas_35);
		if(orange_line_map.get("70007") != null){
			canvas_35.setBackground(Color.BLACK);
		}
		
		Canvas canvas_36 = new Canvas();
		canvas_36.setBounds(869, 5, 20, 20);
		FH_OG_panel.add(canvas_36);
		if(orange_line_map.get("70005") != null){
			canvas_36.setBackground(Color.BLACK);
		}
		
		Canvas canvas_37 = new Canvas();
		canvas_37.setBounds(922, 5, 20, 20);
		FH_OG_panel.add(canvas_37);
		if(orange_line_map.get("70003") != null){
			canvas_37.setBackground(Color.BLACK);
		}
		
		Canvas FH_OG_start = new Canvas();
		FH_OG_start.setBounds(975, 5, 20, 20);
		FH_OG_panel.add(FH_OG_start);
		if(orange_line_map.get("70001") != null){
			FH_OG_start.setBackground(Color.BLACK);
		}
		
		Canvas OG_FH_start = new Canvas();
		OG_FH_start.setBounds(58, 120, 20, 20);
		mbtaMapPanel.add(OG_FH_start);
		if(orange_line_map.get("70036") != null){
			OG_FH_start.setBackground(Color.BLACK);
		}
		
		Canvas canvas_53 = new Canvas();
		canvas_53.setBounds(112, 120, 20, 20);
		mbtaMapPanel.add(canvas_53);
		if(orange_line_map.get("70034") != null){
			canvas_53.setBackground(Color.BLACK);
		}
		
		Canvas canvas_54 = new Canvas();
		canvas_54.setBounds(167, 120, 20, 20);
		mbtaMapPanel.add(canvas_54);
		if(orange_line_map.get("70032") != null){
			canvas_54.setBackground(Color.BLACK);
		}
		
		Canvas canvas_39 = new Canvas();
		canvas_39.setBounds(219, 120, 20, 20);
		mbtaMapPanel.add(canvas_39);
		if(orange_line_map.get("70030") != null){
			canvas_39.setBackground(Color.BLACK);
		}
		
		Canvas canvas_40 = new Canvas();
		canvas_40.setBounds(273, 120, 20, 20);
		mbtaMapPanel.add(canvas_40);
		if(orange_line_map.get("70028") != null){
			canvas_40.setBackground(Color.BLACK);
		}
		
		Canvas canvas_41 = new Canvas();
		canvas_41.setBounds(329, 120, 20, 20);
		mbtaMapPanel.add(canvas_41);
		if(orange_line_map.get("70026") != null){
			canvas_41.setBackground(Color.BLACK);
		}
		
		Canvas canvas_42 = new Canvas();
		canvas_42.setBounds(377, 120, 20, 20);
		mbtaMapPanel.add(canvas_42);
		if(orange_line_map.get("70024") != null){
			canvas_42.setBackground(Color.BLACK);
		}
		
		Canvas canvas_43 = new Canvas();
		canvas_43.setBounds(428, 120, 20, 20);
		mbtaMapPanel.add(canvas_43);
		if(orange_line_map.get("70022") != null){
			canvas_43.setBackground(Color.BLACK);
		}
		
		Canvas canvas_44 = new Canvas();
		canvas_44.setBounds(478, 120, 20, 20);
		mbtaMapPanel.add(canvas_44);
		if(orange_line_map.get("70020") != null){
			canvas_44.setBackground(Color.BLACK);
		}
		
		Canvas canvas_45 = new Canvas();
		canvas_45.setBounds(529, 120, 20, 20);
		mbtaMapPanel.add(canvas_45);
		if(orange_line_map.get("70018") != null){
			canvas_45.setBackground(Color.BLACK);
		}
		
		Canvas canvas_46 = new Canvas();
		canvas_46.setBounds(581, 120, 20, 20);
		mbtaMapPanel.add(canvas_46);
		if(orange_line_map.get("70016") != null){
			canvas_46.setBackground(Color.BLACK);
		}
		
		Canvas canvas_47 = new Canvas();
		canvas_47.setBounds(629, 120, 20, 20);
		mbtaMapPanel.add(canvas_47);
		if(orange_line_map.get("70014") != null){
			canvas_47.setBackground(Color.BLACK);
		}
		
		Canvas canvas_48 = new Canvas();
		canvas_48.setBounds(680, 120, 20, 20);
		mbtaMapPanel.add(canvas_48);
		if(orange_line_map.get("70012") != null){
			canvas_48.setBackground(Color.BLACK);
		}
		
		Canvas canvas_49 = new Canvas();
		canvas_49.setBounds(728, 120, 20, 20);
		mbtaMapPanel.add(canvas_49);
		if(orange_line_map.get("70010") != null){
			canvas_49.setBackground(Color.BLACK);
		}
		
		Canvas canvas_50 = new Canvas();
		canvas_50.setBounds(778, 120, 20, 20);
		mbtaMapPanel.add(canvas_50);
		if(orange_line_map.get("70008") != null){
			canvas_50.setBackground(Color.BLACK);
		}
		
		Canvas canvas_51 = new Canvas();
		canvas_51.setBounds(828, 120, 20, 20);
		mbtaMapPanel.add(canvas_51);
		if(orange_line_map.get("70006") != null){
			canvas_51.setBackground(Color.BLACK);
		}
		
		Canvas canvas_55 = new Canvas();
		canvas_55.setBounds(873, 120, 20, 20);
		mbtaMapPanel.add(canvas_55);
		if(orange_line_map.get("70004") != null){
			canvas_55.setBackground(Color.BLACK);
		}
		
		Canvas canvas_56 = new Canvas();
		canvas_56.setBounds(924, 120, 20, 20);
		mbtaMapPanel.add(canvas_56);
		if(orange_line_map.get("70002") != null){
			canvas_56.setBackground(Color.BLACK);
		}
		
		Canvas canvas_57 = new Canvas();
		canvas_57.setBounds(974, 120, 20, 20);
		mbtaMapPanel.add(canvas_57);
		if(orange_line_map.get("70000") != null){
			canvas_57.setBackground(Color.BLACK);
		}
		
		JLabel lblOakGrove = new JLabel("Oak Grove");
		lblOakGrove.setBounds(16, 163, 76, 16);
		mbtaMapPanel.add(lblOakGrove);
		
		JLabel lblMaldenCenter = new JLabel("Malden Center");
		lblMaldenCenter.setBounds(58, 191, 103, 16);
		mbtaMapPanel.add(lblMaldenCenter);
		
		JLabel lblWellington = new JLabel("Wellington");
		lblWellington.setBounds(122, 163, 76, 16);
		mbtaMapPanel.add(lblWellington);
		
		JLabel lblSullivanSquare = new JLabel("Sullivan Square");
		lblSullivanSquare.setBounds(167, 191, 95, 16);
		mbtaMapPanel.add(lblSullivanSquare);
		
		JLabel lblCommunityCollege = new JLabel("Community College");
		lblCommunityCollege.setBounds(202, 163, 134, 16);
		mbtaMapPanel.add(lblCommunityCollege);
		
		JLabel lblNorthStation = new JLabel("North Station");
		lblNorthStation.setBounds(273, 191, 96, 16);
		mbtaMapPanel.add(lblNorthStation);
		
		JLabel lblHaymarket = new JLabel("Haymarket");
		lblHaymarket.setBounds(339, 163, 69, 16);
		mbtaMapPanel.add(lblHaymarket);
		
		JLabel lblStateSt = new JLabel("State St");
		lblStateSt.setBounds(392, 191, 61, 16);
		mbtaMapPanel.add(lblStateSt);
		
		JLabel lblDowntownCrossing = new JLabel("Downtown Crossing");
		lblDowntownCrossing.setBounds(420, 163, 135, 16);
		mbtaMapPanel.add(lblDowntownCrossing);
		
		JLabel lblChinatown = new JLabel("Chinatown");
		lblChinatown.setBounds(489, 191, 85, 16);
		mbtaMapPanel.add(lblChinatown);
		
		JLabel lblTufts = new JLabel("Tufts");
		lblTufts.setBounds(554, 163, 61, 16);
		mbtaMapPanel.add(lblTufts);
		
		JLabel lblBackBay = new JLabel("Back Bay");
		lblBackBay.setBounds(602, 191, 61, 16);
		mbtaMapPanel.add(lblBackBay);
		
		JLabel lblMassAve = new JLabel("Mass Ave");
		lblMassAve.setBounds(639, 163, 61, 16);
		mbtaMapPanel.add(lblMassAve);
		
		JLabel lblRuggles = new JLabel("Ruggles");
		lblRuggles.setBounds(701, 191, 61, 16);
		mbtaMapPanel.add(lblRuggles);
		
		JLabel lblRoxburyCrossing = new JLabel("Roxbury Crossing");
		lblRoxburyCrossing.setBounds(728, 163, 134, 16);
		mbtaMapPanel.add(lblRoxburyCrossing);
		
		JLabel lblJacksonSquare = new JLabel("Jackson Square");
		lblJacksonSquare.setBounds(778, 191, 112, 16);
		mbtaMapPanel.add(lblJacksonSquare);
		
		JLabel lblStonyBrook = new JLabel("Stony Brook");
		lblStonyBrook.setBounds(856, 163, 76, 16);
		mbtaMapPanel.add(lblStonyBrook);
		
		JLabel lblGreenSt = new JLabel("Green St");
		lblGreenSt.setBounds(897, 191, 61, 16);
		mbtaMapPanel.add(lblGreenSt);
		
		JLabel lblForestHills = new JLabel("Forest Hills");
		lblForestHills.setBounds(947, 163, 86, 16);
		mbtaMapPanel.add(lblForestHills);
		
		// First red line stop
		
		Station alewife = new Station("Alewife", RED);
		alewife.setBounds(14, 295, 34, 32);
		mbtaMapPanel.add(alewife);
		
		Station davis = new Station("Davis", RED);
		davis.setBounds(62, 295, 34, 32);
		mbtaMapPanel.add(davis);
		
		Station porter = new Station("Porter Square", RED);
		porter.setBounds(110, 295, 34, 32);
		mbtaMapPanel.add(porter);
		
		Station harvard = new Station("Harvard Square", RED);
		harvard.setBounds(158, 295, 34, 32);
		mbtaMapPanel.add(harvard);
		
		Station central = new Station("Central Square", RED);
		central.setBounds(206, 295, 34, 32);
		mbtaMapPanel.add(central);
		
		Station kendall = new Station("Kendall/MIT", RED);
		kendall.setBounds(254, 295, 34, 32);
		mbtaMapPanel.add(kendall);
		
		Station charles = new Station("Charles/MGH", RED);
		charles.setBounds(302, 295, 34, 32);
		mbtaMapPanel.add(charles);
		
		Station parkSt = new Station("Park Street", RED);
		parkSt.setBounds(350, 295, 34, 32);
		mbtaMapPanel.add(parkSt);
		
		Station downtownCrossingRed = new Station("Downtown Crossing", RED);
		downtownCrossingRed.setBounds(398, 295, 34, 32);
		mbtaMapPanel.add(downtownCrossingRed);
		
		Station southStation = new Station("South Station", RED);
		southStation.setBounds(446, 295, 34, 32);
		mbtaMapPanel.add(southStation);
		
		Station broadway = new Station("Broadway", RED);
		broadway.setBounds(494, 295, 34, 32);
		mbtaMapPanel.add(broadway);
		
		Station andrew = new Station("Andrew", RED);
		andrew.setBounds(542, 295, 34, 32);
		mbtaMapPanel.add(andrew);
		
		Station jfk = new Station("JFK/UMass", RED);
		jfk.setBounds(590, 295, 34, 32);
		mbtaMapPanel.add(jfk);
		
		Station savinHill = new Station("Savin Hill", RED);
		savinHill.setBounds(638, 295, 34, 32);
		mbtaMapPanel.add(savinHill);
		
		Station fieldsCorner = new Station("Fields Corner", RED);
		fieldsCorner.setBounds(686, 295, 34, 32);
		mbtaMapPanel.add(fieldsCorner);
		
		Station shawmut = new Station("Shawmut", RED);
		shawmut.setBounds(734, 295, 34, 32);
		mbtaMapPanel.add(shawmut);
		
		Station ashmont = new Station("Ashmont", RED);
		ashmont.setBounds(782, 295, 34, 32);
		mbtaMapPanel.add(ashmont);
		
		Station northQuincy = new Station("North Quincy", RED);
		northQuincy.setBounds(830, 295, 34, 32);
		mbtaMapPanel.add(northQuincy);
		
		Station wollaston = new Station("Wollaston", RED);
		wollaston.setBounds(878, 295, 34, 32);
		mbtaMapPanel.add(wollaston);
		
		Station quincyCenter = new Station("Quincy Center", RED);
		quincyCenter.setBounds(926, 295, 34, 32);
		mbtaMapPanel.add(quincyCenter);
		
		Station quincyAdams = new Station("Quincy Adams", RED);
		quincyAdams.setBounds(974, 295, 34, 32);
		mbtaMapPanel.add(quincyAdams);
		
		Station braintree = new Station("Braintree", RED);
		braintree.setBounds(1022, 295, 34, 32);
		mbtaMapPanel.add(braintree);
		
		// ^ Last red line stop
		
		// v First <---- red line train
		
		HashMap<String, Train> red_line_map = MyMbta.getCurrentLocationHash("Red", this.location);
		
		Canvas canvas_77 = new Canvas();
		canvas_77.setBounds(34, 242, 20, 20);
		mbtaMapPanel.add(canvas_77);
		if(red_line_map.get("70061") != null){
			canvas_77.setBackground(Color.BLACK);
		}
		
		Canvas canvas_78 = new Canvas();
		canvas_78.setBounds(88, 242, 20, 20);
		mbtaMapPanel.add(canvas_78);
		if(red_line_map.get("70064") != null){
			canvas_78.setBackground(Color.BLACK);
		}
		
		Canvas canvas_79 = new Canvas();
		canvas_79.setBounds(138, 242, 20, 20);
		mbtaMapPanel.add(canvas_79);
		if(red_line_map.get("70066") != null){
			canvas_79.setBackground(Color.BLACK);
		}
		
		Canvas canvas_80 = new Canvas();
		canvas_80.setBounds(184, 242, 20, 20);
		mbtaMapPanel.add(canvas_80);
		if(red_line_map.get("70068") != null){
			canvas_80.setBackground(Color.BLACK);
		}
		
		Canvas canvas_81 = new Canvas();
		canvas_81.setBounds(231, 242, 20, 20);
		mbtaMapPanel.add(canvas_81);
		if(red_line_map.get("70070") != null){
			canvas_81.setBackground(Color.BLACK);
		}
		
		Canvas canvas_82 = new Canvas();
		canvas_82.setBounds(284, 242, 20, 20);
		mbtaMapPanel.add(canvas_82);
		if(red_line_map.get("70072") != null){
			canvas_82.setBackground(Color.BLACK);
		}
		
		Canvas canvas_83 = new Canvas();
		canvas_83.setBounds(329, 242, 20, 20);
		mbtaMapPanel.add(canvas_83);
		if(red_line_map.get("70074") != null){
			canvas_83.setBackground(Color.BLACK);
		}
		
		Canvas canvas_84 = new Canvas();
		canvas_84.setBounds(377, 242, 20, 20);
		mbtaMapPanel.add(canvas_84);
		if(red_line_map.get("70076") != null){
			canvas_84.setBackground(Color.BLACK);
		}
		
		Canvas canvas_85 = new Canvas();
		canvas_85.setBounds(428, 242, 20, 20);
		mbtaMapPanel.add(canvas_85);
		if(red_line_map.get("70078") != null){
			canvas_85.setBackground(Color.BLACK);
		}
		
		Canvas canvas_86 = new Canvas();
		canvas_86.setBounds(482, 242, 20, 20);
		mbtaMapPanel.add(canvas_86);
		if(red_line_map.get("70080") != null){
			canvas_86.setBackground(Color.BLACK);
		}
		
		Canvas canvas_87 = new Canvas();
		canvas_87.setBounds(529, 242, 20, 20);
		mbtaMapPanel.add(canvas_87);
		if(red_line_map.get("70082") != null){
			canvas_87.setBackground(Color.BLACK);
		}
		
		Canvas canvas_88 = new Canvas();
		canvas_88.setBounds(568, 242, 20, 20);
		mbtaMapPanel.add(canvas_88);
		if(red_line_map.get("70084") != null){
			canvas_88.setBackground(Color.BLACK);
		}
		
		Canvas canvas_89 = new Canvas();
		canvas_89.setBounds(615, 242, 20, 20);
		mbtaMapPanel.add(canvas_89);
		if(red_line_map.get("70086") != null){
			canvas_89.setBackground(Color.BLACK);
		}
		
		Canvas canvas_90 = new Canvas();
		canvas_90.setBounds(663, 242, 20, 20);
		mbtaMapPanel.add(canvas_90);
		if(red_line_map.get("70088") != null){
			canvas_90.setBackground(Color.BLACK);
		}
		
		Canvas canvas_91 = new Canvas();
		canvas_91.setBounds(713, 242, 20, 20);
		mbtaMapPanel.add(canvas_91);
		if(red_line_map.get("70090") != null){
			canvas_91.setBackground(Color.BLACK);
		}
		
		Canvas canvas_92 = new Canvas();
		canvas_92.setBounds(763, 242, 20, 20);
		mbtaMapPanel.add(canvas_92);
		if(red_line_map.get("70092") != null){
			canvas_92.setBackground(Color.BLACK);
		}
		
		Canvas canvas_93 = new Canvas();
		canvas_93.setBounds(808, 242, 20, 20);
		mbtaMapPanel.add(canvas_93);
		if(red_line_map.get("70096") != null){
			canvas_93.setBackground(Color.BLACK);
		}
		
		Canvas canvas_94 = new Canvas();
		canvas_94.setBounds(859, 242, 20, 20);
		mbtaMapPanel.add(canvas_94);
		if(red_line_map.get("70098") != null){
			canvas_94.setBackground(Color.BLACK);
		}
		
		Canvas canvas_95 = new Canvas();
		canvas_95.setBounds(909, 242, 20, 20);
		mbtaMapPanel.add(canvas_95);
		if(red_line_map.get("70100") != null){
			canvas_95.setBackground(Color.BLACK);
		}
		
		Canvas canvas_117 = new Canvas();
		canvas_117.setBounds(959, 242, 20, 20);
		mbtaMapPanel.add(canvas_117);
		if(red_line_map.get("70102") != null){
			canvas_117.setBackground(Color.BLACK);
		}
		
		Canvas canvas_122 = new Canvas();
		canvas_122.setBounds(999, 242, 20, 20);
		mbtaMapPanel.add(canvas_122);
		if(red_line_map.get("70104") != null){
			canvas_122.setBackground(Color.BLACK);
		}
		
		// ^ Last <--- red line train
		
		// v First ---> red line train
		
		Canvas canvas_96 = new Canvas();
		canvas_96.setBounds(34, 352, 20, 20);
		mbtaMapPanel.add(canvas_96);
		if(red_line_map.get("70063") != null){
			canvas_96.setBackground(Color.BLACK);
		}
		
		Canvas canvas_97 = new Canvas();
		canvas_97.setBounds(88, 352, 20, 20);
		mbtaMapPanel.add(canvas_97);
		if(red_line_map.get("70065") != null){
			canvas_97.setBackground(Color.BLACK);
		}
		
		Canvas canvas_98 = new Canvas();
		canvas_98.setBounds(138, 352, 20, 20);
		mbtaMapPanel.add(canvas_98);
		if(red_line_map.get("70067") != null){
			canvas_98.setBackground(Color.BLACK);
		}
		
		Canvas canvas_99 = new Canvas();
		canvas_99.setBounds(184, 352, 20, 20);
		mbtaMapPanel.add(canvas_99);
		if(red_line_map.get("70069") != null){
			canvas_99.setBackground(Color.BLACK);
		}
		
		Canvas canvas_100 = new Canvas();
		canvas_100.setBounds(231, 352, 20, 20);
		mbtaMapPanel.add(canvas_100);
		if(red_line_map.get("70071") != null){
			canvas_100.setBackground(Color.BLACK);
		}
		
		Canvas canvas_101 = new Canvas();
		canvas_101.setBounds(284, 352, 20, 20);
		mbtaMapPanel.add(canvas_101);
		if(red_line_map.get("70073") != null){
			canvas_101.setBackground(Color.BLACK);
		}
		
		Canvas canvas_102 = new Canvas();
		canvas_102.setBounds(329, 352, 20, 20);
		mbtaMapPanel.add(canvas_102);
		if(red_line_map.get("70075") != null){
			canvas_102.setBackground(Color.BLACK);
		}
		
		Canvas canvas_103 = new Canvas();
		canvas_103.setBounds(377, 352, 20, 20);
		mbtaMapPanel.add(canvas_103);
		if(red_line_map.get("70077") != null){
			canvas_103.setBackground(Color.BLACK);
		}
		
		Canvas canvas_104 = new Canvas();
		canvas_104.setBounds(428, 352, 20, 20);
		mbtaMapPanel.add(canvas_104);
		if(red_line_map.get("70079") != null){
			canvas_104.setBackground(Color.BLACK);
		}
		
		Canvas canvas_105 = new Canvas();
		canvas_105.setBounds(478, 352, 20, 20);
		mbtaMapPanel.add(canvas_105);
		if(red_line_map.get("70081") != null){
			canvas_105.setBackground(Color.BLACK);
		}
		
		Canvas canvas_106 = new Canvas();
		canvas_106.setBounds(526, 352, 20, 20);
		mbtaMapPanel.add(canvas_106);
		if(red_line_map.get("70083") != null){
			canvas_106.setBackground(Color.BLACK);
		}
		
		// JFK split line
		Canvas canvas_107 = new Canvas();
		canvas_107.setBounds(568, 352, 20, 20);
		mbtaMapPanel.add(canvas_107);
		if(red_line_map.get("70085") != null || red_line_map.get("70095") != null){
			canvas_107.setBackground(Color.BLACK);
		}
		
		// Savin hill
		Canvas canvas_108 = new Canvas();
		canvas_108.setBounds(615, 352, 20, 20);
		mbtaMapPanel.add(canvas_108);
		if(red_line_map.get("70087") != null){
			canvas_108.setBackground(Color.BLACK);
		}
		
		Canvas canvas_109 = new Canvas();
		canvas_109.setBounds(663, 352, 20, 20);
		mbtaMapPanel.add(canvas_109);
		if(red_line_map.get("70089") != null){
			canvas_109.setBackground(Color.BLACK);
		}
		
		Canvas canvas_110 = new Canvas();
		canvas_110.setBounds(713, 352, 20, 20);
		mbtaMapPanel.add(canvas_110);
		if(red_line_map.get("70091") != null){
			canvas_110.setBackground(Color.BLACK);
		}
		
		// Ashmont
		Canvas canvas_111 = new Canvas();
		canvas_111.setBounds(763, 352, 20, 20);
		mbtaMapPanel.add(canvas_111);
		if(red_line_map.get("70093") != null){
			canvas_111.setBackground(Color.BLACK);
		}
		
		// North quincy
		Canvas canvas_112 = new Canvas();
		canvas_112.setBounds(808, 352, 20, 20);
		mbtaMapPanel.add(canvas_112);
		if(red_line_map.get("70097") != null){
			canvas_112.setBackground(Color.BLACK);
		}
		
		Canvas canvas_113 = new Canvas();
		canvas_113.setBounds(859, 352, 20, 20);
		mbtaMapPanel.add(canvas_113);
		if(red_line_map.get("70099") != null){
			canvas_113.setBackground(Color.BLACK);
		}
		
		Canvas canvas_114 = new Canvas();
		canvas_114.setBounds(909, 352, 20, 20);
		mbtaMapPanel.add(canvas_114);
		if(red_line_map.get("70101") != null){
			canvas_114.setBackground(Color.BLACK);
		}
		
		Canvas canvas_116 = new Canvas();
		canvas_116.setBounds(959, 352, 20, 20);
		mbtaMapPanel.add(canvas_116);
		if(red_line_map.get("70103") != null){
			canvas_116.setBackground(Color.BLACK);
		}
		
		// braintree
		Canvas canvas_121 = new Canvas();
		canvas_121.setBounds(998, 352, 20, 20);
		mbtaMapPanel.add(canvas_121);
		if(red_line_map.get("70105") != null){
			canvas_116.setBackground(Color.BLACK);
		}
		
		JLabel lblAlewife = new JLabel("Alewife");
		lblAlewife.setBounds(6, 390, 61, 16);
		mbtaMapPanel.add(lblAlewife);
		
		JLabel lblDavis = new JLabel("Davis");
		lblDavis.setBounds(45, 403, 61, 16);
		mbtaMapPanel.add(lblDavis);
		
		JLabel lblPorter = new JLabel("Porter");
		lblPorter.setBounds(98, 390, 61, 16);
		mbtaMapPanel.add(lblPorter);
		
		JLabel lblHarvard = new JLabel("Harvard");
		lblHarvard.setBounds(138, 403, 61, 16);
		mbtaMapPanel.add(lblHarvard);
		
		JLabel lblCentral = new JLabel("Central");
		lblCentral.setBounds(197, 390, 61, 16);
		mbtaMapPanel.add(lblCentral);
		
		JLabel lblKendall = new JLabel("Kendall");
		lblKendall.setBounds(254, 403, 61, 16);
		mbtaMapPanel.add(lblKendall);
		
		JLabel lblCharles = new JLabel("Charles");
		lblCharles.setBounds(302, 390, 61, 16);
		mbtaMapPanel.add(lblCharles);
		
		JLabel lblPark = new JLabel("Park");
		lblPark.setBounds(350, 403, 61, 16);
		mbtaMapPanel.add(lblPark);
		
		JLabel lblDowntown = new JLabel("Downtown");
		lblDowntown.setBounds(392, 390, 70, 16);
		mbtaMapPanel.add(lblDowntown);
		
		JLabel lblSouthStation = new JLabel("South Station");
		lblSouthStation.setBounds(443, 403, 87, 16);
		mbtaMapPanel.add(lblSouthStation);
		
		JLabel lblBroadway = new JLabel("Broadway");
		lblBroadway.setBounds(494, 390, 61, 16);
		mbtaMapPanel.add(lblBroadway);
		
		JLabel lblAndrew = new JLabel("Andrew");
		lblAndrew.setBounds(541, 403, 61, 16);
		mbtaMapPanel.add(lblAndrew);
		
		JLabel lblJfk = new JLabel("JFK");
		lblJfk.setBounds(588, 390, 61, 16);
		mbtaMapPanel.add(lblJfk);
		
		JLabel lblSavinHill = new JLabel("Savin Hill");
		lblSavinHill.setBounds(627, 403, 61, 16);
		mbtaMapPanel.add(lblSavinHill);
		
		JLabel lblFieldsCorner = new JLabel("Fields Corner");
		lblFieldsCorner.setBounds(674, 390, 88, 16);
		mbtaMapPanel.add(lblFieldsCorner);
		
		JLabel lblShawmut = new JLabel("Shawmut");
		lblShawmut.setBounds(728, 403, 61, 16);
		mbtaMapPanel.add(lblShawmut);
		
		JLabel lblAshmont = new JLabel("Ashmont");
		lblAshmont.setBounds(781, 390, 61, 16);
		mbtaMapPanel.add(lblAshmont);
		
		JLabel lblNorthQuincy = new JLabel("North Quincy");
		lblNorthQuincy.setBounds(826, 403, 86, 16);
		mbtaMapPanel.add(lblNorthQuincy);
		
		JLabel lblWollaston = new JLabel("Wollaston");
		lblWollaston.setBounds(871, 390, 75, 16);
		mbtaMapPanel.add(lblWollaston);
		
		JLabel lblQuincyC = new JLabel("Quincy Center");
		lblQuincyC.setBounds(920, 403, 97, 16);
		mbtaMapPanel.add(lblQuincyC);
		
		JLabel lblQuincyA = new JLabel("Quincy Adams");
		lblQuincyA.setBounds(971, 390, 103, 16);
		mbtaMapPanel.add(lblQuincyA);
		
		JLabel lblBraintree = new JLabel("Braintree");
		lblBraintree.setBounds(1029, 403, 61, 16);
		mbtaMapPanel.add(lblBraintree);
		
		JLabel lblBowdoin = new JLabel("Bowdoin");
		lblBowdoin.setBounds(52, 566, 61, 16);
		mbtaMapPanel.add(lblBowdoin);
		
		JLabel lblGovernmentCenter = new JLabel("Government Center");
		lblGovernmentCenter.setBounds(112, 594, 128, 16);
		mbtaMapPanel.add(lblGovernmentCenter);
		
		JLabel lblStateSt_1 = new JLabel("State St");
		lblStateSt_1.setBounds(219, 566, 61, 16);
		mbtaMapPanel.add(lblStateSt_1);
		
		JLabel lblAquarium = new JLabel("Aquarium");
		lblAquarium.setBounds(310, 594, 61, 16);
		mbtaMapPanel.add(lblAquarium);
		
		JLabel lblMaverick = new JLabel("Maverick");
		lblMaverick.setBounds(390, 566, 61, 16);
		mbtaMapPanel.add(lblMaverick);
		
		JLabel lblAirport = new JLabel("Airport");
		lblAirport.setBounds(481, 594, 61, 16);
		mbtaMapPanel.add(lblAirport);
		
		JLabel lblWoodIsland = new JLabel("Wood Island");
		lblWoodIsland.setBounds(554, 566, 79, 16);
		mbtaMapPanel.add(lblWoodIsland);
		
		JLabel lblOrientHeights = new JLabel("Orient Heights");
		lblOrientHeights.setBounds(639, 594, 96, 16);
		mbtaMapPanel.add(lblOrientHeights);
		
		JLabel lblSuffolkDowns = new JLabel("Suffolk Downs");
		lblSuffolkDowns.setBounds(711, 566, 107, 16);
		mbtaMapPanel.add(lblSuffolkDowns);
		
		JLabel lblBeachmont = new JLabel("Beachmont");
		lblBeachmont.setBounds(808, 594, 74, 16);
		mbtaMapPanel.add(lblBeachmont);
		
		JLabel lblRevereBeach = new JLabel("Revere Beach");
		lblRevereBeach.setBounds(893, 566, 85, 16);
		mbtaMapPanel.add(lblRevereBeach);
		
		JLabel lblWonderland = new JLabel("Wonderland");
		lblWonderland.setBounds(999, 594, 91, 16);
		mbtaMapPanel.add(lblWonderland);
		
		JRadioButton rdbtnStationMode = new JRadioButton("Station Mode");
		rdbtnStationMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mbtaMapPanel.setMode(Mode.STATION);
			}
		});
		rdbtnStationMode.setBounds(16, 649, 116, 23); 
		mbtaMapPanel.add(rdbtnStationMode);
		
		JRadioButton rdbtnRouteMode = new JRadioButton("Route Mode");
		rdbtnRouteMode.setSelected(true);
		rdbtnRouteMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mbtaMapPanel.setMode(Mode.ORDERED_ROUTE);
			}
		});
		rdbtnRouteMode.setBounds(147, 649, 112, 23);
		mbtaMapPanel.add(rdbtnRouteMode);
		
		JRadioButton rdbtnUnorderedRouteMode = new JRadioButton("Unordered Route Mode");
		rdbtnUnorderedRouteMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mbtaMapPanel.setMode(Mode.UNORDERED_ROUTE);
			}
		});
		rdbtnUnorderedRouteMode.setBounds(273, 649, 191, 23);
		mbtaMapPanel.add(rdbtnUnorderedRouteMode);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnStationMode);
		group.add(rdbtnRouteMode);
		group.add(rdbtnUnorderedRouteMode);
		
		JButton btnGetDirections = new JButton("Get Directions");
		btnGetDirections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Route route = MyMbta.getRoute(mbtaMapPanel, location);
				JOptionPane.showMessageDialog(mbtaMapPanel, "Directions! \n "+ route);
			}
		});
		btnGetDirections.setBounds(581, 648, 117, 29);
		mbtaMapPanel.add(btnGetDirections);
		
		
	}
}

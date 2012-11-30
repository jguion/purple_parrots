package softwaredev.purpleparrots.gui;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.*;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import softwaredev.purpleparrots.MyMbta;
import softwaredev.purpleparrots.Route;
import softwaredev.purpleparrots.Train;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TrainLocation extends JFrame {

	private MbtaMap mbtaMapPanel;
	private String ORANGE = "Orange";
	private String RED = "Red";
	private String BLUE = "Blue";
	private String location;
	private TrainUpdater updater;
	private Timer timer = new Timer();
	private ArrayList<UITrain> blueLineTrains;
	private ArrayList<UITrain> orangeLineTrains;
	private ArrayList<UITrain> redLineTrains;	

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

	class ImagePanel extends JComponent {
		private Image image;
		public ImagePanel(Image image) {
			this.image = image;
		}
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
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
		this.location = MyMbta.test;
		setUndecorated(false);
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setMinimumSize(new Dimension(1300, 700));
		setMaximumSize(new Dimension(1300, 700));


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mbtaMapPanel = new MbtaMap();
		mbtaMapPanel.setBackground(Color.WHITE);
		mbtaMapPanel.setOpaque(false);
		mbtaMapPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mbtaMapPanel);

		mbtaMapPanel.setLayout(null);

		// v first blue line stop

		Station bowdoin = new Station("Bowdoin", BLUE);
		bowdoin.setBounds(52, 478, 36, 32);
		mbtaMapPanel.add(bowdoin);

		Station governmentCenter = new Station("Government Center", BLUE);
		governmentCenter.setBounds(138, 478, 36, 32);
		mbtaMapPanel.add(governmentCenter);

		Station stateStBlue = new Station("State Street", BLUE);
		stateStBlue.setBounds(224, 478, 36, 32);
		mbtaMapPanel.add(stateStBlue);

		Station aquarium = new Station("Aquarium", BLUE);
		aquarium.setBounds(310, 478, 36, 32);
		mbtaMapPanel.add(aquarium);

		Station maverick = new Station("Maverick", BLUE);
		maverick.setBounds(396, 478, 36, 32);
		mbtaMapPanel.add(maverick);

		Station airport = new Station("Airport", BLUE);
		airport.setBounds(482, 478, 36, 32);
		mbtaMapPanel.add(airport);

		Station woodIsland = new Station("Wood Island", BLUE);
		woodIsland.setBounds(568, 478, 36, 32);
		mbtaMapPanel.add(woodIsland);

		Station orientHeight = new Station("Orient Height", BLUE);
		orientHeight.setBounds(654, 478, 36, 32);
		mbtaMapPanel.add(orientHeight);

		Station suffolkDowns = new Station("Suffolk Downs", BLUE);
		suffolkDowns.setBounds(740, 478, 36, 32);
		mbtaMapPanel.add(suffolkDowns);

		Station beachmont = new Station("Beachmont", BLUE);
		beachmont.setBounds(826, 478, 36, 32);
		mbtaMapPanel.add(beachmont);

		Station revereBeach = new Station("Revere Beach", BLUE);
		revereBeach.setBounds(912, 478, 36, 32);
		mbtaMapPanel.add(revereBeach);

		Station wonderland = new Station("Wonderland", BLUE);
		wonderland.setBounds(998, 478, 36, 32);
		mbtaMapPanel.add(wonderland);

		// ^ last blue line stop

		// v first <--- blue line train (yes, I know they're reverse ordered)
		HashMap<String, Train> blue_line_map = MyMbta.getCurrentLocationMap("Blue", this.location);
		blueLineTrains = new ArrayList<UITrain>();

		UITrain canvas_145 = new UITrain("70037");
		canvas_145.setBounds(110, 450, 18, 18);
		mbtaMapPanel.add(canvas_145);
		blueLineTrains.add(canvas_145);

		UITrain canvas_144 = new UITrain("70039");
		canvas_144.setBounds(200, 450, 18, 18);
		mbtaMapPanel.add(canvas_144);
		blueLineTrains.add(canvas_144);

		UITrain canvas_143 = new UITrain("70041");
		canvas_143.setBounds(280, 450, 18, 18);
		mbtaMapPanel.add(canvas_143);
		blueLineTrains.add(canvas_143);

		UITrain canvas_142 = new UITrain("70043");
		canvas_142.setBounds(375, 450, 18, 18);
		mbtaMapPanel.add(canvas_142);
		blueLineTrains.add(canvas_142);

		UITrain canvas_141 = new UITrain("70045");
		canvas_141.setBounds(455, 450, 18, 18);
		mbtaMapPanel.add(canvas_141);
		blueLineTrains.add(canvas_141);

		UITrain canvas_140 = new UITrain("70047");
		canvas_140.setBounds(540, 450, 18, 18);
		mbtaMapPanel.add(canvas_140);
		blueLineTrains.add(canvas_140);

		UITrain canvas_139 = new UITrain("70049");
		canvas_139.setBounds(630, 450, 18, 18);
		mbtaMapPanel.add(canvas_139);
		blueLineTrains.add(canvas_139);

		UITrain canvas_138 = new UITrain("70051");
		canvas_138.setBounds(720, 450, 18, 18);
		mbtaMapPanel.add(canvas_138);
		blueLineTrains.add(canvas_138);

		UITrain canvas_137 = new UITrain("70053");
		canvas_137.setBounds(800, 450, 18, 18);
		mbtaMapPanel.add(canvas_137);
		blueLineTrains.add(canvas_137);

		UITrain canvas_136 = new UITrain("70055");
		canvas_136.setBounds(880, 450, 18, 18);
		mbtaMapPanel.add(canvas_136);
		blueLineTrains.add(canvas_136);

		UITrain canvas_135 = new UITrain("70057");
		canvas_135.setBounds(975, 450, 18, 18);
		mbtaMapPanel.add(canvas_135);
		blueLineTrains.add(canvas_135);

		// ^ last <--- blue line train

		// v first ---> blue line train

		UITrain canvas_146 = new UITrain("70040");
		canvas_146.setBounds(110, 518, 18, 18);
		mbtaMapPanel.add(canvas_146);
		blueLineTrains.add(canvas_146);

		UITrain canvas_147 = new UITrain("70042");
		canvas_147.setBounds(200, 518, 18, 18);
		mbtaMapPanel.add(canvas_147);
		blueLineTrains.add(canvas_147);

		UITrain canvas_148 = new UITrain("70044");
		canvas_148.setBounds(280, 518, 18, 18);
		mbtaMapPanel.add(canvas_148);
		blueLineTrains.add(canvas_148);

		UITrain canvas_149 = new UITrain("70046");
		canvas_149.setBounds(375, 518, 18, 18);
		mbtaMapPanel.add(canvas_149);
		blueLineTrains.add(canvas_149);

		UITrain canvas_150 = new UITrain("70048");
		canvas_150.setBounds(455, 518, 18, 18);
		mbtaMapPanel.add(canvas_150);
		blueLineTrains.add(canvas_150);

		UITrain canvas_151 = new UITrain("70050");
		canvas_151.setBounds(540, 518, 18, 18);
		mbtaMapPanel.add(canvas_151);
		blueLineTrains.add(canvas_151);

		UITrain canvas_152 = new UITrain("70052");
		canvas_152.setBounds(630, 518, 18, 18);
		mbtaMapPanel.add(canvas_152);
		blueLineTrains.add(canvas_152);

		UITrain canvas_153 = new UITrain("70054");
		canvas_153.setBounds(720, 518, 18, 18);
		mbtaMapPanel.add(canvas_153);
		blueLineTrains.add(canvas_153);

		UITrain canvas_154 = new UITrain("70056");
		canvas_154.setBounds(800, 518, 18, 18);
		mbtaMapPanel.add(canvas_154);
		blueLineTrains.add(canvas_154);

		UITrain canvas_155 = new UITrain("70058");
		canvas_155.setBounds(880, 518, 18, 18);
		mbtaMapPanel.add(canvas_155);
		blueLineTrains.add(canvas_155);

		UITrain canvas_156 = new UITrain("70060");
		canvas_156.setBounds(975, 518, 18, 18);
		mbtaMapPanel.add(canvas_156);
		blueLineTrains.add(canvas_156);

		// ^ last ---> blue line train

		Canvas canvas_119 = new Canvas();
		canvas_119.setBounds(822, 287, 5, 45);
		canvas_119.setBackground(Color.BLACK);
		mbtaMapPanel.add(canvas_119);

		Canvas canvas_118 = new Canvas();
		canvas_118.setBounds(630, 287, 5, 45);
		canvas_118.setBackground(Color.BLACK);
		mbtaMapPanel.add(canvas_118);

		Station oakGrove = new Station("Oak Grove", ORANGE);
		oakGrove.setBounds(19, 63, 36, 32);
		mbtaMapPanel.add(oakGrove);

		Station maldenCenter = new Station("Malden Center", ORANGE);
		maldenCenter.setBounds(72, 63, 36, 32);
		mbtaMapPanel.add(maldenCenter);

		Station wellington = new Station("Wellington", ORANGE);
		wellington.setBounds(125, 63, 36, 32);
		mbtaMapPanel.add(wellington);

		Station sullivanSquare = new Station("Sullivan", ORANGE);
		sullivanSquare.setBounds(178, 63, 36, 32);
		mbtaMapPanel.add(sullivanSquare);

		Station communityCollege = new Station("Community College", ORANGE);
		communityCollege.setBounds(231, 63, 36, 32);
		mbtaMapPanel.add(communityCollege);

		Station northStaion = new Station("North Station", ORANGE);
		northStaion.setBounds(284, 63, 36, 32);
		mbtaMapPanel.add(northStaion);

		Station haymarket = new Station("Haymarket", ORANGE);
		haymarket.setBounds(337, 63, 36, 32);
		mbtaMapPanel.add(haymarket);

		Station stateSt = new Station("State Street", ORANGE);
		stateSt.setBounds(390, 63, 36, 32);
		mbtaMapPanel.add(stateSt);

		Station downtownCrossing = new Station("Downtown Crossing", ORANGE);
		downtownCrossing.setBounds(443, 63, 36, 32);
		mbtaMapPanel.add(downtownCrossing);

		Station chinatown = new Station("Chinatown", ORANGE);
		chinatown.setBounds(496, 63, 36, 32);
		mbtaMapPanel.add(chinatown);

		Station tufts = new Station("Tufts Medical", ORANGE);
		tufts.setBounds(549, 63, 36, 32);
		mbtaMapPanel.add(tufts);

		Station backBay = new Station("Back Bay", ORANGE);
		backBay.setBounds(602, 63, 36, 32);
		mbtaMapPanel.add(backBay);

		Station massAve = new Station("Mass Ave", ORANGE);
		massAve.setBounds(655, 63, 36, 32);
		mbtaMapPanel.add(massAve);

		Station ruggles = new Station("Ruggles", ORANGE);
		ruggles.setBounds(708, 63, 36, 32);
		ruggles.setBackground(Color.WHITE);
		mbtaMapPanel.add(ruggles);

		Station roxburyCrossing = new Station("Roxbury Crossing", ORANGE);
		roxburyCrossing.setBounds(761, 63, 36, 32);
		mbtaMapPanel.add(roxburyCrossing);

		Station jacksonSquare = new Station("Jackson Square", ORANGE);
		jacksonSquare.setBounds(814, 63, 36, 32);
		mbtaMapPanel.add(jacksonSquare);

		Station stonyBrook = new Station("Stony Brook", ORANGE);
		stonyBrook.setBounds(867, 63, 36, 32);
		mbtaMapPanel.add(stonyBrook);

		Station greenSt = new Station("Green Street", ORANGE);
		greenSt.setBounds(920, 63, 36, 32);
		mbtaMapPanel.add(greenSt);

		Station forestHills = new Station("Forest Hills", ORANGE);
		forestHills.setBounds(973, 63, 36, 32);
		mbtaMapPanel.add(forestHills);

		Canvas orangeLineEnd = new Canvas();
		orangeLineEnd.setBounds(1072, 30, 18, 18);
		orangeLineEnd.setBackground(Color.WHITE);
		mbtaMapPanel.add(orangeLineEnd);

		//ORANGE LINE STOPS 20 - 51
		HashMap<String, Train> orange_line_map = MyMbta.getCurrentLocationMap("Orange", this.location);
		orangeLineTrains = new ArrayList<UITrain>();

		JPanel FH_OG_panel = new JPanel();
		FH_OG_panel.setOpaque(false);
		FH_OG_panel.setBackground(Color.WHITE);
		FH_OG_panel.setBounds(0, 0, 1100, 100);
		mbtaMapPanel.add(FH_OG_panel);
		FH_OG_panel.setLayout(null);

		UITrain canvas_20 = new UITrain("70036");
		canvas_20.setBounds(63, 37, 18, 18);
		FH_OG_panel.add(canvas_20);
		orangeLineTrains.add(canvas_20);

		UITrain canvas_21 = new UITrain("70035");
		canvas_21.setBounds(118, 37, 18, 18);
		FH_OG_panel.add(canvas_21);
		orangeLineTrains.add(canvas_21);

		UITrain canvas_22 = new UITrain("70033");
		canvas_22.setBounds(173, 37, 18, 18);
		FH_OG_panel.add(canvas_22);
		orangeLineTrains.add(canvas_22);

		UITrain canvas_23 = new UITrain("70031");
		canvas_23.setBounds(222, 37, 18, 18);
		FH_OG_panel.add(canvas_23);
		orangeLineTrains.add(canvas_23);

		UITrain canvas_24 = new UITrain("70029");
		canvas_24.setBounds(273, 37, 18, 18);
		FH_OG_panel.add(canvas_24);
		orangeLineTrains.add(canvas_24);

		UITrain canvas_25 = new UITrain("70027");
		canvas_25.setBounds(329, 37, 18, 18);
		FH_OG_panel.add(canvas_25);
		orangeLineTrains.add(canvas_25);

		UITrain canvas_26 = new UITrain("70025");
		canvas_26.setBounds(382, 37, 18, 18);
		FH_OG_panel.add(canvas_26);
		orangeLineTrains.add(canvas_26);

		UITrain canvas_27 = new UITrain("70023");
		canvas_27.setBounds(432, 37, 18, 18);
		FH_OG_panel.add(canvas_27);
		orangeLineTrains.add(canvas_27);

		UITrain canvas_28 = new UITrain("70021");
		canvas_28.setBounds(485, 37, 18, 18);
		FH_OG_panel.add(canvas_28);
		orangeLineTrains.add(canvas_28);

		UITrain canvas_29 = new UITrain("70019");
		canvas_29.setBounds(540, 37, 18, 18);
		FH_OG_panel.add(canvas_29);
		orangeLineTrains.add(canvas_29);

		UITrain canvas_30 = new UITrain("70017");
		canvas_30.setBounds(590, 37, 18, 18);
		FH_OG_panel.add(canvas_30);
		orangeLineTrains.add(canvas_30);

		UITrain canvas_31 = new UITrain("70015");
		canvas_31.setBounds(643, 37, 18, 18);
		FH_OG_panel.add(canvas_31);
		orangeLineTrains.add(canvas_31);

		UITrain canvas_32 = new UITrain("70013");
		canvas_32.setBounds(701, 37, 18, 18);
		FH_OG_panel.add(canvas_32);
		orangeLineTrains.add(canvas_32);

		UITrain canvas_33 = new UITrain("70011");
		canvas_33.setBounds(754, 37, 18, 18);
		FH_OG_panel.add(canvas_33);
		orangeLineTrains.add(canvas_33);

		UITrain canvas_34 = new UITrain("70009");
		canvas_34.setBounds(804, 37, 18, 18);
		FH_OG_panel.add(canvas_34);
		orangeLineTrains.add(canvas_34);

		UITrain canvas_35 = new UITrain("70007");
		canvas_35.setBounds(859, 37, 18, 18);
		FH_OG_panel.add(canvas_35);
		orangeLineTrains.add(canvas_35);

		UITrain canvas_36 = new UITrain("70005");
		canvas_36.setBounds(912, 37, 18, 18);
		FH_OG_panel.add(canvas_36);
		orangeLineTrains.add(canvas_36);

		UITrain canvas_37 = new UITrain("70003");
		canvas_37.setBounds(937, 5, 18, 18);
		FH_OG_panel.add(canvas_37);
		orangeLineTrains.add(canvas_37);

		UITrain FH_OG_start = new UITrain("70001");
		canvas_37.setBounds(1026, 37, 18, 18);
		FH_OG_panel.add(FH_OG_start);
		orangeLineTrains.add(FH_OG_start);

		UITrain OG_FH_start = new UITrain("70036");
		OG_FH_start.setBounds(63, 103, 18, 18);
		mbtaMapPanel.add(OG_FH_start);
		orangeLineTrains.add(OG_FH_start);

		UITrain canvas_53 = new UITrain("70034");
		canvas_53.setBounds(118, 103, 18, 18);
		mbtaMapPanel.add(canvas_53);
		orangeLineTrains.add(canvas_53);

		UITrain canvas_54 = new UITrain("70032");
		canvas_54.setBounds(173, 103, 18, 18);
		mbtaMapPanel.add(canvas_54);
		orangeLineTrains.add(canvas_54);

		UITrain canvas_39 = new UITrain("70030");
		canvas_39.setBounds(222, 103, 18, 18);
		mbtaMapPanel.add(canvas_39);
		orangeLineTrains.add(canvas_39);

		UITrain canvas_40 = new UITrain("70028");
		canvas_40.setBounds(273, 103, 18, 18);
		mbtaMapPanel.add(canvas_40);
		orangeLineTrains.add(canvas_40);

		UITrain canvas_41 = new UITrain("70026");
		canvas_41.setBounds(329, 103, 18, 18);
		mbtaMapPanel.add(canvas_41);
		orangeLineTrains.add(canvas_41);

		UITrain canvas_42 = new UITrain("70024");
		canvas_42.setBounds(382, 103, 18, 18);
		mbtaMapPanel.add(canvas_42);
		orangeLineTrains.add(canvas_42);

		UITrain canvas_43 = new UITrain("70022");
		canvas_43.setBounds(432, 103, 18, 18);
		mbtaMapPanel.add(canvas_43);
		orangeLineTrains.add(canvas_43);

		UITrain canvas_44 = new UITrain("70020");
		canvas_44.setBounds(485, 103, 18, 18);
		mbtaMapPanel.add(canvas_44);
		orangeLineTrains.add(canvas_44);

		UITrain canvas_45 = new UITrain("70018");
		canvas_45.setBounds(540, 103, 18, 18);
		mbtaMapPanel.add(canvas_45);
		orangeLineTrains.add(canvas_45);

		UITrain canvas_46 = new UITrain("70016");
		canvas_46.setBounds(590, 103, 18, 18);
		mbtaMapPanel.add(canvas_46);
		orangeLineTrains.add(canvas_46);

		UITrain canvas_47 = new UITrain("70014");
		canvas_47.setBounds(643, 103, 18, 18);
		mbtaMapPanel.add(canvas_47);
		orangeLineTrains.add(canvas_47);

		UITrain canvas_48 = new UITrain("70012");
		canvas_48.setBounds(701, 103, 18, 18);
		mbtaMapPanel.add(canvas_48);
		orangeLineTrains.add(canvas_48);

		UITrain canvas_49 = new UITrain("70010");
		canvas_49.setBounds(754, 103, 18, 18);
		mbtaMapPanel.add(canvas_49);
		orangeLineTrains.add(canvas_49);

		UITrain canvas_50 = new UITrain("70008");
		canvas_50.setBounds(804, 103, 18, 18);
		mbtaMapPanel.add(canvas_50);
		orangeLineTrains.add(canvas_50);

		UITrain canvas_51 = new UITrain("70006");
		canvas_51.setBounds(859, 103, 18, 18);
		mbtaMapPanel.add(canvas_51);
		orangeLineTrains.add(canvas_51);

		UITrain canvas_55 = new UITrain("70004");
		canvas_55.setBounds(912, 103, 18, 18);
		mbtaMapPanel.add(canvas_55);
		orangeLineTrains.add(canvas_55);

		UITrain canvas_56 = new UITrain("70002");
		canvas_56.setBounds(959, 103, 18, 18);
		mbtaMapPanel.add(canvas_56);
		orangeLineTrains.add(canvas_56);

		UITrain canvas_57 = new UITrain("70000");
		canvas_57.setBounds(1026, 103, 18, 18);
		mbtaMapPanel.add(canvas_57);
		orangeLineTrains.add(canvas_57);


		// First red line stop

		Station alewife = new Station("Alewife", RED);
		alewife.setBounds(14, 295, 36, 32);
		mbtaMapPanel.add(alewife);

		Station davis = new Station("Davis", RED);
		davis.setBounds(62, 295, 36, 32);
		mbtaMapPanel.add(davis);

		Station porter = new Station("Porter Square", RED);
		porter.setBounds(110, 295, 36, 32);
		mbtaMapPanel.add(porter);

		Station harvard = new Station("Harvard Square", RED);
		harvard.setBounds(158, 295, 36, 32);
		mbtaMapPanel.add(harvard);

		Station central = new Station("Central Square", RED);
		central.setBounds(206, 295, 36, 32);
		mbtaMapPanel.add(central);

		Station kendall = new Station("Kendall/MIT", RED);
		kendall.setBounds(254, 295, 36, 32);
		mbtaMapPanel.add(kendall);

		Station charles = new Station("Charles/MGH", RED);
		charles.setBounds(302, 295, 36, 32);
		mbtaMapPanel.add(charles);

		Station parkSt = new Station("Park Street", RED);
		parkSt.setBounds(350, 295, 36, 32);
		mbtaMapPanel.add(parkSt);

		Station downtownCrossingRed = new Station("Downtown Crossing", RED);
		downtownCrossingRed.setBounds(398, 295, 36, 32);
		mbtaMapPanel.add(downtownCrossingRed);

		Station southStation = new Station("South Station", RED);
		southStation.setBounds(446, 295, 36, 32);
		mbtaMapPanel.add(southStation);

		Station broadway = new Station("Broadway", RED);
		broadway.setBounds(494, 295, 36, 32);
		mbtaMapPanel.add(broadway);

		Station andrew = new Station("Andrew", RED);
		andrew.setBounds(542, 295, 36, 32);
		mbtaMapPanel.add(andrew);

		Station jfk = new Station("JFK/UMass", RED);
		jfk.setBounds(590, 295, 36, 32);
		mbtaMapPanel.add(jfk);

		Station savinHill = new Station("Savin Hill", RED);
		savinHill.setBounds(638, 295, 36, 32);
		mbtaMapPanel.add(savinHill);

		Station fieldsCorner = new Station("Fields Corner", RED);
		fieldsCorner.setBounds(686, 295, 36, 32);
		mbtaMapPanel.add(fieldsCorner);

		Station shawmut = new Station("Shawmut", RED);
		shawmut.setBounds(734, 295, 36, 32);
		mbtaMapPanel.add(shawmut);

		Station ashmont = new Station("Ashmont", RED);
		ashmont.setBounds(782, 295, 36, 32);
		mbtaMapPanel.add(ashmont);

		Station northQuincy = new Station("North Quincy", RED);
		northQuincy.setBounds(830, 295, 36, 32);
		mbtaMapPanel.add(northQuincy);

		Station wollaston = new Station("Wollaston", RED);
		wollaston.setBounds(878, 295, 36, 32);
		mbtaMapPanel.add(wollaston);

		Station quincyCenter = new Station("Quincy Center", RED);
		quincyCenter.setBounds(926, 295, 36, 32);
		mbtaMapPanel.add(quincyCenter);

		Station quincyAdams = new Station("Quincy Adams", RED);
		quincyAdams.setBounds(974, 295, 36, 32);
		mbtaMapPanel.add(quincyAdams);

		Station braintree = new Station("Braintree", RED);
		braintree.setBounds(1022, 295, 36, 32);
		mbtaMapPanel.add(braintree);

		// ^ Last red line stop

		// v First <---- red line train

		HashMap<String, Train> red_line_map = MyMbta.getCurrentLocationMap("Red", this.location);
		redLineTrains = new ArrayList<UITrain>();

		UITrain canvas_77 = new UITrain("70061");
		canvas_77.setBounds(52, 267, 18, 18);
		mbtaMapPanel.add(canvas_77);
		redLineTrains.add(canvas_77);

		UITrain canvas_78 = new UITrain("70064");
		canvas_78.setBounds(104, 267, 18, 18);
		mbtaMapPanel.add(canvas_78);
		redLineTrains.add(canvas_78);

		UITrain canvas_79 = new UITrain("70066");
		canvas_79.setBounds(152, 267, 18, 18);
		mbtaMapPanel.add(canvas_79);
		redLineTrains.add(canvas_79);

		UITrain canvas_80 = new UITrain("70068");
		canvas_80.setBounds(197, 267, 18, 18);
		mbtaMapPanel.add(canvas_80);
		redLineTrains.add(canvas_80);

		UITrain canvas_81 = new UITrain("70070");
		canvas_81.setBounds(245, 267, 18, 18);
		mbtaMapPanel.add(canvas_81);
		redLineTrains.add(canvas_81);

		UITrain canvas_82 = new UITrain("70072");
		canvas_82.setBounds(294, 267, 18, 18);
		mbtaMapPanel.add(canvas_82);
		redLineTrains.add(canvas_82);

		UITrain canvas_83 = new UITrain("70074");
		canvas_83.setBounds(342, 267, 18, 18);
		mbtaMapPanel.add(canvas_83);
		redLineTrains.add(canvas_83);

		UITrain canvas_84 = new UITrain("70076");
		canvas_84.setBounds(390, 267, 18, 18);
		mbtaMapPanel.add(canvas_84);
		redLineTrains.add(canvas_84);

		UITrain canvas_85 = new UITrain("70078");
		canvas_85.setBounds(437, 267, 18, 18);
		mbtaMapPanel.add(canvas_85);
		redLineTrains.add(canvas_85);

		UITrain canvas_86 = new UITrain("70080");
		canvas_86.setBounds(485, 267, 18, 18);
		mbtaMapPanel.add(canvas_86);
		redLineTrains.add(canvas_86);

		UITrain canvas_87 = new UITrain("70082");
		canvas_87.setBounds(533, 267, 18, 18);
		mbtaMapPanel.add(canvas_87);
		redLineTrains.add(canvas_87);

		UITrain canvas_88 = new UITrain("70084");
		canvas_88.setBounds(582, 267, 18, 18);
		mbtaMapPanel.add(canvas_88);
		redLineTrains.add(canvas_88);

		UITrain canvas_89 = new UITrain("70086");
		canvas_89.setBounds(632, 267, 18, 18);
		mbtaMapPanel.add(canvas_89);
		redLineTrains.add(canvas_89);

		UITrain canvas_90 = new UITrain("70088");
		canvas_90.setBounds(680, 267, 18, 18);
		mbtaMapPanel.add(canvas_90);
		redLineTrains.add(canvas_90);

		UITrain canvas_91 = new UITrain("70090");
		canvas_91.setBounds(730, 267, 18, 18);
		mbtaMapPanel.add(canvas_91);
		redLineTrains.add(canvas_91);

		UITrain canvas_92 = new UITrain("70092");
		canvas_92.setBounds(775, 267, 18, 18);
		mbtaMapPanel.add(canvas_92);
		redLineTrains.add(canvas_92);

		UITrain canvas_93 = new UITrain("70096");
		canvas_93.setBounds(827, 267, 18, 18);
		mbtaMapPanel.add(canvas_93);
		redLineTrains.add(canvas_93);

		UITrain canvas_94 = new UITrain("70098");
		canvas_94.setBounds(874, 267, 18, 18);
		mbtaMapPanel.add(canvas_94);
		redLineTrains.add(canvas_94);

		UITrain canvas_95 = new UITrain("70100");
		canvas_95.setBounds(921, 267, 18, 18);
		mbtaMapPanel.add(canvas_95);
		redLineTrains.add(canvas_95);

		UITrain canvas_117 = new UITrain("70102");
		canvas_117.setBounds(965, 267, 18, 18);
		mbtaMapPanel.add(canvas_117);
		redLineTrains.add(canvas_117);

		UITrain canvas_122 = new UITrain("70104");
		canvas_122.setBounds(1014, 267, 18, 18);
		mbtaMapPanel.add(canvas_122);
		redLineTrains.add(canvas_122);

		// ^ Last <--- red line train

		// v First ---> red line train

		UITrain canvas_96 = new UITrain("70063");
		canvas_96.setBounds(52, 333, 18, 18);
		mbtaMapPanel.add(canvas_96);
		redLineTrains.add(canvas_96);

		UITrain canvas_97 = new UITrain("70065");
		canvas_97.setBounds(104, 333, 18, 18);
		mbtaMapPanel.add(canvas_97);
		redLineTrains.add(canvas_97);

		UITrain canvas_98 = new UITrain("70067");
		canvas_98.setBounds(152, 333, 18, 18);
		mbtaMapPanel.add(canvas_98);
		redLineTrains.add(canvas_98);

		UITrain canvas_99 = new UITrain("70069");
		canvas_99.setBounds(197, 333, 18, 18);
		mbtaMapPanel.add(canvas_99);
		redLineTrains.add(canvas_99);

		UITrain canvas_100 = new UITrain("70071");
		canvas_100.setBounds(245, 333, 18, 18);
		mbtaMapPanel.add(canvas_100);
		redLineTrains.add(canvas_100);

		UITrain canvas_101 = new UITrain("70073");
		canvas_101.setBounds(294, 333, 18, 18);
		mbtaMapPanel.add(canvas_101);
		redLineTrains.add(canvas_101);

		UITrain canvas_102 = new UITrain("70075");
		canvas_102.setBounds(342, 333, 18, 18);
		mbtaMapPanel.add(canvas_102);
		redLineTrains.add(canvas_102);

		UITrain canvas_103 = new UITrain("70077");
		canvas_103.setBounds(390, 333, 18, 18);
		mbtaMapPanel.add(canvas_103);
		redLineTrains.add(canvas_103);

		UITrain canvas_104 = new UITrain("70079");
		canvas_104.setBounds(437, 333, 18, 18);
		mbtaMapPanel.add(canvas_104);
		redLineTrains.add(canvas_104);

		UITrain canvas_105 = new UITrain("70081");
		canvas_105.setBounds(485, 333, 18, 18);
		mbtaMapPanel.add(canvas_105);
		redLineTrains.add(canvas_105);

		UITrain canvas_106 = new UITrain("70083");
		canvas_106.setBounds(533, 333, 18, 18);
		mbtaMapPanel.add(canvas_106);
		redLineTrains.add(canvas_106);

		// JFK split line
		UITrain canvas_107 = new UITrain("70085");
		canvas_107.setBounds(582, 333, 18, 18);
		mbtaMapPanel.add(canvas_107);
		redLineTrains.add(canvas_107);

		// Savin hill
		UITrain canvas_108 = new UITrain("70087");
		canvas_108.setBounds(632, 333, 18, 18);
		mbtaMapPanel.add(canvas_108);
		redLineTrains.add(canvas_108);

		UITrain canvas_109 = new UITrain("70089");
		canvas_109.setBounds(680, 333, 18, 18);
		mbtaMapPanel.add(canvas_109);
		redLineTrains.add(canvas_109);

		UITrain canvas_110 = new UITrain("70091");
		canvas_110.setBounds(730, 333, 18, 18);
		mbtaMapPanel.add(canvas_110);
		redLineTrains.add(canvas_110);

		// Ashmont
		UITrain canvas_111 = new UITrain("70093");
		canvas_111.setBounds(775, 333, 18, 18);
		mbtaMapPanel.add(canvas_111);
		redLineTrains.add(canvas_111);

		// North quincy
		UITrain canvas_112 = new UITrain("70097");
		canvas_112.setBounds(827, 333, 18, 18);
		mbtaMapPanel.add(canvas_112);
		redLineTrains.add(canvas_112);

		UITrain canvas_113 = new UITrain("70099");
		canvas_113.setBounds(874, 333, 18, 18);
		mbtaMapPanel.add(canvas_113);
		redLineTrains.add(canvas_113);

		UITrain canvas_114 = new UITrain("70101");
		canvas_114.setBounds(921, 333, 18, 18);
		mbtaMapPanel.add(canvas_114);
		redLineTrains.add(canvas_114);

		UITrain canvas_116 = new UITrain("70103");
		canvas_116.setBounds(965, 333, 18, 18);
		mbtaMapPanel.add(canvas_116);
		redLineTrains.add(canvas_116);

		// braintree
		UITrain canvas_121 = new UITrain("70105");
		canvas_121.setBounds(1014, 333, 18, 18);
		mbtaMapPanel.add(canvas_121);
		redLineTrains.add(canvas_121);


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
		rdbtnUnorderedRouteMode.setBounds(254, 649, 178, 23);
		mbtaMapPanel.add(rdbtnUnorderedRouteMode);

		updateTrains(orangeLineTrains, redLineTrains, blueLineTrains);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnStationMode);
		group.add(rdbtnRouteMode);
		group.add(rdbtnUnorderedRouteMode);

		final JComboBox arriveDepart = new JComboBox(new String[]{"Leave Now", "Depart At", "Arrive By"});
		arriveDepart.setBounds(432, 649, 137, 20);
		mbtaMapPanel.add(arriveDepart);

		final JSpinner timeOfTrip = new JSpinner( new SpinnerDateModel() );
		timeOfTrip.setEditor(new DateEditor(timeOfTrip, "HH:mm"));
		timeOfTrip.setValue(new Date());
		timeOfTrip.setBounds(569, 649, 66, 20);
		mbtaMapPanel.add(timeOfTrip);

		JButton btnGetDirections = new JButton("Get Directions");
		btnGetDirections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long time = timeOfTripInMilliseconds((Date) timeOfTrip.getValue());
				mbtaMapPanel.setTimeOfTrip(time);
				mbtaMapPanel.setTimeOfTripIndex(arriveDepart.getSelectedIndex());
				Route route = MyMbta.getRoute(mbtaMapPanel, location);
				JOptionPane.showMessageDialog(mbtaMapPanel, "Directions! \n "+ route);
			}
		});
		btnGetDirections.setBounds(663, 646, 117, 29);
		mbtaMapPanel.add(btnGetDirections);


		JRadioButton rdbtnTest = new JRadioButton("Test");
		rdbtnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				location = MyMbta.test;
				updateTrains(orangeLineTrains, redLineTrains, blueLineTrains);
			}
		});
		rdbtnTest.setSelected(true);
		rdbtnTest.setBounds(814, 649, 61, 23);
		mbtaMapPanel.add(rdbtnTest);

		JRadioButton rdbtnLive = new JRadioButton("Live");
		rdbtnLive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				location = MyMbta.http;
				updateTrains(orangeLineTrains, redLineTrains, blueLineTrains);
			}
		});
		rdbtnLive.setBounds(897, 649, 57, 23);
		mbtaMapPanel.add(rdbtnLive);

		ButtonGroup dataGroup = new ButtonGroup();
		dataGroup.add(rdbtnTest);
		dataGroup.add(rdbtnLive);
		
		/**
		 * @author - ryanbigelow
		 */
		
		final List list = new List();
		list.setBounds(1100, 50, 214, 550);

		mbtaMapPanel.add(list);

		Button button = new Button("Add/\nUpdate");
		button.setBounds(1100, 600, 100, 80);
		mbtaMapPanel.add(button);

		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mbtaMapPanel.clearScreen();
						list.clear();
						ArrayList<Station> stations = mbtaMapPanel.getRoute();
						for(Station s : stations){
							list.add(s.getName());
						}
					}
				});

		Button button_1 = new Button("Clear");

		button_1.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						list.clear();
						mbtaMapPanel.setAllWhite();
						mbtaMapPanel.clearRoute();
					}
				});

		button_1.setBounds(1200, 600, 100, 80);
		mbtaMapPanel.add(button_1);

		JLabel lblNewLabel = new JLabel("Route Planner");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(1143, 0, 137, 50);
		mbtaMapPanel.add(lblNewLabel);
		
	}

	


	/**
	 * Converts the depart at/arrive by time to the correct milliseconds
	 * @param date - date object created by the JSpinner with the desired time of the trip
	 * @return time in milliseconds
	 * @author - leighannastolfi
	 */
	private Long timeOfTripInMilliseconds(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long timeInMillis = (calendar.get(Calendar.HOUR_OF_DAY)*3600000) + (calendar.get(Calendar.MINUTE) * 60000) 
				+ (calendar.get(Calendar.SECOND)*6000) + calendar.get(Calendar.MILLISECOND);
		return timeInMillis;
	}

	/**
	 * creates a timer that updates the trains on the map every 10 seconds
	 * 
	 * @param orange
	 * @param red
	 * @param blue
	 * @author jeffreyguion
	 */
	private void updateTrains(ArrayList<UITrain> orange, ArrayList<UITrain> red, ArrayList<UITrain> blue){
	    timer.cancel();
	    MyMbta.updateCache(this.location);
	    updater = new TrainUpdater(this.location, this.mbtaMapPanel);
	    updater.setOrangeLineTrains(orange);
	    updater.setRedLineTrains(red);
	    updater.setBlueLineTrains(blue);
	    timer = new Timer();
	    timer.schedule(updater, 0, 10000);
	}
	
}

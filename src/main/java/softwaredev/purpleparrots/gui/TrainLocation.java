package softwaredev.purpleparrots.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
import softwaredev.purpleparrots.Train;
import java.awt.Label;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import java.awt.FlowLayout;

public class TrainLocation extends JFrame {

	private JPanel contentPane;

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
	 * Create the frame.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public TrainLocation() throws JsonParseException, JsonMappingException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		HashMap<String, Train> blue_line_map = MyMbta.getCurrentLocationHash("blue");
		contentPane.setLayout(null);
		
		// v first blue line stop
		
		Canvas blueLineStart = new Canvas();
		blueLineStart.setBounds(52, 478, 34, 32);
		blueLineStart.setBackground(Color.WHITE);
		contentPane.add(blueLineStart);
		
		Canvas canvas_124 = new Canvas();
		canvas_124.setBounds(138, 478, 34, 32);
		contentPane.add(canvas_124);
		
		Canvas canvas_125 = new Canvas();
		canvas_125.setBounds(224, 478, 34, 32);
		canvas_125.setBackground(Color.WHITE);
		contentPane.add(canvas_125);
		
		Canvas canvas_126 = new Canvas();
		canvas_126.setBounds(310, 478, 34, 32);
		contentPane.add(canvas_126);
		
		Canvas canvas_127 = new Canvas();
		canvas_127.setBounds(396, 478, 34, 32);
		contentPane.add(canvas_127);
		
		Canvas canvas_128 = new Canvas();
		canvas_128.setBounds(482, 478, 34, 32);
		contentPane.add(canvas_128);
		
		Canvas canvas_129 = new Canvas();
		canvas_129.setBounds(568, 478, 34, 32);
		contentPane.add(canvas_129);
		
		Canvas canvas_130 = new Canvas();
		canvas_130.setBounds(654, 478, 34, 32);
		contentPane.add(canvas_130);
		
		Canvas canvas_131 = new Canvas();
		canvas_131.setBounds(740, 478, 34, 32);
		contentPane.add(canvas_131);
		
		Canvas canvas_132 = new Canvas();
		canvas_132.setBounds(826, 478, 34, 32);
		contentPane.add(canvas_132);
		
		Canvas canvas_133 = new Canvas();
		canvas_133.setBounds(912, 478, 34, 32);
		contentPane.add(canvas_133);
		
		Canvas blueLineEnd = new Canvas();
		blueLineEnd.setBounds(998, 478, 34, 32);
		contentPane.add(blueLineEnd);
		
		// ^ last blue line stop
		
		// v first <--- blue line train (yes, I know they're reverse ordered)
		
		Canvas canvas_145 = new Canvas();
		canvas_145.setBounds(98, 425, 34, 32);
		contentPane.add(canvas_145);
		if(blue_line_map.get("70037") != null){
			canvas_145.setBackground(Color.BLACK);
		}
		
		Canvas canvas_144 = new Canvas();
		canvas_144.setBounds(184, 425, 34, 32);
		contentPane.add(canvas_144);
		if(blue_line_map.get("70039") != null){
			canvas_144.setBackground(Color.BLACK);
		}
		
		Canvas canvas_143 = new Canvas();
		canvas_143.setBounds(273, 425, 34, 32);
		contentPane.add(canvas_143);
		if(blue_line_map.get("70041") != null){
			canvas_143.setBackground(Color.BLACK);
		}
		
		Canvas canvas_142 = new Canvas();
		canvas_142.setBounds(360, 425, 34, 32);
		contentPane.add(canvas_142);
		if(blue_line_map.get("70043") != null){
			canvas_142.setBackground(Color.BLACK);
		}
		
		Canvas canvas_141 = new Canvas();
		canvas_141.setBounds(443, 425, 34, 32);
		contentPane.add(canvas_141);
		if(blue_line_map.get("70045") != null){
			canvas_141.setBackground(Color.BLACK);
		}
		
		Canvas canvas_140 = new Canvas();
		canvas_140.setBounds(529, 425, 34, 32);
		contentPane.add(canvas_140);
		if(blue_line_map.get("70047") != null){
			canvas_140.setBackground(Color.BLACK);
		}
		
		Canvas canvas_139 = new Canvas();
		canvas_139.setBounds(615, 425, 34, 32);
		contentPane.add(canvas_139);
		if(blue_line_map.get("70049") != null){
			canvas_139.setBackground(Color.BLACK);
		}
		
		Canvas canvas_138 = new Canvas();
		canvas_138.setBounds(701, 425, 34, 32);
		contentPane.add(canvas_138);
		if(blue_line_map.get("70051") != null){
			canvas_138.setBackground(Color.BLACK);
		}
		
		Canvas canvas_137 = new Canvas();
		canvas_137.setBounds(784, 425, 34, 32);
		contentPane.add(canvas_137);
		if(blue_line_map.get("70053") != null){
			canvas_137.setBackground(Color.BLACK);
		}
		
		Canvas canvas_136 = new Canvas();
		canvas_136.setBounds(873, 425, 34, 32);
		contentPane.add(canvas_136);
		if(blue_line_map.get("70055") != null){
			canvas_136.setBackground(Color.BLACK);
		}
		
		Canvas canvas_135 = new Canvas();
		canvas_135.setBounds(959, 425, 34, 32);
		contentPane.add(canvas_135);
		if(blue_line_map.get("70057") != null){
			canvas_135.setBackground(Color.BLACK);
		}
		
		// ^ last <--- blue line train
		
		// v first ---> blue line train
		
		Canvas canvas_146 = new Canvas();
		canvas_146.setBounds(98, 525, 34, 32);
		contentPane.add(canvas_146);
		if(blue_line_map.get("70040") != null){
			canvas_146.setBackground(Color.BLACK);
		}
		
		Canvas canvas_147 = new Canvas();
		canvas_147.setBounds(184, 525, 34, 32);
		contentPane.add(canvas_147);
		if(blue_line_map.get("70042") != null){
			canvas_147.setBackground(Color.BLACK);
		}
		
		Canvas canvas_148 = new Canvas();
		canvas_148.setBounds(273, 525, 34, 32);
		contentPane.add(canvas_148);
		if(blue_line_map.get("70044") != null){
			canvas_148.setBackground(Color.BLACK);
		}
		
		Canvas canvas_149 = new Canvas();
		canvas_149.setBounds(360, 525, 34, 32);
		contentPane.add(canvas_149);
		if(blue_line_map.get("70046") != null){
			canvas_149.setBackground(Color.BLACK);
		}
		
		Canvas canvas_150 = new Canvas();
		canvas_150.setBounds(443, 525, 34, 32);
		contentPane.add(canvas_150);
		if(blue_line_map.get("70048") != null){
			canvas_150.setBackground(Color.BLACK);
		}
		
		Canvas canvas_151 = new Canvas();
		canvas_151.setBounds(529, 525, 34, 32);
		contentPane.add(canvas_151);
		if(blue_line_map.get("70050") != null){
			canvas_151.setBackground(Color.BLACK);
		}
		
		Canvas canvas_152 = new Canvas();
		canvas_152.setBounds(615, 525, 34, 32);
		contentPane.add(canvas_152);
		if(blue_line_map.get("70052") != null){
			canvas_152.setBackground(Color.BLACK);
		}
		
		Canvas canvas_153 = new Canvas();
		canvas_153.setBounds(701, 525, 34, 32);
		contentPane.add(canvas_153);
		if(blue_line_map.get("70054") != null){
			canvas_153.setBackground(Color.BLACK);
		}
		
		Canvas canvas_154 = new Canvas();
		canvas_154.setBounds(784, 525, 34, 32);
		contentPane.add(canvas_154);
		if(blue_line_map.get("70056") != null){
			canvas_154.setBackground(Color.BLACK);
		}
		
		Canvas canvas_155 = new Canvas();
		canvas_155.setBounds(873, 525, 34, 32);
		contentPane.add(canvas_155);
		if(blue_line_map.get("70058") != null){
			canvas_155.setBackground(Color.BLACK);
		}
		
		Canvas canvas_156 = new Canvas();
		canvas_156.setBounds(959, 525, 34, 32);
		contentPane.add(canvas_156);
		if(blue_line_map.get("70060") != null){
			canvas_156.setBackground(Color.BLACK);
		}
		
		// ^ last ---> blue line train
		
		Canvas canvas_119 = new Canvas();
		canvas_119.setBounds(821, 285, 5, 60);
		canvas_119.setBackground(new Color(192, 192, 192));
		contentPane.add(canvas_119);
		
		Canvas canvas_118 = new Canvas();
		canvas_118.setBounds(628, 285, 5, 60);
		canvas_118.setBackground(new Color(192, 192, 192));
		contentPane.add(canvas_118);
		
		Canvas oakGrove = new Canvas();
		oakGrove.setBounds(19, 63, 34, 32);
		oakGrove.setBackground(Color.WHITE);
		contentPane.add(oakGrove);
		
		Canvas canvas_4 = new Canvas();
		canvas_4.setBounds(72, 63, 34, 32);
		canvas_4.setBackground(Color.WHITE);
		contentPane.add(canvas_4);
		
		Canvas canvas_5 = new Canvas();
		canvas_5.setBounds(125, 63, 34, 32);
		canvas_5.setBackground(Color.WHITE);
		contentPane.add(canvas_5);
		
		Canvas canvas_6 = new Canvas();
		canvas_6.setBounds(178, 63, 34, 32);
		canvas_6.setBackground(Color.WHITE);
		contentPane.add(canvas_6);
		
		Canvas canvas_7 = new Canvas();
		canvas_7.setBounds(231, 63, 34, 32);
		canvas_7.setBackground(Color.WHITE);
		contentPane.add(canvas_7);
		
		Canvas canvas_8 = new Canvas();
		canvas_8.setBounds(284, 63, 34, 32);
		canvas_8.setBackground(Color.WHITE);
		contentPane.add(canvas_8);
		
		Canvas canvas_9 = new Canvas();
		canvas_9.setBounds(337, 63, 34, 32);
		canvas_9.setBackground(Color.WHITE);
		contentPane.add(canvas_9);
		
		Canvas canvas_10 = new Canvas();
		canvas_10.setBounds(390, 63, 34, 32);
		canvas_10.setBackground(Color.WHITE);
		contentPane.add(canvas_10);
		
		Canvas canvas_11 = new Canvas();
		canvas_11.setBounds(443, 63, 34, 32);
		canvas_11.setBackground(Color.WHITE);
		contentPane.add(canvas_11);
		
		Canvas canvas_12 = new Canvas();
		canvas_12.setBounds(496, 63, 34, 32);
		canvas_12.setBackground(Color.WHITE);
		contentPane.add(canvas_12);
		
		Canvas canvas_13 = new Canvas();
		canvas_13.setBounds(549, 63, 34, 32);
		canvas_13.setBackground(Color.WHITE);
		contentPane.add(canvas_13);
		
		Canvas canvas_14 = new Canvas();
		canvas_14.setBounds(602, 63, 34, 32);
		canvas_14.setBackground(Color.WHITE);
		contentPane.add(canvas_14);
		
		Canvas canvas_15 = new Canvas();
		canvas_15.setBounds(655, 63, 34, 32);
		canvas_15.setBackground(Color.WHITE);
		contentPane.add(canvas_15);
		
		Canvas canvas_16 = new Canvas();
		canvas_16.setBounds(708, 63, 34, 32);
		canvas_16.setBackground(Color.WHITE);
		contentPane.add(canvas_16);
		
		Canvas canvas_17 = new Canvas();
		canvas_17.setBounds(761, 63, 34, 32);
		canvas_17.setBackground(Color.WHITE);
		contentPane.add(canvas_17);
		
		Canvas canvas_18 = new Canvas();
		canvas_18.setBounds(814, 63, 34, 32);
		canvas_18.setBackground(Color.WHITE);
		contentPane.add(canvas_18);
		
		Canvas canvas_19 = new Canvas();
		canvas_19.setBounds(867, 63, 34, 32);
		canvas_19.setBackground(Color.WHITE);
		contentPane.add(canvas_19);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(920, 63, 34, 32);
		canvas.setBackground(Color.WHITE);
		contentPane.add(canvas);
		
		Canvas canvas_1 = new Canvas();
		canvas_1.setBounds(973, 63, 34, 32);
		canvas_1.setBackground(Color.WHITE);
		contentPane.add(canvas_1);
		
		Canvas orangeLineEnd = new Canvas();
		orangeLineEnd.setBounds(1026, 63, 34, 32);
		orangeLineEnd.setBackground(Color.WHITE);
		contentPane.add(orangeLineEnd);
		
		//ORANGE LINE STOPS 20 - 51
		HashMap<String, Train> orange_line_map = MyMbta.getCurrentLocationHash("orange");
		
		JPanel FH_OG_panel = new JPanel();
		FH_OG_panel.setBounds(19, 10, 1041, 38);
		contentPane.add(FH_OG_panel);
		FH_OG_panel.setLayout(null);
		
		Canvas FH_OG_end = new Canvas();
		FH_OG_end.setBounds(19, 5, 36, 32);
		FH_OG_panel.add(FH_OG_end);
		if(orange_line_map.get("70036") != null){
			FH_OG_end.setBackground(Color.BLACK);
		}
		
		Canvas canvas_21 = new Canvas();
		canvas_21.setBounds(74, 5, 34, 32);
		FH_OG_panel.add(canvas_21);
		if(orange_line_map.get("70035") != null){
			canvas_21.setBackground(Color.BLACK);
		}
		
		Canvas canvas_22 = new Canvas();
		canvas_22.setBounds(127, 5, 34, 32);
		FH_OG_panel.add(canvas_22);
		if(orange_line_map.get("70033") != null){
			canvas_22.setBackground(Color.BLACK);
		}
		
		Canvas canvas_23 = new Canvas();
		canvas_23.setBounds(180, 5, 34, 32);
		FH_OG_panel.add(canvas_23);
		if(orange_line_map.get("70031") != null){
			canvas_23.setBackground(Color.BLACK);
		}
		
		Canvas canvas_24 = new Canvas();
		canvas_24.setBounds(233, 5, 34, 32);
		FH_OG_panel.add(canvas_24);
		if(orange_line_map.get("70029") != null){
			canvas_24.setBackground(Color.BLACK);
		}
		
		Canvas canvas_25 = new Canvas();
		canvas_25.setBounds(286, 5, 34, 32);
		FH_OG_panel.add(canvas_25);
		if(orange_line_map.get("70027") != null){
			canvas_25.setBackground(Color.BLACK);
		}
		
		Canvas canvas_26 = new Canvas();
		canvas_26.setBounds(339, 5, 34, 32);
		FH_OG_panel.add(canvas_26);
		if(orange_line_map.get("70025") != null){
			canvas_26.setBackground(Color.BLACK);
		}
		
		Canvas canvas_27 = new Canvas();
		canvas_27.setBounds(392, 5, 34, 32);
		FH_OG_panel.add(canvas_27);
		if(orange_line_map.get("70023") != null){
			canvas_27.setBackground(Color.BLACK);
		}
		
		Canvas canvas_28 = new Canvas();
		canvas_28.setBounds(445, 5, 34, 32);
		FH_OG_panel.add(canvas_28);
		if(orange_line_map.get("70021") != null){
			canvas_28.setBackground(Color.BLACK);
		}
		
		Canvas canvas_29 = new Canvas();
		canvas_29.setBounds(498, 5, 34, 32);
		FH_OG_panel.add(canvas_29);
		if(orange_line_map.get("70019") != null){
			canvas_29.setBackground(Color.BLACK);
		}
		
		Canvas canvas_30 = new Canvas();
		canvas_30.setBounds(551, 5, 34, 32);
		FH_OG_panel.add(canvas_30);
		if(orange_line_map.get("70017") != null){
			canvas_30.setBackground(Color.BLACK);
		}
		
		Canvas canvas_31 = new Canvas();
		canvas_31.setBounds(604, 5, 34, 32);
		FH_OG_panel.add(canvas_31);
		if(orange_line_map.get("70015") != null){
			canvas_31.setBackground(Color.BLACK);
		}
		
		Canvas canvas_32 = new Canvas();
		canvas_32.setBounds(657, 5, 34, 32);
		FH_OG_panel.add(canvas_32);
		if(orange_line_map.get("70013") != null){
			canvas_32.setBackground(Color.BLACK);
		}
		
		Canvas canvas_33 = new Canvas();
		canvas_33.setBounds(710, 5, 34, 32);
		FH_OG_panel.add(canvas_33);
		if(orange_line_map.get("70011") != null){
			canvas_33.setBackground(Color.BLACK);
		}
		
		Canvas canvas_34 = new Canvas();
		canvas_34.setBounds(763, 5, 34, 32);
		FH_OG_panel.add(canvas_34);
		if(orange_line_map.get("70009") != null){
			canvas_34.setBackground(Color.BLACK);
		}
		
		Canvas canvas_35 = new Canvas();
		canvas_35.setBounds(816, 5, 34, 32);
		FH_OG_panel.add(canvas_35);
		if(orange_line_map.get("70007") != null){
			canvas_35.setBackground(Color.BLACK);
		}
		
		Canvas canvas_36 = new Canvas();
		canvas_36.setBounds(869, 5, 34, 32);
		FH_OG_panel.add(canvas_36);
		if(orange_line_map.get("70005") != null){
			canvas_36.setBackground(Color.BLACK);
		}
		
		Canvas canvas_37 = new Canvas();
		canvas_37.setBounds(922, 5, 34, 32);
		FH_OG_panel.add(canvas_37);
		if(orange_line_map.get("70003") != null){
			canvas_37.setBackground(Color.BLACK);
		}
		
		Canvas FH_OG_start = new Canvas();
		FH_OG_start.setBounds(975, 5, 34, 32);
		FH_OG_panel.add(FH_OG_start);
		if(orange_line_map.get("70001") != null){
			FH_OG_start.setBackground(Color.BLACK);
		}
		
		Canvas OG_FH_start = new Canvas();
		OG_FH_start.setBounds(58, 120, 34, 32);
		contentPane.add(OG_FH_start);
		if(orange_line_map.get("70036") != null){
			OG_FH_start.setBackground(Color.BLACK);
		}
		
		Canvas canvas_53 = new Canvas();
		canvas_53.setBounds(112, 120, 34, 32);
		contentPane.add(canvas_53);
		if(orange_line_map.get("70034") != null){
			canvas_53.setBackground(Color.BLACK);
		}
		
		Canvas canvas_54 = new Canvas();
		canvas_54.setBounds(167, 120, 34, 32);
		contentPane.add(canvas_54);
		if(orange_line_map.get("70032") != null){
			canvas_54.setBackground(Color.BLACK);
		}
		
		Canvas canvas_39 = new Canvas();
		canvas_39.setBounds(219, 120, 34, 32);
		contentPane.add(canvas_39);
		if(orange_line_map.get("70030") != null){
			canvas_39.setBackground(Color.BLACK);
		}
		
		Canvas canvas_40 = new Canvas();
		canvas_40.setBounds(273, 120, 34, 32);
		contentPane.add(canvas_40);
		if(orange_line_map.get("70028") != null){
			canvas_40.setBackground(Color.BLACK);
		}
		
		Canvas canvas_41 = new Canvas();
		canvas_41.setBounds(329, 120, 34, 32);
		contentPane.add(canvas_41);
		if(orange_line_map.get("70026") != null){
			canvas_41.setBackground(Color.BLACK);
		}
		
		Canvas canvas_42 = new Canvas();
		canvas_42.setBounds(377, 120, 34, 32);
		contentPane.add(canvas_42);
		if(orange_line_map.get("70024") != null){
			canvas_42.setBackground(Color.BLACK);
		}
		
		Canvas canvas_43 = new Canvas();
		canvas_43.setBounds(428, 120, 34, 32);
		contentPane.add(canvas_43);
		if(orange_line_map.get("70022") != null){
			canvas_43.setBackground(Color.BLACK);
		}
		
		Canvas canvas_44 = new Canvas();
		canvas_44.setBounds(478, 120, 34, 32);
		contentPane.add(canvas_44);
		if(orange_line_map.get("70020") != null){
			canvas_44.setBackground(Color.BLACK);
		}
		
		Canvas canvas_45 = new Canvas();
		canvas_45.setBounds(529, 120, 34, 32);
		contentPane.add(canvas_45);
		if(orange_line_map.get("70018") != null){
			canvas_45.setBackground(Color.BLACK);
		}
		
		Canvas canvas_46 = new Canvas();
		canvas_46.setBounds(581, 120, 34, 32);
		contentPane.add(canvas_46);
		if(orange_line_map.get("70016") != null){
			canvas_46.setBackground(Color.BLACK);
		}
		
		Canvas canvas_47 = new Canvas();
		canvas_47.setBounds(629, 120, 34, 32);
		contentPane.add(canvas_47);
		if(orange_line_map.get("70014") != null){
			canvas_47.setBackground(Color.BLACK);
		}
		
		Canvas canvas_48 = new Canvas();
		canvas_48.setBounds(680, 120, 34, 32);
		contentPane.add(canvas_48);
		if(orange_line_map.get("70012") != null){
			canvas_48.setBackground(Color.BLACK);
		}
		
		Canvas canvas_49 = new Canvas();
		canvas_49.setBounds(728, 120, 34, 32);
		contentPane.add(canvas_49);
		if(orange_line_map.get("70010") != null){
			canvas_49.setBackground(Color.BLACK);
		}
		
		Canvas canvas_50 = new Canvas();
		canvas_50.setBounds(778, 120, 34, 32);
		contentPane.add(canvas_50);
		if(orange_line_map.get("70008") != null){
			canvas_50.setBackground(Color.BLACK);
		}
		
		Canvas canvas_51 = new Canvas();
		canvas_51.setBounds(828, 120, 34, 32);
		contentPane.add(canvas_51);
		if(orange_line_map.get("70006") != null){
			canvas_51.setBackground(Color.BLACK);
		}
		
		Canvas canvas_55 = new Canvas();
		canvas_55.setBounds(873, 120, 34, 32);
		contentPane.add(canvas_55);
		if(orange_line_map.get("70004") != null){
			canvas_55.setBackground(Color.BLACK);
		}
		
		Canvas canvas_56 = new Canvas();
		canvas_56.setBounds(924, 120, 34, 32);
		contentPane.add(canvas_56);
		if(orange_line_map.get("70002") != null){
			canvas_56.setBackground(Color.BLACK);
		}
		
		Canvas canvas_57 = new Canvas();
		canvas_57.setBounds(974, 120, 34, 32);
		contentPane.add(canvas_57);
		if(orange_line_map.get("70000") != null){
			canvas_57.setBackground(Color.BLACK);
		}
		
		Canvas orangeLine = new Canvas();
		orangeLine.setBounds(10, 48, 1080, 66);
		orangeLine.setBackground(Color.ORANGE);
		contentPane.add(orangeLine);
		
		JLabel lblOakGrove = new JLabel("Oak Grove");
		lblOakGrove.setBounds(16, 163, 76, 16);
		contentPane.add(lblOakGrove);
		
		JLabel lblMaldenCenter = new JLabel("Malden Center");
		lblMaldenCenter.setBounds(58, 191, 103, 16);
		contentPane.add(lblMaldenCenter);
		
		JLabel lblWellington = new JLabel("Wellington");
		lblWellington.setBounds(122, 163, 76, 16);
		contentPane.add(lblWellington);
		
		JLabel lblSullivanSquare = new JLabel("Sullivan Square");
		lblSullivanSquare.setBounds(167, 191, 95, 16);
		contentPane.add(lblSullivanSquare);
		
		JLabel lblCommunityCollege = new JLabel("Community College");
		lblCommunityCollege.setBounds(202, 163, 134, 16);
		contentPane.add(lblCommunityCollege);
		
		JLabel lblNorthStation = new JLabel("North Station");
		lblNorthStation.setBounds(273, 191, 96, 16);
		contentPane.add(lblNorthStation);
		
		JLabel lblHaymarket = new JLabel("Haymarket");
		lblHaymarket.setBounds(339, 163, 69, 16);
		contentPane.add(lblHaymarket);
		
		JLabel lblStateSt = new JLabel("State St");
		lblStateSt.setBounds(392, 191, 61, 16);
		contentPane.add(lblStateSt);
		
		JLabel lblDowntownCrossing = new JLabel("Downtown Crossing");
		lblDowntownCrossing.setBounds(420, 163, 135, 16);
		contentPane.add(lblDowntownCrossing);
		
		JLabel lblChinatown = new JLabel("Chinatown");
		lblChinatown.setBounds(489, 191, 85, 16);
		contentPane.add(lblChinatown);
		
		JLabel lblTufts = new JLabel("Tufts");
		lblTufts.setBounds(554, 163, 61, 16);
		contentPane.add(lblTufts);
		
		JLabel lblBackBay = new JLabel("Back Bay");
		lblBackBay.setBounds(602, 191, 61, 16);
		contentPane.add(lblBackBay);
		
		JLabel lblMassAve = new JLabel("Mass Ave");
		lblMassAve.setBounds(639, 163, 61, 16);
		contentPane.add(lblMassAve);
		
		JLabel lblRuggles = new JLabel("Ruggles");
		lblRuggles.setBounds(701, 191, 61, 16);
		contentPane.add(lblRuggles);
		
		JLabel lblRoxburyCrossing = new JLabel("Roxbury Crossing");
		lblRoxburyCrossing.setBounds(728, 163, 134, 16);
		contentPane.add(lblRoxburyCrossing);
		
		JLabel lblJacksonSquare = new JLabel("Jackson Square");
		lblJacksonSquare.setBounds(778, 191, 112, 16);
		contentPane.add(lblJacksonSquare);
		
		JLabel lblStonyBrook = new JLabel("Stony Brook");
		lblStonyBrook.setBounds(856, 163, 76, 16);
		contentPane.add(lblStonyBrook);
		
		JLabel lblGreenSt = new JLabel("Green St");
		lblGreenSt.setBounds(897, 191, 61, 16);
		contentPane.add(lblGreenSt);
		
		JLabel lblForestHills = new JLabel("Forest Hills");
		lblForestHills.setBounds(947, 163, 86, 16);
		contentPane.add(lblForestHills);
		
		// First red line stop
		
		Canvas redLineStart = new Canvas();
		redLineStart.setBounds(14, 295, 34, 32);
		redLineStart.setBackground(Color.WHITE);
		contentPane.add(redLineStart);
		
		Canvas canvas_58 = new Canvas();
		canvas_58.setBounds(62, 295, 34, 32);
		canvas_58.setBackground(Color.WHITE);
		contentPane.add(canvas_58);
		
		Canvas canvas_59 = new Canvas();
		canvas_59.setBounds(110, 295, 34, 32);
		canvas_59.setBackground(Color.WHITE);
		contentPane.add(canvas_59);
		
		Canvas canvas_60 = new Canvas();
		canvas_60.setBounds(158, 295, 34, 32);
		canvas_60.setBackground(Color.WHITE);
		contentPane.add(canvas_60);
		
		Canvas canvas_61 = new Canvas();
		canvas_61.setBounds(206, 295, 34, 32);
		canvas_61.setBackground(Color.WHITE);
		contentPane.add(canvas_61);
		
		Canvas canvas_62 = new Canvas();
		canvas_62.setBounds(254, 295, 34, 32);
		canvas_62.setBackground(Color.WHITE);
		contentPane.add(canvas_62);
		
		Canvas canvas_63 = new Canvas();
		canvas_63.setBounds(302, 295, 34, 32);
		canvas_63.setBackground(Color.WHITE);
		contentPane.add(canvas_63);
		
		Canvas canvas_64 = new Canvas();
		canvas_64.setBounds(350, 295, 34, 32);
		canvas_64.setBackground(Color.WHITE);
		contentPane.add(canvas_64);
		
		Canvas canvas_65 = new Canvas();
		canvas_65.setBounds(398, 295, 34, 32);
		canvas_65.setBackground(Color.WHITE);
		contentPane.add(canvas_65);
		
		Canvas canvas_66 = new Canvas();
		canvas_66.setBounds(446, 295, 34, 32);
		canvas_66.setBackground(Color.WHITE);
		contentPane.add(canvas_66);
		
		Canvas canvas_67 = new Canvas();
		canvas_67.setBounds(494, 295, 34, 32);
		canvas_67.setBackground(Color.WHITE);
		contentPane.add(canvas_67);
		
		Canvas canvas_68 = new Canvas();
		canvas_68.setBounds(542, 295, 34, 32);
		canvas_68.setBackground(Color.WHITE);
		contentPane.add(canvas_68);
		
		Canvas canvas_69 = new Canvas();
		canvas_69.setBounds(590, 295, 34, 32);
		canvas_69.setBackground(Color.WHITE);
		contentPane.add(canvas_69);
		
		Canvas canvas_70 = new Canvas();
		canvas_70.setBounds(638, 295, 34, 32);
		canvas_70.setBackground(Color.WHITE);
		contentPane.add(canvas_70);
		
		Canvas canvas_71 = new Canvas();
		canvas_71.setBounds(686, 295, 34, 32);
		canvas_71.setBackground(Color.WHITE);
		contentPane.add(canvas_71);
		
		Canvas canvas_72 = new Canvas();
		canvas_72.setBounds(734, 295, 34, 32);
		canvas_72.setBackground(Color.WHITE);
		contentPane.add(canvas_72);
		
		Canvas canvas_73 = new Canvas();
		canvas_73.setBounds(782, 295, 34, 32);
		canvas_73.setBackground(Color.WHITE);
		contentPane.add(canvas_73);
		
		Canvas canvas_74 = new Canvas();
		canvas_74.setBounds(830, 295, 34, 32);
		canvas_74.setBackground(Color.WHITE);
		contentPane.add(canvas_74);
		
		Canvas canvas_75 = new Canvas();
		canvas_75.setBounds(878, 295, 34, 32);
		canvas_75.setBackground(Color.WHITE);
		contentPane.add(canvas_75);
		
		Canvas canvas_76 = new Canvas();
		canvas_76.setBounds(926, 295, 34, 32);
		canvas_76.setBackground(Color.WHITE);
		contentPane.add(canvas_76);
		
		Canvas canvas_115 = new Canvas();
		canvas_115.setBounds(974, 295, 34, 32);
		canvas_115.setBackground(Color.WHITE);
		contentPane.add(canvas_115);
		
		Canvas redLineEnd = new Canvas();
		redLineEnd.setBounds(1022, 295, 34, 32);
		redLineEnd.setBackground(Color.WHITE);
		contentPane.add(redLineEnd);
		
		// ^ Last red line stop
		
		// v First <---- red line train
		
		HashMap<String, Train> red_line_map = MyMbta.getCurrentLocationHash("red");
		
		Canvas canvas_77 = new Canvas();
		canvas_77.setBounds(34, 242, 34, 32);
		contentPane.add(canvas_77);
		if(red_line_map.get("70061") != null){
			canvas_77.setBackground(Color.BLACK);
		}
		
		Canvas canvas_78 = new Canvas();
		canvas_78.setBounds(88, 242, 34, 32);
		contentPane.add(canvas_78);
		if(red_line_map.get("70064") != null){
			canvas_78.setBackground(Color.BLACK);
		}
		
		Canvas canvas_79 = new Canvas();
		canvas_79.setBounds(138, 242, 34, 32);
		contentPane.add(canvas_79);
		if(red_line_map.get("70066") != null){
			canvas_79.setBackground(Color.BLACK);
		}
		
		Canvas canvas_80 = new Canvas();
		canvas_80.setBounds(184, 242, 34, 32);
		contentPane.add(canvas_80);
		if(red_line_map.get("70068") != null){
			canvas_80.setBackground(Color.BLACK);
		}
		
		Canvas canvas_81 = new Canvas();
		canvas_81.setBounds(231, 242, 34, 32);
		contentPane.add(canvas_81);
		if(red_line_map.get("70070") != null){
			canvas_81.setBackground(Color.BLACK);
		}
		
		Canvas canvas_82 = new Canvas();
		canvas_82.setBounds(284, 242, 34, 32);
		contentPane.add(canvas_82);
		if(red_line_map.get("70072") != null){
			canvas_82.setBackground(Color.BLACK);
		}
		
		Canvas canvas_83 = new Canvas();
		canvas_83.setBounds(329, 242, 34, 32);
		contentPane.add(canvas_83);
		if(red_line_map.get("70074") != null){
			canvas_83.setBackground(Color.BLACK);
		}
		
		Canvas canvas_84 = new Canvas();
		canvas_84.setBounds(377, 242, 34, 32);
		contentPane.add(canvas_84);
		if(red_line_map.get("70076") != null){
			canvas_84.setBackground(Color.BLACK);
		}
		
		Canvas canvas_85 = new Canvas();
		canvas_85.setBounds(428, 242, 34, 32);
		contentPane.add(canvas_85);
		if(red_line_map.get("70078") != null){
			canvas_85.setBackground(Color.BLACK);
		}
		
		Canvas canvas_86 = new Canvas();
		canvas_86.setBounds(482, 242, 34, 32);
		contentPane.add(canvas_86);
		if(red_line_map.get("70080") != null){
			canvas_86.setBackground(Color.BLACK);
		}
		
		Canvas canvas_87 = new Canvas();
		canvas_87.setBounds(529, 242, 34, 32);
		contentPane.add(canvas_87);
		if(red_line_map.get("70082") != null){
			canvas_87.setBackground(Color.BLACK);
		}
		
		Canvas canvas_88 = new Canvas();
		canvas_88.setBounds(568, 242, 34, 32);
		contentPane.add(canvas_88);
		if(red_line_map.get("70084") != null){
			canvas_88.setBackground(Color.BLACK);
		}
		
		Canvas canvas_89 = new Canvas();
		canvas_89.setBounds(615, 242, 34, 32);
		contentPane.add(canvas_89);
		if(red_line_map.get("70086") != null){
			canvas_89.setBackground(Color.BLACK);
		}
		
		Canvas canvas_90 = new Canvas();
		canvas_90.setBounds(663, 242, 34, 32);
		contentPane.add(canvas_90);
		if(red_line_map.get("70088") != null){
			canvas_90.setBackground(Color.BLACK);
		}
		
		Canvas canvas_91 = new Canvas();
		canvas_91.setBounds(713, 242, 34, 32);
		contentPane.add(canvas_91);
		if(red_line_map.get("70090") != null){
			canvas_91.setBackground(Color.BLACK);
		}
		
		Canvas canvas_92 = new Canvas();
		canvas_92.setBounds(763, 242, 34, 32);
		contentPane.add(canvas_92);
		if(red_line_map.get("70092") != null){
			canvas_92.setBackground(Color.BLACK);
		}
		
		Canvas canvas_93 = new Canvas();
		canvas_93.setBounds(808, 242, 34, 32);
		contentPane.add(canvas_93);
		if(red_line_map.get("70096") != null){
			canvas_93.setBackground(Color.BLACK);
		}
		
		Canvas canvas_94 = new Canvas();
		canvas_94.setBounds(859, 242, 34, 32);
		contentPane.add(canvas_94);
		if(red_line_map.get("70098") != null){
			canvas_94.setBackground(Color.BLACK);
		}
		
		Canvas canvas_95 = new Canvas();
		canvas_95.setBounds(909, 242, 34, 32);
		contentPane.add(canvas_95);
		if(red_line_map.get("70100") != null){
			canvas_95.setBackground(Color.BLACK);
		}
		
		Canvas canvas_117 = new Canvas();
		canvas_117.setBounds(959, 242, 34, 32);
		contentPane.add(canvas_117);
		if(red_line_map.get("70102") != null){
			canvas_117.setBackground(Color.BLACK);
		}
		
		Canvas canvas_122 = new Canvas();
		canvas_122.setBounds(999, 242, 34, 32);
		contentPane.add(canvas_122);
		if(red_line_map.get("70104") != null){
			canvas_122.setBackground(Color.BLACK);
		}
		
		// ^ Last <--- red line train
		
		// v First ---> red line train
		
		Canvas canvas_96 = new Canvas();
		canvas_96.setBounds(34, 352, 34, 32);
		contentPane.add(canvas_96);
		if(red_line_map.get("70063") != null){
			canvas_96.setBackground(Color.BLACK);
		}
		
		Canvas canvas_97 = new Canvas();
		canvas_97.setBounds(88, 352, 34, 32);
		contentPane.add(canvas_97);
		if(red_line_map.get("70065") != null){
			canvas_97.setBackground(Color.BLACK);
		}
		
		Canvas canvas_98 = new Canvas();
		canvas_98.setBounds(138, 352, 34, 32);
		contentPane.add(canvas_98);
		if(red_line_map.get("70067") != null){
			canvas_98.setBackground(Color.BLACK);
		}
		
		Canvas canvas_99 = new Canvas();
		canvas_99.setBounds(184, 352, 34, 32);
		contentPane.add(canvas_99);
		if(red_line_map.get("70069") != null){
			canvas_99.setBackground(Color.BLACK);
		}
		
		Canvas canvas_100 = new Canvas();
		canvas_100.setBounds(231, 352, 34, 32);
		contentPane.add(canvas_100);
		if(red_line_map.get("70071") != null){
			canvas_100.setBackground(Color.BLACK);
		}
		
		Canvas canvas_101 = new Canvas();
		canvas_101.setBounds(284, 352, 34, 32);
		contentPane.add(canvas_101);
		if(red_line_map.get("70073") != null){
			canvas_101.setBackground(Color.BLACK);
		}
		
		Canvas canvas_102 = new Canvas();
		canvas_102.setBounds(329, 352, 34, 32);
		contentPane.add(canvas_102);
		if(red_line_map.get("70075") != null){
			canvas_102.setBackground(Color.BLACK);
		}
		
		Canvas canvas_103 = new Canvas();
		canvas_103.setBounds(377, 352, 34, 32);
		contentPane.add(canvas_103);
		if(red_line_map.get("70077") != null){
			canvas_103.setBackground(Color.BLACK);
		}
		
		Canvas canvas_104 = new Canvas();
		canvas_104.setBounds(428, 352, 34, 32);
		contentPane.add(canvas_104);
		if(red_line_map.get("70079") != null){
			canvas_104.setBackground(Color.BLACK);
		}
		
		Canvas canvas_105 = new Canvas();
		canvas_105.setBounds(478, 352, 34, 32);
		contentPane.add(canvas_105);
		if(red_line_map.get("70081") != null){
			canvas_105.setBackground(Color.BLACK);
		}
		
		Canvas canvas_106 = new Canvas();
		canvas_106.setBounds(526, 352, 34, 32);
		contentPane.add(canvas_106);
		if(red_line_map.get("70083") != null){
			canvas_106.setBackground(Color.BLACK);
		}
		
		// JFK split line
		Canvas canvas_107 = new Canvas();
		canvas_107.setBounds(568, 352, 34, 32);
		contentPane.add(canvas_107);
		if(red_line_map.get("70085") != null || red_line_map.get("70095") != null){
			canvas_107.setBackground(Color.BLACK);
		}
		
		// Savin hill
		Canvas canvas_108 = new Canvas();
		canvas_108.setBounds(615, 352, 34, 32);
		contentPane.add(canvas_108);
		if(red_line_map.get("70087") != null){
			canvas_108.setBackground(Color.BLACK);
		}
		
		Canvas canvas_109 = new Canvas();
		canvas_109.setBounds(663, 352, 34, 32);
		contentPane.add(canvas_109);
		if(red_line_map.get("70089") != null){
			canvas_109.setBackground(Color.BLACK);
		}
		
		Canvas canvas_110 = new Canvas();
		canvas_110.setBounds(713, 352, 34, 32);
		contentPane.add(canvas_110);
		if(red_line_map.get("70091") != null){
			canvas_110.setBackground(Color.BLACK);
		}
		
		// Ashmont
		Canvas canvas_111 = new Canvas();
		canvas_111.setBounds(763, 352, 34, 32);
		contentPane.add(canvas_111);
		if(red_line_map.get("70093") != null){
			canvas_111.setBackground(Color.BLACK);
		}
		
		// North quincy
		Canvas canvas_112 = new Canvas();
		canvas_112.setBounds(808, 352, 34, 32);
		contentPane.add(canvas_112);
		if(red_line_map.get("70097") != null){
			canvas_112.setBackground(Color.BLACK);
		}
		
		Canvas canvas_113 = new Canvas();
		canvas_113.setBounds(859, 352, 34, 32);
		contentPane.add(canvas_113);
		if(red_line_map.get("70099") != null){
			canvas_113.setBackground(Color.BLACK);
		}
		
		Canvas canvas_114 = new Canvas();
		canvas_114.setBounds(909, 352, 34, 32);
		contentPane.add(canvas_114);
		if(red_line_map.get("70101") != null){
			canvas_114.setBackground(Color.BLACK);
		}
		
		Canvas canvas_116 = new Canvas();
		canvas_116.setBounds(959, 352, 34, 32);
		contentPane.add(canvas_116);
		if(red_line_map.get("70103") != null){
			canvas_116.setBackground(Color.BLACK);
		}
		
		// braintree
		Canvas canvas_121 = new Canvas();
		canvas_121.setBounds(998, 352, 34, 32);
		contentPane.add(canvas_121);
		if(red_line_map.get("70105") != null){
			canvas_116.setBackground(Color.BLACK);
		}
		
		// ^ last ---> red line train
		
		Canvas redLine = new Canvas();
		redLine.setBounds(10, 290, 1080, 49);
		redLine.setBackground(Color.RED);
		contentPane.add(redLine);
		
		Canvas blueLine = new Canvas();
		blueLine.setBounds(10, 470, 1080, 49);
		blueLine.setBackground(Color.BLUE);
		contentPane.add(blueLine);
		
		
	}
}

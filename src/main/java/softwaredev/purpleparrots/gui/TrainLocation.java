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
		contentPane.setLayout(null);
		
		Canvas oakGrove = new Canvas();
		oakGrove.setBackground(Color.WHITE);
		oakGrove.setBounds(36, 63, 34, 32);
		contentPane.add(oakGrove);
		
		Canvas canvas_4 = new Canvas();
		canvas_4.setBounds(88, 63, 34, 32);
		contentPane.add(canvas_4);
		
		Canvas canvas_5 = new Canvas();
		canvas_5.setBackground(Color.WHITE);
		canvas_5.setBounds(141, 63, 34, 32);
		contentPane.add(canvas_5);
		
		Canvas canvas_6 = new Canvas();
		canvas_6.setBounds(195, 63, 34, 32);
		contentPane.add(canvas_6);
		
		Canvas canvas_7 = new Canvas();
		canvas_7.setBounds(251, 63, 34, 32);
		contentPane.add(canvas_7);
		
		Canvas canvas_8 = new Canvas();
		canvas_8.setBounds(302, 63, 34, 32);
		contentPane.add(canvas_8);
		
		Canvas canvas_9 = new Canvas();
		canvas_9.setBounds(354, 63, 34, 32);
		contentPane.add(canvas_9);
		
		Canvas canvas_10 = new Canvas();
		canvas_10.setBounds(406, 63, 34, 32);
		contentPane.add(canvas_10);
		
		Canvas canvas_11 = new Canvas();
		canvas_11.setBounds(456, 63, 34, 32);
		contentPane.add(canvas_11);
		
		Canvas canvas_12 = new Canvas();
		canvas_12.setBounds(507, 63, 34, 32);
		contentPane.add(canvas_12);
		
		Canvas canvas_13 = new Canvas();
		canvas_13.setBounds(558, 63, 34, 32);
		contentPane.add(canvas_13);
		
		Canvas canvas_14 = new Canvas();
		canvas_14.setBounds(606, 63, 34, 32);
		contentPane.add(canvas_14);
		
		Canvas canvas_15 = new Canvas();
		canvas_15.setBounds(656, 63, 34, 32);
		contentPane.add(canvas_15);
		
		Canvas canvas_16 = new Canvas();
		canvas_16.setBounds(707, 63, 34, 32);
		contentPane.add(canvas_16);
		
		Canvas canvas_17 = new Canvas();
		canvas_17.setBounds(756, 63, 34, 32);
		contentPane.add(canvas_17);
		
		Canvas canvas_18 = new Canvas();
		canvas_18.setBounds(805, 63, 34, 32);
		contentPane.add(canvas_18);
		
		Canvas canvas_19 = new Canvas();
		canvas_19.setBounds(856, 63, 34, 32);
		contentPane.add(canvas_19);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(906, 63, 34, 32);
		contentPane.add(canvas);
		
		Canvas canvas_1 = new Canvas();
		canvas_1.setBounds(953, 63, 34, 32);
		contentPane.add(canvas_1);
		
		Canvas canvas_2 = new Canvas();
		canvas_2.setBounds(999, 63, 34, 32);
		contentPane.add(canvas_2);
		
		//ORANGE LINE STOPS 20 - 51
		HashMap<String, Train> orange_line_map = MyMbta.getCurrentLocationHash("orange");
		
		Canvas canvas_20 = new Canvas();
		canvas_20.setBounds(58, 10, 34, 32);
		contentPane.add(canvas_20);
		if(orange_line_map.get("70036") != null){
			canvas_20.setBackground(Color.BLACK);
		}
		
		Canvas canvas_21 = new Canvas();
		canvas_21.setBounds(112, 10, 34, 32);
		contentPane.add(canvas_21);
		if(orange_line_map.get("70035") != null){
			canvas_21.setBackground(Color.BLACK);
		}
		
		Canvas canvas_22 = new Canvas();
		canvas_22.setBounds(167, 10, 34, 32);
		contentPane.add(canvas_22);
		if(orange_line_map.get("70033") != null){
			canvas_22.setBackground(Color.BLACK);
		}
		
		Canvas canvas_23 = new Canvas();
		canvas_23.setBounds(219, 10, 34, 32);
		contentPane.add(canvas_23);
		if(orange_line_map.get("70031") != null){
			canvas_23.setBackground(Color.BLACK);
		}
		
		Canvas canvas_24 = new Canvas();
		canvas_24.setBounds(273, 10, 34, 32);
		contentPane.add(canvas_24);
		if(orange_line_map.get("70029") != null){
			canvas_24.setBackground(Color.BLACK);
		}
		
		Canvas canvas_25 = new Canvas();
		canvas_25.setBounds(329, 10, 34, 32);
		contentPane.add(canvas_25);
		if(orange_line_map.get("70027") != null){
			canvas_25.setBackground(Color.BLACK);
		}
		
		Canvas canvas_26 = new Canvas();
		canvas_26.setBounds(377, 10, 34, 32);
		contentPane.add(canvas_26);
		if(orange_line_map.get("70025") != null){
			canvas_26.setBackground(Color.BLACK);
		}
		
		Canvas canvas_27 = new Canvas();
		canvas_27.setBounds(428, 10, 34, 32);
		contentPane.add(canvas_27);
		if(orange_line_map.get("70023") != null){
			canvas_27.setBackground(Color.BLACK);
		}
		
		Canvas canvas_28 = new Canvas();
		canvas_28.setBounds(478, 10, 34, 32);
		contentPane.add(canvas_28);
		if(orange_line_map.get("70021") != null){
			canvas_28.setBackground(Color.BLACK);
		}
		
		Canvas canvas_29 = new Canvas();
		canvas_29.setBounds(529, 10, 34, 32);
		contentPane.add(canvas_29);
		if(orange_line_map.get("70019") != null){
			canvas_29.setBackground(Color.BLACK);
		}
		
		Canvas canvas_30 = new Canvas();
		canvas_30.setBounds(581, 10, 34, 32);
		contentPane.add(canvas_30);
		if(orange_line_map.get("70017") != null){
			canvas_30.setBackground(Color.BLACK);
		}
		
		Canvas canvas_31 = new Canvas();
		canvas_31.setBounds(629, 10, 34, 32);
		contentPane.add(canvas_31);
		if(orange_line_map.get("70015") != null){
			canvas_31.setBackground(Color.BLACK);
		}
		
		Canvas canvas_32 = new Canvas();
		canvas_32.setBounds(680, 10, 34, 32);
		contentPane.add(canvas_32);
		if(orange_line_map.get("70013") != null){
			canvas_32.setBackground(Color.BLACK);
		}
		
		Canvas canvas_33 = new Canvas();
		canvas_33.setBounds(728, 10, 34, 32);
		contentPane.add(canvas_33);
		if(orange_line_map.get("70011") != null){
			canvas_33.setBackground(Color.BLACK);
		}
		
		Canvas canvas_34 = new Canvas();
		canvas_34.setBounds(778, 10, 34, 32);
		contentPane.add(canvas_34);
		if(orange_line_map.get("70009") != null){
			canvas_34.setBackground(Color.BLACK);
		}
		
		Canvas canvas_35 = new Canvas();
		canvas_35.setBounds(828, 10, 34, 32);
		contentPane.add(canvas_35);
		if(orange_line_map.get("70007") != null){
			canvas_35.setBackground(Color.BLACK);
		}
		
		Canvas canvas_36 = new Canvas();
		canvas_36.setBounds(873, 10, 34, 32);
		contentPane.add(canvas_36);
		if(orange_line_map.get("70005") != null){
			canvas_36.setBackground(Color.BLACK);
		}
		
		Canvas canvas_37 = new Canvas();
		canvas_37.setBounds(924, 10, 34, 32);
		contentPane.add(canvas_37);
		if(orange_line_map.get("70003") != null){
			canvas_37.setBackground(Color.BLACK);
		}
		
		Canvas canvas_38 = new Canvas();
		canvas_38.setBounds(974, 10, 34, 32);
		contentPane.add(canvas_38);
		if(orange_line_map.get("70001") != null){
			canvas_38.setBackground(Color.BLACK);
		}
		
		Canvas canvas_52 = new Canvas();
		canvas_52.setBounds(58, 120, 34, 32);
		contentPane.add(canvas_52);
		if(orange_line_map.get("70036") != null){
			canvas_52.setBackground(Color.BLACK);
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
		orangeLine.setBackground(Color.ORANGE);
		orangeLine.setBounds(10, 48, 1080, 66);
		contentPane.add(orangeLine);
		
		Canvas blueLine = new Canvas();
		blueLine.setBackground(Color.BLUE);
		blueLine.setBounds(10, 481, 1080, 49);
		contentPane.add(blueLine);
		
		Canvas redLine = new Canvas();
		redLine.setBackground(Color.RED);
		redLine.setBounds(10, 294, 1080, 49);
		contentPane.add(redLine);
		
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
	}
}

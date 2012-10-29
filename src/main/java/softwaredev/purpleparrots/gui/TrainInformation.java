package softwaredev.purpleparrots.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import softwaredev.purpleparrots.MyMbta;
import java.awt.Color;

public class TrainInformation extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainInformation frame = new TrainInformation();
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
	public TrainInformation() throws JsonParseException, JsonMappingException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTrainInformation = new JLabel("Train Information");
		lblTrainInformation.setBounds(18, 16, 110, 16);
		contentPane.add(lblTrainInformation);
		
		JTextPane txtpnTest = new JTextPane();
		txtpnTest.setBackground(Color.ORANGE);
		txtpnTest.setBounds(18, 44, 366, 88);
		txtpnTest.setText(MyMbta.getCurrentLocationOfTrains("orange"));
		contentPane.add(txtpnTest);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.RED);
		textPane.setText(MyMbta.getCurrentLocationOfTrains("red"));
		textPane.setBounds(18, 144, 366, 88);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(Color.BLUE);
		textPane_1.setText(MyMbta.getCurrentLocationOfTrains("blue"));
		textPane_1.setBounds(18, 255, 366, 88);
		contentPane.add(textPane_1);
	}
}

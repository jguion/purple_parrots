package softwaredev.purpleparrots.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MyMBTAGui {

    private JFrame frmHome;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        MyMBTAGui window = new MyMBTAGui();
                        window.frmHome.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    /**
     * Create the application.
     */
    public MyMBTAGui() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmHome = new JFrame();
        frmHome.getContentPane().setBackground(new Color(148, 0, 211));
        frmHome.setTitle("MyMBTA");
        frmHome.setBounds(100, 100, 450, 300);
        frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblMyMbta = new JLabel("My MBTA");
        lblMyMbta.setForeground(new Color(255, 255, 0));
        lblMyMbta.setFont(new Font("Dialog", Font.BOLD, 30));

        JButton currentTrainsBtn = new JButton("View Current Train Locations");
        currentTrainsBtn.setBackground(new Color(216, 191, 216));
        currentTrainsBtn.setFont(new Font("Dialog", Font.PLAIN, 11));
        currentTrainsBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        //frame = new TrainInformation();
                        frmHome = new TrainLocation();
                    } catch (JsonParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (JsonMappingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    frmHome.setVisible(true);
                }
            });
        GroupLayout groupLayout = new GroupLayout(frmHome.getContentPane());
        groupLayout.setHorizontalGroup(
                                       groupLayout.createParallelGroup(Alignment.LEADING)
                                       .addGroup(groupLayout.createSequentialGroup()
                                                 .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                           .addGroup(groupLayout.createSequentialGroup()
                                                                     .addGap(115)
                                                                     .addComponent(currentTrainsBtn, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
                                                           .addGroup(groupLayout.createSequentialGroup()
                                                                     .addGap(150)
                                                                     .addComponent(lblMyMbta)))
                                                 .addContainerGap(114, Short.MAX_VALUE))
                                       );
        groupLayout.setVerticalGroup(
                                     groupLayout.createParallelGroup(Alignment.LEADING)
                                     .addGroup(groupLayout.createSequentialGroup()
                                               .addGap(12)
                                               .addComponent(lblMyMbta)
                                               .addGap(59)
                                               .addComponent(currentTrainsBtn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                               .addGap(110))
                                     );
        frmHome.getContentPane().setLayout(groupLayout);
    }
}

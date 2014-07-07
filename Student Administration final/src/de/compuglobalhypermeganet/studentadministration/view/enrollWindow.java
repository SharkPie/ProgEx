
package de.compuglobalhypermeganet.studentadministration.view;

//Str+Shift+O = to reorganize imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.compuglobalhypermeganet.studentadministration.controller.JdbcTemplate;
import de.compuglobalhypermeganet.studentadministration.model.buttonCL;
import de.compuglobalhypermeganet.studentadministration.model.labelCL;
import de.compuglobalhypermeganet.studentadministration.model.passwordfieldCL;
import de.compuglobalhypermeganet.studentadministration.model.textfieldCL;


public class enrollWindow extends JFrame implements ActionListener{
	

	private JDesktopPane enrollWindow = new JDesktopPane();
	private buttonCL buttonEnrolling = new buttonCL("Enroll");

		public enrollWindow( String eWusername,  String eWpassword){
			
			/*	try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(this);
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			*/	
			
				
				
				
				setLayout(new BorderLayout(100, 100));
			
				getEnrollWindow().setLayout(new GridLayout(5, 1, 10, 10));
				add(enrollWindow);
		
				setTitle("Student Administration");		
				setResizable(false);
				setVisible(true);
				
				
				
				
				pack(); 
			
							
				//Button Listener
				buttonEnrolling.addActionListener(this);
			
				
				
				setSize(1024, 720);
				setLocationRelativeTo(null);
			
			}
		



		public JDesktopPane getEnrollWindow() {
			return enrollWindow;
		}

		public void setEnrollWindow(JDesktopPane enrollWindow) {
			this.enrollWindow = enrollWindow;
		}


	
		public buttonCL getButtonEnrolling() {
			return buttonEnrolling;
		}




		public void setButtonEnrolling(buttonCL buttonEnrolling) {
			this.buttonEnrolling = buttonEnrolling;
		}




		@Override //Login Comparison
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("OK")){
				
		}

		
		}

}


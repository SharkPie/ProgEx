
package de.compuglobalhypermeganet.studentadministration.view;

//Str+Shift+O = to reorganize imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import de.compuglobalhypermeganet.studentadministration.model.buttonCL;


public class viewGrades extends JFrame implements ActionListener{
	

	private JDesktopPane viewGrades = new JDesktopPane();
	private buttonCL buttonClose = new buttonCL("Close");

	
	
		public viewGrades( String vGusername,  String vGpassword){
			
			/*	try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(this);
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			*/	
			
				
				
				
				setLayout(new BorderLayout(100, 100));
			
				getViewGrades().setLayout(new GridLayout(5, 1, 10, 10));
				add(viewGrades);
		
				setTitle("Student Administration");		
				setResizable(false);
				setVisible(true);
				
				getViewGrades().add(buttonClose, BorderLayout.SOUTH);
				
				
				
				pack(); 
			
							
				//Button Listener
				buttonClose.addActionListener(this);
			
				
				
				setSize(1024, 720);
				setLocationRelativeTo(null);
			
			}
		



		public JDesktopPane getViewGrades() {
			return viewGrades;
		}

		public void setViewGrades(JDesktopPane unrollWindow) {
			this.viewGrades = viewGrades;
		}


	
		public buttonCL getButtonUnrolling() {
			return buttonClose;
		}




		public void setButtonUnrolling(buttonCL buttonUnrolling) {
			this.buttonClose = buttonUnrolling;
		}




		@Override //Login Comparison
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Close")){
				dispose();
		}

		
		}

}



package de.compuglobalhypermeganet.studentadministration.view;

//Str+Shift+O = to reorganize imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import de.compuglobalhypermeganet.studentadministration.model.buttonCL;


public class unrollWindow extends JFrame implements ActionListener{
	

	private JDesktopPane unrollWindow = new JDesktopPane();
	private buttonCL buttonUnrolling = new buttonCL("Unroll");

	
	
		public unrollWindow( String uWusername,  String uWpassword){
			
			/*	try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(this);
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			*/	
			
				
				
				
				setLayout(new BorderLayout(100, 100));
			
				getUnrollWindow().setLayout(new GridLayout(5, 1, 10, 10));
				add(unrollWindow);
		
				setTitle("Student Administration");		
				setResizable(false);
				setVisible(true);
				
				
				
				
				pack(); 
			
							
				//Button Listener
				buttonUnrolling.addActionListener(this);
			
				
				
				setSize(1024, 720);
				setLocationRelativeTo(null);
			
			}
		



		public JDesktopPane getUnrollWindow() {
			return unrollWindow;
		}

		public void setUnrollWindow(JDesktopPane unrollWindow) {
			this.unrollWindow = unrollWindow;
		}


	
		public buttonCL getButtonUnrolling() {
			return buttonUnrolling;
		}




		public void setButtonUnrolling(buttonCL buttonUnrolling) {
			this.buttonUnrolling = buttonUnrolling;
		}




		@Override //Login Comparison
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("OK")){
				
		}

		
		}

}


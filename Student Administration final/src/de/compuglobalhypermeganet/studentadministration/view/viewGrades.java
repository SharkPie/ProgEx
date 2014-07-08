
package de.compuglobalhypermeganet.studentadministration.view;

//Str+Shift+O = to reorganize imports
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.compuglobalhypermeganet.studentadministration.controller.JdbcTemplate;
import de.compuglobalhypermeganet.studentadministration.model.buttonCL;


public class viewGrades extends JFrame implements ActionListener{
	

	private JDesktopPane viewGrades = new JDesktopPane();

	private buttonCL buttonClose = new buttonCL("Close");
	private JScrollPane sPane;
	public static String usr = null;
	public static String pwd = null;
	
	
		public viewGrades( String vGusername,  String vGpassword){
			
			/*	try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(this);
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			*/	
			usr=vGusername;
			pwd=vGpassword;
				
				setsPane(new String [30][2]);
				String[][] eeData = JdbcTemplate.getInstance().getviewGrades(usr, pwd);
				setsPane(eeData);
				
				setLayout(new BorderLayout(100, 100));
			
				getViewGrades().setLayout(new GridLayout(5, 1, 10, 10));
				add(viewGrades);
				
				setTitle("Student Administration");		
				setResizable(false);
				setVisible(true);
				
				getViewGrades().add(sPane);
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

		public void setViewGrades(JDesktopPane viewGrades) {
			this.viewGrades = viewGrades;
		}

		public buttonCL getButtonClose() {
			return buttonClose;
		}


		public void setButtonClose(buttonCL buttonClose) {
			this.buttonClose = buttonClose;
		}
	
		
		public JScrollPane getsPane() {
			return sPane;
		}

		public void setsPane(String[][] data) {
			String[] col_names = {"Course Name","Grade"};
			JTable table = new JTable(data, col_names);
			sPane = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			sPane.setViewportView(table);
			pack();
		}



		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Close")){
				dispose();
		}

		
		}

}


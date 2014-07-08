package de.compuglobalhypermeganet.studentadministration.view;



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


	public class welcomeWindow extends JFrame implements ActionListener{
		
		private JDesktopPane welcomeWindow = new JDesktopPane();
		private buttonCL buttonEnroll = new buttonCL("Enroll Exam");		//(left, bottom, firstWindow)Enroll Button

		private buttonCL buttonUnroll = new buttonCL("Unroll Exam");		//(right, bottom, firstWindow)Unroll Button
		private buttonCL buttonViewGrades = new buttonCL("View Grades");	//(firstWindow)Viewing Grades
		private buttonCL buttonLogout = new buttonCL("Logout");				//(firstWindow)Logout
		private buttonCL buttonClose = new buttonCL("Exit Program");				//(firstWindow)Close
		private labelCL hlineLabel = new labelCL("Welcome");				//(top)Headline Label
		private JScrollPane sPane;											//(bottom)Scroll Window
		public static String usr = null;
		public static String pwd = null;

		
		
		public welcomeWindow( String wusername,  String wpassword){
			
			/*	try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(this);
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			*/	
				
			usr=wusername;
			pwd=wpassword;
			
			
				setLayout(new BorderLayout(100, 100));	
				getWelcomeWindow().setLayout(new GridLayout(5, 1, 10, 10));
				add(welcomeWindow);
				setTitle("Student Administration");		
				setResizable(false);
				setVisible(true);
				
				setsPane(new String [30][2]);
				String[][] eeData = JdbcTemplate.getInstance().getEnroledExam(usr, pwd);
				setsPane(eeData);
				
				getWelcomeWindow().add(hlineLabel, BorderLayout.NORTH);
				getHlineLabel().setText("Welcome "+usr+ " to your Student Administration Panel!");
				getHlineLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				getWelcomeWindow().add(sPane);
				getWelcomeWindow().add(buttonEnroll, BorderLayout.SOUTH);
				getWelcomeWindow().add(buttonUnroll, BorderLayout.SOUTH);
				getWelcomeWindow().add(buttonViewGrades, BorderLayout.SOUTH);
				getWelcomeWindow().add(buttonLogout, BorderLayout.SOUTH);	 
				getWelcomeWindow().add(buttonClose, BorderLayout.SOUTH);
				
				
							
				//Button Listener
				buttonEnroll.addActionListener(this);
				buttonUnroll.addActionListener(this);	
				buttonViewGrades.addActionListener(this);	
				buttonLogout.addActionListener(this);
				buttonClose.addActionListener(this);	
				
				
				
				
				
				setSize(1024, 720);
				setLocationRelativeTo(null);
				
		
		}
		
			
			
		

			public JDesktopPane getWelcomeWindow() {
				return welcomeWindow;
			}

			public void setWelcomeWindow(JDesktopPane welcomeWindow) {
				this.welcomeWindow = welcomeWindow;
			}


			public buttonCL getButtonEnrol() {
				return buttonEnroll;
			}

			public void setButtonEnrol(buttonCL buttonEnrol) {
				this.buttonEnroll = buttonEnrol;
			}

			public buttonCL getButtonUnroll() {
				return buttonUnroll;
			}

			public void setButtonUnroll(buttonCL buttonUnroll) {
				this.buttonUnroll = buttonUnroll;
			}

			public buttonCL getButtonViewGrades() {
				return buttonViewGrades;
			}

			public void setButtonViewGrades(buttonCL buttonViewGrades) {
				this.buttonViewGrades = buttonViewGrades;
			}

			public buttonCL getButtonLogout() {
				return buttonLogout;
			}

			public void setButtonLogout(buttonCL buttonLogout) {
				this.buttonLogout = buttonLogout;
			}

			public buttonCL getButtonClose() {
				return buttonClose;
			}

			public void setButtonClose(buttonCL buttonClose) {
				this.buttonClose = buttonClose;
			}

			public labelCL getHlineLabel() {
				return hlineLabel;
			}

			public void setHlineLabel(labelCL hlineLabel) {
				this.hlineLabel = hlineLabel;
			}

		
			public JScrollPane getsPane() {
				return sPane;
			}

			public void setsPane(String[][] data) {
				String[] col_names = {"Last Name","Course Name"};
				JTable table = new JTable(data, col_names);
				sPane = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				sPane.setViewportView(table);
				pack();
			}

			@Override //Login Comparison
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("Enroll Exam")){
					new enrollWindow(usr, pwd);
				}else{ 
					if(e.getActionCommand().equals("Unroll Exam")){
						new unrollWindow(usr, pwd);	
					}else{
						if(e.getActionCommand().equals("View Grades")){
							new viewGrades(usr,pwd);
						}else{
							if(e.getActionCommand().equals("Exit Program")){
								dispose();
								System.exit(0);
							}else{
								new main();
								dispose();
							}
					
						}
					}	
				}
			
	}//main class

}

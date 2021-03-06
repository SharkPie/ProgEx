/*	Software Project: Student Administration
 * 	Author: Patrick Beck SW Design & Engineering
 *  Begin: 20.4.2014
 * 	End: 
 */

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


public class main extends JFrame implements ActionListener{
	
	private JDesktopPane loginPanel = new JDesktopPane();


	private labelCL labelUsername = new labelCL("Username:");			//(left, center)Username Label
	private textfieldCL textfUsername = new textfieldCL();				//(right, center)Username Textfield
	private labelCL labelPassword = new labelCL("Password:");			//(left, center)Password Label
	private passwordfieldCL textfPassword = new passwordfieldCL();		//(right, center)Password Textfield
	private buttonCL buttonOK = new buttonCL("OK");						//(left, bottom)OK Button
	private buttonCL buttonCancel = new buttonCL("Cancel");				//(right, bottom)Cancel Button
	private labelCL hlineLabel = new labelCL("Please Login");			//(top)Headline Label
	private labelCL cstatusLabel = new labelCL("Connection Status");	//(bottom)Connection Status Label

	
	public static void main(String[] args) {
		new main();
		
	}//main function
	
		public main(){
			
			//Customize the Frame.
			
			
			
			setSize(400, 300);
			//The Location of the Frame is always in the middle of the screen
			setLocationRelativeTo(null);
			setTitle("Student Administration");
			setLayout(new BorderLayout(30,30));
			setResizable(false);
			
			
			//Layout Manager using
			getLoginPanel().setLayout(new GridLayout(3, 2, 30, 30));
			add(loginPanel);
			add(hlineLabel, BorderLayout.NORTH);
			
			//Should test database connectivity
			add(cstatusLabel, BorderLayout.SOUTH);
			
			
			//Text, Label and Button adding
			getLoginPanel().add(labelUsername);
			getLoginPanel().add(textfUsername);
			getLoginPanel().add(labelPassword);
			getLoginPanel().add(textfPassword);
			getLoginPanel().add(buttonOK);
			getLoginPanel().add(buttonCancel);
			
			//Button Listener
			buttonOK.addActionListener(this);
			
			buttonCancel.addActionListener(this);
			
					
			//Label designing
			getLabelUsername().setForeground(Color.black);
			getLabelPassword().setForeground(Color.black);
			getLabelUsername().setFont(new Font(Font.DIALOG, Font.BOLD, 14));
			getLabelPassword().setFont(new Font(Font.DIALOG, Font.BOLD, 14));
			getCstatusLabel().setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			
			//Background Color
			getContentPane().setBackground(Color.white);
		
			//Top left change Icon with a picture.
			Image iconImage = Toolkit.getDefaultToolkit().getImage("imgicon.png");
			setIconImage(iconImage);
			
			
			pack();
			setVisible(true);
			
			//anonym inner class WindowListener/Adapter
			
			addWindowListener(new WindowAdapter(){
				public void windowOpened(WindowEvent e){
					Collection <String[]> data = JdbcTemplate.getInstance().querys("con_test", "con_test");
					if(data.isEmpty()){
						getCstatusLabel().setText("Database Connection Lost!");
						getCstatusLabel().setForeground(Color.RED);
						
					}else{
						getCstatusLabel().setText("Connection Established.");
						getCstatusLabel().setForeground(Color.GREEN);
					}
				}
			});
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					setVisible(false);
					dispose();
				}
				public void windowClosed(WindowEvent e){
					System.exit(0);
				}
			});
			
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			
		}//main public function	
	
		
		
		public JDesktopPane getLoginPanel() {
			return loginPanel;
		}

		public void setLoginPanel(JDesktopPane loginPanel) {
			this.loginPanel = loginPanel;
		}


		public labelCL getLabelUsername() {
			return labelUsername;
		}

		public void setLabelUsername(labelCL labelUsername) {
			this.labelUsername = labelUsername;
		}

		public textfieldCL getTextfUsername() {
			return textfUsername;
		}

		public void setTextfUsername(textfieldCL textfUsername) {
			this.textfUsername = textfUsername;
		}

		public labelCL getLabelPassword() {
			return labelPassword;
		}

		public void setLabelPassword(labelCL labelPassword) {
			this.labelPassword = labelPassword;
		}


		public passwordfieldCL getTextfPassword() {
			return textfPassword;
		}

		public void setTextfPassword(passwordfieldCL textfPassword) {
			this.textfPassword = textfPassword;
		}

		public buttonCL getButtonOK() {
			return buttonOK;
		}

		public void setButtonOK(buttonCL buttonOK) {
			this.buttonOK = buttonOK;
		}

		public buttonCL getButtonCancel() {
			return buttonCancel;
		}

		public void setButtonCancel(buttonCL buttonCancel) {
			this.buttonCancel = buttonCancel;
		}


		public labelCL getHlineLabel() {
			return hlineLabel;
		}

		public void setHlineLabel(labelCL hlineLabel) {
			this.hlineLabel = hlineLabel;
		}

		public labelCL getCstatusLabel() {
			return cstatusLabel;
		}

		public void setCstatusLabel(labelCL cstatusLabel) {
			this.cstatusLabel = cstatusLabel;
		}
	

	
		@Override //Login Comparison
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("OK")){
				String username = getTextfUsername().getText();
				String password = new String (getTextfPassword().getPassword());
				Collection <String[]> data = JdbcTemplate.getInstance().query(username, password);
				
				if(data.isEmpty()){
				JOptionPane.showMessageDialog(this,"Username/Password invalid, otherwise perhaps you are not immatriculated. ");
				} else {		
				JOptionPane.showMessageDialog(this,username+ " identified by " +" ******* "+ " conntecting to database... ");
				setVisible(false);
				
				new welcomeWindow(username,password);
				
				
				}
			}else 
			{
					JOptionPane.showMessageDialog(this,"Closing connecting... ");
					setVisible(false);
					dispose();
			}
			
			
			
		}

		
		
}//main class

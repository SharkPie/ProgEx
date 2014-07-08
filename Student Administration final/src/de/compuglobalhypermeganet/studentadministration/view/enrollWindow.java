
package de.compuglobalhypermeganet.studentadministration.view;

//Str+Shift+O = to reorganize imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import de.compuglobalhypermeganet.studentadministration.model.CheckBoxNodeTree;
import de.compuglobalhypermeganet.studentadministration.model.buttonCL;
import de.compuglobalhypermeganet.studentadministration.model.labelCL;
import de.compuglobalhypermeganet.studentadministration.model.passwordfieldCL;
import de.compuglobalhypermeganet.studentadministration.model.textfieldCL;


public class enrollWindow extends JFrame implements ActionListener{


	private JDesktopPane enrollWindow = new JDesktopPane();
	private buttonCL buttonEnrolling = new buttonCL("Enroll");
	private buttonCL buttonClose = new buttonCL("Close");

    public CheckBoxNodeTree MyTree = new CheckBoxNodeTree(); //nötig?!?

	private JScrollPane sPane;
	public static String usr = null;
	public static String pwd = null;

	public enrollWindow( String eWusername,  String eWpassword){
		//usr=eWusername;
		//pwd=eWpassword;

		setsPane(new String [40][4]);
		//String[][] eeData = JdbcTemplate.getInstance().getEnrollWindow(usr, pwd);
		String[][] eeData = JdbcTemplate.getInstance().getEnrollWindow(eWusername, eWpassword);
		setsPane(eeData);

		setLayout(new BorderLayout(100, 100));

		getEnrollWindow().setLayout(new GridLayout(5, 1, 10, 10));
		add(enrollWindow);

		setTitle("Student Administration");		
		setResizable(false);
		setVisible(true);
		getEnrollWindow().add(sPane);

		getEnrollWindow().add(buttonEnrolling);
		getEnrollWindow().add(buttonClose);

		pack(); 

		//Button Listener
		buttonEnrolling.addActionListener(this);
		buttonClose.addActionListener(this);

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

	public buttonCL getButtonClose() {
		return buttonClose;
	}

	public void setButtonClose(buttonCL buttonClose) {
		this.buttonClose = buttonClose;
	}




	public void setsPane(String[][] data) {
		String[] col_names = {"Course Name","ECTS", "Exam Type", "Enroll"};
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


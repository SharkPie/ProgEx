package de.compuglobalhypermeganet.studentadministration.model;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class buttonCL extends JButton {
	
	//Button Constructor
	public buttonCL(String text){
		setPreferredSize(new Dimension(100, 25));
		setText(text);
		setActionCommand(text);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
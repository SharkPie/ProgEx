package de.compuglobalhypermeganet.studentadministration.model;

import java.awt.Dimension;

import javax.swing.JLabel;

public class labelCL extends JLabel {
	
	//Label Constructor
	public labelCL(String text){
		setPreferredSize(new Dimension(100, 25));
		setText(text);
	}
}	

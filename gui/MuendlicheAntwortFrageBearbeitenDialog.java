package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.*;

@SuppressWarnings("serial")
public class MuendlicheAntwortFrageBearbeitenDialog extends JDialog implements ActionListener {
    Fragencontainer container = Fragencontainer.instance();
    JTextField vorlesung = new JTextField("", 20);
    JTextField thema = new JTextField("", 20);
    JTextField frage = new JTextField("", 20);
    JTextField antwort = new JTextField("", 20);
    JButton ok;
    JButton abbrechen;
    Frage alteFrage;
    
    public MuendlicheAntwortFrageBearbeitenDialog(MuendlicheAntwortFrage frage) {
	this.setModal(true);
	this.setLayout(new BorderLayout());
	this.alteFrage = frage;
	
	JPanel north = new JPanel();
	north.setLayout(new FlowLayout());
	JLabel vorl = new JLabel("Vorlesung: ");
	north.add(vorl);
	north.add(vorlesung);
	JLabel them = new JLabel("Thema: ");
	north.add(them);
	north.add(thema);
	this.add(north, BorderLayout.NORTH);
	
	JPanel center = new JPanel();
	center.setLayout(new GridLayout(2, 2));
	JLabel fr = new JLabel("Frage: ");
	center.add(fr);
	center.add(this.frage);
	JLabel antw = new JLabel("Antwort: ");
	center.add(antw);
	center.add(antwort);
	this.add(center, BorderLayout.CENTER);
	
	JPanel south = new JPanel();
	south.setLayout(new FlowLayout());
	ok = new JButton("OK");
	ok.addActionListener(this);
	south.add(ok);
	abbrechen = new JButton("Abbrechen");
	abbrechen.addActionListener(this);
	south.add(abbrechen);
	this.add(south, BorderLayout.SOUTH);
	
	if (frage == null) {
	    this.setTitle("neue MuendlicheAntwortFrage");
	} else {
	    this.setTitle("MuendlicheAntwortFrage bearbeiten");
	    this.vorlesung.setText(frage.getVorlesung());
	    this.thema.setText(frage.getThema());
	    this.frage.setText(frage.getFrage());
	    this.antwort.setText(frage.getAntwort());
	}
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.pack();
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(ok)) {
	    if (this.vorlesung.getText() == null || this.vorlesung.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Der Titel der Vorlesung darf nicht leer sein.");
	    } else if (this.thema.getText() == null || this.thema.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Das Thema darf nicht leer sein.");
	    } else if (this.frage.getText() == null || this.frage.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Die Frage darf nicht leer sein.");
	    } else if (this.antwort.getText() == null || this.antwort.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Die Antwort darf nicht leer sein.");
	    } else {
		MuendlicheAntwortFrage frage = new MuendlicheAntwortFrage(this.vorlesung.getText(), 
			this.thema.getText(), this.frage.getText(), this.antwort.getText());
		container.linkFrage(frage);
		container.unlinkFrage(alteFrage);
		JOptionPane.showMessageDialog(null, "Die Frage wurde hinzugefügt.");
		this.dispose();
	    }
	} else if (e.getSource().equals(abbrechen)) {
	    this.dispose();
	}
    }
}

package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
    JComboBox<String> vorlesung = new JComboBox<>();
    JComboBox<String> thema = new JComboBox<>();
    JTextField frage = new JTextField("", 20);
    JTextField antwort = new JTextField("", 20);
    JButton ok;
    JButton abbrechen;
    Frage alteFrage;
    
    public MuendlicheAntwortFrageBearbeitenDialog(MuendlicheAntwortFrage frage) {
	this.setModal(true);
	this.setLayout(new BorderLayout());
	this.alteFrage = frage;
	
	VorlesungenThemenContainer vtc = VorlesungenThemenContainer.instance();
	
	JPanel north = new JPanel();
	north.setLayout(new FlowLayout());
	JLabel vorl = new JLabel("Vorlesung: ");
	north.add(vorl);
	for (String vorles : vtc.getVorlesungen()) {
	    vorlesung.addItem(vorles);
	}
	vorlesung.addItem("eigene Vorlesung...");
	vorlesung.setEditable(true);
	north.add(vorlesung);
	JLabel them = new JLabel("Thema: ");
	north.add(them);
	for (String the : vtc.getThemen()) {
	    thema.addItem(the);
	}
	thema.addItem("eigenes Thema...");
	thema.setEditable(true);
	north.add(thema);
	north.setBackground(GuiFarbauswahl.officialColor);
	this.add(north, BorderLayout.NORTH);
	
	JPanel center = new JPanel();
	center.setLayout(new GridLayout(2, 2));
	JLabel fr = new JLabel("Frage: ");
	center.add(fr);
	center.add(this.frage);
	JLabel antw = new JLabel("Antwort: ");
	center.add(antw);
	center.add(antwort);
	center.setBackground(GuiFarbauswahl.officialColor);
	this.add(center, BorderLayout.CENTER);
	
	JPanel south = new JPanel();
	south.setLayout(new FlowLayout());
	ok = new JButton("OK");
	ok.addActionListener(this);
	south.add(ok);
	abbrechen = new JButton("Abbrechen");
	abbrechen.addActionListener(this);
	south.add(abbrechen);
	south.setBackground(GuiFarbauswahl.officialColor);
	this.add(south, BorderLayout.SOUTH);
	
	if (frage == null) {
	    this.setTitle("neue MuendlicheAntwortFrage");
	} else {
	    this.setTitle("MuendlicheAntwortFrage bearbeiten");
	    this.vorlesung.setSelectedItem(frage.getVorlesung());
	    this.thema.setSelectedItem(frage.getVorlesung());
	    this.frage.setText(frage.getFrage());
	    this.antwort.setText(frage.getAntwort());
	}
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(ok)) {
	    if (this.frage.getText() == null || this.frage.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Die Frage darf nicht leer sein.");
	    } else if (this.antwort.getText() == null || this.antwort.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Die Antwort darf nicht leer sein.");
	    } else {
		MuendlicheAntwortFrage frage = new MuendlicheAntwortFrage((String) this.vorlesung.getSelectedItem(), 
			(String) this.thema.getSelectedItem(), this.frage.getText(), this.antwort.getText());
		container.linkFrage(frage);
		container.unlinkFrage(alteFrage);
		JOptionPane.showMessageDialog(null, "Die Frage wurde hinzugef�gt.");
		this.dispose();
	    }
	} else if (e.getSource().equals(abbrechen)) {
	    this.dispose();
	}
    }
    
    /*public static void main(String[] args) {
	new MuendlicheAntwortFrageBearbeitenDialog(null);
    }*/
}

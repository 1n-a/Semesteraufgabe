package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import controller.*;

@SuppressWarnings("serial")
public class MuendlicheAntwortFrageBearbeitenDialog extends JDialog implements ActionListener {
    Fragencontainer container = Fragencontainer.instance();
    JComboBox<String> vorlesung = new JComboBox<>();
    JComboBox<String> thema = new JComboBox<>();
    JTextArea frage = new JTextArea("", 1, 20);
    JTextArea antwort = new JTextArea("", 1, 20);
    JButton ok;
    JButton abbrechen;
    Frage alteFrage;
    
    public MuendlicheAntwortFrageBearbeitenDialog(MuendlicheAntwortFrage frage) {
	this.setModal(true);
	this.setLayout(new BorderLayout());
	this.alteFrage = frage;
	
	Font officialFont = Einstellungen.instance().getOfficialFont();
	
	VorlesungenThemenContainer vtc = VorlesungenThemenContainer.instance();
	
	JPanel north = new JPanel();
	north.setLayout(new FlowLayout());
	JLabel vorl = new JLabel("Vorlesung: ");
	vorl.setFont(officialFont);
	north.add(vorl);
	for (String vorles : vtc.getVorlesungen()) {
	    vorlesung.addItem(vorles);
	}
	vorlesung.addItem("eigene Vorlesung...");
	vorlesung.setEditable(true);
	vorlesung.setFont(officialFont);
	north.add(vorlesung);
	JLabel them = new JLabel("Thema: ");
	them.setFont(officialFont);
	north.add(them);
	for (String the : vtc.getThemen()) {
	    thema.addItem(the);
	}
	thema.addItem("eigenes Thema...");
	thema.setEditable(true);
	thema.setFont(officialFont);
	north.add(thema);
	north.setBackground(GuiFarbauswahl.officialColor);
	this.add(north, BorderLayout.NORTH);
	
	JPanel center = new JPanel();
	center.setLayout(new GridLayout(2, 2));
	JLabel fr = new JLabel("Frage: ");
	fr.setFont(officialFont);
	center.add(fr);
	center.add(new JScrollPane(this.frage));
	this.frage.setFont(officialFont);
	JLabel antw = new JLabel("Antwort: ");
	antw.setFont(officialFont);
	center.add(antw);
	center.add(new JScrollPane(antwort));
	this.antwort.setFont(officialFont);
	center.setBackground(GuiFarbauswahl.officialColor);
	this.add(center, BorderLayout.CENTER);
	
	JPanel south = new JPanel();
	south.setLayout(new FlowLayout());
	ok = new JButton("OK");
	ok.setFont(officialFont);
	ok.addActionListener(this);
	south.add(ok);
	abbrechen = new JButton("Abbrechen");
	abbrechen.setFont(officialFont);
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
	    } else if (this.frage.getText().contains("\n")) {
		JOptionPane.showMessageDialog(this, "Die Frage darf nicht aus mehreren Zeilen bestehen.");
	    } else if (this.antwort.getText().contains("\n")) {
		JOptionPane.showMessageDialog(this, "Die Antwort darf nicht aus mehreren Zeilen bestehen.");
	    } else if (this.frage.getText().contains("$")) {
		JOptionPane.showMessageDialog(this, "Das Zeichen '$' ist in der Frage nicht erlaubt.");
	    } else if (this.antwort.getText().contains("$")) {
		JOptionPane.showMessageDialog(this, "Das Zeichen'$' ist in der Antwort nicht erlaubt");
	    } else {
		MuendlicheAntwortFrage frage = new MuendlicheAntwortFrage((String) this.vorlesung.getSelectedItem(), 
			(String) this.thema.getSelectedItem(), this.frage.getText(), this.antwort.getText());
		container.linkFrage(frage);
		container.unlinkFrage(alteFrage);
		JOptionPane.showMessageDialog(null, "Die Frage wurde hinzugefuegt.");
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

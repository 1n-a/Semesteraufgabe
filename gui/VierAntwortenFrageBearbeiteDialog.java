package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.*;

@SuppressWarnings("serial")
public class VierAntwortenFrageBearbeiteDialog extends JDialog implements ActionListener {
    Fragencontainer container = Fragencontainer.instance();
    JComboBox<String> vorlesung = new JComboBox<>();
    JComboBox<String> thema = new JComboBox<>();
    JTextArea frage = new JTextArea("", 1, 20);
    JTextArea richtigeAntwort = new JTextArea("", 1, 20);
    JTextArea falscheAntwort1 = new JTextArea("", 1, 20);
    JTextArea falscheAntwort2 = new JTextArea("", 1, 20);
    JTextArea falscheAntwort3 = new JTextArea("", 1, 20);
    JButton ok;
    JButton abbrechen;
    Frage alteFrage;
    
    
    public VierAntwortenFrageBearbeiteDialog(VierAntwortenFrage frage) {
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
	north.add(thema);
	thema.setEditable(true);
	thema.setFont(officialFont);
	north.setBackground(GuiFarbauswahl.officialColor);
	this.add(north, BorderLayout.NORTH);
	
	JPanel center = new JPanel();
	center.setLayout(new BorderLayout());
	
	JPanel centerNorth = new JPanel();
	centerNorth.setLayout(new FlowLayout());
	JLabel fr = new JLabel("Frage: ");
	fr.setFont(officialFont);
	centerNorth.add(fr);
	centerNorth.add(new JScrollPane(this.frage));
	this.frage.setFont(officialFont);
	centerNorth.setBackground(GuiFarbauswahl.officialColor);
	center.add(centerNorth, BorderLayout.NORTH);
	
	JPanel centerCenter = new JPanel();
	centerCenter.setLayout(new GridLayout(2, 8));
	JLabel ra = new JLabel("richtige Antwort: ");
	ra.setFont(officialFont);
	centerCenter.add(ra);
	centerCenter.add(new JScrollPane(richtigeAntwort));
	JLabel f1 = new JLabel("falsche Antwort: ");
	f1.setFont(officialFont);
	centerCenter.add(f1);
	centerCenter.add(new JScrollPane(falscheAntwort1));
	JLabel f2 = new JLabel("falsche Antwort: ");
	f2.setFont(officialFont);
	centerCenter.add(f2);
	centerCenter.add(new JScrollPane(falscheAntwort2));
	JLabel f3 = new JLabel("falsche Antwort: ");
	f3.setFont(officialFont);
	centerCenter.add(f3);
	centerCenter.add(new JScrollPane(falscheAntwort3));
	centerCenter.setBackground(GuiFarbauswahl.officialColor);
	center.add(centerCenter);
	
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
	
	vorlesung.setFont(officialFont);
	thema.setFont(officialFont);
	this.frage.setFont(officialFont);
	richtigeAntwort.setFont(officialFont);
	falscheAntwort1.setFont(officialFont);
	this.falscheAntwort2.setFont(officialFont);
	this.falscheAntwort3.setFont(officialFont);
	
	if (frage == null) {
	    this.setTitle("neue VierAntwortenFrage");
	} else {
	    this.setTitle("VierAntwortenFrage bearbeiten");
	    this.vorlesung.setSelectedItem(frage.getVorlesung());
	    this.thema.setSelectedItem(frage.getThema());
	    this.frage.setText(frage.getFrage());
	    this.richtigeAntwort.setText(frage.getAntworten()[0]);
	    this.falscheAntwort1.setText(frage.getAntworten()[1]);
	    this.falscheAntwort2.setText(frage.getAntworten()[2]);
	    this.falscheAntwort3.setText(frage.getAntworten()[3]);
	}
	this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(ok)) {
	    if (this.frage.getText() == null || this.frage.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Die Frage darf nicht leer sein.");
	    } else if (this.richtigeAntwort.getText() == null || this.richtigeAntwort.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Die richtige Antwort darf nicht leer sein.");
	    } else if (this.falscheAntwort1.getText() == null || this.falscheAntwort1.getText().equals("")
		    || this.falscheAntwort2.getText() == null || this.falscheAntwort2.getText().equals("")
		    || this.falscheAntwort3.getText() == null || this.falscheAntwort3.getText().equals("")) {
		JOptionPane.showMessageDialog(null, "Die falschen Antworten dürfen nicht leer sein.");
	    } else if (this.frage.getText().contains("\n")) {
		JOptionPane.showMessageDialog(this, "Die Frage darf nicht aus mehreren Zeilen bestehen.");
	    } else if (this.frage.getText().contains("$")) {
		JOptionPane.showMessageDialog(this, "Das Zeichen '$' ist in der Frage nicht erlaubt.");
	    } else if (this.richtigeAntwort.getText().contains("\n") || this.falscheAntwort1.getText().contains("\n")
		    || this.falscheAntwort2.getText().contains("\n") || this.falscheAntwort3.getText().contains("\n")) {
		JOptionPane.showMessageDialog(this, "Keine Antwort darf aus mehreren Zeilen bestehen.");
	    } else if (this.richtigeAntwort.getText().contains("$") || this.falscheAntwort1.getText().contains("$") 
		    || this.falscheAntwort2.getText().contains("$") || this.falscheAntwort3.getText().contains("$")) {
		JOptionPane.showMessageDialog(this, "Das Zeichen '$' ist in den Antworten nicht erlaubt");
	    } else {
		VierAntwortenFrage frage = new VierAntwortenFrage((String) this.vorlesung.getSelectedItem(), 
			(String) this.thema.getSelectedItem(), this.frage.getText(), this.richtigeAntwort.getText(), 
			this.falscheAntwort1.getText(), this.falscheAntwort2.getText(), 
			this.falscheAntwort3.getText());
		container.linkFrage(frage);
		container.unlinkFrage(alteFrage);
		JOptionPane.showMessageDialog(null, "Die Frage wurde hinzugefuegt.");
		this.dispose();
	    }
	} else if (e.getSource().equals(abbrechen)) {
	    this.dispose();
	}
    }
}

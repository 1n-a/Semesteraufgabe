package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Einstellungen;

@SuppressWarnings("serial")
public class HinzufuegeDialog extends JDialog implements ActionListener {
    JComboBox<String> fragen;
    JButton ok;
    JButton abbrechen;
    
    public HinzufuegeDialog(JDialog owner) {
	super(owner, "Frage hinzufuegen", true);
	this.setLayout(new GridLayout(0, 1));
	
	Font officalFont = Einstellungen.instance().getOfficialFont();
	
	JLabel frage = new JLabel("Was fuer eine Frage moechtest du hinzufuegen?");
	frage.setFont(officalFont);
	this.add(frage);
	fragen = new JComboBox<String>();
	fragen.setFont(officalFont);
	fragen.addItem("VierAntwortenFrage");
	fragen.addItem("MuendlicheAntwortFrage");
	this.add(fragen);
	
	JPanel south = new JPanel();
	south.setLayout(new FlowLayout());
	ok = new JButton("OK");
	ok.setFont(officalFont);
	ok.addActionListener(this);
	south.add(ok);
	abbrechen = new JButton("Abbrechen");
	abbrechen.setFont(officalFont);
	abbrechen.addActionListener(this);
	south.add(abbrechen);
	
	this.add(south);
	
	this.pack();
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setVisible(true);
	
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(ok)) {
	    if (fragen.getSelectedItem().equals("VierAntwortenFrage")) {
		new VierAntwortenFrageBearbeiteDialog(null);
		this.dispose();
	    } else if (fragen.getSelectedItem().equals("MuendlicheAntwortFrage")) {
		new MuendlicheAntwortFrageBearbeitenDialog(null);
		this.dispose();
	    }
	} else if (e.getSource().equals(abbrechen)) {
	    this.dispose();
	}
    }
}

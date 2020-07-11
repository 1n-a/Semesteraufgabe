package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class HinzufuegeDialog extends JDialog implements ActionListener {
    JComboBox<String> fragen;
    JButton ok;
    JButton abbrechen;
    
    public HinzufuegeDialog(JDialog owner) {
	super(owner, "Frage hinzufügen", true);
	this.setLayout(new GridLayout(0, 1));
	
	JLabel frage = new JLabel("Was für eine Frage möchtest du hinzufügen?");
	this.add(frage);
	fragen = new JComboBox<String>();
	fragen.addItem("VierAntwortenFrage");
	fragen.addItem("MuendlicheAntwortFrage");
	this.add(fragen);
	
	JPanel south = new JPanel();
	south.setLayout(new FlowLayout());
	ok = new JButton("OK");
	ok.addActionListener(this);
	south.add(ok);
	abbrechen = new JButton("Abbrechen");
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

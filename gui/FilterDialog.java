package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.Frage;
import controller.Fragencontainer;

@SuppressWarnings("serial")
public class FilterDialog extends JDialog implements ActionListener, AdjustmentListener {
    private JTextField vorlesungen;
    private JTextField themen;
    private JScrollBar anzahlFragen;
    private Fragencontainer container;
    private JButton ok;
    private JButton abbrechen;
    private JLabel anzFra;
    private SpielManager manager;
    
    public FilterDialog(SpielManager manager) {
	super(new JDialog(), "Fragen filtern", true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	container = Fragencontainer.instance();
	this.manager = manager;
	
	JPanel north = new JPanel();
	north.setLayout(new GridLayout(0, 2));
	JLabel vorl = new JLabel("Vorlesungen (durch Semikolon getrennt): ");
	north.add(vorl);
	vorlesungen = new JTextField();
	north.add(vorlesungen);
	JLabel them = new JLabel("Themen (durch Semikolon getrennt): ");
	north.add(them);
	themen = new JTextField();
	north.add(themen);
	
	this.setLayout(new BorderLayout());
	this.add(north, BorderLayout.NORTH);
	
	JPanel center = new JPanel();
	center.setLayout(new GridLayout(0, 3));
	center.add(new JLabel("Anzahl Fragen: "));
	anzahlFragen = new JScrollBar(JScrollBar.HORIZONTAL, 1, 0, 1, container.getFragen().size());
	anzahlFragen.addAdjustmentListener(this);
	center.add(anzahlFragen);
	anzFra = new JLabel("1");
	center.add(anzFra);
	this.add(center, BorderLayout.CENTER);
	
	JPanel south = new JPanel();
	south.setLayout(new FlowLayout());
	ok = new JButton("OK");
	ok.addActionListener(this);
	abbrechen = new JButton("Abbrechen");
	abbrechen.addActionListener(this);
	south.add(ok);
	south.add(abbrechen);
	
	this.add(south, BorderLayout.SOUTH);
	
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
    
    public FilterDialog(SpielManager manager, String name1, String name2) {
	this(manager);
	this.manager.setNamen(name1, name2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("OK")) {
	    String[] vorlArray = this.vorlesungen.getText().split("\\;");
	    ArrayList<String> vorlesungen = new ArrayList<String>();
	    for (String s : vorlArray) {
		if (s.length() > 0) {
		    vorlesungen.add(s);
		}
	    }
	    if (vorlesungen.size() == 0) {
		vorlesungen = null;
	    }
	    String[] themArray = this.themen.getText().split("\\;");
	    ArrayList<String> themen = new ArrayList<String>();
	    for (String s : themArray) {
		if (s.length() > 0) {
		    themen.add(s);
		}
	    }
	    if (themen.size() == 0) {
		themen = null;
	    }
	    int anzahlFragen = this.anzahlFragen.getValue();
	    ArrayList<Frage> fragen = container.getFragenGefiltert(vorlesungen, themen, anzahlFragen);
	    manager.setFragen(fragen);
	    manager.init(fragen.size());
	    manager.next(0);
	    this.dispose();
	} else if (e.getActionCommand().equals("Abbrechen")) {
	    this.dispose();
	    new Hauptmenue("Hauptmenue");
	}
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
	anzFra.setText(e.getValue() + "");
    }
}

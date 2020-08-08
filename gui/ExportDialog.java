package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Einstellungen;
import controller.Frage;
import controller.Fragencontainer;

@SuppressWarnings("serial")
public class ExportDialog extends JDialog implements ActionListener {
    private JList<Frage> liste;
    private String filename;
    
    public ExportDialog(Frame owner, String filename) {
	super(owner, "Export...", true);
	this.filename = filename;
	
	Font officialFont = Einstellungen.instance().getOfficialFont();
	
	Fragencontainer container = Fragencontainer.instance();
	Vector<Frage> vector = new Vector<>();
	for (Frage f : container) {
	    vector.add(f);
	}
	liste = new JList<>(vector);
	this.add(new JScrollPane(liste), BorderLayout.CENTER);
	liste.setFont(officialFont);
	
	JPanel south = new JPanel(new GridLayout(1, 4));
	JButton ok = new JButton("Exportieren");
	ok.addActionListener(this);
	south.add(ok);
	ok.setFont(officialFont);
	JLabel label = new JLabel();
	south.add(label);
	JButton abbrechen = new JButton("Abbrechen");
	abbrechen.addActionListener(this);
	south.add(abbrechen);
	abbrechen.setFont(officialFont);
	JLabel label2 = new JLabel();
	south.add(label2);
	south.setBackground(GuiFarbauswahl.officialColor);
	this.add(south, BorderLayout.SOUTH);
	
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("Exportieren")) {
	    List<Frage> list = liste.getSelectedValuesList();
	    try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
		for (Frage f : list) {
		    writer.println(f.toStringTextdatei());
		}
		JOptionPane.showMessageDialog(this, "Die Fragen wurden erfolgreich in die ausgewählte Datei exportiert.");
	    } catch (IOException e1) {
		JOptionPane.showMessageDialog(this, "Fehler beim Beschreiben der Datei.");
	    } finally {
		this.dispose();
	    }
	} else if (e.getActionCommand().equals("Abbrechen")) {
	    this.dispose();
	}
    }

}

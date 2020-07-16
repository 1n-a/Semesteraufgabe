/**
 * modaler Dialog, der alle im Programm gespeicherten Fragen in einer JList anzeigt und Buttons hat, um diese
 * zu bearbeiten und löschen und neue Fragen hinzuzufügen.
 */

package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.*;

@SuppressWarnings("serial")
public class ListDialog extends JDialog implements ActionListener {
    Fragencontainer container = Fragencontainer.instance();
    JList<Frage> list;
    JButton hinzufuegen;
    JButton bearbeiten;
    JButton loeschen;
    Vector<Frage> listVector;
    
    public ListDialog() {
	super(new JFrame(), "Fragenliste", true);
	list = new JList<Frage>();
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.updateList();
	JScrollPane sp = new JScrollPane(list);
	sp.setPreferredSize(new Dimension(2000, 500));
	this.setLayout(new BorderLayout());
	this.add(sp, BorderLayout.NORTH);
	
	JPanel south = new JPanel(new GridLayout(0, 6));
	ImageIcon hinzufuegenBild = new ImageIcon(".\\src\\bilder\\hinzufuegen.png");
	ImageIcon bearbeitenBild = new ImageIcon(".\\src\\bilder\\bearbeiten.png");
	ImageIcon loeschenBild = new ImageIcon(".\\src\\bilder\\loeschen.png");
	
	hinzufuegen = new JButton(hinzufuegenBild);
	hinzufuegen.addActionListener(this);
	bearbeiten = new JButton(bearbeitenBild);
	bearbeiten.addActionListener(this);
	loeschen = new JButton(loeschenBild);
	loeschen.addActionListener(this);
	
	JLabel hin = new JLabel("neue Frage");
	JLabel bearb = new JLabel("Frage bearbeiten");
	JLabel loesch = new JLabel("Frage loeschen");
	
	south.add(hinzufuegen);
	south.add(hin);
	south.add(bearbeiten);
	south.add(bearb);
	south.add(loeschen);
	south.add(loesch);
	
	south.setBackground(GuiFarbauswahl.officialColor);
	this.add(south);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(hinzufuegen)) {
	    new HinzufuegeDialog(this);
	    this.updateList();
	} else if (e.getSource().equals(bearbeiten)) {
	    if (list.getSelectedValue() == null) {
		JOptionPane.showMessageDialog(null, "Waehle zuerst die Frage aus, die du bearbeiten moechtest!");
	    } else {
		new BearbeiteDialog(list.getSelectedValue());
		this.updateList();
	    }
	} else if (e.getSource().equals(loeschen)) {
	    if (list.getSelectedValue() == null) {
		JOptionPane.showMessageDialog(null, "Waehle zuerst die Frage aus, die du loeschen moechtest!");
	    } else {
		container.unlinkFrage(list.getSelectedValue());
		JOptionPane.showMessageDialog(null, "Die Frage wurde geloescht.");
		this.updateList();
	    }
	}
    }
    
    public void updateList() {
	listVector = new Vector<Frage>();
	for(Frage f : container.getFragen()) {
	    listVector.addElement(f);
	}
	list.setListData(listVector);
    }
}

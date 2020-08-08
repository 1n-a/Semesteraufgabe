package gui;

import javax.swing.*;

import controller.Einstellungen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GuiNamen extends JFrame implements ActionListener {

    private JButton exit = new JButton("Hauptmenue");
    private JButton weiter = new JButton("Weiter");
    private JTextArea fieldName1;
    private JTextArea fieldName2;
    
    private String modus;

    public GuiNamen(String title, String modus) {
        super(title);
        
        this.modus = modus;
        
        Font officialFont = Einstellungen.instance().getOfficialFont();
        
        //this.setSize(300, 180);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        //this.setBackground(officialColor);

        //JPanel center = new JPanel(new BorderLayout());
        //center.setBackground(officialColor);
        JPanel panelName1 = new JPanel(new FlowLayout());
        panelName1.setBackground(officialColor);
        JPanel panelName2 = new JPanel(new FlowLayout());
        panelName2.setBackground(officialColor);
        JPanel namenPanel = new JPanel(new BorderLayout());
        
        JLabel nameEingeben = new JLabel("Bitte Name eingeben!");
        nameEingeben.setFont(officialFont);
        //JLabel labelName1 = new JLabel("Spieler 1");
        //JLabel labelName2 = new JLabel("Spieler 2");

        fieldName1 = new JTextArea("Spieler1", 1, 15);
        fieldName2 = new JTextArea("Spieler2", 1, 15);
        fieldName1.setFont(officialFont);
        fieldName2.setFont(officialFont);


        //panelName1.add(labelName1);
        panelName1.add(fieldName1);

        //panelName2.add(labelName2);
        panelName2.add(fieldName2);

        namenPanel.add(panelName1, BorderLayout.NORTH);
        namenPanel.add(panelName2, BorderLayout.SOUTH);
        this.add(nameEingeben, BorderLayout.NORTH);
        this.add(namenPanel, BorderLayout.CENTER);
        //this.add(center, BorderLayout.CENTER);
        
        //Navigationsleiste unten
        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);

        this.add(south, BorderLayout.SOUTH);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(weiter);
        weiter.addActionListener(this);
        weiter.setFont(officialFont);
        navigation.add(exit);
        exit.addActionListener(this);
        exit.setFont(officialFont);

        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == weiter) {
            if (modus.equals("Mp")) {
        	//TODO: Fehler, falls Name leer
        	//System.out.println(fieldName1.getText() + " " + fieldName2.getText());
        	if (fieldName1.getText().length() < 1) {
        	    JOptionPane.showMessageDialog(null, "Der Name f�r Spieler 1 darf nicht leer sein!");
        	} else if (fieldName2.getText().length() < 1) {
        	    JOptionPane.showMessageDialog(null, "Der Name f�r Spieler 2 darf nicht leer sein!");
        	} else {
            		this.dispose();
            		new newFilterDialog(new MehrspielerManager(), fieldName1.getText(), fieldName2.getText());
        	}
            } else if (modus.equals("Buzzer")) {
        	if (fieldName1.getText().length() < 1) {
        	    JOptionPane.showMessageDialog(null, "Der Name f�r Spieler 1 darf nicht leer sein!");
        	} else if (fieldName2.getText().length() < 1) {
        	    JOptionPane.showMessageDialog(null, "Der Name f�r Spieler 2 darf nicht leer sein!");
        	} else {
            		this.dispose();
            		new newFilterDialog(new BuzzermodusManager(), fieldName1.getText(), fieldName2.getText());
        	}
            }
            //hier Funktion einfügen :D
        }
    }
}

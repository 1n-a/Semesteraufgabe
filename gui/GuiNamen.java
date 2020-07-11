package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiNamen extends JFrame implements ActionListener {

    private JButton exit = new JButton("Zurueck");
    private JButton weiter = new JButton("Weiter");

    public GuiNamen(String title) {
        super(title);
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(officialColor);
        JPanel panelName1 = new JPanel(new FlowLayout());
        panelName1.setBackground(officialColor);
        JPanel panelName2 = new JPanel(new FlowLayout());
        panelName2.setBackground(officialColor);

        JLabel nameEingeben = new JLabel("Bitte Name eingeben!");
        JLabel labelName1 = new JLabel("Spieler 1");
        JLabel labelName2 = new JLabel("Spieler 2");

        JTextArea fieldName1 = new JTextArea("Spieler1", 1, 15);
        JTextArea fieldName2 = new JTextArea("Spieler2", 1, 15);

        this.add(nameEingeben, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        center.add(panelName1, BorderLayout.NORTH);
        center.add(panelName2, BorderLayout.CENTER);

        //panelName1.add(labelName1);
        panelName1.add(fieldName1);

        //panelName2.add(labelName2);
        panelName2.add(fieldName2);

        //Navigationsleiste unten
        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);

        this.add(south, BorderLayout.SOUTH);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(weiter);
        weiter.addActionListener(this);
        navigation.add(exit);
        exit.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == weiter){
            //hier Funktion einfügen :D
        }
    }
}

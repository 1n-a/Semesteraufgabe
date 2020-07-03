package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hauptmenue extends JFrame implements ActionListener {

    private JButton spielen = new JButton("Spielen");
    private JButton erstellen = new JButton("Frage erstellen");
    private JButton bearbeiten = new JButton("Frage bearbeiten");
    private JButton loeschen = new JButton("Frage loeschen");
    private JButton exit = new JButton("Beenden");
    private JButton farbe = new JButton("Farbe");

    public Hauptmenue(String title) {
        super(title);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((EXIT_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);


        JPanel west = new JPanel(new GridLayout(0, 1));
        west.setBackground(officialColor);

        west.add(spielen);
        spielen.addActionListener(this);
        west.add(erstellen);
        west.add(bearbeiten);
        west.add(loeschen);

        this.add(west, BorderLayout.WEST);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(farbe);
        navigation.add(exit);
        exit.addActionListener(this);
        farbe.addActionListener(this);

        this.add(south, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen.
        if(e.getSource() == exit){
            System.exit(0);
        } else if(e.getSource() == spielen) {
            new Spielauswahl("Spielmodus");
            dispose();
        } else if(e.getSource() == farbe) {
            new GuiFarbauswahl("Farboptionen");
            dispose();
        }
    }
}

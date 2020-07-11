package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hauptmenue extends JFrame implements ActionListener {

    private JButton spielen = new JButton("Spielen");
    private JButton exit = new JButton("Beenden");

    private JMenuItem fragen = new JMenuItem("Fragen");
    private JMenuItem farbe = new JMenuItem("Farbe");


    public Hauptmenue(String title) {
        super(title);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((EXIT_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);

        //Menüleiste
        JMenuBar menuBar = new JMenuBar();
        JSeparator sep = new JSeparator();

        JMenu datei = new JMenu("Datei");
        JMenu einstellung = new JMenu("Einstellungen");

        menuBar.add(datei);
        datei.add(fragen);
        datei.add(sep);
        datei.add(einstellung);
        einstellung.add(farbe);

        fragen.addActionListener(this);
        farbe.addActionListener(this);

        setJMenuBar(menuBar);

        //Rumpf
        JPanel west = new JPanel(new FlowLayout());
        west.setBackground(officialColor);

        west.add(spielen);
        spielen.addActionListener(this);

        this.add(west, BorderLayout.WEST);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(exit);
        exit.addActionListener(this);

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
        } else if(e.getSource() == fragen) {
            new ListDialog();
        }
    }
}



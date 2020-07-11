package gui;

import javax.swing.*;

import controller.Einstellungen;
import controller.Farbe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;

public class GuiFarbauswahl extends JFrame implements ActionListener {

    static Color officialColor = Color.yellow;

    static Font officialFont = new Font("TimesRoman", PLAIN, 30);
    
    private Einstellungen einstellungen = Einstellungen.instance();

    private JButton exit = new JButton("Hauptmenue");
    private JButton dunkelLila = new JButton();
    private JButton gelb = new JButton();
    private JButton schwarz = new JButton();
    private JButton weiss = new JButton();
    private JButton grau = new JButton();
    private JButton pink = new JButton();

    public GuiFarbauswahl(String title) {
        super(title);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));

        dunkelLila.setBackground(new Color(Farbe.dunkelLila.getValue1(), Farbe.dunkelLila.getValue2(), Farbe.dunkelLila.getValue3()));
        gelb.setBackground(new Color(Farbe.gelb.getValue1(), Farbe.gelb.getValue2(), Farbe.gelb.getValue3()));
        schwarz.setBackground(new Color(Farbe.schwarz.getValue1(), Farbe.schwarz.getValue2(), Farbe.schwarz.getValue3()));
        weiss.setBackground(new Color(Farbe.weiss.getValue1(), Farbe.weiss.getValue2(), Farbe.weiss.getValue3()));
        grau.setBackground(new Color(Farbe.grau.getValue1(), Farbe.grau.getValue2(), Farbe.grau.getValue3()));
        pink.setBackground(new Color(Farbe.pink.getValue1(), Farbe.pink.getValue2(), Farbe.pink.getValue3()));

        this.add(new JLabel("Waehlen Sie eine neue Farbe!"), BorderLayout.NORTH);

        JPanel farbWahl = new JPanel(new GridLayout(3, 3));
        farbWahl.add(dunkelLila);
        farbWahl.add(gelb);
        farbWahl.add(schwarz);
        farbWahl.add(weiss);
        farbWahl.add(grau);
        farbWahl.add(pink);
        dunkelLila.addActionListener(this);
        gelb.addActionListener(this);
        schwarz.addActionListener(this);
        weiss.addActionListener(this);
        grau.addActionListener(this);
        pink.addActionListener(this);
        this.add(farbWahl, BorderLayout.CENTER);


        JPanel south = new JPanel(new BorderLayout());
        this.add(south, BorderLayout.SOUTH);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.add(exit);
        exit.addActionListener(this);

        south.add(navigation, BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == dunkelLila) {
            officialColor = new Color(Farbe.dunkelLila.getValue1(), Farbe.dunkelLila.getValue2(), Farbe.dunkelLila.getValue3());
            einstellungen.setFarbe(Farbe.dunkelLila.toString());
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == schwarz) {
            officialColor = new Color(Farbe.schwarz.getValue1(), Farbe.schwarz.getValue2(), Farbe.schwarz.getValue3());
            einstellungen.setFarbe(Farbe.schwarz.toString());
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == weiss) {
            officialColor = new Color(Farbe.weiss.getValue1(), Farbe.weiss.getValue2(), Farbe.weiss.getValue3());
            einstellungen.setFarbe(Farbe.weiss.toString());
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == gelb) {
            officialColor = new Color(Farbe.gelb.getValue1(), Farbe.gelb.getValue2(), Farbe.gelb.getValue3());
            einstellungen.setFarbe(Farbe.gelb.toString());
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == grau) {
            officialColor = new Color(Farbe.grau.getValue1(), Farbe.grau.getValue2(), Farbe.grau.getValue3());
            einstellungen.setFarbe(Farbe.grau.toString());
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == pink) {
            officialColor = new Color(Farbe.pink.getValue1(), Farbe.pink.getValue2(), Farbe.pink.getValue3());
            einstellungen.setFarbe(Farbe.pink.toString());
            new Hauptmenue("Hauptmenue");
            dispose();
        }
    }
}

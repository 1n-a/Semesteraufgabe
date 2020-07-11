package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;

public class GuiFarbauswahl extends JFrame implements ActionListener {

    static Color officialColor = Color.yellow;

    static Font officialFont = new Font("TimesRoman", PLAIN, 30);

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

        dunkelLila.setBackground(new Color(75, 58, 132));
        gelb.setBackground(Color.yellow);
        schwarz.setBackground((Color.black));
        weiss.setBackground(Color.white);
        grau.setBackground(new Color(142, 142, 142));
        pink.setBackground(new Color(255, 0, 255));

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
            officialColor = new Color(75, 58, 132);
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == schwarz) {
            officialColor = new Color(0,0,0);
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == weiss) {
            officialColor = new Color(255,255,255);
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == gelb) {
            officialColor = new Color(224, 224, 58);
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == grau) {
            officialColor = new Color(142, 142, 142);
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == pink) {
            officialColor = new Color(255, 0, 255);
            new Hauptmenue("Hauptmenue");
            dispose();
        }
    }
}

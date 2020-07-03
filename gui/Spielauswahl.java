package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spielauswahl extends JFrame implements ActionListener {

    private JButton standard = new JButton("Standard");
    private JButton abwechselnd = new JButton("Abwechselnd");
    private JButton duell = new JButton("Duell");
    private JButton exit = new JButton("Hauptmenue");

    public Spielauswahl(String title) {
        super(title);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);

        JLabel einzel = new JLabel("      Ein Spieler");
        JLabel duo = new JLabel("      Zwei Spieler");
        JLabel blank = new JLabel();

        JPanel west = new JPanel(new GridLayout(0, 2));
        west.setBackground(officialColor);

        west.add(einzel);
        west.add(duo);
        west.add(standard);
        west.add(abwechselnd);
        west.add(blank);
        west.add(duell);

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
            new Hauptmenue("Hauptmenue");
            dispose();
        }
    }
}

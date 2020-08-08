package gui;

import javax.swing.*;

import controller.Einstellungen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Spielauswahl extends JFrame implements ActionListener {

    private JButton standard = new JButton("Standard");
    private JButton abwechselnd = new JButton("Abwechselnd");
    private JButton duell = new JButton("Duell");
    private JButton exit = new JButton("Hauptmenue");

    public Spielauswahl(String title) {
        super(title);
        this.setSize(500, 300);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        Font officialFont = Einstellungen.instance().getOfficialFont();

        JLabel einzel = new JLabel("      Ein Spieler");
        einzel.setFont(officialFont);
        JLabel duo = new JLabel("      Zwei Spieler");
        duo.setFont(officialFont);
        JLabel blank = new JLabel();

        JPanel west = new JPanel(new GridLayout(0, 2));
        west.setBackground(officialColor);

        west.add(einzel);
        west.add(duo);
        west.add(standard);
        standard.setFont(officialFont);
        west.add(abwechselnd);
        abwechselnd.setFont(officialFont);
        west.add(blank);
        west.add(duell);
        duell.setFont(officialFont);

        this.add(west, BorderLayout.WEST);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);
        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(exit);
        exit.setFont(officialFont);
        exit.addActionListener(this);

        this.add(south, BorderLayout.SOUTH);
        this.standard.addActionListener(this);
        this.abwechselnd.addActionListener(this);
        this.duell.addActionListener(this);

        //this.pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen.
        if(e.getSource() == exit){
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == this.standard) {
            dispose();
            new newFilterDialog(new StandardspielManager());
        } else if (e.getSource() == this.abwechselnd) {
            dispose();
            new GuiNamen("Namen eingeben", "Mp");
        } else if (e.getSource() == this.duell) {
            dispose();
            new GuiNamen("Namen eingeben", "Buzzer");
        }
    }
}

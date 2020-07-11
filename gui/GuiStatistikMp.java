package gui;

import javax.swing.*;
import java.awt.*;


public class GuiStatistikMp extends JFrame {

    public GuiStatistikMp(String title) {

        super(title);
        this.setSize(200, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        setLayout(new GridLayout(2, 1));

        JPanel frameFrage = new JPanel(new FlowLayout());
        frameFrage.setBackground(officialColor);
        JPanel framePunktzahl = new JPanel(new FlowLayout());
        framePunktzahl.setBackground(officialColor);

        //diese müssen noch ersetzt werden
        String spieler1 = "Spieler 1";
        String spieler2 = "Spieler 2";
        int punktzahl1 = 0;
        int punktzahl2 = 1;
        int aktuelleFrage = 1;
        int maxFrage = 10;

        JLabel stringFrage = new JLabel("Frage ");
        JLabel anzeigeAktuelleFrage = new JLabel(String.valueOf(aktuelleFrage));
        JLabel trennerFrage = new JLabel(" / ");
        JLabel anzeigeMaxFrage = new JLabel(String.valueOf(maxFrage));

        //namen müssen natürlich noch übergeben werden
        JLabel name1 = new JLabel(spieler1);
        JLabel name2 = new JLabel(spieler2);
        JLabel punkteSpieler1 = new JLabel(String.valueOf(punktzahl1));
        JLabel trennerPunkte = new JLabel(" : ");
        JLabel punkteSpieler2 = new JLabel(String.valueOf(punktzahl2));


        framePunktzahl.add(name1);
        framePunktzahl.add(new JLabel("  "));
        framePunktzahl.add(punkteSpieler1);
        framePunktzahl.add(trennerPunkte);
        framePunktzahl.add(punkteSpieler2);
        framePunktzahl.add(new JLabel("  "));
        framePunktzahl.add(name2);
        frameFrage.add(stringFrage);
        frameFrage.add(anzeigeAktuelleFrage);
        frameFrage.add(trennerFrage);
        frameFrage.add(anzeigeMaxFrage);

        this.add(framePunktzahl);
        this.add(frameFrage);

        setVisible(true);
    }
}

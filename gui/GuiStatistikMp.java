package gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

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
        int punktzahl = 1;
        int aktuelleFrage = 1;
        int maxFrage = 10;

        JLabel anzeigePunkte = new JLabel(String.valueOf(punktzahl));
        JLabel stringFrage = new JLabel("Frage ");
        JLabel anzeigeAktuelleFrage = new JLabel(String.valueOf(aktuelleFrage));
        JLabel trennerFrage = new JLabel(" / ");
        JLabel anzeigeMaxFrage = new JLabel(String.valueOf(maxFrage));

        //namen müssen natürlich noch übergeben werden
        JLabel name1 = new JLabel("Spieler 1");
        JLabel name2 = new JLabel("Spieler 2");
        JLabel punkteSpieler1 = new JLabel("0");
        JLabel trennerPunkte = new JLabel(" : ");


        framePunktzahl.add(anzeigePunkte);
        frameFrage.add(stringFrage);
        frameFrage.add(anzeigeAktuelleFrage);
        frameFrage.add(trennerFrage);
        frameFrage.add(anzeigeMaxFrage);

        this.add(framePunktzahl);
        this.add(frameFrage);

        setVisible(true);
    }
}

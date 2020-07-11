package gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GuiStatistikStandard extends JFrame {

    public GuiStatistikStandard(String title) {

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

        JLabel stringPunkte = new JLabel("Punktzahl: ");
        JLabel anzeigePunkte = new JLabel(String.valueOf(punktzahl));
        JLabel stringFrage = new JLabel("Frage ");
        JLabel anzeigeAktuelleFrage = new JLabel(String.valueOf(aktuelleFrage));
        JLabel trennerFrage = new JLabel(" / ");
        JLabel anzeigeMaxFrage = new JLabel(String.valueOf(maxFrage));

        framePunktzahl.add(stringPunkte);
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

package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class GuiMuendlicheAntwortFrage extends JFrame implements ActionListener {

    private String loesungText;
    private String frageText;

    private JButton loesung = new JButton("Loesung anzeigen");
    private JButton exit = new JButton("Ende");
    private JButton frage = new JButton("Frage anzeigen");
    private JButton ja = new JButton("Ja");
    private JButton nein = new JButton("Nein");
    private JButton weiter = new JButton("Naechste Frage");

    private JLabel richtigeAntwort = new JLabel("Richtige Antwort?");

    private JTextArea frageFeld = new JTextArea(12, 20);

    private JPanel navigation;

    public GuiMuendlicheAntwortFrage(String title, String frageT, String loesungT) {

        super(title);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        Font officialFont = GuiFarbauswahl.officialFont;


        frageText = frageT;
        loesungText = loesungT;


        frageFeld = new JTextArea(frageText,12, 20);
        frageFeld.setFont(officialFont);
        JScrollPane scrollFrageFeld = new JScrollPane(frageFeld);
        frageFeld.setEnabled(true);
        frageFeld.setLineWrap(true);
        frageFeld.setWrapStyleWord(true);

        this.add(scrollFrageFeld, BorderLayout.NORTH);


        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);
        this.add(south, BorderLayout.SOUTH);

        JPanel frageNavigation = new JPanel(new FlowLayout());
        frageNavigation.setBackground(officialColor);
        south.add(frageNavigation, BorderLayout.WEST);
        frageNavigation.add(loesung);
        frageNavigation.add(frage);
        loesung.addActionListener(this);
        frage.addActionListener(this);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(weiter);
        navigation.add(exit);
        exit.addActionListener(this);


        JPanel loesungPanel = new JPanel(new FlowLayout());
        loesungPanel.setBackground(officialColor);
        south.add(loesungPanel, BorderLayout.NORTH);

        loesungPanel.add(richtigeAntwort);
        loesungPanel.add(ja);
        ja.addActionListener(this);
        loesungPanel.add(nein);
        nein.addActionListener(this);
        frage.setVisible(false);
        ja.setVisible(false);
        nein.setVisible(false);
        richtigeAntwort.setVisible(false);
        weiter.setVisible(false);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen.
        if(e.getSource() == exit){
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == loesung) {
            frageFeld.replaceRange(loesungText, 0, frageText.length());
            loesung.setVisible(false);
            richtigeAntwort.setVisible(true);
            ja.setVisible(true);
            nein.setVisible(true);
            frage.setVisible(true);
        } else if(e.getSource() == frage) {
            frageFeld.replaceRange(frageText, 0, loesungText.length());
            loesung.setVisible(true);
            frage.setVisible(false);
        } else if(e.getSource() == ja) {
            ja.setBackground(Color.green);
            ja.setEnabled(false);
            nein.setEnabled(false);
            weiter.setVisible(true);
        } else if(e.getSource() == nein) {
            nein.setBackground(Color.red);
            ja.setEnabled(false);
            nein.setEnabled(false);
            weiter.setVisible(true);
        }
    }
}

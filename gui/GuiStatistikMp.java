package gui;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class GuiStatistikMp extends JFrame {

    private int aktuelleFrage;
    private int maxFrage;
    private int punkteSpieler1;
    private int punkteSpieler2;
    
    private JLabel anzeigeAktuelleFrage;
    private JLabel anzeigeMaxFrage;
    private JLabel anzeigePunkteSpieler1;
    private JLabel anzeigePunkteSpieler2;
    private JLabel name1;
    private JLabel name2;
    
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

        JLabel stringFrage = new JLabel("Frage ");
        anzeigeAktuelleFrage = new JLabel(0 + "");
        JLabel trennerFrage = new JLabel(" / ");
        anzeigeMaxFrage = new JLabel(0 + "");

        name1 = new JLabel("");
        name2 = new JLabel("");
        anzeigePunkteSpieler1 = new JLabel(0 + "");
        JLabel trennerPunkte = new JLabel(" : ");
        anzeigePunkteSpieler2 = new JLabel(0 + "");


        framePunktzahl.add(name1);
        framePunktzahl.add(new JLabel("  "));
        framePunktzahl.add(anzeigePunkteSpieler1);
        framePunktzahl.add(trennerPunkte);
        framePunktzahl.add(anzeigePunkteSpieler2);
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
    
    public int getAktuelleFrage() {
        return aktuelleFrage;
    }

    public void setAktuelleFrage(int aktuelleFrage) {
        this.aktuelleFrage = aktuelleFrage;
        this.anzeigeAktuelleFrage.setText(aktuelleFrage + "");
    }

    public int getMaxFrage() {
        return maxFrage;
    }

    public void setMaxFrage(int maxFrage) {
        this.maxFrage = maxFrage;
        this.anzeigeMaxFrage.setText(maxFrage + "");
    }

    public int getPunkteSpieler1() {
        return punkteSpieler1;
    }

    public void setPunkteSpieler1(int punkteSpieler1) {
        this.punkteSpieler1 = punkteSpieler1;
        this.anzeigePunkteSpieler1.setText(punkteSpieler1 + "");
    }

    public int getPunkteSpieler2() {
        return punkteSpieler2;
    }

    public void setPunkteSpieler2(int punkteSpieler2) {
        this.punkteSpieler2 = punkteSpieler2;
        this.anzeigePunkteSpieler2.setText(punkteSpieler2 + "");
    }
    
    public void highlightSpieler1() {
	name1.setForeground(Color.yellow);
	name2.setForeground(Color.black);
    }
    
    public void highlightSpieler2() {
	name2.setForeground(Color.yellow);
	name1.setForeground(Color.black);
    }
    
    public void setName1(String name1) {
	this.name1.setText(name1);
    }
    
    public String getName1() {
	return name1.getText();
    }
    
    public void setName2(String name2) {
	this.name2.setText(name2);
    }
    
    public String getName2() {
	return name2.getText();
    }
    
    
}

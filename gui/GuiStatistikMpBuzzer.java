package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.Flow;


@SuppressWarnings("serial")
public class GuiStatistikMpBuzzer extends JFrame implements KeyListener, ActionListener {

    Timer timer = new Timer(1000, null);
    int counter;

    private JButton buzzer1;
    private JButton buzzer2;
    private JButton ueberspringen;

    private JButton bCountdown0 = new JButton("0");
    private JButton bCountdown1 = new JButton("1");
    private JButton bCountdown2 = new JButton("2");
    private JButton bCountdown3 = new JButton("3");
    private JButton bCountdown4 = new JButton("4");
    private JButton bCountdown5 = new JButton("5");

    private JLabel name1;
    private JLabel name2;
    
    private int maxFrage;
    private String spieler1;
    private String spieler2;
    private int punkteSpieler1 = 0;
    private int punkteSpieler2 = -1;
    private JLabel anzeigePunkteSpieler1;
    private JLabel anzeigePunkteSpieler2;
    private JLabel anzeigeAktuelleFrage;
    private int aktuelleFrage;
    private JLabel anzeigeMaxFrage;
    
    private boolean spieler1Gedrueckt = true;
    
    private BuzzermodusManager manager;

    public GuiStatistikMpBuzzer(String title, BuzzermodusManager manager) {

        super(title);
        
        this.manager = manager;
        
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        setLayout(new GridLayout(5, 1));

        JPanel frameFrage = new JPanel(new FlowLayout());
        frameFrage.setBackground(officialColor);
        JPanel framePunktzahl = new JPanel(new FlowLayout());
        framePunktzahl.setBackground(officialColor);
        JPanel frameCountdown = new JPanel(new FlowLayout());
        frameCountdown.setBackground((officialColor));
        JPanel frameUeberspringen = new JPanel(new FlowLayout());
        frameUeberspringen.setBackground((officialColor));

        buzzer1 = new JButton("A");
        buzzer2 = new JButton("L");
        ueberspringen = new JButton("Frage ueberspringen");

        spieler1 = "";
        spieler2 = "";
        int punktzahl1 = 0;
        int punktzahl2 = -1;
        aktuelleFrage = 0;

        JLabel stringFrage = new JLabel("Frage ");
        anzeigeAktuelleFrage = new JLabel(String.valueOf(aktuelleFrage));
        JLabel trennerFrage = new JLabel(" / ");
        anzeigeMaxFrage = new JLabel(String.valueOf(maxFrage));


        name1 = new JLabel(spieler1);
        name2 = new JLabel(spieler2);
        anzeigePunkteSpieler1 = new JLabel(String.valueOf(punktzahl1));
        JLabel trennerPunkte = new JLabel(" : ");
        anzeigePunkteSpieler2 = new JLabel(String.valueOf(punktzahl2));


        framePunktzahl.add(buzzer1);
        buzzer1.addKeyListener(this);
        framePunktzahl.add(name1);
        framePunktzahl.add(new JLabel("  "));
        framePunktzahl.add(anzeigePunkteSpieler1);
        framePunktzahl.add(trennerPunkte);
        framePunktzahl.add(anzeigePunkteSpieler2);
        framePunktzahl.add(new JLabel("  "));
        framePunktzahl.add(name2);
        framePunktzahl.add(buzzer2);
        frameFrage.add(stringFrage);
        frameFrage.add(anzeigeAktuelleFrage);
        frameFrage.add(trennerFrage);
        frameFrage.add(anzeigeMaxFrage);


        frameCountdown.add(bCountdown5);
        frameCountdown.add(bCountdown4);
        frameCountdown.add(bCountdown3);
        frameCountdown.add(bCountdown2);
        frameCountdown.add(bCountdown1);
        frameCountdown.add(bCountdown0);

        frameUeberspringen.add(ueberspringen);
        ueberspringen.addActionListener(this);

        this.add(framePunktzahl);
        this.add(frameFrage);
        this.add(frameCountdown);
        this.add(frameUeberspringen);

        pack();
        setVisible(true);
    }


    public void countdown5() {
        bCountdown5.setBackground(Color.yellow);
    }

    public void countdown4() {
        bCountdown4.setBackground(Color.yellow);
    }

    public void countdown3() {
        bCountdown3.setBackground(Color.yellow);
    }

    public void countdown2() {
        bCountdown2.setBackground(Color.yellow);
    }

    public void countdown1() {
        bCountdown1.setBackground(Color.yellow);
    }

    public void countdown0() {
        bCountdown0.setBackground(Color.yellow);
    }

    public void countdownEnd() {
        bCountdown0.setBackground(Color.red);
        bCountdown1.setBackground(Color.red);
        bCountdown2.setBackground(Color.red);
        bCountdown3.setBackground(Color.red);
        bCountdown4.setBackground(Color.red);
        bCountdown5.setBackground(Color.red);
    }

    
    public void deactivateBuzzer() {
        buzzer1.setEnabled(false);
        buzzer2.setEnabled(false);
        ueberspringen.setEnabled(false);
    }

    public void activateBuzzer() {
        buzzer1.setEnabled(true);
        buzzer2.setEnabled(true);
        ueberspringen.setEnabled(true);
    }

    public void resetCountdownAnzeige() {
	    timer.stop();
        bCountdown0.setBackground(Color.white);
        bCountdown1.setBackground(Color.white);
        bCountdown2.setBackground(Color.white);
        bCountdown3.setBackground(Color.white);
        bCountdown4.setBackground(Color.white);
        bCountdown5.setBackground(Color.white);
        name1.setForeground(Color.black);
        name2.setForeground(Color.black);
        counter = 6;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyChar()) == 'a') {
            name1.setForeground(Color.yellow);
            name2.setForeground(Color.black);
            spieler1Gedrueckt = true;
            manager.enableFragenGui();
            System.out.println("a");
            manager.buzzerP1Pressed();
        } else if ((e.getKeyChar()) == 'l') {
            name2.setForeground(Color.yellow);
            name1.setForeground(Color.black);
            spieler1Gedrueckt = false;
            manager.enableFragenGui();
            System.out.println("l");
            manager.buzzerP2Pressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean getSpieler1Gedrueckt() {
	return this.spieler1Gedrueckt;
    }


    public void setMaxFrage(int size) {
	this.maxFrage = size;
	this.anzeigeMaxFrage.setText(size + "");
    }
    
    public void setNamen(String name1, String name2) {
	this.spieler1 = name1;
	this.name1.setText(name1);
	this.spieler2 = name2;
	this.name2.setText(name2);
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
    
    public int getAktuelleFrage() {
	return this.aktuelleFrage;
    }
    
    public void setAktuelleFrage(int aktuelleFrage) {
	this.aktuelleFrage = aktuelleFrage;
	this.anzeigeAktuelleFrage.setText(aktuelleFrage + "");
    }
    
    public String getName1() {
	return this.spieler1;
    }
    
    public String getName2() {
	return this.spieler2;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ueberspringen) {
            manager.frageUeberspringen();
        }
    }

    public void setFocusOnBuzzer() {
        buzzer1.requestFocus();
    }

}

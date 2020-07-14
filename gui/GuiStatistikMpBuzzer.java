package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class GuiStatistikMpBuzzer extends JFrame implements KeyListener {

    Timer timer;
    int counter;

    private JButton buzzer1;
    private JButton buzzer2;

    private JButton bCountdown0 = new JButton("0");
    private JButton bCountdown1 = new JButton("1");
    private JButton bCountdown2 = new JButton("2");
    private JButton bCountdown3 = new JButton("3");
    private JButton bCountdown4 = new JButton("4");
    private JButton bCountdown5 = new JButton("5");

    private JLabel name1;
    private JLabel name2;

    private String tonZaehlen = ".\\src\\audioDateien\\tonZaehlen_neu.wav";
    private String tonEnde = ".\\src\\audioDateien\\tonEnde_neu.wav";
    private Clip clipZaehlen = null;
    private Clip clipEnde = null;
    private AudioInputStream streamZaehlen = null;
    private AudioInputStream streamEnde = null;

    public GuiStatistikMpBuzzer(String title) {

        super(title);
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DISPOSE_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        setLayout(new GridLayout(3, 1));

        JPanel frameFrage = new JPanel(new FlowLayout());
        frameFrage.setBackground(officialColor);
        JPanel framePunktzahl = new JPanel(new FlowLayout());
        framePunktzahl.setBackground(officialColor);
        JPanel frameCountdown = new JPanel(new FlowLayout());
        frameCountdown.setBackground((officialColor));

        buzzer1 = new JButton("A");
        buzzer2 = new JButton("L");

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


        name1 = new JLabel(spieler1);
        name2 = new JLabel(spieler2);
        JLabel punkteSpieler1 = new JLabel(String.valueOf(punktzahl1));
        JLabel trennerPunkte = new JLabel(" : ");
        JLabel punkteSpieler2 = new JLabel(String.valueOf(punktzahl2));


        framePunktzahl.add(buzzer1);
        framePunktzahl.add(name1);
        framePunktzahl.add(new JLabel("  "));
        framePunktzahl.add(punkteSpieler1);
        framePunktzahl.add(trennerPunkte);
        framePunktzahl.add(punkteSpieler2);
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

        this.add(framePunktzahl);
        this.add(frameFrage);
        this.add(frameCountdown);

        buzzer1.addKeyListener(this);

        setVisible(true);
    }


    public void countdown() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                
                try {
        	    streamZaehlen = AudioSystem.getAudioInputStream(new File(tonZaehlen).getAbsoluteFile());
        	    clipZaehlen = AudioSystem.getClip(); 
        	    clipZaehlen.open(streamZaehlen);
        	    streamEnde = AudioSystem.getAudioInputStream(new File(tonEnde).getAbsoluteFile());
        	    clipEnde = AudioSystem.getClip();
        	    clipEnde.open(streamEnde);
        	} catch (Exception e1) {
        	    e1.printStackTrace();
        	}
                
                if(counter == 5) {
                    bCountdown5.setBackground(Color.yellow);
                    try {
                	if (clipZaehlen != null) {
                	    clipZaehlen.start();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                } else if(counter == 4) {
                    bCountdown4.setBackground(Color.yellow);
                    try {
                	if (clipZaehlen != null) {
                	    clipZaehlen.start();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                } else if(counter == 3) {
                    bCountdown3.setBackground(Color.yellow);
                    try {
                	if (clipZaehlen != null) {
                	    clipZaehlen.start();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                } else if(counter == 2) {
                    bCountdown2.setBackground(Color.yellow);
                    try {
                	if (clipZaehlen != null) {
                	    clipZaehlen.start();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                } else if(counter == 1) {
                    bCountdown1.setBackground(Color.yellow);
                    try {
                	if (clipZaehlen != null) {
                	    clipZaehlen.start();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                } else if(counter == 0) {
                    bCountdown0.setBackground(Color.yellow);
                    try {
                	if (clipEnde != null) {
                	    clipEnde.start();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                } else if(counter == -1) {
                    bCountdown0.setBackground(Color.red);
                    bCountdown1.setBackground(Color.red);
                    bCountdown2.setBackground(Color.red);
                    bCountdown3.setBackground(Color.red);
                    bCountdown4.setBackground(Color.red);
                    bCountdown5.setBackground(Color.red);
                    timer.stop();
                    try {
                	if (clipZaehlen != null) {
                	    clipZaehlen.close();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                    try {
                	if (clipEnde != null) {
                	    clipEnde.close();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                    try {
                	if (streamZaehlen != null) {
                	    streamZaehlen.close();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                    try {
                	if (streamEnde != null) {
                	    streamEnde.close();
                	}
                    } catch (Exception e2) {
                	e2.printStackTrace();
                    }
                }
            }
        });
    }

    public void deactivateBuzzer() {
        buzzer1.setEnabled(false);
        buzzer2.setEnabled(false);
    }

    public void activateBuzzer() {
        buzzer1.setEnabled(true);
        buzzer2.setEnabled(true);
    }

    public void resetCountdownAnzeige() {
        bCountdown0.setBackground(Color.white);
        bCountdown1.setBackground(Color.white);
        bCountdown2.setBackground(Color.white);
        bCountdown3.setBackground(Color.white);
        bCountdown4.setBackground(Color.white);
        bCountdown5.setBackground(Color.white);
        counter = 6;
    }

    public void startCountdown () {
        deactivateBuzzer();
        countdown();
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //Funktion des KeyPressedEvents anpassen
    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyChar()) == 'a') {
            name1.setForeground(Color.yellow);
            System.out.println("a");
        } else if ((e.getKeyChar()) == 'l') {
            name2.setForeground(Color.yellow);
            System.out.println("l");
        }
        resetCountdownAnzeige();
        startCountdown();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

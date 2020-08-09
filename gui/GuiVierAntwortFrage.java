package gui;

import javax.swing.*;

import controller.Einstellungen;
import controller.VierAntwortenFrage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("serial")
public class GuiVierAntwortFrage extends GuiFrage implements ActionListener {

    private char loesung;

    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;
    private JButton buttonD;

    private JTextArea feldARichtig;
    private JTextArea feldAFalsch1;
    private JTextArea feldAFalsch2;
    private JTextArea feldAFalsch3;

    private JScrollPane scrollFeldARichtig;
    private JScrollPane scrollFeldAFalsch1;
    private JScrollPane scrollFeldAFalsch2;
    private JScrollPane scrollFeldAFalsch3;

    private JButton weiter = new JButton("naechste Frage");
    private JButton exit = new JButton("Ende");
    private SpielManager manager;

    private boolean richtig = false;
    private boolean antwortGegeben = false;



    public GuiVierAntwortFrage(String title, String frageText, String aRichtig, String aFalsch1, String aFalsch2, String aFalsch3) {

        super(title);
        setAntwortGegeben(false);
        //this.setSize(500, 300);
        this.setDefaultCloseOperation((DO_NOTHING_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        Font officialFont = Einstellungen.instance().getOfficialFont();


        buttonA = new JButton("A");
        buttonA.setFont(officialFont);
        buttonA.setSize(new Dimension(10, 10));
        buttonB = new JButton("B");
        buttonB.setFont(officialFont);
        buttonB.setSize(new Dimension(10, 10));
        buttonC = new JButton("C");
        buttonC.setFont(officialFont);
        buttonC.setSize(new Dimension(10, 10));
        buttonD = new JButton("D");
        buttonD.setFont(officialFont);
        buttonD.setSize(new Dimension(10, 10));

        feldARichtig = new JTextArea(aRichtig,0, 0);
        feldARichtig.setFont(officialFont);
        feldEigenschaftenSetzen(feldARichtig);
        feldAFalsch1 = new JTextArea(aFalsch1,0, 0);
        feldAFalsch1.setFont(officialFont);
        feldEigenschaftenSetzen(feldAFalsch1);
        feldAFalsch2 = new JTextArea(aFalsch2,0, 0);
        feldAFalsch2.setFont(officialFont);
        feldEigenschaftenSetzen(feldAFalsch2);
        feldAFalsch3 = new JTextArea(aFalsch3,0, 0);
        feldAFalsch3.setFont(officialFont);
        feldEigenschaftenSetzen(feldAFalsch3);

        scrollFeldARichtig = new JScrollPane(feldARichtig);
        scrollFeldAFalsch1 = new JScrollPane(feldAFalsch1);
        scrollFeldAFalsch2 = new JScrollPane(feldAFalsch2);
        scrollFeldAFalsch3 = new JScrollPane(feldAFalsch3);

        JScrollPane antworten[] = {scrollFeldARichtig, scrollFeldAFalsch1, scrollFeldAFalsch2, scrollFeldAFalsch3};
        Collections.shuffle(Arrays.asList(antworten));

        if (antworten[0] == scrollFeldARichtig) {
            loesung = 'a';
        } else if (antworten[1] == scrollFeldARichtig) {
            loesung = 'b';
        } else if (antworten[2] == scrollFeldARichtig) {
            loesung = 'c';
        } else {
            loesung = 'd';
        }

        JTextArea frageFeld = new JTextArea(frageText,7, 20);
        frageFeld.setFont(officialFont);
        feldEigenschaftenSetzen(frageFeld);
        JScrollPane scrollFrageFeld = new JScrollPane(frageFeld);

        this.add(scrollFrageFeld, BorderLayout.NORTH);

        JPanel antwortButtonPanel = new JPanel(new GridLayout(4, 1));
        antwortButtonPanel.setBackground(officialColor);
        this.add(antwortButtonPanel, BorderLayout.WEST);

        JPanel antwortPanel = new JPanel(new GridLayout(4, 1));
        antwortPanel.setBackground(officialColor);
        this.add(antwortPanel, BorderLayout.CENTER);

        antwortButtonPanel.add(buttonA);
        antwortButtonPanel.add(buttonB);
        antwortButtonPanel.add(buttonC);
        antwortButtonPanel.add(buttonD);
        antwortPanel.add(antworten[0]);
        antwortPanel.add(antworten[1]);
        antwortPanel.add(antworten[2]);
        antwortPanel.add(antworten[3]);

        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);
        buttonD.addActionListener(this);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);
        this.add(south, BorderLayout.SOUTH);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(weiter);
        weiter.setFont(officialFont);
        navigation.add(exit);
        exit.setFont(officialFont);
        weiter.addActionListener(this);
        exit.addActionListener(this);
        weiter.setVisible(false);

        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public GuiVierAntwortFrage(VierAntwortenFrage f, SpielManager manager) {
	this("Frage", f.getFrage(), f.getAntworten()[0], f.getAntworten()[1], 
		f.getAntworten()[2], f.getAntworten()[3]);
	this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit) {
            manager.next(-1);
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == weiter) {
            if (richtig) {
        	manager.next(1);
            } else {
        	manager.next(0);
            }
            this.dispose();
        } else if (e.getSource() == buttonA) {
            if (loesung == 'a') {
                richtig = true;
                feldARichtig.setBackground(Color.green);
            } else {
                feldARichtig.setBackground(Color.green);
                feldAFalsch1.setBackground(Color.red);
                feldAFalsch2.setBackground(Color.red);
                feldAFalsch3.setBackground(Color.red);
            }
            setAntwortGegeben(true);
            deactivateAnswers();
        } else if (e.getSource() == buttonB) {
            if (loesung == 'b') {
                richtig = true;
                feldARichtig.setBackground(Color.green);
            } else {
                feldARichtig.setBackground(Color.green);
                feldAFalsch1.setBackground(Color.red);
                feldAFalsch2.setBackground(Color.red);
                feldAFalsch3.setBackground(Color.red);
            }
            setAntwortGegeben(true);
            deactivateAnswers();
        } else if (e.getSource() == buttonC) {
            if (loesung == 'c') {
                richtig = true;
                feldARichtig.setBackground(Color.green);
            } else {
                feldARichtig.setBackground(Color.green);
                feldAFalsch1.setBackground(Color.red);
                feldAFalsch2.setBackground(Color.red);
                feldAFalsch3.setBackground(Color.red);
            }
            setAntwortGegeben(true);
            deactivateAnswers();
        } else if (e.getSource() == buttonD) {
            if (loesung == 'd') {
                richtig = true;
                feldARichtig.setBackground(Color.green);
            } else {
                feldARichtig.setBackground(Color.green);
                feldAFalsch1.setBackground(Color.red);
                feldAFalsch2.setBackground(Color.red);
                feldAFalsch3.setBackground(Color.red);
            }
            setAntwortGegeben(true);
            deactivateAnswers();
        }
    }

    public void deactivateAnswers() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        weiter.setVisible(true);
    }

    public void activateAnswers() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

    private void feldEigenschaftenSetzen(JTextArea textArea) {
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.black);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    @Override
    public void showAnswer() {
        richtig = false;
        feldARichtig.setBackground(Color.green);
        feldAFalsch1.setBackground(Color.red);
        feldAFalsch2.setBackground(Color.red);
        feldAFalsch3.setBackground(Color.red);
        deactivateAnswers();
    }

    public void setAntwortGegeben (boolean wert) {
        antwortGegeben = wert;
    }

    public boolean getAntwortGegeben () {
        return antwortGegeben;
    }

    public void weiterInvisible() {
        weiter.setVisible(false);
    }
}

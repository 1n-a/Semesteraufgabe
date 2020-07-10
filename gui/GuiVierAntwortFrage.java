package gui;

import javax.swing.*;

import controller.VierAntwortenFrage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

public class GuiVierAntwortFrage extends JFrame implements ActionListener {

    private JButton buttonRichtig;
    private JButton buttonFalsch1;
    private JButton buttonFalsch2;
    private JButton buttonFalsch3;
    private JButton weiter = new JButton("naechste Frage");
    private JButton exit = new JButton("Ende");
    private SpielManager manager;
    private boolean richtig = false;



    public GuiVierAntwortFrage(String title, String frageText, String aRichtig, String aFalsch1, String aFalsch2, String aFalsch3) {

        super(title);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((DO_NOTHING_ON_CLOSE));
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        Font officialFont = GuiFarbauswahl.officialFont;

        buttonRichtig = new JButton(aRichtig);
        buttonFalsch1 = new JButton(aFalsch1);
        buttonFalsch2 = new JButton(aFalsch2);
        buttonFalsch3 = new JButton(aFalsch3);

        JButton[] antworten = {buttonRichtig, buttonFalsch1, buttonFalsch2, buttonFalsch3};

        Collections.shuffle(Arrays.asList(antworten));

        JTextArea frageFeld = new JTextArea(frageText,7, 20);
        JScrollPane scrollFrageFeld = new JScrollPane(frageFeld);
        frageFeld.setFont(officialFont);
        frageFeld.setEnabled(false);
        frageFeld.setDisabledTextColor(Color.black);
        frageFeld.setLineWrap(true);
        frageFeld.setWrapStyleWord(true);

        this.add(scrollFrageFeld, BorderLayout.NORTH);

        JPanel antwortPanel = new JPanel(new GridLayout(2, 2));
        antwortPanel.setBackground(officialColor);
        this.add(antwortPanel, BorderLayout.CENTER);

        for (int i = 0; i < 4; ++i) {
            antwortPanel.add(antworten[i]);
        }
        buttonRichtig.addActionListener(this);
        buttonFalsch1.addActionListener(this);
        buttonFalsch2.addActionListener(this);
        buttonFalsch3.addActionListener(this);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);
        this.add(south, BorderLayout.SOUTH);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(weiter);
        navigation.add(exit);
        weiter.addActionListener(this);
        exit.addActionListener(this);
        weiter.setVisible(false);


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
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if (e.getSource() == weiter) {
            //funktion einfügen
            if (richtig) {
        	manager.next(1);
            } else {
        	manager.next(0);
            }
            this.dispose();
        } else if (e.getSource() == buttonRichtig) {
            buttonRichtig.setBackground(Color.green);
            deactivateAnswers();
            richtig = true;
        } else if (e.getSource() == buttonFalsch1) {
            buttonFalsch1.setBackground(Color.red);
            buttonRichtig.setBackground(Color.green);
            deactivateAnswers();
        } else if (e.getSource() == buttonFalsch2) {
            buttonFalsch2.setBackground(Color.red);
            buttonRichtig.setBackground(Color.green);
            deactivateAnswers();
        } else if (e.getSource() == buttonFalsch3) {
            buttonFalsch3.setBackground(Color.red);
            buttonRichtig.setBackground(Color.green);
            deactivateAnswers();
        }
    }

    public void deactivateAnswers() {
        buttonRichtig.setEnabled(false);
        buttonFalsch1.setEnabled(false);
        buttonFalsch2.setEnabled(false);
        buttonFalsch3.setEnabled(false);
        weiter.setVisible(true);
    }
}

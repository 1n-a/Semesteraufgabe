package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCardLayoutTest extends JFrame implements ActionListener {

    private JButton btnPrev;
    private JButton btnNext;
    private JButton btnFirst;
    private JButton btnLast;
    private JButton btn5;

    private CardLayout cl;

    private JPanel pnlMain;

    public GuiCardLayoutTest() {
        super("Titel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new FlowLayout());
        pnlMain = new JPanel();
        cl = new CardLayout();
        pnlMain.setLayout(cl);

        this.add(pnlButton, BorderLayout.SOUTH);
        this.add(pnlMain, BorderLayout.CENTER);


        btnPrev = new JButton("<");
        btnNext = new JButton(">");
        btnFirst = new JButton("Start");
        btnLast = new JButton("Ende");
        btn5 = new JButton("Mitte");

        pnlButton.add(btnPrev);
        pnlButton.add(btnNext);
        pnlButton.add(btnFirst);
        pnlButton.add(btnLast);
        pnlButton.add(btn5);

        btnPrev.addActionListener(this);
        btnNext.addActionListener(this);
        btnFirst.addActionListener(this);
        btnLast.addActionListener(this);
        btn5.addActionListener(this);

        for(int i=0;i<10;i++){
            pnlMain.add(new JTextArea(
                    "Karte " + i), "" + i);
        }


        setSize(400,500);
        setLocation(50,50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnNext)){
            cl.next(pnlMain);
        }else if(e.getSource().equals(btnPrev)){
            cl.previous(pnlMain);
        }else if(e.getSource().equals(btnFirst)){
            cl.first(pnlMain);
        }else if(e.getSource().equals(btnLast)){
            cl.last(pnlMain);
        }else{
            cl.show(pnlMain, "5");
        }

    }
}

package gui;

import controller.Fragencontainer;
import controller.VorlesungenThemenContainer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class newFilterDialog extends JDialog implements ActionListener, AdjustmentListener {

    Fragencontainer container = Fragencontainer.instance();

    private JTextField vorlesungen;
    private JTextField themen;

    private JScrollBar anzahlFragen;

    private JButton btnOk;
    private JButton btnAbbrechen;

    private JPanel pnlMain;

    private CardLayout cardLayout;

    private JLabel anzFra;

    private JComboBox<String> cbVorlesungen;

    private SpielManager manager;

    public newFilterDialog(SpielManager manager) {
        super(new JDialog(), "Fragen filtern", true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.manager = manager;

        VorlesungenThemenContainer vtc = VorlesungenThemenContainer.instance();

        String[] stringThemen = {"Thema1", "Thema2", "Thema3"};

        cbVorlesungen = new JComboBox<>();
        cbVorlesungen.addActionListener(this);

        ArrayList<String> listVorlesungen = vtc.getVorlesungen();
        ArrayList<JPanel> pnlVorlesungen = new ArrayList<>();
        ArrayList<ArrayList<JLabel>> sammlungThemenVls = new ArrayList<>();


        pnlMain = new JPanel();
        cardLayout = new CardLayout();
        pnlMain.setLayout(cardLayout);



        for(int i = 0; i < listVorlesungen.size(); ++i) {
            cbVorlesungen.addItem(listVorlesungen.get(i));

            ArrayList<String> listThemen = vtc.getThemenZuVorlesung(listVorlesungen.get(i));
            JPanel neuesPanel = new JPanel();

            for(int j = 0; j < listThemen.size(); ++j) {
                JCheckBox neuesThema = new JCheckBox(listThemen.get(j));
                neuesThema.addActionListener(this);
                neuesPanel.add(neuesThema);
            }

            pnlMain.add(neuesPanel, Integer.toString(i));


            sammlungThemenVls.add(new ArrayList<JLabel>());

        }







        //GUI Navigation
        JPanel south = new JPanel(new BorderLayout());
        JPanel navigation = new JPanel(new FlowLayout());
        south.add(navigation, BorderLayout.EAST);

        btnOk = new JButton("OK");
        btnAbbrechen = new JButton("Abbrechen");
        navigation.add(btnOk);
        navigation.add(btnAbbrechen);
        btnOk.addActionListener(this);
        btnAbbrechen.addActionListener(this);

        this.add(cbVorlesungen, BorderLayout.NORTH);
        this.add(pnlMain, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);


        setSize(600, 300);
        setVisible(true);


        /*
        JPanel north = new JPanel();
        north.setLayout(new GridLayout(0, 2));
        JLabel vorl = new JLabel("Vorlesungen (durch Semikolon getrennt): ");
        north.add(vorl);
        vorlesungen = new JTextField();
        north.add(vorlesungen);
        JLabel them = new JLabel("Themen (durch Semikolon getrennt): ");
        north.add(them);
        themen = new JTextField();
        north.add(themen);

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 3));
        center.add(new JLabel("Anzahl Fragen: "));
        anzahlFragen = new JScrollBar(JScrollBar.HORIZONTAL, 1, 0, 1, container.getFragen().size());
        anzahlFragen.addAdjustmentListener(this);
        center.add(anzahlFragen);
        anzFra = new JLabel("1");
        center.add(anzFra);
        this.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        ok = new JButton("OK");
        ok.addActionListener(this);
        abbrechen = new JButton("Abbrechen");
        abbrechen.addActionListener(this);
        south.add(ok);
        south.add(abbrechen);

        this.add(south, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        */
    }

    public newFilterDialog(SpielManager manager, String name1, String name2) {
        this(manager);
        this.manager.setNamen(name1, name2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            String[] vorlArray = this.vorlesungen.getText().split("\\;");
            ArrayList<String> vorlesungen = new ArrayList<String>();
            for (String s : vorlArray) {
                if (s.length() > 0) {
                    vorlesungen.add(s);
                }
            }
            if (vorlesungen.size() == 0) {
                vorlesungen = null;
            }
            String[] themArray = this.themen.getText().split("\\;");
            ArrayList<String> themen = new ArrayList<String>();
            for (String s : themArray) {
                if (s.length() > 0) {
                    themen.add(s);
                }
            }
            if (themen.size() == 0) {
                themen = null;
            }
            /*
            int anzahlFragen = this.anzahlFragen.getValue();
            ArrayList<Frage> fragen = container.getFragenGefiltert(vorlesungen, themen, anzahlFragen);
            manager.setFragen(fragen);
            manager.init(fragen.size());
            manager.next(0);
            this.dispose();
            */
        } else if (e.getSource() == btnAbbrechen) {
            this.dispose();
            new Hauptmenue("Hauptmenue");
        } else if (e.getSource() == cbVorlesungen) {
            int index = cbVorlesungen.getSelectedIndex();
            System.out.println(index);
            cardLayout.show(pnlMain, Integer.toString(index));
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        anzFra.setText(e.getValue() + "");
    }
}

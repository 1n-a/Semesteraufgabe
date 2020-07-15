package gui;

import controller.Frage;
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
   
    private ArrayList<JCheckBox> themenBoxen = new ArrayList<>();

    public newFilterDialog(SpielManager manager) {
        super(new JDialog(), "Fragen filtern", true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.manager = manager;

        VorlesungenThemenContainer vtc = VorlesungenThemenContainer.instance();


        cbVorlesungen = new JComboBox<>();
        cbVorlesungen.addActionListener(this);

        ArrayList<String> listVorlesungen = vtc.getVorlesungen();
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
        
        JPanel center = new JPanel(new BorderLayout());
        center.add(new JLabel("Anzahl Fragen: "), BorderLayout.WEST);
        int max = 0;
        if (container.getFragen().size() > 15) {
            max = 15;
        } else {
            max = container.getFragen().size();
        }
        anzahlFragen = new JScrollBar(JScrollBar.HORIZONTAL, 1, 0, 1, max);
        //anzahlFragen.setMinimumSize(new Dimension(300, 10));
        anzahlFragen.addAdjustmentListener(this);
        center.add(anzahlFragen, BorderLayout.CENTER);
        anzFra = new JLabel("1");
        center.add(anzFra, BorderLayout.EAST);
        south.add(center, BorderLayout.NORTH);
        
        
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
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public newFilterDialog(SpielManager manager, String name1, String name2) {
        this(manager);
        this.manager.setNamen(name1, name2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            int anzahlFragen = this.anzahlFragen.getValue();
            ArrayList<String> stringThemen = new ArrayList<>();
            for (JCheckBox j : themenBoxen) {
        	stringThemen.add(j.getActionCommand());
            }
            ArrayList<String> stringVorlesungen = new ArrayList<>();
            stringVorlesungen.add((String) cbVorlesungen.getSelectedItem()); 
            ArrayList<Frage> fragen = container.getFragenGefiltert(stringVorlesungen, stringThemen, anzahlFragen);
            manager.setFragen(fragen);
            manager.init(fragen.size());
            manager.next(0);
            this.dispose();
        } else if (e.getSource() == btnAbbrechen) {
            this.dispose();
            new Hauptmenue("Hauptmenue");
        } else if (e.getSource() == cbVorlesungen) {
            int index = cbVorlesungen.getSelectedIndex();
            System.out.println(index);
            cardLayout.show(pnlMain, Integer.toString(index));
        } else if (e.getSource() instanceof JCheckBox) {
            JCheckBox box = (JCheckBox) e.getSource();
            if (box.isSelected()) {
        	themenBoxen.add(box);
            } else {
        	themenBoxen.remove(box);
            }
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        anzFra.setText(e.getValue() + "");
    }
}

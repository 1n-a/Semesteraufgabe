package gui;
/**
 * TODO weiter GuiStatistikMp
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Einstellungen;
import controller.Fragencontainer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Hauptmenue extends JFrame implements ActionListener {

    private JButton spielen = new JButton("Spielen");
    private JButton exit = new JButton("Beenden");

    private JMenuItem fragen = new JMenuItem("Fragen");
    private JMenuItem farbe = new JMenuItem("Farbe");
    private JMenuItem im = new JMenuItem("Import...");
    private JMenuItem ex = new JMenuItem("Export...");
    private JMenuItem times = new JMenuItem("TimesRoman");
    private JMenuItem courier = new JMenuItem("Courier");
    private JMenuItem arial = new JMenuItem("Arial");
    //private JMenuItem def = new JMenuItem("default");
    private JMenuItem klein = new JMenuItem("klein");
    private JMenuItem mittel = new JMenuItem("mittel");
    private JMenuItem gross = new JMenuItem("gross");
    private JMenuItem normal = new JMenuItem("normal");
    private JMenuItem kursiv = new JMenuItem("kursiv");
    private JMenuItem fett = new JMenuItem("fett");

    private Einstellungen einstellungen = Einstellungen.instance();
    
    public Hauptmenue(String title) {
        super(title);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation((EXIT_ON_CLOSE));
        
        Einstellungen einstellungen = Einstellungen.instance();
        GuiFarbauswahl.officialColor = einstellungen.getColor();
        this.setFont(einstellungen.getOfficialFont());
        
        Color officialColor = GuiFarbauswahl.officialColor;
        this.getContentPane().setBackground(officialColor);
        
        //Menüleiste
        JMenuBar menuBar = new JMenuBar();
        JSeparator sep = new JSeparator();

        JMenu datei = new JMenu("Datei");
        datei.setFont(einstellungen.getOfficialFont());
        JMenu einstellung = new JMenu("Einstellungen");
        einstellung.setFont(einstellungen.getOfficialFont());
        JMenu schrift = new JMenu("Schrift");
        schrift.setFont(einstellungen.getOfficialFont());
        JMenu schriftart = new JMenu("Schriftart");
        schriftart.setFont(einstellungen.getOfficialFont());
        JMenu schriftgroesse = new JMenu("Schriftgroesse");
        schriftgroesse.setFont(einstellungen.getOfficialFont());
        JMenu stil = new JMenu("Stil");
        stil.setFont(einstellungen.getOfficialFont());

        menuBar.add(datei);
        datei.add(fragen);
        fragen.setFont(einstellungen.getOfficialFont());
        datei.add(sep);
        datei.add(im);
        im.setFont(einstellungen.getOfficialFont());
        datei.add(ex);
        ex.setFont(einstellungen.getOfficialFont());
        datei.add(new JSeparator());
        datei.add(einstellung);
        einstellung.add(farbe);
        farbe.setFont(einstellungen.getOfficialFont());
        einstellung.add(new JSeparator());
        einstellung.add(schrift);
        schrift.add(schriftart);
        schriftart.add(times);
        times.setFont(new Font("TimesRoman", einstellungen.getOfficialFont().getStyle(), einstellungen.getOfficialFont().getSize()));
        schriftart.add(courier);
        courier.setFont(new Font("Courier", einstellungen.getOfficialFont().getStyle(), einstellungen.getOfficialFont().getSize()));
        schriftart.add(arial);
        arial.setFont(new Font("Arial", einstellungen.getOfficialFont().getStyle(), einstellungen.getOfficialFont().getSize()));
        //schriftart.add(def);
        //def.setFont(new Font("default", einstellungen.getOfficialFont().getStyle(), einstellungen.getOfficialFont().getSize()));
        schrift.add(schriftgroesse);
        schriftgroesse.add(klein);
        klein.setFont(new Font(einstellungen.getOfficialFont().getFontName(), einstellungen.getOfficialFont().getStyle(), 11));
        schriftgroesse.add(mittel);
        mittel.setFont(new Font(einstellungen.getOfficialFont().getFontName(), einstellungen.getOfficialFont().getStyle(), 15));
        schriftgroesse.add(gross);
        gross.setFont(new Font(einstellungen.getOfficialFont().getFontName(), einstellungen.getOfficialFont().getStyle(), 20));
        schrift.add(stil);
        stil.add(normal);
        normal.setFont(new Font(einstellungen.getOfficialFont().getFontName(), Font.PLAIN, einstellungen.getOfficialFont().getSize()));
        stil.add(kursiv);
        kursiv.setFont(new Font(einstellungen.getOfficialFont().getFontName(), Font.ITALIC, einstellungen.getOfficialFont().getSize()));
        stil.add(fett);
        fett.setFont(new Font(einstellungen.getOfficialFont().getFontName(), Font.BOLD, einstellungen.getOfficialFont().getSize()));

        fragen.addActionListener(this);
        farbe.addActionListener(this);
        im.addActionListener(this);
        ex.addActionListener(this);
        times.addActionListener(this);
        courier.addActionListener(this);
        arial.addActionListener(this);
        //def.addActionListener(this);
        klein.addActionListener(this);
        mittel.addActionListener(this);
        gross.addActionListener(this);
        normal.addActionListener(this);
        kursiv.addActionListener(this);
        fett.addActionListener(this);

        setJMenuBar(menuBar);

        //Rumpf
        JPanel west = new JPanel(new FlowLayout());
        west.setBackground(officialColor);

        west.add(spielen);
        spielen.setFont(einstellungen.getOfficialFont());
        spielen.addActionListener(this);

        this.add(west, BorderLayout.WEST);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(officialColor);

        JPanel navigation = new JPanel(new FlowLayout());
        navigation.setBackground(officialColor);
        south.add(navigation, BorderLayout.EAST);

        navigation.add(exit);
        exit.setFont(einstellungen.getOfficialFont());
        exit.addActionListener(this);

        this.add(south, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen.
        if(e.getSource() == exit){
            System.exit(0);
        } else if(e.getSource() == spielen) {
            new Spielauswahl("Spielmodus");
            dispose();
        } else if(e.getSource() == farbe) {
            new GuiFarbauswahl("Farboptionen");
            dispose();
        } else if(e.getSource() == fragen) {
            new ListDialog();
        } else if(e.getSource() == im) {
            //TODO evtl. Fehlermeldungen
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Textdatei", "txt");
            chooser.setFileFilter(filter);
            int value = chooser.showOpenDialog(this);
            if (value == JFileChooser.APPROVE_OPTION) {
        	Fragencontainer container = Fragencontainer.instance();
        	container.load(chooser.getSelectedFile().getPath());
        	JOptionPane.showMessageDialog(this, "Die Fragen wurden erfolgreich importiert");
            }
        } else if(e.getSource() == ex) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Textdatei", "txt");
            chooser.setFileFilter(filter);
            int value = chooser.showSaveDialog(this);
            if (value == JFileChooser.APPROVE_OPTION) {
        	new ExportDialog(this, chooser.getSelectedFile().getPath());
            }
        } else if(e.getSource() == times) {
            einstellungen.setSchriftart("TimesRoman");
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == courier) {
            einstellungen.setSchriftart("Courier");
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == arial) {
            einstellungen.setSchriftart("Arial");
            new Hauptmenue("Hauptmenue");
            dispose();
        //} else if(e.getSource() == def) {
        //    einstellungen.setSchriftart("default");
        //    new Hauptmenue("Hauptmenue");
        //    dispose();
        } else if(e.getSource() == klein) {
            einstellungen.setSchriftgroesse("11");
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == mittel) {
            einstellungen.setSchriftgroesse("15");
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == gross) {
            einstellungen.setSchriftgroesse("20");
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == normal) {
            einstellungen.setStil("normal");
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == kursiv) {
            einstellungen.setStil("kursiv");
            new Hauptmenue("Hauptmenue");
            dispose();
        } else if(e.getSource() == fett) {
            einstellungen.setStil("fett");
            new Hauptmenue("Hauptmenue");
            dispose();
        }
    }
}



package gui;

import javax.swing.JOptionPane;

import controller.Frage;
import controller.MuendlicheAntwortFrage;
import controller.VierAntwortenFrage;

public class MehrspielerManager extends SpielManager {

    private GuiStatistikMp statistik;
    private boolean spieler1Dran = true;
    
    public MehrspielerManager() {
	statistik = new GuiStatistikMp("Statistik");
	statistik.setLocation(200, 200);
	statistik.setVisible(false);
    }
    
    @Override
    public void next(int anzahlPunkte) {
	if (anzahlPunkte == -1) {
	    statistik.dispose();
	    return;
	}
	if (spieler1Dran) {
	    spieler1Dran = false;
	    statistik.highlightSpieler1();
	    statistik.setPunkteSpieler2(statistik.getPunkteSpieler2() + anzahlPunkte);
	    
	} else {
	    spieler1Dran = true;
	    statistik.highlightSpieler2();
	    statistik.setPunkteSpieler1(statistik.getPunkteSpieler1() + anzahlPunkte);
	}
	if (fragen.size() == 0) {
	    if (statistik.getPunkteSpieler1() > statistik.getPunkteSpieler2()) {
		JOptionPane.showMessageDialog(null, statistik.getName1() + " hat gewonnen!");
	    } else if (statistik.getPunkteSpieler1() < statistik.getPunkteSpieler2()) {
		JOptionPane.showMessageDialog(null, statistik.getName2() + " hat gewonnen!");
	    } else {
		JOptionPane.showMessageDialog(null, "Unentschieden!");
	    }
	    new Hauptmenue("Hauptmenue");
	    statistik.dispose();
	    return;
	}
	statistik.setAktuelleFrage(statistik.getAktuelleFrage() + 1);
	Frage f = fragen.remove(0);
	if (f instanceof VierAntwortenFrage) {
	    new GuiVierAntwortFrage((VierAntwortenFrage) f, this);
	} else if (f instanceof MuendlicheAntwortFrage) {
	    new GuiMuendlicheAntwortFrage((MuendlicheAntwortFrage) f, this);
	}
    }

    @Override
    public void init(int anzahlFragen) {
	statistik.setMaxFrage(fragen.size());
    }

    @Override
    public void setNamen(String name1, String name2) {
	this.statistik.setName1(name1);
	this.statistik.setName2(name2);
	statistik.setVisible(true);
    }
}

package gui;

import javax.swing.JOptionPane;

import controller.Frage;
import controller.MuendlicheAntwortFrage;
import controller.VierAntwortenFrage;

public class BuzzermodusManager extends SpielManager {

    private GuiStatistikMpBuzzer statistik;
    private GuiFrage aktuelleFrage;
    private boolean zeitUm = false;
    
    public BuzzermodusManager() {
	statistik = new GuiStatistikMpBuzzer("Statistik", this);
	statistik.setLocation(200, 200);
	statistik.setVisible(false);
    }
    
    @Override
    public void next(int anzahlPunkte) {
	statistik.setVisible(true);
	statistik.resetCountdownAnzeige();
	//statistik.setModal(true);
	statistik.activateBuzzer();
	if (anzahlPunkte == -1) {
	    statistik.dispose();
	    return;
	}
	if (zeitUm) {
	    //JOptionPane.showMessageDialog(null, "Die Zeit ist schon um!");
	    if (statistik.getSpieler1Gedrueckt()) {
		statistik.setPunkteSpieler2(statistik.getPunkteSpieler2() + 1);
	    } else {
		statistik.setPunkteSpieler1(statistik.getPunkteSpieler1() + 1);
	    }
	    zeitUm = false;
	} else {
	    if (statistik.getSpieler1Gedrueckt()) {
		if (anzahlPunkte == 0) {
		    statistik.setPunkteSpieler2(statistik.getPunkteSpieler2() + 1);
		} else {
		    statistik.setPunkteSpieler1(statistik.getPunkteSpieler1() + anzahlPunkte); 
		}   
	    } else {
		if (anzahlPunkte == 0) {
		    statistik.setPunkteSpieler1(statistik.getPunkteSpieler1() + 1);
		} else {
		    statistik.setPunkteSpieler2(statistik.getPunkteSpieler2() + anzahlPunkte);
		}
	    }
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
	    GuiFrage fr = new GuiVierAntwortFrage((VierAntwortenFrage) f, this);
	    fr.setEnabled(false);
	    this.aktuelleFrage = fr;
	} else if (f instanceof MuendlicheAntwortFrage) {
	    GuiFrage fr = new GuiMuendlicheAntwortFrage((MuendlicheAntwortFrage) f, this);
	    fr.setEnabled(false);
	    this.aktuelleFrage = fr;
	}
    }

    @Override
    public void init(int anzahlFragen) {
	statistik.setMaxFrage(fragen.size());
    }

    @Override
    public void setNamen(String name1, String name2) {
	statistik.setNamen(name1, name2);
    }
    
    public void disposeAktuelleFrage() {
	aktuelleFrage.dispose();
    }
    
    public void enableFragenGui() {
	this.aktuelleFrage.setEnabled(true);
    }
    
    public void setZeitUm(boolean zeitUm) {
	this.zeitUm = zeitUm;
	JOptionPane.showMessageDialog(null, "Die Zeit ist um! Beantworte die Frage bitte trotzdem.");
    }
    
    public void stopCountdown() {
	if (!zeitUm) {
	    statistik.resetCountdownAnzeige();
	}
    }

    public void stopSpiel() {
	statistik.dispose();
	aktuelleFrage.dispose();
	new Hauptmenue("Hauptmenue");
    }

}

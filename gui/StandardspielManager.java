package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.*;

public class StandardspielManager extends SpielManager {
    private GuiStatistikStandard statistik;
    
    public StandardspielManager() {
	statistik = new GuiStatistikStandard("Statistik");
	statistik.setLocation(200, 200);
	statistik.setVisible(false);
    }
    
    @Override
    public void init(int anzahlFragen) {
	statistik.setMaxFrage(anzahlFragen);
	statistik.setVisible(true);
    }
    
    @Override
    public void next(int punkte) {
	if (punkte == -1) {
	    statistik.dispose();
	    return;
	}
	statistik.setPunktzahl(statistik.getPunktzahl() + punkte);
	if (fragen.size() == 0) {
	    JOptionPane.showMessageDialog(null, "Du hast " + statistik.getPunktzahl() + " Punkte!");
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
    public void setNamen(String name1, String name2) {
	// hier unnötige Methode
    }
}

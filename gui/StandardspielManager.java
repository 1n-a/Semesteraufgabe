package gui;

import java.util.ArrayList;

import controller.*;

public class StandardspielManager extends SpielManager {
    
    public StandardspielManager() {
	System.out.println("Hallo");
    }
    
    @Override
    public void next(int punkte) {
	if (fragen.size() == 0) {
	    new Hauptmenue("Hauptmenue");
	    return;
	}
	Frage f = fragen.remove(0);
	if (f instanceof VierAntwortenFrage) {
	    new GuiVierAntwortFrage((VierAntwortenFrage) f, this);
	} else if (f instanceof MuendlicheAntwortFrage) {
	    new GuiMuendlicheAntwortFrage((MuendlicheAntwortFrage) f, this);
	}
    }
}

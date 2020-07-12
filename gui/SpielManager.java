package gui;

import java.util.ArrayList;

import controller.Frage;

public abstract class SpielManager {
    protected ArrayList<Frage> fragen;
    
    public void setFragen(ArrayList<Frage> fragen) {
	this.fragen = fragen;
    }
    
    public abstract void next(int anzahlPunkte);
    
    public abstract void init(int anzahlFragen);
    
    public abstract void setNamen(String name1, String name2);

}

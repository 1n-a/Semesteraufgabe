/**
 * Container, um Fragen in einer ArrayList abzuspeichern
 * Dabei werden alle Änderungen durch die Standardverwaltungsoperationen nur in der Liste geändert,
 * um diese in die Textdatei zu übernehmen, muss die save()-Methode aufgerufen werden
 * TODO: Methode zum Filtern der Fragen
 */

package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.*;

public class Fragencontainer implements Iterable<Frage> {
    private static Fragencontainer unique = null;
    private ArrayList<Frage> fragen;
    private String dateiname = ".\\src\\textdateien\\Fragen.txt";
    
    private Fragencontainer() {
	fragen = Dateieinleser.DateiZuFragen(dateiname, -1);
    }
    
    public static Fragencontainer instance() {
	if (unique == null) {
	    unique = new Fragencontainer();
	}
	return unique;
    }
    
    /**
     * fügt die Frage der Datenstruktur hinzu, diese wird aber noch nicht in der Textdatei gespeichert!!!
     * @param f die hinzuzufügende Frage
     */
    public void linkFrage(Frage f) {
	this.fragen.add(f);
    }
    
    /**
     * entfernt die Frage aus der Datenstruktur, diese wird aber noch nicht in der Textdatei gespeichert!!!
     * @param f die zu entfernende Frage
     */
    public void unlinkFrage(Frage f) {
	this.fragen.remove(f);
    }
    
    /**
     * gibt eine Liste mit allen Fragen des Programms zurück
     * @return die Liste mit allen Fragen des Programms
     */
    public ArrayList<Frage> getFragen() {
	return this.fragen;
    }
    
    public boolean save() {
	return Dateischreiber.FragenZuDatei(dateiname, fragen);
    }
    
    @Override
    public Iterator<Frage> iterator() {
	return fragen.iterator();
    }

    /*public static void main(String[] args) {
	Fragencontainer f = Fragencontainer.instance();
	f.linkFrage(new VierAntwortenFrage(3, Schwierigkeit.LEICHT, "Test", "Test",
	    "frage", "a1", "a2", "a3", "a4", 
	    2));
	f.save();
    }*/
    
}

/**
 * Container, um Fragen in einer ArrayList abzuspeichern
 * Dabei werden alle �nderungen durch die Standardverwaltungsoperationen nur in der Liste ge�ndert,
 * um diese in die Textdatei zu �bernehmen, muss die save()-Methode aufgerufen werden
 * TODO: Methode zum Filtern der Fragen
 */

package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.*;

public class Fragencontainer implements Iterable<Frage> {
    private static Fragencontainer unique = null;
    private ArrayList<Frage> fragen;
    private String dateiname = ".\\src\\textdateien\\Fragen.txt";
    
    private Fragencontainer() {
	fragen = Dateieinleser.DateiZuFragen(dateiname);
    }
    
    public static Fragencontainer instance() {
	if (unique == null) {
	    unique = new Fragencontainer();
	}
	return unique;
    }
    
    /**
     * f�gt die Frage der Datenstruktur hinzu, diese wird aber noch nicht in der Textdatei gespeichert!!!
     * @param f die hinzuzuf�gende Frage
	 * :)
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
     * gibt eine Liste mit allen Fragen des Programms zur�ck
     * @return die Liste mit allen Fragen des Programms
     */
    public ArrayList<Frage> getFragen() {
	return this.fragen;
    }
    
    /**
     * gibt gefilterte Fragen zur�ck
     * @param vorlesungen alle Vorlesungen, aus denen Themen vorkommen sollen
     * falls vorlesungen null ist, werden alle Vorlesungen ausgew�hlt
     * @param themen alle Themen, zu denen Fragen vorkommen sollen
     * falls themen null ist, werden alle Themen ausgew�hlt
     * @param anzahl die gew�nschte Anzahl an Fragen
     * falls anzahl -1 ist, werden alle Fragen ausgew�hlt
     * @return
     */
    public ArrayList<Frage> getFragenGefiltert(ArrayList<String> vorlesungen, ArrayList<String> themen, int anzahl) {
	ArrayList<Frage> fragen = this.getFragen();
	Object[] fr;
	if (vorlesungen != null && themen != null) {
	    fr = fragen.stream()
		    .filter(f -> vorlesungen.contains(f.getVorlesung()))
		    .filter(f -> themen.contains(f.getThema()))
		    .toArray();
	} else if (vorlesungen == null && themen != null) {
	    fr = fragen.stream()
		    .filter(f -> themen.contains(f.getThema()))
		    .toArray();
	} else if (vorlesungen != null && themen == null) {
	    fr = fragen.stream()
		    .filter(f -> vorlesungen.contains(f.getVorlesung()))
		    .toArray();
	} else {
	    fr = fragen.stream()
		    .toArray();
	}
	fragen = new ArrayList<Frage>();
	if (anzahl > 0) {
	    if (anzahl >= fr.length) {
		for (Object i : fr) {
		    fragen.add((Frage) i);
		}
		return fragen;
	    }
	    ArrayList<Integer> indices = Fragencontainer.indicesFragen(fr.length, anzahl);
	    for (int i : indices) {
		fragen.add((Frage) fr[i]);
	    }
	} else {
	    for (Object i : fr) {
		fragen.add((Frage) i);
	    }
	}
	return fragen;
    }
    
    private static ArrayList<Integer> indicesFragen(int anzahlFragen, int anzahlGewuenscht) {
	Random r = new Random();
	ArrayList<Integer> ret = new ArrayList<>();
	int counter = 0;
	while(counter < anzahlGewuenscht) {
	    int i = r.nextInt(anzahlFragen);
	    if (!ret.contains(i)) {
		ret.add(i);
		counter++;
	    }
	}
	return ret;
    }
    
    public boolean save() {
	return Dateischreiber.FragenZuDatei(dateiname, fragen);
    }
    
    @Override
    public Iterator<Frage> iterator() {
	return fragen.iterator();
    }

    /*public static void main(String[] args) {
	ArrayList<Integer> a = Fragencontainer.indicesFragen(40, 20);
	
	Fragencontainer f = Fragencontainer.instance();
	ArrayList<String> str = new ArrayList<String>();
	str.add("Rechnerarchitektur");
	str.add("Sonstiges");
	ArrayList<String> str2 = new ArrayList<String>();
	str2.add("Informatik 1");
	ArrayList<Frage> fr = f.getFragenGefiltert(null, null, -1);
	f.linkFrage(new MuendlicheAntwortFrage("Sonstiges", "Sonstiges", "Wie viele "
		+ "Informatiker braucht man, um eine Gl�hbirne zu wechseln?", "Keinen, das ist kein Softwareproblem."));
	f.save();
    }*/
    
}

/**
 * Klasse, die alle Fragen speichert.
 * Sie hat dafür als Attribute eine Liste aller Fragen und Methoden, um diese zu bekommen und zu verändern.
 * Die Klasse soll die Schnittstelle zum Controller sein, auf alle anderen Klassen soll dieser nicht zugreifen.
 * Das Filtern der Fragen soll im Controller gemacht werden.
 */

package model;

import java.util.ArrayList;

public class AlleFragen {
    private ArrayList<Frage> alleFragen;
    private static String vierAntwortenFragenDatei = ".\\src\\textdateien\\VierAntwortenFragen.txt";
    VierAntwortenFrageEinleserSchreiber vaEinleser = new VierAntwortenFrageEinleserSchreiber();
    
    public AlleFragen() {
	alleFragen = vaEinleser.DateiZuFragen(vierAntwortenFragenDatei, -1);
    }
    
    /**
     * 
     * @return eine ArrayList mit allen Fragen des Programms (von allen Fragetypen)
     */
    public ArrayList<Frage> getAlleFragen() {
	return this.alleFragen;
    }
    
    /**
     * fügt eine VierAntwortenFrage der Liste alleFragen hinzu und einen zu der Frage passenden String 
     * in die Textdatei
     * @param frage die übergebene Frage
     */
    public void addFrage(Frage frage) {
	this.alleFragen.add(frage);
	if (frage instanceof VierAntwortenFrage) {
	    this.vaEinleser.inDateiSchreiben(vierAntwortenFragenDatei, frage.toString());
	}
    }
    
    /**
     * Löscht eine übergebene Frage. TODO: Löschen der Frage aus der Textdatei 
     * @param frage
     */
    public void deleteFrage(Frage frage) {
	this.alleFragen.remove(frage);
    }
    
    public static void main(String[] args) {
	AlleFragen f = new AlleFragen();
	VierAntwortenFrage v = new VierAntwortenFrage(3, Schwierigkeit.LEICHT, "Informatik 2", "Java-Programmstruktur",
	    "Welcher Methodenkopf einer Mainmethode ist nicht richtig?", "public static void main(String[] args", 
	    "public static void main(String args[])", "public static void main(String... args)", "public static void"
	    	+ "main(string args)", 3);
	f.addFrage(v);
    }
}

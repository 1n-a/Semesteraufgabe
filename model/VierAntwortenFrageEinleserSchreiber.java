/**
 * Klasse zum Einlesen einer Datei, deren Inhalt VierAntwortenFragen sind
 */

package model;

import java.util.ArrayList;

public class VierAntwortenFrageEinleserSchreiber extends Dateieinleser implements Dateischreiber {

    @Override
    public ArrayList<Frage> DateiZuFragen(String dateiname, int letzteID) {
	ArrayList<Frage> fragen = new ArrayList<Frage>();
	ArrayList<String> zeilen = Dateieinleser.dateiEinlesen(dateiname);
	for(String s : zeilen) {
	    String[] woerter = s.split("\\$");
	    if(woerter.length != 9) {
		System.out.println("Ungültige Zeile gelesen");
	    } else {
		try {
		    Schwierigkeit schwierigkeit = Schwierigkeit.valueOf(woerter[0]);
		    String vorlesung = woerter[1];
		    String thema = woerter[2];
		    String frage = woerter[3];
		    String a1 = woerter[4];
		    String a2 = woerter[5];
		    String a3 = woerter[6];
		    String a4 = woerter[7];
		    int index = Integer.parseInt(woerter[8]);
		    fragen.add(new VierAntwortenFrage(++letzteID, schwierigkeit, vorlesung, thema,
			    frage, a1, a2, a3, a4, index));
		} catch (NumberFormatException e) {
		    System.out.println("Fehler!");
 		} catch (IllegalArgumentException e) {
		    System.out.println("Fehler!");
		}
	    }
	}
	return fragen;
    }
    
}

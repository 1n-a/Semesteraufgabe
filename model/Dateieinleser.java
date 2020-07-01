/**
 * Klasse, um Dateien einzulesen und zeilenweise die Strings in Fragen umzuwandeln
 */

package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dateieinleser {

    /**
     * liest die Datei an der übergebenen Stelle ein und schreibt jede Zeile als ein String in eine ArrayList
     * @param dateiname der Name und Pfad zu der Datei als String (wenn wie die Dateien in dem Ordner 
     * "textdateien ablegen, muss hier ".\\src\\textdateien\\<name>.txt" stehen
     * @return zeilen: ArrayList an Strings, die je der Inhalt einer Zeile der Datei ist :-)
     */
    public static ArrayList<String> dateiEinlesen(String dateiname) {
	ArrayList<String> zeilen = new ArrayList<String>();  

	BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(dateiname));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
        	zeilen.add(zeile);
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Datei " + dateiname);
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Fehler beim Schließen der Datei" + dateiname);
                }
        }
        return zeilen;
    }
    
    /**
     * liest erst die Datei zeilenweise ein und wandelt dann jede Zeile, falls möglich, in ein Frageobjekt um
     * @param dateiname der String der Stelle, wo die Datei zu finden ist
     * @return eine Liste an den erfolgreich umgewandelten Fragen
     */
    public static ArrayList<Frage> DateiZuFragen(String dateiname) {
	ArrayList<String> zeilen = Dateieinleser.dateiEinlesen(dateiname);
	ArrayList<Frage> fragen = new ArrayList<Frage>();
	for(String s : zeilen) {
	    String[] woerter = s.split("\\$");
	    if (woerter.length >= 1) {
		if (woerter[0].equals(Fragentyp.VierAntwortenFrage.toString())) {
		    try {
			fragen.add(VierAntwortenFrage.StringZuFrage(woerter));
		    } catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		    }
		} else if(woerter[0].equals(Fragentyp.MuendlicheAntwortFrage.toString())) {
		    try {
			fragen.add(MuendlicheAntwortFrage.StringZuFrage(woerter));
		    } catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		    }
		}
	    }
	}
	return fragen;
    }
    
}

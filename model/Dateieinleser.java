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
     * @return zeilen: ArrayList an Strings, die je der Inhalt einer Zeile der Datei ist
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
     * @param letzteID die letzte ID, die übergeben wurde, jede neue Frage bekommt eine darauffolgend, eindeutige
     * ID
     * @return eine Liste an den erfolgreich umgewandelten Fragen
     */
    public static ArrayList<Frage> DateiZuFragen(String dateiname, int letzteID) {
	ArrayList<String> zeilen = Dateieinleser.dateiEinlesen(dateiname);
	ArrayList<Frage> fragen = new ArrayList<Frage>();
	for(String s : zeilen) {
	    String[] woerter = s.split("\\$");
	    if (woerter.length >= 1) {
		if (woerter[0].equals(Fragentyp.VIERANTWORTENFRAGE.toString())) {
		    if(woerter.length != 10) {
			System.out.println("Ungültige Zeile gelesen");
		    } else {
			try {
			    Schwierigkeit schwierigkeit = Schwierigkeit.valueOf(woerter[1]);
			    String vorlesung = woerter[2];
			    String thema = woerter[3];
			    String frage = woerter[4];
			    String a1 = woerter[5];
			    String a2 = woerter[6];
			    String a3 = woerter[7];
			    String a4 = woerter[8];
			    int index = Integer.parseInt(woerter[9]);
			    fragen.add(new VierAntwortenFrage(++letzteID, schwierigkeit, vorlesung, thema,
				    frage, a1, a2, a3, a4, index));
			} catch (NumberFormatException e) {
			    System.out.println("Fehler!");
	 		} catch (IllegalArgumentException e) {
			    System.out.println("Fehler!");
			}
		    }
		}
	    }
	}
	return fragen;
    }
    
}

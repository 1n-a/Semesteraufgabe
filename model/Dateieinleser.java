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
    
}

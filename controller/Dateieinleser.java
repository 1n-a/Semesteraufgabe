package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Dateieinleser {

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
                    System.out.println("Fehler beim Schlieﬂen der Datei" + dateiname);
                }
        }
        return zeilen;
    }
    
    public abstract ArrayList<Frage> DateiZuFragen(String dateiname, int letzteID);
    
}

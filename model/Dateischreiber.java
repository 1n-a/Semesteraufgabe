/**
 * Klasse, um Dateien zu beschreiben
 */

package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Dateischreiber {
    
    /**
     * �berschreibt den Inhalt der Textdatei mit dem �bergebenen String
     * @param dateiname der String, unter dem die Datei zu finden ist
     * @param text der zu schreibende Text
     * @return false, falls ein Fehler aufgetreten ist, sonst true
     */
    public static boolean inDateiSchreiben(String dateiname, String text) {
	PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(dateiname)));
            pWriter.print(text);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        }
        return true;
    }
}


/**
 * Klasse, um Dateien zu beschreiben
 */

package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Dateischreiber {
    
    /**
     * überschreibt den Inhalt der Textdatei mit dem übergebenen String
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
 
    /**
     * schreibt die Stringdarstellung der übergebenen Fragenliste zeilenweise in die Datei
     * @param dateiname der String, wo die Datei zu finden ist
     * @param fragen die zu schreibenden Frageobjekte
     * @return false, falls (beim Beschreiben der Datei) ein Fehler aufgetreten ist, sonst true
     */
    public static boolean FragenZuDatei(String dateiname, ArrayList<Frage> fragen) {
	String text = "";
	for (Frage f : fragen) {
	    text += f.toString() + "\n";
	}
	if (Dateischreiber.inDateiSchreiben(dateiname, text)) {
	    return true;
	}
	return false;
    }
}


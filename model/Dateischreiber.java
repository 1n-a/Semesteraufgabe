package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public interface Dateischreiber {
    
    default boolean inDateiSchreiben(String dateiname, String text) {
	PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter(dateiname, true)));
            pWriter.println(text);
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


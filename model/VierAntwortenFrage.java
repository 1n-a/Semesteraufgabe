/**
 * Klasse, die eine Frage repräsentiert, die eine Fragestellung und vier Antworten hat, von denen genau eine
 * richtig ist (vgl. Wer wird Millionär)
 */

package model;

public class VierAntwortenFrage extends Frage {
    String frage;
    String[] antworten = new String[4];
    int indexRichtigeAntwort;
    
    public VierAntwortenFrage(String vorlesung, String thema,
	    String frage, String a1, String a2, String a3, String a4, 
	    int index) {
	super(Fragentyp.VierAntwortenFrage, vorlesung, thema, 1);
	this.setFrage(frage);
	this.setAntworten(a1, a2, a3, a4);
	this.setIndexRichtigeAntwort(index);
    }
    
    /** 
     * Setzt als Fragestellung dieser VierAntwortenFrage den übergebenen String
     * @param frage
     * @throws IllegalArgumentException, falls der String kürzer als 1 Zeichen oder leer ist.
     */
    public void setFrage(String frage) throws IllegalArgumentException {
	if (frage == null || frage.length() == 0) {
	    throw new IllegalArgumentException("Der String Frage muss länger sein.");
	}
	this.frage = frage;
    }
    
    /** 
     * Setzt als Antwortmöglichkeiten dieser VierAntwortenFrage die übergebenen Strings in der Reihenfolge
     * @param a1, a2, a3, a4 die vier Antwortmöglichkeiten
     * @throws IllegalArgumentException, falls einer der Strings kürzer als 1 Zeichen oder leer ist.
     */
    public void setAntworten(String a1, String a2, String a3, String a4) throws IllegalArgumentException {
	if (a1 == null || a1.length() == 0 || a2 == null || a2.length() == 0 || a3 == null || a3.length() == 0 || a4 == null || a4.length() == 0) {
	    throw new IllegalArgumentException("Einer der Antwortstrings ist zu kurz.");
	}
	antworten[0] = a1;
	antworten[1] = a2;
	antworten[2] = a3;
	antworten[3] = a4;
    }
    
    /**
     * Setzt den Index im Feld antworten, bei dem die richtige Antwort steht, auf den übergebenen Wert, falls er
     * auf einen String in dem Feld zeigt.
     * @param index
     */
    public void setIndexRichtigeAntwort(int index) throws IllegalArgumentException {
	if (index < 0 || index > 3) {
	    throw new IllegalArgumentException("Der Index zeigt auf keine Antwort :-(");
	}
	this.indexRichtigeAntwort = index;
    }
    
    public String getFrage() {
	return this.frage;
    }
    
    public String[] getAntworten() {
	return this.antworten;
    }
    
    public int getIndexRichtigeAntwort() {
	return this.indexRichtigeAntwort;
    }
    

    /**
     * Stringdarstellung der Frage, wie sie in der Textdatei stehen sollte
     * Format: typ$schwierigkeit$vorlesung$thema$frage$a1$a2$a3$a4$indexRichtigeAntwort
     */
    @Override
    public String toString() {
	return  this.getTyp().toString() + "$" +
		this.getVorlesung() + "$" + 
		this.getThema() + "$" + 
		this.getFrage() + "$" + 
		this.antworten[0] + "$" + 
		this.antworten[1] + "$" + 
		this.antworten[2] + "$" +
		this.antworten[3] + "$"
		+ this.getIndexRichtigeAntwort();
    }
    
    //@Override
    public static Frage StringZuFrage(String[] woerter) throws IllegalArgumentException {
	if(woerter.length != 9) {
		throw new IllegalArgumentException("Zeile mit zu wenig Attributen gelesen");
	} else {
	    try {
		String vorlesung = woerter[1];
		String thema = woerter[2];
		String frage = woerter[3];
		String a1 = woerter[4];
		String a2 = woerter[5];
		String a3 = woerter[6];
		String a4 = woerter[7];
		int index = Integer.parseInt(woerter[8]);
		return new VierAntwortenFrage(vorlesung, thema,
			    frage, a1, a2, a3, a4, index);
	    } catch (NumberFormatException e) {
		System.out.println("Fehler!");
	    } catch (IllegalArgumentException e) {
		System.out.println("Fehler!");
	    }
	}
	return null;
    }

}

package controller;

public class VierAntwortenFrage extends Frage {
    String frage;
    String[] antworten = new String[4];
    int indexRichtigeAntwort;
    
    public VierAntwortenFrage(int id, Schwierigkeit schwierigkeit, String vorlesung, String thema,
	    String frage, String a1, String a2, String a3, String a4, 
	    int index) {
	super(id, schwierigkeit, vorlesung, thema, 1);
	this.setFrage(frage);
	this.setAntworten(a1, a2, a3, a4);
	this.setIndexRichtigeAntwort(index);
    }
    
    /** TODO: evtl. exception
     * Setzt als Fragestellung dieser VierAntwortenFrage den übergebenen String
     * @param frage
     */
    public void setFrage(String frage) {
	this.frage = frage;
    }
    
    /** TODO: evtl. exception
     * Setzt als Antwortmöglichkeiten dieser VierAntwortenFrage die übergebenen Strings in der Reihenfolge
     * @param a1, a2, a3, a4 die vier Antwortmöglichkeiten
     */
    public void setAntworten(String a1, String a2, String a3, String a4) {
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

}

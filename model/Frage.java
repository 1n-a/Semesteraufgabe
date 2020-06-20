/**
 * abstrakte Klasse, die ein Frage repr�sentiert
 */

package model;

public abstract class Frage {
    private Fragentyp typ;
    private int ID;
    private Schwierigkeit schwierigkeit;
    private String vorlesung;
    private String thema;
    private int maxPunkte;
    
    public Frage(Fragentyp typ, int id, Schwierigkeit schwierigkeit, String vorlesung, String thema, int maxPunkte) {
	this.setFragentyp(typ);
	this.ID = id;
	this.setSchwierigkeit(schwierigkeit);
	this.setVorlesung(vorlesung);
	this.setThema(thema);
	this.setMaxPunkte(maxPunkte);
    }
    
    /**
     * setzt den Fragentyp dieser Frage auf den �bergebenen Wert
     * @param typ
     */
    public void setFragentyp(Fragentyp typ) {
	this.typ = typ;
    }
    /** 
     * setzt die Schwierigkeit dieser Frage auf den gegebenen Wert
     * @param schwierigkeit
     */
    public void setSchwierigkeit(Schwierigkeit schwierigkeit) {
	this.schwierigkeit = schwierigkeit;
    }
    
    /**
     * setzt den Vorlesungstyp (also die Vorlesung, zu der diese Frage passt) auf den �bergebenen String
     * @param vorlesung
     * @throws IllegalArgumentException, falls der String k�rzer als 1 Zeichen oder leer ist
     */
    public void setVorlesung(String vorlesung) throws IllegalArgumentException {
	if (vorlesung == null ||vorlesung.length() == 0) {
	    throw new IllegalArgumentException("Der String Vorlesung muss l�nger sein.");
	}
	this.vorlesung = vorlesung;
    }
    
    /**
     * setzt das Thema dieser Frage (also das Thema aus der Vorlesung, das am besten zu der Frage passt) auf den 
     * �bergebenen String
     * @param thema
     * @throws IllegalArgumentException, falls der String k�rzer als 1 Zeichen oder leer ist.
     */
    public void setThema(String thema) throws IllegalArgumentException {
	if (thema == null || thema.length() == 0) {
	    throw new IllegalArgumentException("Der String Thema muss l�nger sein.");
	}
	this.thema = thema;
    }
    
    /**
     * setzt die maximal bei dieser Frage erreichbare Punkteanzahl auf den �bergebenen Wert, wenn dieser mindestens
     * 1 ist
     * @param maxPunkte
     * @throws IllegalArgumentException wenn die Anzahl erreichbarer Punkte kleiner als 1 ist
     */
    public void setMaxPunkte(int maxPunkte) throws IllegalArgumentException {
	if (maxPunkte < 1) {
	    throw new IllegalArgumentException("Bei dieser Frage m�ssen mehr Punkte erreichbar sein!");
	}
	this.maxPunkte = maxPunkte;
    }
    
    public Fragentyp getTyp() {
	return this.typ;
    }
    
    public Schwierigkeit getSchwierigkeit() {
	return this.schwierigkeit;
    }
    
    public String getVorlesung() {
	return this.vorlesung;
    }
    
    public String getThema() {
	return this.thema;
    }
    
    public int getMaxPunkte() {
	return this.maxPunkte;
    }
    
    public int getID() {
	return this.ID;
    }
}

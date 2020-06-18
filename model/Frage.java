/**
 * abstrakte Klasse, die ein Frage repräsentiert
 */

package model;

public abstract class Frage {
    private int ID;
    private Schwierigkeit schwierigkeit;
    private String vorlesung;
    private String thema;
    private int maxPunkte;
    
    public Frage(int id, Schwierigkeit schwierigkeit, String vorlesung, String thema, int maxPunkte) {
	this.ID = id;
	this.setSchwierigkeit(schwierigkeit);
	this.setVorlesung(vorlesung);
	this.setThema(thema);
	this.setMaxPunkte(maxPunkte);
    }
    
    /** 
     * setzt die Schwierigkeit dieser Frage auf den gegebenen Wert
     * @param schwierigkeit
     */
    public void setSchwierigkeit(Schwierigkeit schwierigkeit) {
	this.schwierigkeit = schwierigkeit;
    }
    
    /**
     * setzt den Vorlesungstyp (also die Vorlesung, zu der diese Frage passt) auf den übergebenen String
     * @param vorlesung
     * @throws IllegalArgumentException, falls der String kürzer als 1 Zeichen oder leer ist
     */
    public void setVorlesung(String vorlesung) throws IllegalArgumentException {
	if (vorlesung == null ||vorlesung.length() == 0) {
	    throw new IllegalArgumentException("Der String Vorlesung muss länger sein.");
	}
	this.vorlesung = vorlesung;
    }
    
    /**
     * setzt das Thema dieser Frage (also das Thema aus der Vorlesung, das am besten zu der Frage passt) auf den 
     * übergebenen String
     * @param thema
     * @throws IllegalArgumentException, falls der String kürzer als 1 Zeichen oder leer ist.
     */
    public void setThema(String thema) throws IllegalArgumentException {
	if (thema == null || thema.length() == 0) {
	    throw new IllegalArgumentException("Der String Thema muss länger sein.");
	}
	this.thema = thema;
    }
    
    /**
     * setzt die maximal bei dieser Frage erreichbare Punkteanzahl auf den übergebenen Wert, wenn dieser mindestens
     * 1 ist
     * @param maxPunkte
     * @throws IllegalArgumentException wenn die Anzahl erreichbarer Punkte kleiner als 1 ist
     */
    public void setMaxPunkte(int maxPunkte) throws IllegalArgumentException {
	if (maxPunkte < 1) {
	    throw new IllegalArgumentException("Bei dieser Frage müssen mehr Punkte erreichbar sein!");
	}
	this.maxPunkte = maxPunkte;
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

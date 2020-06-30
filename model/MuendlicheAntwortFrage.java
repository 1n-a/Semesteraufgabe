package model;

public class MuendlicheAntwortFrage extends Frage {
    private String frage;
    private String antwort;
    
    public MuendlicheAntwortFrage(int id, Schwierigkeit schwierigkeit, String vorlesung, String thema, String frage, String antwort) {
	super(Fragentyp.MuendlicheAntwortFrage, id, schwierigkeit, vorlesung, thema, 1);
	this.setFrage(frage);
	this.setAntwort(antwort);
    }
    
    //@Override
    public static Frage StringZuFrage(String[] woerter, int letzteID) throws IllegalArgumentException {
	if(woerter.length != 6) {
		System.out.println("Ungültige Zeile gelesen");
	} else {
	    try {
		Schwierigkeit schwierigkeit = Schwierigkeit.valueOf(woerter[1]);
		String vorlesung = woerter[2];
		String thema = woerter[3];
		String frage = woerter[4];
		String antwort = woerter[5];
		return new MuendlicheAntwortFrage(++letzteID, schwierigkeit, vorlesung, thema,
			    frage, antwort);
	    } catch (IllegalArgumentException e) {
		System.out.println("Fehler!");
	    }
	}
	return null;
    }
    
    /** 
     * Setzt als Fragestellung dieser MuendlicheAntwortFrage den übergebenen String
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
     * Setzt als Antwort dieser MuendlicheAntwortFrage den übergebenen String
     * @param antwort
     * @throws IllegalArgumentException, falls der String kürzer als 1 Zeichen oder leer ist.
     */
    public void setAntwort(String antwort) throws IllegalArgumentException {
	if (antwort == null || antwort.length() == 0) {
	    throw new IllegalArgumentException("Der String Antwort muss länger sein.");
	}
	this.antwort = antwort;
    }
    
    public String getFrage() {
	return this.frage;
    }
    
    public String getAntwort() {
	return this.antwort;
    }
    
    /**
     * Stringdarstellung der Frage, wie sie in der Textdatei stehen soll
     * Format: typ$schwierigkeit$vorlesung$thema$frage$antwort
     */
    public String toString() {
	return this.getTyp().toString() + "$"
		+ this.getSchwierigkeit().toString() + "$"
		+ this.getVorlesung() + "$"
		+ this.getThema() + "$"
		+ this.getFrage() + "$"
		+ this.getAntwort();
    }
}

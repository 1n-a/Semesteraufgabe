/**
 * Klasse, die die Einstellungen farbe, schriftart, schriftgroesse und stil des Programms als String in einer 
 * Textdatei abspeichert. Die Klasse muss als Singleton aufgerufen werden. Zu allen Werten gibt es getter und 
 * setter, Änderungen werden sofort in der Textdatei gespeichert.
 */

package controller;

import java.awt.Color;
import java.util.ArrayList;

import model.Dateieinleser;
import model.Dateischreiber;

public class Einstellungen {
    private static Einstellungen unique = null;
    private String dateiname = ".\\src\\textdateien\\Einstellungen.txt";
    private String farbe;
    private String schriftart;
    private String schriftgroesse;
    private String stil;
    
    private Einstellungen() {
	this.load();
    }
    
    public static Einstellungen instance() {
	if (unique == null) {
	    unique = new Einstellungen();
	}
	return unique;
    }
    
    public boolean save() {
	String n = System.getProperty("line.separator");
	String text = farbe + n + schriftart + n + schriftgroesse + n + stil;
	return Dateischreiber.inDateiSchreiben(dateiname, text);
    }
    
    public boolean load() {
	ArrayList<String> text = Dateieinleser.dateiEinlesen(dateiname);
	if (text.size() != 4) {
	    this.farbe = "schwarz";
	    this.schriftart = "TimesRoman";
	    this.schriftgroesse = "12";
	    this.stil = "plain";
	    return false;
	}
	this.farbe = text.get(0);
	this.schriftart = text.get(1);
	this.schriftgroesse = text.get(2);
	this.stil = text.get(3);
	return true;
    }
    
    public String getFarbe() {
	return this.farbe;
    }
    
    public Color getColor() {
	if (this.farbe.equals(Farbe.dunkelLila.toString())) {
	    return new Color(Farbe.dunkelLila.getValue1(), Farbe.dunkelLila.getValue2(), Farbe.dunkelLila.getValue3());
	} else if (this.farbe.equals(Farbe.gelb.toString())) {
	    return new Color(Farbe.gelb.getValue1(), Farbe.gelb.getValue2(), Farbe.gelb.getValue3());
	} else if (this.farbe.equals(Farbe.grau.toString())) {
	    return new Color(Farbe.grau.getValue1(), Farbe.grau.getValue2(), Farbe.grau.getValue3());
	} else if (this.farbe.equals(Farbe.pink.toString())) {
	    return new Color(Farbe.pink.getValue1(), Farbe.pink.getValue2(), Farbe.pink.getValue3());
	} else if (this.farbe.equals(Farbe.schwarz.toString())) {
	    return new Color(Farbe.schwarz.getValue1(), Farbe.schwarz.getValue2(), Farbe.schwarz.getValue3());
	} else {
	    return new Color(Farbe.weiss.getValue1(), Farbe.weiss.getValue2(), Farbe.weiss.getValue3());
	}
    }
    
    public void setFarbe(String farbe) {
	this.farbe = farbe;
	this.save();
    }
    
    public String getSchriftart() {
	return this.schriftart;
    }
    
    public void setSchriftart(String schriftart) {
	this.schriftart = schriftart;
	this.save();
    }
    
    public String getSchriftgroesse() {
	return this.schriftgroesse;
    }
    
    public void setSchriftgroesse(String schriftgroesse) {
	this.schriftgroesse = schriftgroesse;
	this.save();
    }
    
    public String getStil() {
	return this.stil;
    }
    
    public void setStil(String stil) {
	this.stil = stil;
	this.save();
    }
    
    /*public static void main(String[] args) {
	Einstellungen e = Einstellungen.instance();
	e.setSchriftart("Minion");
    } */
}

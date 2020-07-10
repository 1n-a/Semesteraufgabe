package controller;

import java.util.ArrayList;

public class VorlesungenThemenContainer {
    private static VorlesungenThemenContainer unique = null;
    private ArrayList<String> vorlesungen;
    private ArrayList<String> themen;
    
    private VorlesungenThemenContainer() {
	vorlesungen = new ArrayList<>();
	themen = new ArrayList<>();
    }
    
    public static VorlesungenThemenContainer instance() {
	if (unique == null) {
	    unique = new VorlesungenThemenContainer();
	}
	return unique;
    }
    
    public void addVorlesung(String vorlesung) {
	if(!vorlesungen.contains(vorlesung)) {
	    vorlesungen.add(vorlesung);
	}
    }
    
    public ArrayList<String> getVorlesungen() {
	return this.vorlesungen;
    }
    
    public void addThema(String thema) {
	if(!themen.contains(thema)) {
	    themen.add(thema);
	}
    }
    
    public ArrayList<String> getThemen() {
	return this.themen;
    }
}

package controller;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class VorlesungenThemenContainer {
    private static VorlesungenThemenContainer unique = null;
    private ArrayList<String> vorlesungen;
    private TreeMap<String, ArrayList<String>> themenZuVorlesungen;
    
    private VorlesungenThemenContainer() {
	vorlesungen = new ArrayList<>();
	themenZuVorlesungen = new TreeMap<>();
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
    
    public void addThema(String thema, String vorlesung) {
	ArrayList<String> list = themenZuVorlesungen.get(thema);
	      if(list == null){
	         list = new ArrayList<String>();
	         themenZuVorlesungen.put(thema, list);
	      }
	      if (!list.contains(vorlesung)) {
		  list.add(vorlesung);
	      }
    }
    
    public ArrayList<String> getThemenZuVorlesung(String vorlesung) {
	Set<String> schluessel = themenZuVorlesungen.keySet();
	ArrayList<String> ret = new ArrayList<>();
	for (String thema : schluessel) {
	    if (themenZuVorlesungen.get(thema).contains(vorlesung)) {
		ret.add(thema);
	    }
	}
	return ret;
    }
    
    public ArrayList<String> getThemen() {
	Set<String> themen = themenZuVorlesungen.keySet();
	ArrayList<String> ret = new ArrayList<>();
	for (String s : themen) {
	    ret.add(s);
	}
	return ret;
    }
    
    /*public static void main(String[] args) {
	VorlesungenThemenContainer vtc = VorlesungenThemenContainer.instance();
	vtc.addThema("Thema1", "vZuThema1");
	vtc.addThema("Thema1", "vZuThema1");
	vtc.addThema("Thema2", "vZuThema2");
	vtc.addThema("Thema2", "v2ZuThema2");
	vtc.addThema("Thema3", "vZuThema2");
	System.out.println(vtc.getThemenZuVorlesung("vZuThema2"));
    }*/
}

package gui;

public class TestGui {
    public static void main(String[] args) {
        //new Hauptmenue("Hauptmenue");
        //new Spielauswahl("Spielmodus");
        //new GuiMuendlicheAntwortFrage("Frage", "Hier koennte ihre Frage stehen", "Hier koennte ihre Loesung stehen");
        new GuiVierAntwortFrage("Frage", "Hier steht der Fragetext", "Richtige Antwort, die aber viel zu lang ist", "Falsche Antwort 1", "Falsche Antwort 2", "Falsche Antwort 3");
    }
}
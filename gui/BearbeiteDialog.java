package gui;


import controller.*;

public class BearbeiteDialog {

    public BearbeiteDialog(Frage frage) {
	if (frage.getTyp().equals(Fragentyp.VierAntwortenFrage)) {
	    this.frageBearbeiten((VierAntwortenFrage) frage); 
	} else if (frage.getTyp().equals(Fragentyp.MuendlicheAntwortFrage)) {
	    this.frageBearbeiten((MuendlicheAntwortFrage) frage);
	}
    }
    
    public void frageBearbeiten(VierAntwortenFrage frage) {
	new VierAntwortenFrageBearbeiteDialog(frage);
    }
    
    public void frageBearbeiten(MuendlicheAntwortFrage frage) {
	new MuendlicheAntwortFrageBearbeitenDialog(frage);
    }
    
    public static void main(String[] args) {
	new BearbeiteDialog(new MuendlicheAntwortFrage("1", "2", "3", "4"));
    }
}

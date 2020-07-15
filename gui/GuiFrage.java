package gui;

import javax.swing.JFrame;

public abstract class GuiFrage extends JFrame {

    private boolean antwortGegeben = false;

    public GuiFrage(String title) {
	super(title);
    }

    public void showAnswer() {}

    public void setAntwortGegeben(boolean wert) {
        antwortGegeben = wert;
    }

    public boolean getAntwortGegeben() {
        return antwortGegeben;
    }

    public void deactivateAnswers() {}

    public void activateAnswers() {}

    public void weiterInvisible() {}
}

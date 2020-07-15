package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import controller.Frage;
import controller.MuendlicheAntwortFrage;
import controller.VierAntwortenFrage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BuzzermodusManager extends SpielManager {

    private GuiStatistikMpBuzzer statistik;
    private GuiFrage aktuelleFrage;
    private boolean zeitUm = false;
    private boolean skip = false;
    private boolean buzzerEnabled = true;

    private int counter;

    private boolean buzzerP1;
    private boolean buzzerP2;

    Timer fokusTimer;
	Timer timerSekunde = new Timer(1000, null);

	private String tonZaehlen = ".\\src\\audioDateien\\drumstick.wav";
	private String tonEnde = ".\\src\\audioDateien\\tonEnde_neu.wav";
	private Clip clipZaehlen = null;
	private Clip clipEnde = null;
	private AudioInputStream streamZaehlen = null;
	private AudioInputStream streamEnde = null;
    
    public BuzzermodusManager() {
		statistik = new GuiStatistikMpBuzzer("Statistik", this);

		ActionListener fokusAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (buzzerEnabled) {
					statistik.setFocusOnBuzzer();
				}
			}
		};
		Timer fokusTimer = new Timer(3000, fokusAction);
		fokusTimer.start();


		statistik.setLocation(200, 200);
		statistik.setVisible(false);
	}
    
    @Override
    public void next(int anzahlPunkte) {
		statistik.setVisible(true);
		statistik.resetCountdownAnzeige();
		statistik.activateBuzzer();
		buzzerEnabled = true;
		if(!skip) {
			if (anzahlPunkte == -1) {
				statistik.dispose();
				return;
			}
			if (zeitUm) {
				if (statistik.getSpieler1Gedrueckt()) {
					statistik.setPunkteSpieler2(statistik.getPunkteSpieler2() + 1);
				} else {
					statistik.setPunkteSpieler1(statistik.getPunkteSpieler1() + 1);
				}
				zeitUm = false;
			} else {
				if (statistik.getSpieler1Gedrueckt()) {
					if (anzahlPunkte == 0) {
						statistik.setPunkteSpieler2(statistik.getPunkteSpieler2() + 1);
					} else if (anzahlPunkte == -5) {

					} else {
						statistik.setPunkteSpieler1(statistik.getPunkteSpieler1() + anzahlPunkte);
					}
				} else {
					if (anzahlPunkte == 0) {
						statistik.setPunkteSpieler1(statistik.getPunkteSpieler1() + 1);
					} else if (anzahlPunkte == -5) {

					} else {
						statistik.setPunkteSpieler2(statistik.getPunkteSpieler2() + anzahlPunkte);
					}
				}
			}
		}
		if (fragen.size() == 0) {
			if (statistik.getPunkteSpieler1() > statistik.getPunkteSpieler2()) {
			JOptionPane.showMessageDialog(null, statistik.getName1() + " hat gewonnen!");
			} else if (statistik.getPunkteSpieler1() < statistik.getPunkteSpieler2()) {
			JOptionPane.showMessageDialog(null, statistik.getName2() + " hat gewonnen!");
			} else {
			JOptionPane.showMessageDialog(null, "Unentschieden!");
			}
			new Hauptmenue("Hauptmenue");
			statistik.dispose();
			return;
		}
		statistik.setAktuelleFrage(statistik.getAktuelleFrage() + 1);
		Frage f = fragen.remove(0);
		if (f instanceof VierAntwortenFrage) {
			GuiFrage fr = new GuiVierAntwortFrage((VierAntwortenFrage) f, this);
			this.aktuelleFrage = fr;
		} else if (f instanceof MuendlicheAntwortFrage) {
			GuiFrage fr = new GuiMuendlicheAntwortFrage((MuendlicheAntwortFrage) f, this);
			this.aktuelleFrage = fr;
		}
		aktuelleFrage.deactivateAnswers();
		aktuelleFrage.weiterInvisible();
		statistik.setFocusOnBuzzer();
		skip = false;
    }

    public void buzzerP1Pressed() {
    	setBuzzerP1(true);
		startCountdown();
	}

	public void buzzerP2Pressed() {
		setBuzzerP2(true);
		startCountdown();
	}

    @Override
    public void init(int anzahlFragen) {
	statistik.setMaxFrage(fragen.size());
    }

    @Override
    public void setNamen(String name1, String name2) {
	statistik.setNamen(name1, name2);
    }
    
    public void disposeAktuelleFrage() {
	aktuelleFrage.dispose();
    }
    
    public void enableFragenGui() {
	this.aktuelleFrage.setEnabled(true);
    }

    public void setZeitUm(boolean zeitUm) {
		this.zeitUm = zeitUm;
		JOptionPane.showMessageDialog(null, "Die Zeit ist um!");
    }


    public void stopSpiel() {
		statistik.dispose();
		aktuelleFrage.dispose();
		new Hauptmenue("Hauptmenue");
    }

    public void startCountdown() {
    	counter = 6;
    	statistik.deactivateBuzzer();
    	buzzerEnabled = false;
    	aktuelleFrage.activateAnswers();
		timerSekunde = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				counter--;

				try {
					streamZaehlen = AudioSystem.getAudioInputStream(new File(tonZaehlen).getAbsoluteFile());
					clipZaehlen = AudioSystem.getClip();
					clipZaehlen.open(streamZaehlen);
					streamEnde = AudioSystem.getAudioInputStream(new File(tonEnde).getAbsoluteFile());
					clipEnde = AudioSystem.getClip();
					clipEnde.open(streamEnde);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if(counter == 5) {
					if (aktuelleFrage.getAntwortGegeben()) {
						timerSekunde.stop();
						aktuelleFrage.setAntwortGegeben(false);
					}
					statistik.countdown5();
					try {
						if (clipZaehlen != null) {
							clipZaehlen.start();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else if(counter == 4) {
					statistik.countdown4();
					if (aktuelleFrage.getAntwortGegeben()) {
						timerSekunde.stop();
						aktuelleFrage.setAntwortGegeben(false);
					}
					try {
						if (clipZaehlen != null) {
							clipZaehlen.start();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else if(counter == 3) {
					statistik.countdown3();
					if (aktuelleFrage.getAntwortGegeben()) {
						timerSekunde.stop();
						aktuelleFrage.setAntwortGegeben(false);
					}
					try {
						if (clipZaehlen != null) {
							clipZaehlen.start();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else if(counter == 2) {
					statistik.countdown2();
					if (aktuelleFrage.getAntwortGegeben()) {
						timerSekunde.stop();
						aktuelleFrage.setAntwortGegeben(false);
					}
					try {
						if (clipZaehlen != null) {
							clipZaehlen.start();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else if(counter == 1) {
					statistik.countdown1();
					if (aktuelleFrage.getAntwortGegeben()) {
						timerSekunde.stop();
						aktuelleFrage.setAntwortGegeben(false);
					}
					try {
						if (clipZaehlen != null) {
							clipZaehlen.start();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else if(counter == 0) {
					statistik.countdown0();
					if (aktuelleFrage.getAntwortGegeben()) {
						timerSekunde.stop();
						aktuelleFrage.setAntwortGegeben(false);
					}
					try {
						if (clipEnde != null) {
							clipEnde.start();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else if(counter == -1) {
					statistik.countdownEnd();

					timerSekunde.stop();

					aktuelleFrage.showAnswer();

					try {
						if (clipZaehlen != null) {
							clipZaehlen.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					try {
						if (clipEnde != null) {
							clipEnde.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					try {
						if (streamZaehlen != null) {
							streamZaehlen.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					try {
						if (streamEnde != null) {
							streamEnde.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		timerSekunde.start();
	}

    public void setBuzzerP1 (boolean aktiviert) {
    	buzzerP1 = aktiviert;
	}

	public void setBuzzerP2 (boolean aktiviert) {
    	buzzerP2 = aktiviert;
	}

	public boolean getBuzzerP1 () {
    	return buzzerP1;
	}

	public boolean getBuzzerP2 () {
    	return buzzerP2;
	}

	public void frageUeberspringen() {
    	aktuelleFrage.showAnswer();
    	skip = true;
	}
}

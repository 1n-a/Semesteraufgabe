package controller;

public enum Farbe {

    dunkelLila(75, 58, 132), 
    gelb(255, 173, 0), 
    schwarz(0, 0, 0), 
    weiss(255, 255, 255),
    grau(142, 142, 142),
    pink(255, 0, 255);

    private final int value1;
    private final int value2;
    private final int value3;
      
    private Farbe(int value1, int value2, int value3) {
      this.value1 = value1;
      this.value2 = value2;
      this.value3 = value3;
    }
    
    public int getValue1() {
      return value1;
    }
    
    public int getValue2() {
      return value2;
    }
    
    public int getValue3() {
	return value3;
    }
}

package ir.ac.kntu.LOGIC;

public class ArmoredTank extends Tank{
    private int health=2;
    private int bulletStrength=1;
    private int point=200;
    private boolean isRandom=false;

    public void setRandom(boolean random) {
        isRandom = random;
    }
}

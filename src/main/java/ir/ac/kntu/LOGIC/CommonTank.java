package ir.ac.kntu.LOGIC;

public class CommonTank extends Tank{
    private int health=1;
    private int bulletStrength=1;
    private int point=100;

    private boolean isRandom=false;
    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }
}

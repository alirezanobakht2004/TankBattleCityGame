package ir.ac.kntu.LOGIC;

import javafx.scene.image.Image;

public class CommonTank extends Tank{
    private int health=1;
    private int bulletStrength=1;
    private int point=100;

    private boolean isRandom=false;

    public CommonTank(Image image) {
        super(image);
    }

    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }
}

package ir.ac.kntu.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick extends ImageView {


    private int health = 4;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Brick(Image image) {
        super(image);
    }
}

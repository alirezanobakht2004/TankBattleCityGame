package ir.ac.kntu.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick extends ImageView {


    private int Health=4;
    public int getHealth() {
        return Health;
    }
    public void setHealth(int health) {
        Health = health;
    }
    public Brick(Image image){
        super(image);
    }
}

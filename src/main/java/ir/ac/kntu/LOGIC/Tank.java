package ir.ac.kntu.LOGIC;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Tank extends ImageView {
    private Direction direction;

    public Tank(Image image) {
        super(image);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


}

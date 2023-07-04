package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends ImageView {

    private int x;

    private int y;

    private Direction direction;

    public Bullet(Image image) {
        super(image);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}

package ir.ac.kntu.LOGIC;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class ArmoredTank extends Tank{
    private Random random = new Random();
    private int health=2;
    private int bulletStrength=1;
    private int point=200;
    private boolean isRandom=false;

    public ArmoredTank(Image image) {
        super(image);
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public void move() {
        setDirection(randomDirection());
        if (getDirection() == Direction.UP) {
            if (GridPane.getRowIndex(this) != 0) {
                GridPane.setRowIndex(this, GridPane.getRowIndex(this) - 1);
            }
        } else if (getDirection() == Direction.DOWN) {
            if (GridPane.getRowIndex(this) != 12) {
                GridPane.setRowIndex(this, GridPane.getRowIndex(this) + 1);
            }
        } else if (getDirection() == Direction.LEFT) {
            if (GridPane.getColumnIndex(this) != 0) {
                GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) - 1);
            }
        } else if (getDirection() == Direction.RIGHT) {
            if (GridPane.getColumnIndex(this) != 12) {
                GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) + 1);
            }
        }
    }

    public Direction randomDirection() {
        switch (random.nextInt(4)) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.LEFT;
        }
        return null;
    }
}

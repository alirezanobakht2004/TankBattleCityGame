package ir.ac.kntu.LOGIC;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class CommonTank extends Tank {
    private Random random = new Random();
    private int health = 1;
    private int bulletStrength = 1;
    private int point = 100;
    private Direction direction = randomDirection();
    private boolean isRandom = false;

    public CommonTank(Image image) {
        super(image);
    }

    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public void move() {
        if (!collision(direction)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-up.png"));
            } else {
                setImage(new Image("images/common-tank-up.png"));
            }
            GridPane.setRowIndex(this, GridPane.getRowIndex(this) - 1);
        }
        if (!collision(direction)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-down.png"));
            } else {
                setImage(new Image("images/common-tank-down.png"));
            }
            GridPane.setRowIndex(this, GridPane.getRowIndex(this) + 1);
        }
        if (!collision(direction)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-left.png"));
            } else {
                setImage(new Image("images/common-tank-left.png"));
            }
            GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) - 1);
        }
        if (!collision(direction)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-right.png"));
            } else {
                setImage(new Image("images/common-tank-right.png"));
            }
            GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) + 1);
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

    public void changeDirection() {
        direction = randomDirection();
    }

    public boolean collision(Direction direction) {
        if (direction == Direction.UP) {
            if (GridPane.getRowIndex(this) == 0) {
                return true;
            }
        } else if (direction == Direction.DOWN) {
            if (GridPane.getRowIndex(this) == 12) {
                return true;
            }
        } else if (direction == Direction.RIGHT) {
            if (GridPane.getColumnIndex(this) == 12) {
                return true;
            }
        } else if (direction == Direction.LEFT) {
            if (GridPane.getColumnIndex(this) == 0) {
                return true;
            }
        }
        return false;
    }

}

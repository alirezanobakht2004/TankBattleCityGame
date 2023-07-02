package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class ArmoredTank extends Tank {
    private Random random = new Random();
    private int health = 2;
    private int bulletStrength = 1;
    private int point = 200;
    private boolean isRandom = false;
    private Direction direction = randomDirection();

    public ArmoredTank(Image image) {
        super(image);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setBulletStrength(int bulletStrength) {
        this.bulletStrength = bulletStrength;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getHealth() {
        return health;
    }

    public int getBulletStrength() {
        return bulletStrength;
    }

    public int getPoint() {
        return point;
    }

    public boolean isRandom() {
        return isRandom;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public void move(GridPane gameMap) {
        if (!collision(direction, gameMap) && direction.equals(Direction.UP)) {
            if (isRandom) {
                setImage(new Image("images/armored-random-tank-up.png"));
            } else {
                setImage(new Image("images/armored-tank-up.png"));
            }
            GridPane.setRowIndex(this, GridPane.getRowIndex(this) - 1);
        }
        if (!collision(direction, gameMap) && direction.equals(Direction.DOWN)) {
            if (isRandom) {
                setImage(new Image("images/armored-random-tank-down.png"));
            } else {
                setImage(new Image("images/armored-tank-down.png"));
            }
            GridPane.setRowIndex(this, GridPane.getRowIndex(this) + 1);
        }
        if (!collision(direction, gameMap) && direction.equals(Direction.LEFT)) {
            if (isRandom) {
                setImage(new Image("images/armored-random-tank-left.png"));
            } else {
                setImage(new Image("images/armored-tank-left.png"));
            }
            GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) - 1);
        }
        if (!collision(direction, gameMap) && direction.equals(Direction.RIGHT)) {
            if (isRandom) {
                setImage(new Image("images/armored-random-tank-right.png"));
            } else {
                setImage(new Image("images/armored-tank-right.png"));
            }
            GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) + 1);
        }
        if (collision(direction, gameMap)) {
            changeDirection();
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

    public boolean collision(Direction direction, GridPane gameMap) {
        if (direction == Direction.UP) {
            if (GridPane.getRowIndex(this) == 0 || objectCollision(GridPane.getRowIndex(this) - 1, GridPane.getColumnIndex(this), gameMap)) {
                return true;
            }

        } else if (direction == Direction.DOWN) {
            if (GridPane.getRowIndex(this) == 12 || objectCollision(GridPane.getRowIndex(this) + 1, GridPane.getColumnIndex(this), gameMap)) {
                return true;
            }
        } else if (direction == Direction.RIGHT) {
            if (GridPane.getColumnIndex(this) == 12 || objectCollision(GridPane.getRowIndex(this), GridPane.getColumnIndex(this) + 1, gameMap)) {
                return true;
            }
        } else if (direction == Direction.LEFT) {
            if (GridPane.getColumnIndex(this) == 0 || objectCollision(GridPane.getRowIndex(this), GridPane.getColumnIndex(this) - 1, gameMap)) {
                return true;
            }
        }
        return false;
    }

    public boolean objectCollision(int rowIndex, int columnIndex, GridPane gameMap) {
        Node node = null;
        for (Node n : gameMap.getChildren()) {
            if (GridPane.getRowIndex(n) == rowIndex && GridPane.getColumnIndex(n) == columnIndex) {
                node = n;
                break;
            }
        }
        if (node != null) {
            if (Map.getMap()[rowIndex][columnIndex] != Block.WATER) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

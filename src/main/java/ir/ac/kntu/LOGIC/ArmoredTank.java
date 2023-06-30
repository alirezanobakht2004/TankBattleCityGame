package ir.ac.kntu.LOGIC;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class ArmoredTank extends Tank {
    private Random random = new Random();
    private int health = 2;
    private int bulletStrength = 1;
    private int point = 200;
    private boolean isRandom = false;
    private Direction direction=randomDirection();

    public ArmoredTank(Image image) {
        super(image);
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public void move() {
        if (direction.equals(Direction.UP)) {
            if (GridPane.getRowIndex(this) != 0) {
                if(isRandom){
                    setImage(new Image("images/armored-random-tank-up.png"));
                }else {
                    setImage(new Image("images/armored-tank-up.png"));
                }
                GridPane.setRowIndex(this, GridPane.getRowIndex(this) - 1);
            }
        } else if (direction.equals(Direction.DOWN)) {
            if (GridPane.getRowIndex(this) != 12) {
                if(isRandom){
                    setImage(new Image("images/armored-random-tank-down.png"));
                }else {
                    setImage(new Image("images/armored-tank-down.png"));
                }
                GridPane.setRowIndex(this, GridPane.getRowIndex(this) + 1);
            }
        } else if (direction.equals(Direction.LEFT)  ) {
            if (GridPane.getColumnIndex(this) != 0) {
                if(isRandom){
                    setImage(new Image("images/armored-random-tank-left.png"));
                }else {
                    setImage(new Image("images/armored-tank-left.png"));
                }
                GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) - 1);
            }
        } else if (direction.equals(Direction.RIGHT)) {
            if (GridPane.getColumnIndex(this) != 12) {
                if(isRandom){
                    setImage(new Image("images/armored-random-tank-right.png"));
                }else {
                    setImage(new Image("images/armored-tank-right.png"));
                }
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

    public void changeDirection(){
        direction=randomDirection();
    }
}

package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;
import ir.ac.kntu.GUI.Brick;
import ir.ac.kntu.GUI.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CommonTank extends Tank {
    private Random random = new Random();
    private int health = 1;
    private int bulletStrength = 1;
    private int point = 100;
    private Direction direction = randomDirection();
    private boolean isRandom = false;
    private Game game;
    private GridPane gameMap;

    public CommonTank(Image image) {
        super(image);
    }

    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
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

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void move(GridPane gameMap, Game game) {
        this.game = game;
        this.gameMap = gameMap;
        if (!collision(direction, gameMap) && direction.equals(Direction.UP)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-up.png"));
            } else {
                setImage(new Image("images/common-tank-up.png"));
            }
            GridPane.setRowIndex(this, GridPane.getRowIndex(this) - 1);
        }
        if (!collision(direction, gameMap) && direction.equals(Direction.DOWN)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-down.png"));
            } else {
                setImage(new Image("images/common-tank-down.png"));
            }
            GridPane.setRowIndex(this, GridPane.getRowIndex(this) + 1);
        }
        if (!collision(direction, gameMap) && direction.equals(Direction.LEFT)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-left.png"));
            } else {
                setImage(new Image("images/common-tank-left.png"));
            }
            GridPane.setColumnIndex(this, GridPane.getColumnIndex(this) - 1);
        }
        if (!collision(direction, gameMap) && direction.equals(Direction.RIGHT)) {
            if (isRandom) {
                setImage(new Image("images/random-tank-right.png"));
            } else {
                setImage(new Image("images/common-tank-right.png"));
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


    public void shoot(GridPane gameMap, Game game) {
        if (direction.equals(Direction.UP)) {
            shootUp(gameMap, game);
        } else if (direction.equals(Direction.DOWN)) {
            shootDown(gameMap, game);
        } else if (direction.equals(Direction.LEFT)) {
            shootLeft(gameMap, game);
        } else if (direction.equals(Direction.RIGHT)) {
            shootRight(gameMap, game);
        }
        System.out.println("ffg12222");

    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
        // code to be executed after 1 second
    }));

    public void shootUp(GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-up.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(this), GridPane.getRowIndex(this));
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            if (GridPane.getRowIndex(bullet) != 0 && !objectCollision(GridPane.getRowIndex(bullet) - 1, GridPane.getColumnIndex(bullet), gameMap)) {
                gameMap.getChildren().remove(bullet);
                gameMap.add(bullet, GridPane.getColumnIndex(bullet), GridPane.getRowIndex(bullet) - 1);
                bullet.setVisible(true);
            } else if (GridPane.getRowIndex(bullet) != 0) {
                timeline.stop();
                afterCollision(objectOfMap(GridPane.getRowIndex(bullet) - 1, GridPane.getColumnIndex(bullet), gameMap), gameMap, game);
                gameMap.getChildren().remove(bullet);
            } else {
                timeline.stop();
                gameMap.getChildren().remove(bullet);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void shootDown(GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-down.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(this), GridPane.getRowIndex(this));
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            if (GridPane.getRowIndex(bullet) != 12 && !objectCollision(GridPane.getRowIndex(bullet) + 1, GridPane.getColumnIndex(bullet), gameMap)) {
                gameMap.getChildren().remove(bullet);
                gameMap.add(bullet, GridPane.getColumnIndex(bullet), GridPane.getRowIndex(bullet) + 1);
                bullet.setVisible(true);
            } else if (GridPane.getRowIndex(bullet) != 12) {
                timeline.stop();
                afterCollision(objectOfMap(GridPane.getRowIndex(bullet) + 1, GridPane.getColumnIndex(bullet), gameMap), gameMap, game);
                gameMap.getChildren().remove(bullet);
            } else {
                timeline.stop();
                gameMap.getChildren().remove(bullet);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void shootRight(GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-right.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(this), GridPane.getRowIndex(this));
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            if (GridPane.getColumnIndex(bullet) != 12 && !objectCollision(GridPane.getRowIndex(bullet), GridPane.getColumnIndex(bullet) + 1, gameMap)) {
                gameMap.getChildren().remove(bullet);
                gameMap.add(bullet, GridPane.getColumnIndex(bullet) + 1, GridPane.getRowIndex(bullet));
                bullet.setVisible(true);
            } else if (GridPane.getColumnIndex(bullet) != 12) {
                timeline.stop();
                afterCollision(objectOfMap(GridPane.getRowIndex(bullet), GridPane.getColumnIndex(bullet) + 1, gameMap), gameMap, game);
                gameMap.getChildren().remove(bullet);
            } else {
                timeline.stop();
                gameMap.getChildren().remove(bullet);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void shootLeft(GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-left.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(this), GridPane.getRowIndex(this));
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            if (GridPane.getColumnIndex(bullet) != 0 && !objectCollision(GridPane.getRowIndex(bullet), GridPane.getColumnIndex(bullet) - 1, gameMap)) {
                gameMap.getChildren().remove(bullet);
                gameMap.add(bullet, GridPane.getColumnIndex(bullet) - 1, GridPane.getRowIndex(bullet));
                bullet.setVisible(true);
            } else if (GridPane.getColumnIndex(bullet) != 0) {
                timeline.stop();
                afterCollision(objectOfMap(GridPane.getRowIndex(bullet), GridPane.getColumnIndex(bullet) - 1, gameMap), gameMap, game);
                gameMap.getChildren().remove(bullet);
            } else {
                timeline.stop();
                gameMap.getChildren().remove(bullet);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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

    public ImageView objectOfMap(int rowIndex, int columnIndex, GridPane gameMap) {
        Node node = null;
        for (Node n : gameMap.getChildren()) {
            if (GridPane.getRowIndex(n) == rowIndex && GridPane.getColumnIndex(n) == columnIndex) {
                node = n;
                break;
            }
        }
        return (ImageView) node;
    }

    public void afterCollision(ImageView n, GridPane gameMap, Game game) {
        if (n instanceof Brick) {
            ((Brick) n).setHealth(((Brick) n).getHealth() - getBulletStrength());
            if (((Brick) n).getHealth() == 3) {
                n.setImage(new Image("images/3rowsBrick.png"));
            } else if (((Brick) n).getHealth() == 2) {
                n.setImage(new Image("images/2rowsBrick.png"));
            } else if (((Brick) n).getHealth() == 1) {
                n.setImage(new Image("images/1rowBrick.png"));
            } else {
                gameMap.getChildren().remove(n);
            }
        } else if (n instanceof Bullet) {
            gameMap.getChildren().remove(n);
        } else if (n instanceof PlayerTank) {
            game.getPlayer().setHealth(game.getPlayer().getHealth() - getBulletStrength());
            game.updateRightSide();
            GridPane.setColumnIndex(n, ((PlayerTank) n).getStartColumn());
            GridPane.setRowIndex(n, ((PlayerTank) n).getStartRow());
        }
    }
}

package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;
import ir.ac.kntu.GUI.Brick;
import ir.ac.kntu.GUI.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import javax.sound.sampled.Line;
import java.util.List;

public class PlayerTank extends Tank {
    private PlayerSaving playerSaving = new PlayerSaving();
    private int health = 3;
    private int bulletStrength = 1;
    private int startRow;
    private int startColumn;


    public PlayerTank(Image image) {
        super(image);
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void playerTankController(Scene gameScene, PlayerTank node, Block[][] mapBlocks, GridPane gameMap, Game game) {
        timeline.stop();
        node.setDirection(Direction.UP);
        gameScene.setOnKeyPressed(event -> {
            int columnIndex = GridPane.getColumnIndex(node);
            int rowIndex = GridPane.getRowIndex(node);
            if (columnIndex == 0 && event.getCode() == KeyCode.LEFT) return;
            if (columnIndex == 12 && event.getCode() == KeyCode.RIGHT) return;
            if (rowIndex == 0 && event.getCode() == KeyCode.UP) return;
            if (rowIndex == 12 && event.getCode() == KeyCode.DOWN) return;
            switch (event.getCode()) {
                case LEFT:
                    if (node.getDirection().equals(Direction.LEFT) && !collision(getDirection(), gameMap)) {
                        GridPane.setColumnIndex(node, columnIndex - 1);
                    }
                    node.setImage(new Image("images/yellow-tank-left.png"));
                    node.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    if (node.getDirection().equals(Direction.RIGHT) && !collision(getDirection(), gameMap)) {
                        GridPane.setColumnIndex(node, columnIndex + 1);
                    }
                    node.setImage(new Image("images/yellow-tank-right.png"));
                    node.setDirection(Direction.RIGHT);
                    break;
                case UP:
                    if (node.getDirection().equals(Direction.UP) && !collision(getDirection(), gameMap)) {
                        GridPane.setRowIndex(node, rowIndex - 1);
                    }
                    node.setImage(new Image("images/yellow-tank-up.png"));
                    node.setDirection(Direction.UP);
                    break;
                case DOWN:
                    if (node.getDirection().equals(Direction.DOWN) && !collision(getDirection(), gameMap)) {
                        GridPane.setRowIndex(node, rowIndex + 1);
                    }
                    node.setImage(new Image("images/yellow-tank-down.png"));
                    node.setDirection(Direction.DOWN);
                    break;
                case SPACE:
                    playerBulletShoot(node, gameMap, game);
                    break;
                default:
                    break;
            }
        });
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
        // code to be executed after 1 second
    }));

    public void playerBulletShoot(PlayerTank playerTank, GridPane gameMap, Game game) {
        if (playerTank.getDirection().equals(Direction.UP)) {
            shootUp(playerTank, gameMap, game);
        } else if (playerTank.getDirection().equals(Direction.DOWN)) {
            shootDown(playerTank, gameMap, game);
        } else if (playerTank.getDirection().equals(Direction.LEFT)) {
            shootLeft(playerTank, gameMap, game);
        } else if (playerTank.getDirection().equals(Direction.RIGHT)) {
            shootRight(playerTank, gameMap, game);
        }
    }

    public void shootUp(PlayerTank playerTank, GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-up.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(playerTank), GridPane.getRowIndex(playerTank));
        GridPane.setHalignment(bullet, HPos.CENTER);
        GridPane.setValignment(bullet, VPos.CENTER);
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
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

    public void shootDown(PlayerTank playerTank, GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-down.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(playerTank), GridPane.getRowIndex(playerTank));
        GridPane.setHalignment(bullet, HPos.CENTER);
        GridPane.setValignment(bullet, VPos.CENTER);
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
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

    public void shootRight(PlayerTank playerTank, GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-right.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(playerTank), GridPane.getRowIndex(playerTank));
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
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

    public void shootLeft(PlayerTank playerTank, GridPane gameMap, Game game) {
        Bullet bullet = new Bullet(new Image("images/missile-left.gif"));
        gameMap.add(bullet, GridPane.getColumnIndex(playerTank), GridPane.getRowIndex(playerTank));
        bullet.setVisible(false);
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
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
        List<Player> l = playerSaving.read();
        if (n instanceof CommonTank) {
            ((CommonTank) n).setHealth(((CommonTank) n).getHealth() - l.get(findPlayer(game)).getPlayerBulletStrentgh());
            if (((CommonTank) n).getHealth() <= 0) {
                l.get(findPlayer(game)).setScore(l.get(findPlayer(game)).getScore() + 100);
                game.getTanks().remove(n);
                gameMap.getChildren().remove(n);
                game.updateRightSide();
                if(((CommonTank) n).isRandom()){
                    game.chanceItem();
                }
            }
        } else if (n instanceof ArmoredTank) {
            ((ArmoredTank) n).setHealth(((ArmoredTank) n).getHealth() - l.get(findPlayer(game)).getPlayerBulletStrentgh());
            if (((ArmoredTank) n).getHealth() <= 0) {
                l.get(findPlayer(game)).setScore(l.get(findPlayer(game)).getScore() + 200);
                game.getTanks().remove(n);
                gameMap.getChildren().remove(n);
                game.updateRightSide();
                if(((ArmoredTank) n).isRandom()){
                    game.chanceItem();
                }
            }
        } else if (n instanceof Brick) {
            ((Brick) n).setHealth(((Brick) n).getHealth() - l.get(findPlayer(game)).getPlayerBulletStrentgh());
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
        }
        playerSaving.setPlayers(l);
        playerSaving.save();
    }

    public int findPlayer(Game game) {
        List<Player> l = playerSaving.read();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals(game.getPlayer().getName())) {
                return i;
            }
        }
        return 0;
    }

}


package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;
import ir.ac.kntu.GUI.Brick;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class PlayerTank extends Tank {
    private int health = 3;
    private int bulletStrength = 1;

    public PlayerTank(Image image) {
        super(image);
    }

    public void playerTankController(Scene gameScene, PlayerTank node, Block[][] mapBlocks, GridPane gameMap) {
        node.setDirection(Direction.UP);
        mapBlocks[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = Block.EMPTY;
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
                    playerBulletShoot(node, gameMap);
                    break;
                default:
                    break;
            }
        });
    }
    private Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        // initializer
    }));
    public void playerBulletShoot(PlayerTank playerTank, GridPane gameMap) {
        if (playerTank.getDirection().equals(Direction.UP)) {
            Bullet bullet = new Bullet(new Image("images/missile-up.gif"));
            int tankCol = GridPane.getColumnIndex(playerTank);
            int tankRow = GridPane.getRowIndex(playerTank);
            gameMap.add(bullet, tankCol, tankRow);
            bullet.setVisible(false);
            timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
                if (GridPane.getRowIndex(bullet) != 0 && !objectCollision(GridPane.getRowIndex(bullet) - 1, GridPane.getColumnIndex(bullet), gameMap)) {
                    gameMap.getChildren().remove(bullet);
                    gameMap.add(bullet, GridPane.getColumnIndex(bullet), GridPane.getRowIndex(bullet) - 1);
                    bullet.setVisible(true);
                } else if(GridPane.getRowIndex(bullet) != 0){
                    afterCollision(objectOfMap(GridPane.getRowIndex(bullet) - 1,GridPane.getColumnIndex(bullet),gameMap),gameMap);
                    System.out.println("h");
                    gameMap.getChildren().remove(bullet);
                    timeline.stop();
                }
                else {
                    System.out.println("g");
                    gameMap.getChildren().remove(bullet);
                     timeline.stop();
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

        }
        /*else if (playerTank.getDirection().equals(Direction.DOWN)) {
            Bullet bullet = new Bullet(new Image("images/missile-down.gif"));
        } else if (playerTank.getDirection().equals(Direction.LEFT)) {
            Bullet bullet = new Bullet(new Image("images/missile-left.gif"));
        } else if (playerTank.getDirection().equals(Direction.RIGHT)) {
            Bullet bullet = new Bullet(new Image("images/missile-right.gif"));
        }*/
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

    public void afterCollision(ImageView n,GridPane gameMap){
        if(n instanceof Brick){
            ((Brick) n).setHealth(((Brick) n).getHealth()-1);
            if(((Brick) n).getHealth()==3){
                n.setFitHeight(36);
                n.setImage(new Image("images/3rowsBrick.png"));
            }else  if(((Brick) n).getHealth()==2){
                n.setFitHeight(22);
                n.setImage(new Image("images/2rowsBrick.png"));
            }else  if(((Brick) n).getHealth()==1){
                n.setFitHeight(8);
                n.setImage(new Image("images/1rowBrick.png"));
            }else  if(((Brick) n).getHealth()==0){
                gameMap.getChildren().remove(n);
            }
        }
    }

}


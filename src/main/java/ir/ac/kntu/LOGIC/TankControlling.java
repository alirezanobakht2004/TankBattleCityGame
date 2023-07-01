package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;
import ir.ac.kntu.GUI.Brick;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankControlling {
    private Random random = new Random(300);
    private List<Tank> tanks = new ArrayList<>();

    public List<Tank> tankMaker(int level) {
        for (int i = 0; i < 10 + (level - 1) * 4; i++) {
            int d = random.nextInt(4);
            switch (d) {
                case 0:
                    CommonTank commonTank = new CommonTank(new Image("images/common-tank-down.png"));
                    commonTank.setFitHeight(51);
                    commonTank.setFitWidth(51);
                    tanks.add(commonTank);
                    break;
                case 1:
                    ArmoredTank armoredTank = new ArmoredTank(new Image("images/armored-tank-down.png"));
                    armoredTank.setFitHeight(51);
                    armoredTank.setFitWidth(51);
                    tanks.add(armoredTank);
                    break;
                case 2:
                    CommonTank commonTankRandom = new CommonTank(new Image("images/random-tank-down.png"));
                    commonTankRandom.setFitHeight(51);
                    commonTankRandom.setFitWidth(51);
                    commonTankRandom.setRandom(true);
                    tanks.add(commonTankRandom);
                    break;
                case 3:
                    ArmoredTank armoredTankRandom = new ArmoredTank(new Image("images/armored-random-tank-down.png"));
                    armoredTankRandom.setFitHeight(51);
                    armoredTankRandom.setFitWidth(51);
                    armoredTankRandom.setRandom(true);
                    tanks.add(armoredTankRandom);
                    break;
                default:
                    break;
            }
        }
        return tanks;
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
                    if (node.getDirection().equals(Direction.LEFT) && mapBlocks[GridPane.getRowIndex(node)][columnIndex - 1].equals(Block.EMPTY)) {
                        GridPane.setColumnIndex(node, columnIndex - 1);
                    }
                    node.setImage(new Image("images/yellow-tank-left.png"));
                    node.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    if (node.getDirection().equals(Direction.RIGHT) && mapBlocks[GridPane.getRowIndex(node)][columnIndex + 1].equals(Block.EMPTY)) {
                        GridPane.setColumnIndex(node, columnIndex + 1);
                    }
                    node.setImage(new Image("images/yellow-tank-right.png"));
                    node.setDirection(Direction.RIGHT);
                    break;
                case UP:
                    if (node.getDirection().equals(Direction.UP) && mapBlocks[rowIndex - 1][GridPane.getColumnIndex(node)].equals(Block.EMPTY)) {
                        GridPane.setRowIndex(node, rowIndex - 1);
                    }
                    node.setImage(new Image("images/yellow-tank-up.png"));
                    node.setDirection(Direction.UP);
                    break;
                case DOWN:
                    if (node.getDirection().equals(Direction.DOWN) && mapBlocks[rowIndex + 1][GridPane.getColumnIndex(node)].equals(Block.EMPTY)) {
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


    public void tankMove(List<Tank> tanks, GridPane gameMap) {

        for (int i = 0; i < tanks.size(); i++) {
            try {
                Thread.sleep(50);
                if (tanks.get(i) instanceof CommonTank) {
                    ((CommonTank) tanks.get(i)).move(gameMap);
                } else if (tanks.get(i) instanceof ArmoredTank) {
                    ((ArmoredTank) tanks.get(i)).move(gameMap);
                }
            } catch (Exception e) {

            }
        }


    }

    public void playerBulletShoot(PlayerTank playerTank, GridPane gameMap) {

        if (playerTank.getDirection().equals(Direction.UP)) {
            Bullet bullet = new Bullet(new Image("images/missile-up.gif"));
            int tankCol = GridPane.getColumnIndex(playerTank);
            int tankRow = GridPane.getRowIndex(playerTank);
            gameMap.add(bullet, tankCol, tankRow);
            bullet.setVisible(false);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                if (GridPane.getRowIndex(bullet)!=0 &&!objectCollision(GridPane.getRowIndex(bullet)-1, GridPane.getColumnIndex(bullet) , gameMap)) {
                    gameMap.getChildren().remove(bullet);
                    gameMap.add(bullet, GridPane.getColumnIndex(bullet), GridPane.getRowIndex(bullet) - 1);
                    bullet.setVisible(true);
                } else{
                    gameMap.getChildren().remove(bullet);
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
                if(node instanceof Brick){
                    ((Brick) node).setHealth(((Brick) node).getHealth()-1);
                    if(((Brick) node).getHealth()==3){
                        ((Brick) node).setFitHeight(36);
                        ((Brick) node).setImage(new Image("images/3rowsBrick.png"));
                    } else if(((Brick) node).getHealth()==2){
                        ((Brick) node).setFitHeight(22);
                        ((Brick) node).setImage(new Image("images/2rowsBrick.png"));
                    }else if(((Brick) node).getHealth()==1){
                        ((Brick) node).setFitHeight(7);
                        ((Brick) node).setImage(new Image("images/1rowBrick.png"));
                    } else if(((Brick) node).getHealth()==0){
                        gameMap.getChildren().remove(node);
                    }


                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }


    }


}

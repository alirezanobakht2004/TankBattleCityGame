package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankControlling {
    private Random random = new Random(300);
    private List<Tank> tanks=new ArrayList<>();

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

    public void playerTankController(Scene gameScene, PlayerTank node, Block[][] mapBlocks){
        node.setDirection(Direction.UP);
        mapBlocks[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)]=Block.EMPTY;
        gameScene.setOnKeyPressed(event -> {
            int columnIndex = GridPane.getColumnIndex(node);
            int rowIndex = GridPane.getRowIndex(node);
            if (columnIndex == 0 && event.getCode() == KeyCode.LEFT) return;
            if (columnIndex == 12 && event.getCode() == KeyCode.RIGHT) return;
            if (rowIndex == 0 && event.getCode() == KeyCode.UP) return;
            if (rowIndex == 12 && event.getCode() == KeyCode.DOWN) return;
            switch (event.getCode()) {
                case LEFT:
                    if (node.getDirection().equals(Direction.LEFT) && mapBlocks[GridPane.getRowIndex(node)][columnIndex-1].equals(Block.EMPTY)) {
                        GridPane.setColumnIndex(node, columnIndex - 1);
                    }
                    node.setImage(new Image("images/yellow-tank-left.png"));
                    node.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    if (node.getDirection().equals(Direction.RIGHT) && mapBlocks[GridPane.getRowIndex(node)][columnIndex+1].equals(Block.EMPTY)) {
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
                    if (node.getDirection().equals(Direction.DOWN) && mapBlocks[rowIndex +1][GridPane.getColumnIndex(node)].equals(Block.EMPTY)) {
                        GridPane.setRowIndex(node, rowIndex + 1);
                    }
                    node.setImage(new Image("images/yellow-tank-down.png"));
                    node.setDirection(Direction.DOWN);
                    break;
                case SPACE:
                    if(node.getDirection().equals(Direction.UP)){
                        Bullet bullet = new Bullet(new Image("images/missile-up.gif"));
                    } else if(node.getDirection().equals(Direction.DOWN)){
                        Bullet bullet = new Bullet(new Image("images/missile-down.gif"));
                    }else if(node.getDirection().equals(Direction.LEFT)){
                        Bullet bullet = new Bullet(new Image("images/missile-left.gif"));
                    }else if(node.getDirection().equals(Direction.RIGHT)){
                        Bullet bullet = new Bullet(new Image("images/missile-right.gif"));
                    }
                    break;
                default:
                    break;
            }
        });
    }


}

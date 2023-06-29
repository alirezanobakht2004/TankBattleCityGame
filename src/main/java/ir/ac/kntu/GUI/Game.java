package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Map;
import ir.ac.kntu.LOGIC.Tank;
import ir.ac.kntu.LOGIC.TankControlling;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

public class Game {
    private GridPane gameMap = new GridPane();
    private List<Tank> tanks;
    private int level;
    private Map map = new Map();

    public GridPane getGameMap() {
        return gameMap;
    }

    public void gameStart(Node node) {
        gameMap.setPrefSize(650, 650);
        gameMap.setStyle("-fx-background-color: black;");
        gameMap.setPadding(new Insets(0));
        gameMap.setHgap(0);
        gameMap.setVgap(0);
        for (int i = 0; i < 13; i++) {
            RowConstraints rowConstraints = new RowConstraints(50);
            gameMap.getRowConstraints().add(rowConstraints);
            ColumnConstraints columnConstraints = new ColumnConstraints(50);
            gameMap.getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                mapBuilding(i, j);
            }
        }
        setLevel(node);
        tanks=map.tankMake(level);
    }

    public void setLevel(Node node) {
        if (node.equals("level1")) {
            level = 1;
        } else if (node.equals("level2")) {
            level = 2;
        } else if (node.equals("level3")) {
            level = 3;
        } else if (node.equals("level4")) {
            level = 4;
        } else if (node.equals("level5")) {
            level = 5;
        } else if (node.equals("level6")) {
            level = 6;
        } else if (node.equals("level7")) {
            level = 7;
        } else if (node.equals("level8")) {
            level = 8;
        } else if (node.equals("level9")) {
            level = 9;
        } else if (node.equals("level10")) {
            level = 10;
        }
    }

    public void mapBuilding(int i, int j) {
        if (Map.getMap()[i][j] == Block.BRICK) {
            Image img = new Image("images/brick.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.METAL) {
            Image img = new Image("images/Stone.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.PLAYERTANK) {
            Image img = new Image("images/yellow-tank-up.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.COMMONTANK) {
            Image img = new Image("images/common-tank-down.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.FLAG) {
            Image img = new Image("images/flag.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.ARMOREDTANK) {
            Image img = new Image("images/armored-tank-down.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.RANDOMTANKCOMMON) {
            Image img = new Image("images/random-tank-down.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.RANDOMTANKARMED) {
            Image img = new Image("images/armored-random-tank-down.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        } else if (Map.getMap()[i][j] == Block.WATER) {
            Image img = new Image("images/water.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(51);
            imgView.setFitHeight(51);
            gameMap.add(imgView, j, i);
        }
    }

}

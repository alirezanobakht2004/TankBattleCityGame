package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Map;
import ir.ac.kntu.LOGIC.PlayerTank;
import ir.ac.kntu.LOGIC.Tank;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private GridPane container = new GridPane();
    private GridPane gameMap = new GridPane();
    private List<Tank> reservedTanks = new ArrayList<>();
    private List<Tank> tanks = new ArrayList<>();

    private PlayerTank playerTank = new PlayerTank(new Image("images/yellow-tank-up.png"));
    private int level;
    private Map map = new Map();
    private VBox tanksCon;
    private int indexOfTankAdding;

    public GridPane getGameMap() {
        return container;
    }

    public PlayerTank getPlayerTank() {
        return playerTank;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void containerBuild() {
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        container.getColumnConstraints().addAll(col1, col2, col3);
        container.getRowConstraints().addAll(row1, row2, row3);
        col1.setPercentWidth(5);
        col2.setPercentWidth(75);
        col3.setPercentWidth(20);
        row1.setPercentHeight(2);
        row2.setPercentHeight(96);
        row3.setPercentHeight(2);
        container.setStyle("-fx-background-color: rgb(80, 80, 80);");
        tankSpawn();
        setRightSide();
    }

    public void setRightSide() {
        tanksCon = new VBox();
        for (int i = 0; i < reservedTanks.size() / 2; i++) {
            HBox row = new HBox();
            for (int j = 0; j < 2; j++) {
                Image img = new Image("images/black-tank.png");
                ImageView imgView = new ImageView(img);
                imgView.setFitWidth(25);
                imgView.setFitHeight(25);
                row.getChildren().add(imgView);
            }
            tanksCon.getChildren().add(row);
        }
        tanksCon.setPadding(new Insets(10, 0, 0, 30));
        container.add(tanksCon, 2, 1);
    }

    public void gameStart(String node) {
        setLevel(node);
        reservedTanks = map.tankMake(level);
        containerBuild();
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
        container.add(gameMap, 1, 1);
    }

    public void setLevel(String node) {
        if (node.equals("LEVEL: 1")) {
            level = 1;
        } else if (node.equals("LEVEL: 2")) {
            level = 2;
        } else if (node.equals("LEVEL: 3")) {
            level = 3;
        } else if (node.equals("LEVEL: 4")) {
            level = 4;
        } else if (node.equals("LEVEL: 5")) {
            level = 5;
        } else if (node.equals("LEVEL: 6")) {
            level = 6;
        } else if (node.equals("LEVEL: 7")) {
            level = 7;
        } else if (node.equals("LEVEL: 8")) {
            level = 8;
        } else if (node.equals("LEVEL: 9")) {
            level = 9;
        } else if (node.equals("LEVEL: 10")) {
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
            playerTank.setFitWidth(51);
            playerTank.setFitHeight(51);
            gameMap.add(playerTank, j, i);
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

    public void tankSpawn() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (tanks.size() < 4) {
                    indexOfTankAdding = 0;
                    for (int i = 0; i < 4 - tanks.size(); i++) {
                        Platform.runLater(() -> {
                            for (int a = 0; a < 13; a++) {
                                for (int b = 0; b < 13; b++) {
                                    if (Map.getMap()[a][b] == Block.WATER) {
                                        gameMap.add(reservedTanks.get(indexOfTankAdding), b, a);
                                        tanks.add((reservedTanks.get(indexOfTankAdding)));
                                        reservedTanks.remove(indexOfTankAdding);
                                        indexOfTankAdding++;
                                    }
                                }
                            }
                        });
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void addTanksToMap() {


    }


}

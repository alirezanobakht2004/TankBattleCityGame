package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {

    private GridPane container = new GridPane();
    private GridPane gameMap = new GridPane();
    private List<Tank> reservedTanks = new ArrayList<>();
    private List<Tank> tanks = new ArrayList<>();

    private PlayerTank playerTank = new PlayerTank(new Image("images/yellow-tank-up.png"));
    private int level;
    private Map map = new Map();
    private VBox tanksCon;
    private TankControlling tankControlling = new TankControlling();
    private List<WaterPositions> waterPositions = new ArrayList<>();

    public GridPane getGameMap() {
        return container;
    }

    public GridPane gameMapCell() {
        return gameMap;
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
            Brick brick = new Brick(new Image("images/brick.png"));
            brick.setFitWidth(51);
            brick.setFitHeight(51);
            gameMap.add(brick, j, i);
        } else if (Map.getMap()[i][j] == Block.METAL) {
            Metal metal = new Metal(new Image("images/Stone.png"));
            metal.setFitWidth(51);
            metal.setFitHeight(51);
            gameMap.add(metal, j, i);
        } else if (Map.getMap()[i][j] == Block.PLAYERTANK) {
            playerTank.setFitWidth(51);
            playerTank.setFitHeight(51);
            gameMap.add(playerTank, j, i);
        } else if (Map.getMap()[i][j] == Block.COMMONTANK) {
            CommonTank commonTank = new CommonTank(new Image("images/common-tank-down.png"));
            commonTank.setFitWidth(51);
            commonTank.setFitHeight(51);
            gameMap.add(commonTank, j, i);
        } else if (Map.getMap()[i][j] == Block.FLAG) {
            Flag flag = new Flag(new Image("images/flag.png"));
            flag.setFitWidth(51);
            flag.setFitHeight(51);
            gameMap.add(flag, j, i);
        } else if (Map.getMap()[i][j] == Block.ARMOREDTANK) {
            ArmoredTank armoredTank = new ArmoredTank(new Image("images/armored-tank-down.png"));
            armoredTank.setFitWidth(51);
            armoredTank.setFitHeight(51);
            gameMap.add(armoredTank, j, i);
        } else if (Map.getMap()[i][j] == Block.RANDOMTANKCOMMON) {
            CommonTank commonTankRandom = new CommonTank(new Image("images/random-tank-down.png"));
            commonTankRandom.setFitWidth(51);
            commonTankRandom.setFitHeight(51);
            gameMap.add(commonTankRandom, j, i);
        } else if (Map.getMap()[i][j] == Block.RANDOMTANKARMED) {
            ArmoredTank armoredTankRandom = new ArmoredTank(new Image("images/armored-random-tank-down.png"));
            armoredTankRandom.setFitWidth(51);
            armoredTankRandom.setFitHeight(51);
            gameMap.add(armoredTankRandom, j, i);
        } else if (Map.getMap()[i][j] == Block.WATER) {
            Water water = new Water(new Image("images/water.png"));
            water.setFitWidth(51);
            water.setFitHeight(51);
            WaterPositions waterPositions1 = new WaterPositions(i, j, 0);
            waterPositions.add(waterPositions1);
            gameMap.add(water, j, i);
        }
    }



    public void tankSpawn() {
        Thread thread = new Thread(() -> {
            while (true) {
                tankControlling.tankMove(tanks, gameMap);
                if (tanks.size() < 4) {
                    Platform.runLater(() -> {
                            List<WaterPositions> sortedWaterPositions = waterPositions.stream().sorted(Comparator.comparing(WaterPositions::getNumberOfSpawn)).collect(Collectors.toList());
                            gameMap.add(reservedTanks.get(0), sortedWaterPositions.get(0).getY(), sortedWaterPositions.get(0).getX());
                            tanks.add((reservedTanks.get(0)));
                            reservedTanks.remove(0);
                            sortedWaterPositions.get(0).setNumberOfSpawn(sortedWaterPositions.get(0).getNumberOfSpawn() + 1);

                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


}

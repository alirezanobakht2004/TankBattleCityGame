package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.*;
import ir.ac.kntu.LOGIC.Map;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private GridPane container;
    private GridPane gameMap;
    private List<Tank> reservedTanks = new ArrayList<>();
    private List<Tank> tanks = new ArrayList<>();
    private Random random = new Random();

    private Player player;
    private int level;
    private Map map = new Map();
    private TankControlling tankControlling = new TankControlling();
    private List<WaterPositions> waterPositions = new ArrayList<>();
    private PlayerTank playerTank;
    private VBox rightVbox;
    private VBox tanksCon;
    private TankBattleCity tankBattleCity;
    private PlayerSaving playerSaving = new PlayerSaving();

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

    public Player getPlayer() {
        return player;
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
        container.setStyle("-fx-background-color: rgb(99,99,99);");
        tankSpawn();
        setRightSide();
    }

    public void setRightSide() {
        rightVbox = new VBox();
        reservesdTanksShow();
        healthShow();
        levelShow();
        container.add(rightVbox, 2, 1);
    }

    public void gameOver() {
        List<Player> l = playerSaving.read();
        l.get(findPlayer()).setGamesPlayed(l.get(findPlayer()).getGamesPlayed() + 1);
        int u = l.get(findPlayer()).getScore();
        int v = l.get(findPlayer()).getHighestScore();
        l.get(findPlayer()).setHighestScore(Math.max(u, v));
        l.get(findPlayer()).setScore(0);
        playerSaving.setPlayers(l);
        playerSaving.save();
        ImageView imageView = new ImageView(new Image("images/gameOver.png"));
        imageView.setFitWidth(800);
        imageView.setFitHeight(500);
        imageView.setLayoutY(600);
        imageView.setLayoutX(0);
        container.add(imageView, 0, 2, 1, 2);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), imageView);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        TranslateTransition moveUp = new TranslateTransition(Duration.seconds(2), imageView);
        moveUp.setByY(-350);
        moveUp.setInterpolator(Interpolator.EASE_OUT);
        SequentialTransition sequence = new SequentialTransition(fadeIn, moveUp);
        sequence.play();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(5),
                ae -> {
                    System.exit(0);
                }));
        timeline.play();
    }

    public void updateRightSide() {
        List<Player> l = playerSaving.read();
        if (l.get(findPlayer()).getHealth() <= 0) {
            gameOver();
        } else if (reservedTanks.size() == 0 && tanks.size() == 0) {
            container.getChildren().remove(gameMap);
            tankBattleCity.startGame("LEVEL: " + (level + 1), player);
        }
        container.getChildren().remove(rightVbox);
        setRightSide();
    }

    public int findPlayer() {
        List<Player> l = playerSaving.read();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals(player.getName())) {
                return i;
            }
        }
        return 0;
    }

    public void reservesdTanksShow() {
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
        if (reservedTanks.size() % 2 != 0) {
            HBox row = new HBox();
            Image img = new Image("images/black-tank.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(25);
            imgView.setFitHeight(25);
            row.getChildren().add(imgView);
            tanksCon.getChildren().add(row);
        }
        tanksCon.setPadding(new Insets(10, 0, 0, 30));
        rightVbox.getChildren().add(tanksCon);
    }

    public void healthShow() {
        List<Player> l = playerSaving.read();
        Text label = new Text("\n\n   Health");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        label.setFill(Color.CYAN);
        Text subLabel = new Text("      " + String.valueOf(l.get(findPlayer()).getHealth()));
        subLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        subLabel.setFill(Color.GOLD);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, subLabel);
        rightVbox.getChildren().add(vbox);
        vbox.setAlignment(Pos.BOTTOM_LEFT);
    }

    public void levelShow() {
        Text levelLabel = new Text("\n\n   LEVEL");
        levelLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        levelLabel.setFill(Color.ORANGE);

        Text numberLabel = new Text("      " + level + "\n");
        numberLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        numberLabel.setFill(Color.PINK);
        ImageView imageView = new ImageView(new Image("images/outFlag.png"));
        VBox.setMargin(imageView, new Insets(0, 0, 0, 35));
        rightVbox.getChildren().addAll(levelLabel, numberLabel, imageView);

    }

    public void gameStart(String node, Player player, TankBattleCity tankBattleCity) {
        this.tankBattleCity = tankBattleCity;
        this.player = player;
        List<Player> l = playerSaving.read();
        l.get(findPlayer()).setHealth(3);
        playerSaving.setPlayers(l);
        playerSaving.save();
        container = new GridPane();
        gameMap = new GridPane();
        playerTank = new PlayerTank(new Image("images/yellow-tank-up.png"));
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
            playerTank.setStartRow(i);
            playerTank.setStartColumn(j);
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
        tankShoot(tanks, gameMap, this);
        Thread thread = new Thread(() -> {
            while (true) {
                tankControlling.tankMove(tanks, gameMap, this);
                if (tanks.size() < 4 && reservedTanks.size() != 0) {
                    Platform.runLater(() -> {
                        List<WaterPositions> sortedWaterPositions = waterPositions.stream().sorted(Comparator.comparing(WaterPositions::getNumberOfSpawn)).collect(Collectors.toList());
                        gameMap.add(reservedTanks.get(0), sortedWaterPositions.get(0).getY(), sortedWaterPositions.get(0).getX());
                        tanks.add((reservedTanks.get(0)));
                        reservedTanks.remove(0);
                        sortedWaterPositions.get(0).setNumberOfSpawn(sortedWaterPositions.get(0).getNumberOfSpawn() + 1);
                        updateRightSide();
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

    public void tankShoot(List<Tank> tanks, GridPane gameMap, Game game) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    for (int i = 0; i < tanks.size(); i++) {
                        if (tanks.get(i) instanceof CommonTank) {
                            ((CommonTank) tanks.get(i)).shoot(gameMap, game);
                        } else if (tanks.get(i) instanceof ArmoredTank) {
                            ((ArmoredTank) tanks.get(i)).shoot(gameMap, game);
                        }
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void chanceItem() {
        int choice = random.nextInt(3);
        int row = random.nextInt(13);
        int col = random.nextInt(13);
        if (Map.getMap()[row][col].equals(Block.EMPTY)) {
            switch (choice) {
                case 0:
                    Clock clock = new Clock(new Image("images/Clock.png"));
                    gameMap.getChildren().add(clock);
                    GridPane.setRowIndex(clock, row);
                    GridPane.setColumnIndex(clock, col);
                    Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                        gameMap.getChildren().remove(clock);
                    }));
                    timeline1.play();
                    break;
                case 1:
                    Star star = new Star(new Image("images/Star.png"));
                    gameMap.getChildren().add(star);
                    GridPane.setRowIndex(star, row);
                    GridPane.setColumnIndex(star, col);
                    Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                        gameMap.getChildren().remove(star);
                    }));
                    timeline2.play();
                    break;
                case 2:
                    Tanki tanki = new Tanki(new Image("images/Tanki.png"));
                    gameMap.getChildren().add(tanki);
                    GridPane.setRowIndex(tanki, row);
                    GridPane.setColumnIndex(tanki, col);
                    Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
                        gameMap.getChildren().remove(tanki);
                    }));
                    timeline3.play();
                    break;
                default:
                    break;
            }
        } else {
            chanceItem();
        }
    }

    public void chanceItemCollision(Node node) {
        List<Player> l = playerSaving.read();
        if (node instanceof Tanki) {
            l.get(findPlayer()).setHealth(l.get(findPlayer()).getHealth() + 1);
            gameMap.getChildren().remove(node);
        } else if (node instanceof Star) {
            l.get(findPlayer()).setPlayerBulletStrentgh(l.get(findPlayer()).getPlayerBulletStrentgh() + 1);
            gameMap.getChildren().remove(node);
        } else if (node instanceof Clock) {

        }
        playerSaving.setPlayers(l);
        playerSaving.save();
        updateRightSide();
    }


}

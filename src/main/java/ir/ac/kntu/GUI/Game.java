package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Map;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Game {
    private GridPane gameMap = new GridPane();
    private Map map = new Map();


    public Pane getGameMap() {
        return gameMap;
    }

    public void gameStart() {
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
    }

    public void mapBuilding(int i, int j) {
        if (Map.getMap()[i][j] == Block.BRICK) {
            Image img = new Image("images/brick.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        } else if(Map.getMap()[i][j] == Block.METAL){
            Image img = new Image("images/Stone.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        }else if(Map.getMap()[i][j] == Block.PLAYERTANK){
            Image img = new Image("images/yellow-tank-up.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        }else if(Map.getMap()[i][j] == Block.COMMONTANK){
            Image img = new Image("images/brick.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        }else if(Map.getMap()[i][j] == Block.FLAG){
            Image img = new Image("images/brick.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        }else if(Map.getMap()[i][j] == Block.ARMOREDTANK){
            Image img = new Image("images/brick.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        }else if(Map.getMap()[i][j] == Block.RANDOMTANKCOMMON){
            Image img = new Image("images/brick.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        }else if(Map.getMap()[i][j] == Block.RANDOMTANKARMED){
            Image img = new Image("images/brick.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(50);
            imgView.setFitHeight(50);
            gameMap.add(imgView, j, i);
        }
    }

}

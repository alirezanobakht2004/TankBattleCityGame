package ir.ac.kntu.LOGIC;
import ir.ac.kntu.GUI.SelectLevel;
import ir.ac.kntu.GUI.SelectPlayer;
import ir.ac.kntu.GUI.StartMenu;
import ir.ac.kntu.GUI.TankBattleCity;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class EventHandlerSet  {

    TankBattleCity tankBattleCity;


    public void startMenuEH(StartMenu startMenu,TankBattleCity tankBattleCity){
        this.tankBattleCity=tankBattleCity;
        startMenu.getImageView1().setOnMouseClicked(e ->
        {
            tankBattleCity.selectPlayer();
        });
        startMenu.getImageView2().setOnMouseClicked(e ->
        {
            System.out.println("2");
        });
        startMenu.getImageView3().setOnMouseClicked(e ->
        {
            System.out.println("3");
        });
    }
    public void eSP(SelectPlayer selectPlayer){
        selectPlayer.getPlayersList().getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
               tankBattleCity.selectLevel();
            });
        });
    }
    public void eventSL(SelectLevel selectLevel){
        selectLevel.getLevelsList().getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                tankBattleCity.startGame(((Text) node).getText());
            });
        });
    }

    public void updateMap(Scene gameScene, ImageView node){
        gameScene.setOnKeyPressed(event -> {
            int columnIndex = GridPane.getColumnIndex(node);
            int rowIndex = GridPane.getRowIndex(node);
            if (columnIndex == 0 && event.getCode() == KeyCode.LEFT) return;
            if (columnIndex == 12 && event.getCode() == KeyCode.RIGHT) return;
            if (rowIndex == 0 && event.getCode() == KeyCode.UP) return;
            if (rowIndex == 12 && event.getCode() == KeyCode.DOWN) return;
            switch (event.getCode()) {
                case LEFT:
                    GridPane.setColumnIndex(node, columnIndex - 1);
                    node.setImage(new Image("images/yellow-tank-left.png"));
                    break;
                case RIGHT:
                    GridPane.setColumnIndex(node, columnIndex + 1);
                    node.setImage(new Image("images/yellow-tank-right.png"));
                    break;
                case UP:
                    GridPane.setRowIndex(node, rowIndex - 1);
                    node.setImage(new Image("images/yellow-tank-up.png"));
                    break;
                case DOWN:
                    GridPane.setRowIndex(node, rowIndex + 1);
                    node.setImage(new Image("images/yellow-tank-down.png"));
                    break;
                default:
                    break;
            }
        });

    }

}

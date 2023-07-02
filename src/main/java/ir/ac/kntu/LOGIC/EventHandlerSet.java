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


public class EventHandlerSet {

    TankBattleCity tankBattleCity;


    public void startMenuEH(StartMenu startMenu, TankBattleCity tankBattleCity) {
        this.tankBattleCity = tankBattleCity;
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

    public void eSP(SelectPlayer selectPlayer) {
            selectPlayer.getSignUp().setOnMouseClicked(event -> {
                selectPlayer.startSignUp();
            });

        selectPlayer.getPlayersList().getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                tankBattleCity.selectLevel((Text) node);
            });
        });
    }

    public void eventSL(SelectLevel selectLevel,Player player) {
        selectLevel.getLevelsList().getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                tankBattleCity.startGame(((Text) node).getText(),player);
            });
        });
    }

}

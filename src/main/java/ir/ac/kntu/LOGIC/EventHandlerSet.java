package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.*;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class EventHandlerSet {

    private TankBattleCity tankBattleCity;

    private PlayerSaving playerSaving = new PlayerSaving();

    private Game game;

    public void startMenuEH(StartMenu startMenu, TankBattleCity tankBattleCity) {
        this.tankBattleCity = tankBattleCity;
        startMenu.getImageView1().setOnMouseClicked(e -> {
            tankBattleCity.selectPlayer();
        });
        startMenu.getImageView2().setOnMouseClicked(e -> {
            System.out.println("2");
        });
        startMenu.getImageView3().setOnMouseClicked(e -> {
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

    public void eventSL(SelectLevel selectLevel, Player player) {
        selectLevel.getLevelsList().getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                tankBattleCity.startGame(((Text) node).getText(), player);
            });
        });
    }

    public void eventHandlerWinPage(WinPage winPage, Game game, Scene scene) {
        this.game = game;
        scene.setOnKeyPressed(event -> {
            game.getContainer().getChildren().remove(game.getGameMap());
            scene.setOnKeyPressed(null);
            tankBattleCity.startGame("LEVEL: " + (game.getLevel() + 1), game.getPlayer());
        });
    }

    public int findPlayer() {
        List<Player> l = playerSaving.read();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals(game.getPlayer().getName())) {
                return i;
            }
        }
        return 0;
    }

}

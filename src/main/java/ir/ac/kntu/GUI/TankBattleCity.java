package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.EventHandlerSet;
import ir.ac.kntu.LOGIC.Map;
import ir.ac.kntu.LOGIC.TankControlling;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;

public class TankBattleCity {
    private KeyFrame frame;
    private Timeline animation;
    private Stage stage;
    private EventHandlerSet eventHandlerSet = new EventHandlerSet();
    private TankControlling tankControlling = new TankControlling();
    private StartMenu startMenu = new StartMenu();
    private Scene startProgramScene;
    private SelectPlayer selectPlayer=new SelectPlayer();
    private Scene selectPlayerScene;
    private SelectLevel selectLevel = new SelectLevel();
    private Scene selectLevelScene;
    private Game game = new Game();
    private Scene gameScene;


    public TankBattleCity(Stage stage) {
        this.stage = stage;
    }

    public void startProgram() {
        startMenu.startMenu();
        eventHandlerSet.startMenuEH(startMenu,this);
        startProgramScene = new Scene(startMenu.getStartPane());
        stage.setScene(startProgramScene);
    }

    public void selectPlayer(){
        selectPlayer.selectPlayerStart();
        eventHandlerSet.eSP(selectPlayer);
        selectPlayerScene = new Scene(selectPlayer.getSelectPlayer());
        stage.setScene(selectPlayerScene);
    }

    public void selectLevel(){
        selectLevel.setSelectLevelStart();
        eventHandlerSet.eventSL(selectLevel);
        selectLevelScene = new Scene(selectLevel.getSelectLevel());
        stage.setScene(selectLevelScene);
    }

    public void startGame(String text){
        game.gameStart(text);
        gameScene=new Scene(game.getGameMap());
        tankControlling.playerTankController(gameScene,game.getPlayerTank(), Map.getMap(),game.gameMapCell());
        stage.setScene(gameScene);
    }



}

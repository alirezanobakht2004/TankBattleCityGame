package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.EventHandlerSet;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;

public class TankBattleCity {

    private Stage stage;
    private EventHandlerSet eventHandlerSet = new EventHandlerSet();
    private StartMenu startMenu;
    private SelectPlayer selectPlayer=new SelectPlayer();
    private Scene startProgramScene;
    private Scene selectPlayerScene;
    private SelectLevel selectLevel = new SelectLevel();
    private Scene selectLevelScene;

    public TankBattleCity(Stage stage) {
        this.stage = stage;
    }

    public void startProgram() {
        startMenu = new StartMenu();
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


}

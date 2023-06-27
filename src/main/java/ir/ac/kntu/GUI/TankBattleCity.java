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

    public TankBattleCity(Stage stage) {
        this.stage = stage;
    }

    public void startProgram() {
        StartMenu startMenu = new StartMenu();
        startMenu.startMenu();
        eventHandlerSet.startMenuEH(startMenu,stage);
        Scene scene = new Scene(startMenu.getStartPane());
        stage.setScene(scene);
    }

    public void selectPlayer(){

    }


}

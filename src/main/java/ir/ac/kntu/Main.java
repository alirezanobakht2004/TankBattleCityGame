package ir.ac.kntu;

import ir.ac.kntu.GUI.TankBattleCity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TankBattleCity tankBattleCity = new TankBattleCity(primaryStage);
        tankBattleCity.start();
        primaryStage.show();
    }
}
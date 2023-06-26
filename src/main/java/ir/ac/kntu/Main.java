package ir.ac.kntu;

import ir.ac.kntu.GUI.TankBattleCity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TankBattleCity tankBattleCity = new TankBattleCity();
        Pane fatherPane = new Pane();
        fatherPane.setPrefSize(650,650);
        tankBattleCity.addNodesToPane(fatherPane);
        Scene scene = new Scene(fatherPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
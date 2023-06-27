package ir.ac.kntu.GUI;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

    private GridPane startPane = new GridPane();

    private GridPane menuPane = new GridPane();

    public TankBattleCity(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        startPane.setPrefSize(600, 600);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(60);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(50);
        startPane.getColumnConstraints().addAll(col1, col2, col3);
        startPane.getRowConstraints().addAll(row1, row2);
        Image image = new Image("images/beforeStart.png");
        ImageView imageView = new ImageView(image);
        startPane.add(imageView, 1, 0);
        startPane.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(startPane);
        stage.setScene(scene);
    }


}

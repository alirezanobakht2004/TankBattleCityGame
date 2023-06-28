package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Map;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Game {
    private Pane gameMap = new Pane();
    private Map map = new Map();


    public void gameStart() {
        gameMap.setPrefSize(650, 650);
        for (int i = 0; i < 13; i++) {
            HBox column = new HBox();
            for (int j = 0; j < 13; j++) {
                VBox cell = new VBox();
                cell.setPrefSize(50, 50);
                cell.setMaxWidth(50);
                cell.setMaxWidth(50);
                column.getChildren().add(cell);
            }
        }
    }

}

package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Game {
    private Pane gameMap = new Pane();
    private Map map = new Map();
    private Image Brick = new Image("images/brick.gif");
    private ImageView BrickV = new ImageView(Brick);


    public void gameStart() {
        gameMap.setPrefSize(650, 650);
        for (int i = 0; i < 13; i++) {
            VBox column = new VBox();
            for (int j = 0; j < 13; j++) {
                Pane cell = new Pane();
                cell.setPrefSize(50, 50);
                cell.setMaxWidth(50);
                cell.setMaxWidth(50);
                if(map.)
                column.getChildren().add(cell);
            }
        }
    }

}

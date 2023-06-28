package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Player;
import ir.ac.kntu.LOGIC.PlayerSaving;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SelectPlayer {
    private GridPane selectPlayer = new GridPane();
    private ColumnConstraints col1 = new ColumnConstraints();
    private ColumnConstraints col2 = new ColumnConstraints();
    private ColumnConstraints col3 = new ColumnConstraints();
    private RowConstraints row1 = new RowConstraints();
    private RowConstraints row2 = new RowConstraints();
    private RowConstraints row3 = new RowConstraints();
    private PlayerSaving playerSaving = new PlayerSaving();

    public GridPane getSelectPlayer() {
        return selectPlayer;
    }

    public void selectPlayerStart(){
        selectPlayer.setPrefSize(450, 500);
        selectPlayer.getColumnConstraints().addAll(col1,col2,col3);
        selectPlayer.getRowConstraints().addAll(row1,row2,row3);
        col1.setPercentWidth(33);
        col2.setPercentWidth(33);
        col3.setPercentWidth(33);
        row1.setPercentHeight(10);
        row2.setPercentHeight(80);
        row3.setPercentHeight(10);
        VBox playersList = new VBox(15);
        playerSaving.save();
        Text text = new Text();

        for (Player l: playerSaving.read()) {
            text.setFont(new Font(40));
            //text.setWrappingWidth(100);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setFill(Color.WHITE);
            text.setText(l.getName()+" "+l.getGamesPlayed()+" "+l.getScore());
            playersList.getChildren().add(text);
        }
        selectPlayer.setStyle("-fx-background-color: black;");
        selectPlayer.add(playersList,1,1);
    }
}

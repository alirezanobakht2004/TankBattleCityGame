package ir.ac.kntu.GUI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class StartMenu {

    private GridPane startPane = new GridPane();

    private ColumnConstraints col1 = new ColumnConstraints();

    private ColumnConstraints col2 = new ColumnConstraints();

    private ColumnConstraints col3 = new ColumnConstraints();

    private RowConstraints row1 = new RowConstraints();

    private RowConstraints row2 = new RowConstraints();

    private Image image = new Image("images/beforeStart.png");

    private Image onePlayer = new Image("images/1-player.png");

    private Image twoPlayers = new Image("images/2-players.png");

    private Image construction = new Image("images/construction.png");

    private ImageView imageView = new ImageView(image);

    private ImageView imageView1 = new ImageView(onePlayer);

    private ImageView imageView2 = new ImageView(twoPlayers);

    private ImageView imageView3 = new ImageView(construction);

    public GridPane getStartPane() {
        return startPane;
    }

    public ImageView getImageView1() {
        return imageView1;
    }

    public ImageView getImageView2() {
        return imageView2;
    }

    public ImageView getImageView3() {
        return imageView3;
    }

    public void startMenu() {
        startPane.setPrefSize(450, 500);
        col1.setPercentWidth(25);
        col2.setPercentWidth(50);
        col3.setPercentWidth(25);
        row1.setPercentHeight(50);
        row2.setPercentHeight(50);
        startPane.getColumnConstraints().addAll(col1, col2, col3);
        startPane.getRowConstraints().addAll(row1, row2);
        imageView.setFitHeight(150);
        imageView.setFitWidth(300);
        startPane.add(imageView, 1, 0);
        startPane.setStyle("-fx-background-color: black;");
        VBox vBox = new VBox(15, imageView1, imageView2, imageView3);
        vBox.setAlignment(Pos.TOP_CENTER);
        startPane.add(vBox, 1, 1);
    }

}

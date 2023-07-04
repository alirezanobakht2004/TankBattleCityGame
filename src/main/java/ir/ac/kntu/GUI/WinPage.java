package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Player;
import ir.ac.kntu.LOGIC.PlayerSaving;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class WinPage {

    private GridPane winPage;

    private Stage stage;

    private TankBattleCity tankBattleCity;

    private ColumnConstraints col1 = new ColumnConstraints();

    private ColumnConstraints col2 = new ColumnConstraints();

    private ColumnConstraints col3 = new ColumnConstraints();

    private RowConstraints row1 = new RowConstraints();

    private RowConstraints row2 = new RowConstraints();

    private RowConstraints row3 = new RowConstraints();

    private Game game;

    private PlayerSaving playerSaving = new PlayerSaving();

    public GridPane getWinPage() {
        return winPage;
    }

    public void winPageShow(Stage stage, TankBattleCity tankBattleCity, Game game) {
        winPage = new GridPane();
        this.stage = stage;
        this.game = game;
        this.tankBattleCity = tankBattleCity;
        List<Player> l = playerSaving.read();
        l.get(findPlayer()).setGamesPlayed(l.get(findPlayer()).getGamesPlayed() + 1);
        int u = l.get(findPlayer()).getScore();
        int v = l.get(findPlayer()).getHighestScore();
        l.get(findPlayer()).setHighestScore(Math.max(u, v));
        l.get(findPlayer()).setHealth(3);
        winPage.setPrefSize(650, 650);
        winPage.setStyle("-fx-background-color: black;");
        winPage.getColumnConstraints().addAll(col1, col2, col3);
        winPage.getRowConstraints().addAll(row1, row2, row3);
        col1.setPercentWidth(5);
        col2.setPercentWidth(90);
        col3.setPercentWidth(5);
        row1.setPercentHeight(10);
        row2.setPercentHeight(80);
        row3.setPercentHeight(10);
        Text levelText = new Text("Level: " + game.getLevel() + "\n");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        levelText.setFill(Color.CYAN);
        Text playerNameText = new Text("\n" + "Player Name: " + game.getPlayer().getName());
        playerNameText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        playerNameText.setFill(Color.YELLOW);
        Text highestScoreText = new Text("highest-score: " + l.get(findPlayer()).getHighestScore());
        highestScoreText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        highestScoreText.setFill(Color.LIGHTGREEN);
        Text scoreText = new Text("score: " + l.get(findPlayer()).getScore());
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        scoreText.setFill(Color.WHITE);
        ImageView imageView1 = new ImageView(new Image("images/common-tank-up.png"));
        imageView1.setFitWidth(50);
        imageView1.setFitHeight(50);
        Text imageView1ScoreText = new Text("score: 100 * " + game.getExplodedCommonTank() + "   ");
        imageView1ScoreText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        imageView1ScoreText.setFill(Color.WHITE);
        ImageView imageView2 = new ImageView(new Image("images/armored-tank-up.png"));
        imageView2.setFitWidth(50);
        imageView2.setFitHeight(50);
        Text imageView2ScoreText = new Text("   score:200 * " + game.getExplodedArmoredTank());
        imageView2ScoreText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        imageView2ScoreText.setFill(Color.WHITE);
        HBox leftBox = new HBox(levelText, playerNameText);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        HBox rightBox = new HBox(highestScoreText);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        HBox imageBox = new HBox(imageView1, imageView2);
        imageBox.setAlignment(Pos.CENTER);
        HBox imageScoreBox = new HBox(imageView1ScoreText, imageView2ScoreText);
        imageScoreBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(leftBox, scoreText, rightBox, imageBox, imageScoreBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black");
        winPage.add(root, 1, 1);
        l.get(findPlayer()).setScore(0);
        playerSaving.setPlayers(l);
        playerSaving.save();
    }

    public int findPlayer() {
        List<Player> l = playerSaving.read();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals(game.getPlayer().getName())) {
                return i;
            }
        }
        return 0;
    }
}

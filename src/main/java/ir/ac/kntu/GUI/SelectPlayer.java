package ir.ac.kntu.GUI;

import ir.ac.kntu.LOGIC.Player;
import ir.ac.kntu.LOGIC.PlayerSaving;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SelectPlayer {

    private VBox signUp = new VBox(15);

    private VBox playersList;

    private GridPane selectPlayer = new GridPane();

    private ColumnConstraints col1 = new ColumnConstraints();

    private ColumnConstraints col2 = new ColumnConstraints();

    private ColumnConstraints col3 = new ColumnConstraints();

    private RowConstraints row1 = new RowConstraints();

    private RowConstraints row2 = new RowConstraints();

    private RowConstraints row3 = new RowConstraints();

    private PlayerSaving playerSaving = new PlayerSaving();

    private Stage stage;

    private GridPane signUpGrid;

    private TankBattleCity tankBattleCity;

    public GridPane getSelectPlayer() {
        return selectPlayer;
    }

    public ScrollPane scrollPane() {
        ScrollPane scrollPane = new ScrollPane(selectPlayer);
        scrollPane.setPrefSize(575, 500);
        return scrollPane;
    }

    public VBox getPlayersList() {
        return playersList;
    }

    public VBox getSignUp() {
        return signUp;
    }

    public void selectPlayerStart(Stage stage, TankBattleCity tankBattleCity) {
        this.tankBattleCity = tankBattleCity;
        signUpGrid = new GridPane();
        this.stage = stage;
        selectPlayer.setPrefSize(650, 650);
        selectPlayer.getColumnConstraints().addAll(col1, col2, col3);
        selectPlayer.getRowConstraints().addAll(row1, row2, row3);
        col1.setPercentWidth(5);
        col2.setPercentWidth(90);
        col3.setPercentWidth(5);
        row1.setPercentHeight(10);
        row2.setPercentHeight(80);
        row3.setPercentHeight(10);
        playersList = new VBox(15);
        setSignUp();
        Text info = new Text();
        info.setFont(new Font(40));
        info.setFill(Color.BLACK);
        info.setText("player   gamesPlayed   H-Score");
        playersList.getChildren().add(info);
        List<Player> l = playerSaving.getPlayers().stream().sorted(Comparator.comparing(Player::getHighestScore).reversed()).collect(Collectors.toList());
        for (Player p : l) {
            Text playerNode = new Text();
            playerNode.setFont(new Font(40));
            playerNode.setFill(Color.BLUE);
            playerNode.setText(p.getName() + "                     " + p.getGamesPlayed() + "                " + p.getHighestScore());
            playersList.getChildren().add(playerNode);
        }
        selectPlayer.add(signUp, 1, 0);
        selectPlayer.add(playersList, 1, 1);
        Image backgroundImage = new Image("images/selectPlayerBack.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
        Background background1 = new Background(background);
        selectPlayer.setBackground(background1);
    }

    public void setSignUp() {
        Text signUpText = new Text();
        signUpText.setFont(new Font(40));
        signUpText.setFill(Color.GREEN);
        signUpText.setText("Sign up a new tank!");
        signUp.getChildren().add(signUpText);
    }

    public void startSignUp() {
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20));
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("shoot:");
        nameLabel.setTextFill(Color.BLACK);
        ageLabel.setTextFill(Color.BLACK);
        hBox.getChildren().addAll(nameLabel, nameField, ageLabel, ageField);
        Button signUpButton = new Button("Sign Up!");
        signUpButton.setFont(Font.font("Arial", 45));
        signUpButton.setTextFill(Color.WHITE);
        signUpButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        signUpButton.setOnAction(e -> {
            signUpToList(nameField.getText(), ageField.getText());
        });
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        borderPane.setBottom(signUpButton);
        Scene scene = new Scene(borderPane, 450, 450);
        Image backgroundImage = new Image("images/signUpBack.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
        Background background1 = new Background(background);
        Region region = (Region) scene.getRoot();
        region.setBackground(background1);
        stage.setScene(scene);
    }

    public void signUpToList(String name, String strength) {
        List<Player> l = playerSaving.read();
        Player player = new Player(name, Integer.parseInt(strength));
        playerSaving.getPlayers().add(player);
        playerSaving.save();
        Text playerNode = new Text();
        playerNode.setFont(new Font(40));
        playerNode.setFill(Color.CYAN);
        playerNode.setText(player.getName() + "                     " + player.getGamesPlayed() + "                " + player.getHighestScore());
        tankBattleCity.selectLevel(playerNode);
    }


}

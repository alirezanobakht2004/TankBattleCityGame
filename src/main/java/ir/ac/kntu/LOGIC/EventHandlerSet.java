package ir.ac.kntu.LOGIC;
import ir.ac.kntu.GUI.SelectPlayer;
import ir.ac.kntu.GUI.StartMenu;
import ir.ac.kntu.GUI.TankBattleCity;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EventHandlerSet  {


    public void startMenuEH(StartMenu startMenu,Stage stage){
        startMenu.getImageView1().setOnMouseClicked(e ->
        {
            SelectPlayer selectPlayer = new SelectPlayer();
            selectPlayer.selectPlayerStart();
            Scene scene = new Scene(selectPlayer.getSelectPlayer());
            stage.setScene(scene);
        });
        startMenu.getImageView2().setOnMouseClicked(e ->
        {
            System.out.println("2");
        });
        startMenu.getImageView3().setOnMouseClicked(e ->
        {
            System.out.println("3");
        });
    }
}

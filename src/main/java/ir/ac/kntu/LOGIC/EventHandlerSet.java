package ir.ac.kntu.LOGIC;
import ir.ac.kntu.GUI.StartMenu;
import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class EventHandlerSet {
    public void startMenuEH(StartMenu startMenu){
        startMenu.getImageView1().setOnMouseClicked(e ->
        {
            System.out.println("1");
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

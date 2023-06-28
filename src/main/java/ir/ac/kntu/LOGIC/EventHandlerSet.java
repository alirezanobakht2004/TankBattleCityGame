package ir.ac.kntu.LOGIC;
import ir.ac.kntu.GUI.SelectLevel;
import ir.ac.kntu.GUI.SelectPlayer;
import ir.ac.kntu.GUI.StartMenu;
import ir.ac.kntu.GUI.TankBattleCity;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class EventHandlerSet  {

    TankBattleCity tankBattleCity;


    public void startMenuEH(StartMenu startMenu,TankBattleCity tankBattleCity){
        this.tankBattleCity=tankBattleCity;
        startMenu.getImageView1().setOnMouseClicked(e ->
        {
            tankBattleCity.selectPlayer();
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
    public void eSP(SelectPlayer selectPlayer){

    }
    public void eventSL(SelectLevel selectLevel){
        selectLevel.getLevel1().setOnMouseClicked(e ->
        {
            tankBattleCity.selectPlayer();
        });
        selectLevel.getLevel2().setOnMouseClicked(e ->
        {
            System.out.println("2");
        });
        selectLevel.getLevel3().setOnMouseClicked(e ->
        {
            System.out.println("3");
        });
    }
}

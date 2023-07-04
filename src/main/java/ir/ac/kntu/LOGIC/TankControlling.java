package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;
import ir.ac.kntu.GUI.Brick;
import ir.ac.kntu.GUI.Game;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TankControlling {

    private Random random = new Random(300);

    private List<Tank> tanks = new ArrayList<>();

    public List<Tank> tankMaker(int level) {
        for (int i = 0; i < 1 + (level - 1) * 4; i++) {
            int d = random.nextInt(4);
            switch (d) {
                case 0:
                    CommonTank commonTank = new CommonTank(new Image("images/common-tank-down.png"));
                    commonTank.setFitHeight(51);
                    commonTank.setFitWidth(51);
                    tanks.add(commonTank);
                    break;
                case 1:
                    ArmoredTank armoredTank = new ArmoredTank(new Image("images/armored-tank-down.png"));
                    armoredTank.setFitHeight(51);
                    armoredTank.setFitWidth(51);
                    tanks.add(armoredTank);
                    break;
                case 2:
                    CommonTank commonTankRandom = new CommonTank(new Image("images/random-tank-down.png"));
                    commonTankRandom.setFitHeight(51);
                    commonTankRandom.setFitWidth(51);
                    commonTankRandom.setRandom(true);
                    tanks.add(commonTankRandom);
                    break;
                case 3:
                    ArmoredTank armoredTankRandom = new ArmoredTank(new Image("images/armored-random-tank-down.png"));
                    armoredTankRandom.setFitHeight(51);
                    armoredTankRandom.setFitWidth(51);
                    armoredTankRandom.setRandom(true);
                    tanks.add(armoredTankRandom);
                    break;
                default:
                    break;
            }
        }
        return tanks;
    }

    public void tankMove(List<Tank> tanks, GridPane gameMap, Game game) {
        for (int i = 0; i < tanks.size(); i++) {
            if (tanks.get(i) instanceof CommonTank) {
                ((CommonTank) tanks.get(i)).move(gameMap, game);
            } else if (tanks.get(i) instanceof ArmoredTank) {
                ((ArmoredTank) tanks.get(i)).move(gameMap);
            }

        }
    }


}

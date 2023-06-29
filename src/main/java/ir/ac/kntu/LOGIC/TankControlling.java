package ir.ac.kntu.LOGIC;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankControlling {
    private Random random = new Random(300);
    private List<Tank> tanks=new ArrayList<>();

    public List<Tank> tankMaker(int level) {
        for (int i = 0; i < 10 + (level - 1) * 4; i++) {
            int d = random.nextInt(4);
            switch (d) {
                case 0:
                   CommonTank commonTank = new CommonTank(new Image("images/common-tank-down.png"));
                   tanks.add(commonTank);
                    break;
                case 1:
                    ArmoredTank armoredTank = new ArmoredTank(new Image("images/armored-tank-down.png"));
                    tanks.add(armoredTank);
                    break;
                case 2:
                    CommonTank commonTankRandom = new CommonTank(new Image("images/random-tank-down.png"));
                    commonTankRandom.setRandom(true);
                    tanks.add(commonTankRandom);
                    break;
                case 3:
                    ArmoredTank armoredTankRandom = new ArmoredTank(new Image("images/armored-random-tank-down.png"));
                    armoredTankRandom.setRandom(true);
                    tanks.add(armoredTankRandom);
                    break;
                default:
                    break;
            }
        }
        return tanks;
    }

}

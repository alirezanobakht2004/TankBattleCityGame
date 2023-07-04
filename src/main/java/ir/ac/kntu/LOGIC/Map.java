package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;

import java.io.*;
import java.util.List;
import java.util.Random;

public class Map {

    public static Block[][] map;

    private Random random = new Random();

    private TankControlling tankControlling = new TankControlling();


    public Map() {
        setMap();
    }

    public static Block[][] getMap() {
        return map;
    }

    public void setMap() {
        map = new Block[13][13];
        File file = new File("src/main/java/ir/ac/kntu/LOGIC/Map");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                lineRead(line, i);
                line = reader.readLine();
                i++;
            }
            int count = 0;
            while (count < 4) {
                int row = random.nextInt(map.length);
                int col = random.nextInt(map[row].length);
                if (map[row][col].equals(Block.EMPTY)) {
                    map[row][col] = Block.WATER;
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void lineRead(String line, int lN) {
        for (int i = 0; i < line.length(); i++) {
            map[lN][i] = getBlock(line.charAt(i));
        }
    }

    public static Block getBlock(char c) {
        switch (c) {
            case 'B':
                return Block.BRICK;
            case 'M':
                return Block.METAL;
            case 'F':
                return Block.FLAG;
            case 'A':
                return Block.ARMOREDTANK;
            case 'C':
                return Block.RANDOMTANKARMED;
            case 'c':
                return Block.RANDOMTANKCOMMON;
            case 'P':
                return Block.PLAYERTANK;
            case 'O':
                return Block.COMMONTANK;
            default:
                return Block.EMPTY;
        }
    }

    public List<Tank> tankMake(int level) {
        return tankControlling.tankMaker(level);
    }

}

package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;

import java.io.*;
import java.util.Random;

public class Map {

    public static Block[][] map = new Block[13][13];

    private Random random = new Random();


    public Map() {
        setMap();
    }

    public static Block[][] getMap() {
        return map;
    }

    public void setMap() {
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
            switch (line.charAt(i)) {
                case 'B':
                    map[lN][i] = Block.BRICK;
                    break;
                case 'M':
                    map[lN][i] = Block.METAL;
                    break;
                case 'F':
                    map[lN][i] = Block.FLAG;
                    break;
                case 'A':
                    map[lN][i] = Block.ARMOREDTANK;
                    break;
                case 'C':
                    map[lN][i] = Block.RANDOMTANKARMED;
                    break;
                case 'c':
                    map[lN][i] = Block.RANDOMTANKCOMMON;
                    break;
                case 'P':
                    map[lN][i] = Block.PLAYERTANK;
                    break;
                case 'O':
                    map[lN][i] = Block.COMMONTANK;
                    break;
                default:
                    map[lN][i] = Block.EMPTY;
            }
        }
    }

}

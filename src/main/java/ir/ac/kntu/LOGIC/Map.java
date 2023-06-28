package ir.ac.kntu.LOGIC;

import ir.ac.kntu.GUI.Block;

import java.io.*;

public class Map {
    public static String[][] map = new String[13][13];

    public Map() {
        setMap();
    }

    public static String[][] getMap() {
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
                System.out.println(line);
                line = reader.readLine();
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void lineRead(String line, int lN) {
        for (int i = 0; i < line.length(); i++) {
            System.out.println("Line number : " + lN);
            System.out.println("i : " + i);
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
            }
        }
    }

}

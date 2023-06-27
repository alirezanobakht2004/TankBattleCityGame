package ir.ac.kntu.LOGIC;

import java.io.Serializable;

public class Player implements Serializable {

    private String Name;

    private int gamesPlayed;

    private int Score;

    public String getName() {
        return Name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getScore() {
        return Score;
    }
}

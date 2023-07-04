package ir.ac.kntu.LOGIC;

import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;

    private int highestScore;

    private int gamesPlayed;

    private int score;

    private int playerBulletStrentgh;

    private int health;

    public Player(String name, int playerBulletStrentgh) {
        this.name = name;
        this.playerBulletStrentgh = playerBulletStrentgh;
        gamesPlayed = 0;
        score = 0;
        highestScore = 0;
        health = 3;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerBulletStrentgh(int playerBulletStrentgh) {
        this.playerBulletStrentgh = playerBulletStrentgh;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerBulletStrentgh() {
        return playerBulletStrentgh;
    }

    public int getHealth() {
        return health;
    }
}

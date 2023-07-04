package ir.ac.kntu.LOGIC;

public class WaterPositions {

    private int x;

    private int y;

    private int numberOfSpawn;

    public WaterPositions(int x, int y, int numberOfSpawn) {
        this.x = x;
        this.y = y;
        this.numberOfSpawn = numberOfSpawn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumberOfSpawn() {
        return numberOfSpawn;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNumberOfSpawn(int numberOfSpawn) {
        this.numberOfSpawn = numberOfSpawn;
    }
}

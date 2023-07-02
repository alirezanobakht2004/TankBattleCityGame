package ir.ac.kntu.LOGIC;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerSaving implements Serializable {

    private List<Player> players;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public PlayerSaving() {
        List<Player> pl = read();
        players = pl != null ? pl : new ArrayList<Player>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> read() {
        try {
            FileInputStream fin = new FileInputStream("players.ser");
            ObjectInputStream ois = new ObjectInputStream(fin);
            List<Player> lds = (List<Player>) ois.readObject();
            ois.close();
            return lds.stream().sorted(Comparator.comparing(Player::getScore)).collect(Collectors.toList());
        } catch (Exception ex) {
            return null;
        }
    }

    public void save() {
        try {
            FileOutputStream fout = new FileOutputStream("players.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(players);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

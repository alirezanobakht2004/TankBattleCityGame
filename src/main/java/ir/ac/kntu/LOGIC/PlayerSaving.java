package ir.ac.kntu.LOGIC;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerSaving {

    private List<Player> players;

    public PlayerSaving(){
        players=read();
    }

    public List<Player> read() {
        try{
            FileInputStream fin = new FileInputStream("players.ser");
            ObjectInputStream ois = new ObjectInputStream(fin);
            List<Player> lds = (List<Player>) ois.readObject();
            ois.close();
            return lds.stream().sorted(Comparator.comparing(Player::getScore)).collect(Collectors.toList());
        } catch(Exception ex) {
            return null;
        }
    }

    public void save() {
        try {
            FileOutputStream fout = new FileOutputStream("players.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(players);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

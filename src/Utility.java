import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Utility {
    private final int INITIAL_BAL = 500;
    private ArrayList<String> playerList = new ArrayList<>();

    // TODO: read through savestates folder and add to ArrayList of players
    public void initialize() {
        final File folder = new File("savestates");

        for (final File fileEntry : folder.listFiles())
            playerList.add(fileEntry.getName().replace(".ser", ""));

        for (String string : playerList)
            System.out.println(string);
    }

    public void playerCreate(String playerName) throws IOException {
        int playerID = Math.abs(assignID(playerName));
        Player player = new Player(playerName, INITIAL_BAL, playerID);
        String filePath = "savestates\\" + playerName + ".ser";

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
        out.writeObject(player);
        out.close();
    }

    public void save(Player player) throws IOException {
        String playerName = player.getName();
        String filePath = "savestates\\" + playerName + ".ser";

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
        out.writeObject(player);
        out.close();
    }

    public Player load(String playerName) throws IOException, ClassNotFoundException {
        Player player = null;
        String filePath = "savestates\\" + playerName + ".ser";
        // String filePath = "savestates\\Rabini.ser";

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
        player = (Player) in.readObject();
        in.close();

        return player; 
    }

    public boolean playerExists(String playerName) {
        return (playerList.indexOf(playerName) >= 0) ? true : false;
    }

    private int assignID(String playerName) {
        int hash = 7;

        for (int i = 0; i < playerName.length(); i++)
            hash = hash*31 + playerName.charAt(i);

        return hash;
    }
}
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GamePanel {
    private final int INITIAL_BAL = 500;
    private ArrayList<String> playerList = new ArrayList<>();
    // private Player defaultPlayer; 

    public GamePanel() throws ClassNotFoundException, IOException {
        // defaultPlayer = new Player("John Doe", INITIAL_BAL, -1);
        run();
    }

    public void run() throws IOException, ClassNotFoundException {
        final File folder = new File("savestates");

        for (final File fileEntry : folder.listFiles())
            playerList.add(fileEntry.getName().replace(".ser", ""));

        String playerName = JOptionPane.showInputDialog(null, "Enter player name:");

        if (!playerExists(playerName)) {
            int response = JOptionPane.showConfirmDialog(null, "You don't seem to be registered. Would you like to make an account?");

            if (response == JOptionPane.YES_OPTION)
                playerCreate(playerName);
        } else {
            System.out.println("real");
        }

        // TODO: verify if player exists in savestates,
        // if not, create and initialize new player
        // if yes, gamba time.
        
        
        // Player newPlayer = utility.load(playerName);

        // System.out.println(newPlayer.getName());
        // System.out.println(newPlayer.getBalance());
        // gamble(newPlayer);

        // utility.save(newPlayer);
    }

    public static void gamble(Player player) {
        // TODO: create gamble method 
        int bet = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter amount to bet"));

        player.changeBal(bet);
        player.changeBal(++bet);

    }

    public void playerCreate(String playerName) throws IOException {
        Player player = new Player(playerName, INITIAL_BAL, Math.abs(assignID(playerName)));
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

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
        player = (Player) in.readObject();
        in.close();

        return player; 
    }

    private boolean playerExists(String playerName) {
        return (playerList.indexOf(playerName) >= 0) ? true : false;
    }

    private int assignID(String playerName) {
        int hash = 7;

        for (int i = 0; i < playerName.length(); i++)
            hash = hash*31 + playerName.charAt(i);

        return hash;
    }
}
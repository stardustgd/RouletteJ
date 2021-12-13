import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1;
    private String playerName; 
    private int playerBalance;
    private int playerID; 

    public Player(String name, int bal, int id) {
        this.playerName = name;
        this.playerBalance = bal;
        this.playerID = id; 
    }

    public String getName() {
        return playerName;
    }

    public int getBalance() {
        return playerBalance;
    }

    public int getID() {
        return playerID;
    }

    public void changeBal(int amt) {
        playerBalance += amt;
    }

    @Override
    public String toString() {
        return playerName + " $" + playerBalance + " (" + playerID + ")";
    }
}
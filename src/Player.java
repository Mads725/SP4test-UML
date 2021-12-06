import java.util.ArrayList;

public class Player extends CombatEntity{

    public ArrayList<CombatCard> playerCards = new ArrayList<>();
    public ArrayList<CombatCard> playerHand = new ArrayList<>();
    int currentActionPoints;
    int maxActionPoints;

    public Player(int Health) {
        this.maxHealth = Health;
        this.currentHealth = Health;

        //TODO generate cards for player.

    }

    @Override
    public CombatCard takeTurn() {

        // update GUI

        return playerHand.get(0);

    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }
}

import java.util.ArrayList;

public class Player extends CombatEntity{

    public ArrayList<CombatCard> playerCards = new ArrayList<>();

    public Player(int Health) {
        this.maxHealth = Health;
        this.currentHealth = Health;

        //TODO generate cards for player.

    }

    @Override
    public void takeTurn() {

    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }
}

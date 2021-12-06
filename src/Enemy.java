import java.util.ArrayList;
import java.util.Random;

public class Enemy extends CombatEntity{

    ArrayList<CombatCard> cards ;
    int currentActionPoints;

    public Enemy(int Health, String type, ArrayList cards) {
        this.maxHealth = Health;
        this.currentHealth = Health;
        this.type = type;
        this.cards = cards;

    }

    @Override
    public CombatCard takeTurn() {

        Random random = null;
        int randomNum = random.nextInt(cards.size()-1);
        return cards.get(randomNum);
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }
}

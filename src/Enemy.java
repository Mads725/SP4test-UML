import java.util.ArrayList;
import java.util.Random;

public class Enemy extends CombatEntity{

    public Enemy(int Health, String type, ArrayList<CombatCard> cards) {
        this.maxHealth = Health;
        this.currentHealth = Health;
        this.type = type;
        this.cards = cards;

    }

    ArrayList<CombatCard> cards;

    @Override
    public CombatCard takeTurn() {
        Random r = new Random();
        int randomNum = r.nextInt(cards.size());
        return cards.get(randomNum);
    }

}

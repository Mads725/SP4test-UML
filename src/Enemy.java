import java.util.ArrayList;
import java.util.Random;

public class Enemy extends CombatEntity{

    public Enemy(String name, int Health, String element, ArrayList<CombatCard> cards) {
        this.setName(name);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);
        this.setElement(element);
        this.cards = cards;
    }

    ArrayList<CombatCard> cards;

    @Override
    public CombatCard takeTurn() { // The enemy takes its turn by playing one card from its cards, chosen randomly.
        Random r = new Random();
        int randomNum = r.nextInt(cards.size());
        return cards.get(randomNum);
    }

}
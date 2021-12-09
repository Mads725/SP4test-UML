import java.util.ArrayList;
import java.util.Random;

public class Enemy extends CombatEntity{


    public Enemy(String name, int Health, ElementType element, ArrayList<CombatCard> cards,int actionPoints) {
        this.setName(name);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);
        this.setElement(element);
        this.cards = cards;
        this.setMaxActionPoints(actionPoints);
    }
    ArrayList<CombatCard> cards;
    @Override
    public CombatCard takeTurn() { // The enemy takes its turn by playing one card from its cards, chosen randomly.
        Random r = new Random();
        int randomNum = r.nextInt(cards.size());
        return cards.get(randomNum);
    }

}
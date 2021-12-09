import java.util.ArrayList;
import java.util.Random;

public class Boss extends CombatEntity {

    public Boss(String name, int Health, ElementType element, ArrayList<CombatCard> bossCards, int actionPoints) {
        this.setName(name);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);
        this.setElement(element);
        this.bossCards = bossCards;
        this.setMaxActionPoints(actionPoints);
    }

    ArrayList<CombatCard> bossCards;

    @Override
    public CombatCard takeTurn() {
        Random r = new Random();
        int randomNum = r.nextInt(bossCards.size());
        // System.out.println(bossCards.get(randomNum));
        return bossCards.get(randomNum);
    }
}

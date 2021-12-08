package Test;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends CombatEntity {

    public Enemy(int Health, String type, ArrayList<CombatCard> cards) {
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);
        this.setType(type);
        this.cards = cards;
    }

    ArrayList<CombatCard> cards;

    @Override
    public CombatCard takeTurn() { // The enemy takes its turn by playing one card from its cards, chosen randomly.
        Random r = new Random();
        int randomNum = r.nextInt(cards.size());
        System.out.println(cards.get(randomNum));
        return cards.get(randomNum);
    }

}
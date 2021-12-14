package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends CombatEntity {

    private ArrayList<Card> playedCards;
    private BufferedImage image;
    private ArrayList<Card> cards;

    public Enemy(String name, int Health, ElementType element, ArrayList<Card> cards, int actionPoints) {
        this.setName(name);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);
        this.setElement(element);
        this.cards = cards;
        this.setMaxActionPoints(actionPoints);
        playedCards = new ArrayList<>();
    }

    public Enemy(String name, int Health, ElementType element, ArrayList<Card> cards, int actionPoints, String imageUrl) {
        this.setName(name);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);
        this.setElement(element);
        this.cards = cards;
        this.setMaxActionPoints(actionPoints);
        playedCards = new ArrayList<>();

        try {
            image = ImageIO.read(getClass().getResource(imageUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card takeTurn() { // The enemy takes its turn by playing one card from its cards, chosen randomly.
        Random r = new Random();
        int randomNum = r.nextInt(cards.size());
        Card playedCard = cards.get(randomNum);
        return playedCard;
    }

    public void resetPlayedCard(){

        playedCards.clear();
        playedCards = new ArrayList<>();
    }

    public void addPlayedCard(Card card){
        playedCards.add(card);
    }

    public ArrayList<Card> getPlayedCards() {
        return playedCards;
    }

    // ---------- Getters and setters -----------------

    public BufferedImage getImage() {
        return image;
    }
}
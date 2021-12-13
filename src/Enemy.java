import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends CombatEntity{
    private ArrayList<CombatCard> playedCards = new ArrayList<>();
    BufferedImage image;
    public Enemy(String name, int Health, ElementType element, ArrayList<CombatCard> cards,int actionPoints) {
        this.setName(name);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);
        this.setElement(element);
        this.cards = cards;
        this.setMaxActionPoints(actionPoints);
        playedCards = new ArrayList<>();
    }

    public Enemy(String name, int Health, ElementType element, ArrayList<CombatCard> cards,int actionPoints, String imageUrl) {
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

//        this.image = imageUrl;
    }

    ArrayList<CombatCard> cards;
    @Override
    public CombatCard takeTurn() { // The enemy takes its turn by playing one card from its cards, chosen randomly.
        Random r = new Random();
        int randomNum = r.nextInt(cards.size());
        CombatCard playedCard = cards.get(randomNum);
        addPlayedCard(playedCard);
        return playedCard;
    }

    public void resetPlayedCard(){
        playedCards = new ArrayList<>();
    }

    public void addPlayedCard(CombatCard card){
        playedCards.add(card);
    }

    public ArrayList<CombatCard> getPlayedCards() {
        return playedCards;
    }


}
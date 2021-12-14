package Game;

import java.util.ArrayList;
import java.util.Collections;

public class Player extends CombatEntity {

    static Card usedCard; // The current card played
    public ArrayList<Card> playerCards ;
    public ArrayList<Card> playerHand ;
    public ArrayList<Card> inventory ; // Boss rewards list
    private int heal=0; // Heal after every combat (reward from boss)

    public Player(int Health, int maxActionPoints) {
        this.setMaxActionPoints(maxActionPoints);
        this.setCurrentActionPoints(maxActionPoints);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);

        playerCards = new ArrayList<>();
        playerHand = new ArrayList<>();
        inventory = new ArrayList<>();

        generatePlayerCards(); // Generates 14 standard cards for the player to use.
    }

    @Override
    public Card takeTurn() { // Player turn method

        usedCard = null;

        GameController.frame.getPanel().updateHand();
        GameController.frame.repaint();

        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("e");
            }
        }

        return usedCard;
    }

    public void shuffleDeck() { // Shuffels the player card deck
        Collections.shuffle(playerCards);
    }

    public void drawHand() { // Darws up to the max hand size
        while (playerHand.size() < Balance.MAX_HAND_SIZE) {
            playerHand.add(playerCards.get(0));
            playerCards.remove(0);
        }
    }

    private void generatePlayerCards() {
        //( int damage, String element, String cardName, String cardText,int actionPointsCost)
        //( int hea, String cardName, String cardText,int actionPointsCost)
        Card fireball = new Card(5, ElementType.FIRE, "Fireball",  1);
        playerCards.add(fireball);
        playerCards.add(fireball);
        playerCards.add(fireball);
        Card iceBlast = new Card(5, ElementType.WATER, "Ice blast",  1);
        playerCards.add(iceBlast);
        playerCards.add(iceBlast);
        playerCards.add(iceBlast);
        Card overgrowth = new Card(5, ElementType.EARTH, "Overgrowth",  1);
        playerCards.add(overgrowth);
        playerCards.add(overgrowth);
        playerCards.add(overgrowth);
        Card inferno = new Card(12, ElementType.FIRE, "Inferno",  2);
        playerCards.add(inferno);
        Card surf = new Card(12, ElementType.WATER, "Surf",  2);
        playerCards.add(surf);
        Card earthquake = new Card(12, ElementType.EARTH, "Earthquake",  2);
        playerCards.add(earthquake);
        Card heal = new Card(3, "Heal",1);
        playerCards.add(heal);
        playerCards.add(heal);
        Card majorHeal = new Card(9, "Major heal",2);
        playerCards.add(majorHeal);
    }

    public void setUsedCard(Card usedCard) {
        Player.usedCard = usedCard;
    }

    public void removeCardFromHand(int index) { // Empties hand into deck
        try {
            playerCards.add(playerHand.get(index));
            playerHand.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getCause());
        }
    }
    // ----------------- Getters and setters -----------------------

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

}

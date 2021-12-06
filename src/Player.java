import java.util.ArrayList;

public class Player extends CombatEntity{

    public Player(int Health, int maxActionPoints) {
        this.maxActionPoints = maxActionPoints;
        this.currentActionPoints = maxActionPoints;
        this.maxHealth = Health;
        this.currentHealth = Health;

        generatePlayerCards(); // Generates 14 standard cards for the player to use.
    }

    public ArrayList<CombatCard> playerCards = new ArrayList<>();
    public ArrayList<CombatCard> playerHand = new ArrayList<>();

    @Override
    public CombatCard takeTurn() {

        // update GUI

        //Button play card 0
        //Button play card 1
        //Button play card 2
        //Button play card 3
        //Button play card 4

        //Button next turn (sets actionPoints to 0)


        playerCards.add(playerHand.get(0));
        return playerHand.get(0);
    }

    public void drawHand() {
        while(playerHand.size()<Balance.MAX_HAND_SIZE) {
            playerHand.add(playerCards.get(0));
            playerCards.remove(0);
        }
    }

    private void generatePlayerCards() {
        //( int damage, String element, String cardName, String cardText,int actionPointsCost)
        //( int hea, String cardName, String cardText,int actionPointsCost)
        CombatCard fireball = new CombatCard(5, "FIRE", "Fireball", "Deals 5 fire damage", 1);
        playerCards.add(fireball);
        playerCards.add(fireball);
        CombatCard iceBlast = new CombatCard(5, "WATER", "Ice blast", "Deals 5 water damage", 1);
        playerCards.add(iceBlast);
        playerCards.add(iceBlast);
        CombatCard overgrowth = new CombatCard(5, "GRASS", "Overgrowth", "Deals 5 grass damage", 1);
        playerCards.add(overgrowth);
        playerCards.add(overgrowth);
        CombatCard inferno = new CombatCard(12, "FIRE", "Inferno", "Deals 12 fire damage", 2);
        playerCards.add(inferno);
        CombatCard surf = new CombatCard(12, "WATER", "Surf", "Deals 12 water damage", 2);
        playerCards.add(surf);
        CombatCard earthquake = new CombatCard(12, "GRASS", "Earthquake", "Deals 12 grass damage", 2);
        playerCards.add(earthquake);
        CombatCard heal = new CombatCard(4, "Heal", "Heals 4 hp",  1);
        playerCards.add(heal);
        playerCards.add(heal);
        playerCards.add(heal);
        CombatCard majorHeal = new CombatCard(10, "Major heal", "Heals 10 hp",  2);
        playerCards.add(majorHeal);
        playerCards.add(majorHeal);
    }
}

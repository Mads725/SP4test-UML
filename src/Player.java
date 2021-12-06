import java.util.ArrayList;

public class Player extends CombatEntity{

    public ArrayList<CombatCard> playerCards = new ArrayList<>();
    public ArrayList<CombatCard> playerHand = new ArrayList<>();
    int currentActionPoints;
    int maxActionPoints;

    public Player(int Health, int maxActionPoints) {
        this.maxActionPoints = maxActionPoints;
        this.maxHealth = Health;
        this.currentHealth = Health;

        generatePlayerCards(); // Generates 20 standard cards for the player to use.
    }

    @Override
    public CombatCard takeTurn() {

        // update GUI

        return playerHand.get(0);

    }



    @Override
    public int getCurrentHealth() {
        return currentHealth;
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

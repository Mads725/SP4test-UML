import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player extends CombatEntity {
    static CombatCard usedCard;


    public Player(int Health, int maxActionPoints) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter name:");
        setName(scanner.nextLine());
        this.setMaxActionPoints(maxActionPoints);
        this.setCurrentActionPoints(maxActionPoints);
        this.setMaxHealth(Health);
        this.setCurrentHealth(Health);

        generatePlayerCards(); // Generates 14 standard cards for the player to use.
    }

    public ArrayList<CombatCard> playerCards = new ArrayList<>();
    public ArrayList<CombatCard> playerHand = new ArrayList<>();

    @Override
    public CombatCard takeTurn() {


        int counter = 0;

        usedCard = null;

        GameController.frame.panel.updateHand();
        GameController.frame.panel.drawHand();
        GameController.frame.repaint();
        //System.out.println(playerHand);
        //wait for player input though gui
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("e");
            }
        }

        //Used card returns to deck


        //Button next turn (sets actionPoints to 0)
        //usedCard = playerHand.get(0);


        // Update GUI missing
        //System.out.println(usedCard);
        return usedCard;
    }

    public void shuffleDeck() {
        Collections.shuffle(playerCards);
    }

    public void drawHand() {
        while (playerHand.size() < Balance.MAX_HAND_SIZE) {
            playerHand.add(playerCards.get(0));
            playerCards.remove(0);
        }
        // Update GUI missing
    }

    private void generatePlayerCards() {
        //( int damage, String element, String cardName, String cardText,int actionPointsCost)
        //( int hea, String cardName, String cardText,int actionPointsCost)
        CombatCard fireball = new CombatCard(4, "FIRE", "Fireball", "Deals 4 fire damage", 1);
        playerCards.add(fireball);
        playerCards.add(fireball);
        CombatCard iceBlast = new CombatCard(4, "WATER", "Ice blast", "Deals 4 water damage", 1);
        playerCards.add(iceBlast);
        playerCards.add(iceBlast);
        CombatCard overgrowth = new CombatCard(4, "GRASS", "Overgrowth", "Deals 4 grass damage", 1);
        playerCards.add(overgrowth);
        playerCards.add(overgrowth);
        CombatCard inferno = new CombatCard(10, "FIRE", "Inferno", "Deals 10 fire damage", 2);
        playerCards.add(inferno);
        CombatCard surf = new CombatCard(10, "WATER", "Surf", "Deals 10 water damage", 2);
        playerCards.add(surf);
        CombatCard earthquake = new CombatCard(10, "GRASS", "Earthquake", "Deals 10 grass damage", 2);
        playerCards.add(earthquake);

        CombatCard heal = new CombatCard(3, "Heal", "Heals 3 hp",  1);
        playerCards.add(heal);
        playerCards.add(heal);
        playerCards.add(heal);
        CombatCard majorHeal = new CombatCard(8, "Major heal", "Heals 8 hp",  2);

        playerCards.add(majorHeal);
    }

    public void setUsedCard(CombatCard usedCard) {
        Player.usedCard = usedCard;

    }

    public void removeCardFromHand(int index) {
        try {
            playerCards.add(playerHand.get(index));
            playerHand.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getCause());

        }
    }


}



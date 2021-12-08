import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameController {

    static final Player player = new Player(Balance.MAX_PLAYER_HEALTH, Balance.MAX_ACTION_POINTS); // Player creation;
    static Frame frame;
    private ArrayList<Enemy> randomEnemies = new ArrayList<>(); // List of enemies the player can face.
    private ArrayList<Boss> bosses = new ArrayList<>();
    private int layer = 1; //number of combats completed.

    public void startGame() {

        generateEnemies(); // Generates the enemies the player will face
        generateRewardCards();
        generateBosses();

        frame = new Frame(player);


        //GamePlay loop
        while (player.getCurrentHealth() > 0) { //TODO something empty, fill it again or avoid error.
            Random r = new Random();
            if (layer == 5 || layer == 10) {

            } else {
                int randomNum = r.nextInt(randomEnemies.size());
                System.out.println(randomEnemies.get(randomNum).getName());
                Combat combat = new Combat(player, randomEnemies.get(randomNum));
                combat.startCombat();
                randomEnemies.get(randomNum).setCurrentHealth(randomEnemies.get(randomNum).getMaxHealth());
                layer++;
            }
        }

        System.out.println("Score: " + layer);

    }

    public void runOverview() {

    }

    public void generateEnemies() {

        CombatCard fireIntensity = new CombatCard(6, "Intensity", "Heals 6 hp", 1);
        CombatCard fireTornado = new CombatCard(10, "FIRE", "Fire Tornado", "Deals 10 fire damage", 1);
        CombatCard fireBlast = new CombatCard(6, "FIRE", "Fireblast", "Deals 6 fire damage", 1);
        ArrayList<CombatCard> enemy1Cards = new ArrayList<>();
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireTornado);
        enemy1Cards.add(fireIntensity);
        Enemy enemy1 = new Enemy("Fire Lizard", 90, "FIRE", enemy1Cards);
        randomEnemies.add(enemy1);

        CombatCard eatBanana = new CombatCard(6, "Eat Banana", "Heals 6 hp", 1);
        CombatCard throwBanana = new CombatCard(6, "EARTH", "Throw Banana", "Deals 6 earth damage", 1);
        CombatCard throwBananaHarder = new CombatCard(12, "EARTH", "Throw Banana Harder", "Deals 12 earth damage", 1);
        ArrayList<CombatCard> enemy2Cards = new ArrayList<>();
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(eatBanana);
        enemy2Cards.add(throwBananaHarder);
        Enemy enemy2 = new Enemy("Monkey", 100, "EARTH", enemy2Cards);
        randomEnemies.add(enemy2);

        CombatCard regenerate = new CombatCard(8, "Regenerate", "Heals 8 hp", 1);
        CombatCard drench = new CombatCard(7, "WATER", "Drench", "Deals 7 water damage", 1, 2);
        CombatCard splash = new CombatCard(6, "WATER", "Splash", "Deals 6 water damage", 1);
        ArrayList<CombatCard> enemy3Cards = new ArrayList<>();
        enemy3Cards.add(splash);
        enemy3Cards.add(splash);
        enemy3Cards.add(regenerate);
        enemy3Cards.add(regenerate);
        enemy3Cards.add(drench);
        Enemy enemy3 = new Enemy("Fish", 70, "WATER", enemy3Cards);
        randomEnemies.add(enemy3);
    }
    public void generateBosses(){
        CombatCard headbutt = new CombatCard(6, "EARTH", "Headbutt", "Deals 6 earth damage", 1);
        CombatCard spores = new CombatCard("EARTH", "Spores", "Deals 6 damage each turn", 2, 6, 3);
        CombatCard halloween = new CombatCard("Halloween", "33% chance to fear", 1,1);
        ArrayList<CombatCard> bossCards = new ArrayList<>();
        bossCards.add(headbutt);
        bossCards.add(headbutt);
        bossCards.add(spores);
        bossCards.add(halloween);
        Boss boss1 = new Boss("Pumpkin Man",140,"EARTH", bossCards,3);
        bosses.add(boss1);
    }

    public void rewardCards(ArrayList<CombatCard> rewardCards) {
        Collections.shuffle(rewardCards);
        CombatCard rewardOption1=rewardCards.get(0);
        CombatCard rewardOption2=rewardCards.get(1);
        CombatCard rewardOption3=rewardCards.get(2);


        //CombatCard rewardCard=rewardCards.get(?);
        //player.playerCards.add(rewardCard);
    }

    public void generateRewardCards() {
        ArrayList<CombatCard> rewardCards = new ArrayList<>();
        CombatCard meteor = new CombatCard(20, "FIRE", "Meteor", "Deals 20 fire damage", 3);
        CombatCard heat = new CombatCard(7, "Heat", "Heals 7 hp, +1 actionpoint next turn", 2, -1);
        CombatCard rain = new CombatCard(8, 8, "WATER", "Rain", "Deals 8 water damage, Heals 8 hp", 2);
        CombatCard healingWater = new CombatCard(14, "Healing Water", "Heals 14 hp", 2);
        CombatCard boulder = new CombatCard(14, "EARTH", "Boulder", "Deals 14 earth damage", 2);
        CombatCard regenerate = new CombatCard(8, "Regenerate", "Heals 8 hp", 1);
        CombatCard splash = new CombatCard(6, "WATER", "Splash", "Deals 6 water damage", 1);
        CombatCard fireIntensity = new CombatCard(6, "Intensity", "Heals 6 hp", 1);
        CombatCard fireTornado = new CombatCard(10, "FIRE", "Fire Tornado", "Deals 10 fire damage", 2);
        CombatCard fireBlast = new CombatCard(6, "FIRE", "Fireblast", "Deals 6 fire damage", 1);
        CombatCard sprinkle = new CombatCard(3, "WATER", "Sprinkle", "Deals 3 water damage", 0);
        CombatCard glassOfWater = new CombatCard(3, "Glass of Water", "Heals 3 hp", 0);


        rewardCards.add(sprinkle);
        rewardCards.add(glassOfWater);
        rewardCards.add(regenerate);
        rewardCards.add(splash);
        rewardCards.add(fireIntensity);
        rewardCards.add(fireTornado);
        rewardCards.add(fireBlast);
        rewardCards.add(meteor);
        rewardCards.add(heat);
        rewardCards.add(rain);
        rewardCards.add(healingWater);
        rewardCards.add(boulder);
    }
}

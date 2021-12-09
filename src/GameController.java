import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameController {

    static final Player player = new Player(Balance.MAX_PLAYER_HEALTH, Balance.MAX_ACTION_POINTS); // Player creation.
    static Frame frame;
    private ArrayList<Enemy> randomEnemies = new ArrayList<>(); // List of enemies the player can face.
    private ArrayList<Enemy> bosses = new ArrayList<>();
    private int layer = 1; // Number of combats completed. high score.

    public void startGame() {

        generateEnemies(); // Generates the enemies the player will face
        generateRewardCards();
        generateBosses();

        frame = new Frame(player);

        //GamePlay loop
        while (player.getCurrentHealth() > 0) {
            Random r = new Random();
            if (layer % Balance.BOSS_LAYER == 5 ) {
                /*int randomNum = r.nextInt(bosses.size());
                System.out.println(bosses.get(randomNum).getName());
                Combat combat = new Combat(player, bosses.get(randomNum));
                combat.startCombat();
                bosses.get(randomNum).setCurrentHealth(bosses.get(randomNum).getMaxHealth());
                */
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

        CombatCard burn = new CombatCard("FIRE", "Burn", "Deals 5 fire damage for 3 turns", 1,5,3);
        CombatCard fireTornado = new CombatCard(16, "FIRE", "Fire Tornado", "Deals 16 fire damage", 1);
        CombatCard fireBlast = new CombatCard(8, "FIRE", "Fireblast", "Deals 8 fire damage", 1);
        ArrayList<CombatCard> enemy1Cards = new ArrayList<>();
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireTornado);
        enemy1Cards.add(burn);
        Enemy enemy1 = new Enemy("Fire Lizard", 70, "FIRE", enemy1Cards,1);
        randomEnemies.add(enemy1);

        CombatCard eatBanana = new CombatCard(6, "Eat Banana", "Heals 6 hp", 1);
        CombatCard throwBanana = new CombatCard(6, "EARTH", "Throw Banana", "Deals 6 earth damage", 1);
        CombatCard throwBananaHarder = new CombatCard(14, "EARTH", "Throw Banana Harder", "Deals 14 earth damage", 1);
        ArrayList<CombatCard> enemy2Cards = new ArrayList<>();
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(eatBanana);
        enemy2Cards.add(throwBananaHarder);
        Enemy enemy2 = new Enemy("Monkey", 80, "EARTH", enemy2Cards,1);
        randomEnemies.add(enemy2);

        CombatCard regenerate = new CombatCard("WATER", "Regenerate", "Heals 4 hp for 3 turns",1, 4,3);
        CombatCard drench = new CombatCard(8, "WATER", "Drench", "Deals 8 water damage", 1, 2);
        CombatCard splash = new CombatCard(8, "WATER", "Splash", "Deals 8 water damage", 1);
        ArrayList<CombatCard> enemy3Cards = new ArrayList<>();
        enemy3Cards.add(splash);
        enemy3Cards.add(splash);
        enemy3Cards.add(regenerate);
        enemy3Cards.add(regenerate);
        enemy3Cards.add(drench);
        Enemy enemy3 = new Enemy("Fish", 60, "WATER", enemy3Cards,1);
        randomEnemies.add(enemy3);
    }
    public void generateBosses(){
        CombatCard headbutt = new CombatCard(10, "EARTH", "Headbutt", "Deals 10 earth damage", 1);
        CombatCard spores = new CombatCard("EARTH", "Spores", "Deals 6 damage each turn", 1, 6, 3);
        CombatCard halloween = new CombatCard("Halloween", "33% chance to fear", 1,1);
        ArrayList<CombatCard> bossCards = new ArrayList<>();
        bossCards.add(headbutt);
        bossCards.add(headbutt);
        bossCards.add(headbutt);
        bossCards.add(spores);
        bossCards.add(halloween);
        Enemy boss1 = new Enemy("Pumpkin Man",130,"EARTH", bossCards,3);
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameController {

    static final Player player = new Player(Balance.MAX_PLAYER_HEALTH, Balance.MAX_ACTION_POINTS); // Player creation.
    static Frame frame;
    private ArrayList<Enemy> randomEnemies1 = new ArrayList<>(); // List of enemies the player can face.
    private ArrayList<Enemy> randomEnemies2 = new ArrayList<>();
    private ArrayList<Enemy> bosses = new ArrayList<>();
    private ArrayList<CombatCard> rewardCards = new ArrayList<>();
    private CombatCard[] bossRewards1;
    private int layer = 1; // Number of combats completed. high score.
    private int bossCounter=1;

    public void startGame() {
        frame = new Frame(player);
        //New Game
        newGameScreen();


        generateEnemies(); // Generates the enemies the player will face
        rewardCards = generateRewardCards();
        generateBosses();
        generateBossRewards();



        //GamePlay loop
        while (player.getCurrentHealth() > 0) {

            checkInventory();

            if (layer % Balance.BOSS_LAYER == 0 ) {
                initializeBossCombat();
                if (player.getCurrentHealth() > 0) {
                    if (bossCounter==0) {
                        rewardScreen(bossRewards1[0], bossRewards1[1], bossRewards1[2]);
                    }
                    player.setCurrentHealth(player.getMaxHealth());
                    layer++;
                }
            }else {
                if(bossCounter==0) {
                    initializeCombat(randomEnemies1);
                }else if (bossCounter==1){
                    initializeCombat(randomEnemies2);
                }
                CombatCard[] rewards = rewardCards(rewardCards);
                //OnceCombatFinishes Close combat and open reward screen
                rewardScreen(rewards[0],rewards[1],rewards[2]);
                //open OverView

                layer++;
            }

        }
        //Lose game
        System.out.println("Score: " + layer);

    }

    private void newGameScreen() {
        synchronized (this){
            try {
                frame.setNewGameScreen(this);
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("e");

            }
        }
    }

    public void runOverview() {

    }

    public void generateEnemies() {

        CombatCard burn = new CombatCard(ElementType.FIRE, "Burn", "Deals 6 fire damage for 3 turns", 1,6,3);
        CombatCard fireTornado = new CombatCard(14, ElementType.FIRE, "Fire Tornado",  1);
        CombatCard fireBlast = new CombatCard(7, ElementType.FIRE, "Fireblast",  1);
        ArrayList<CombatCard> enemy1Cards = new ArrayList<>();
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireTornado);
        enemy1Cards.add(burn);
        Enemy enemy1 = new Enemy("Fire Lizard", 70, ElementType.FIRE, enemy1Cards, 1);

        randomEnemies1.add(enemy1);

        CombatCard eatBanana = new CombatCard(7, "Eat Banana",  1);
        CombatCard throwBanana = new CombatCard(6, ElementType.EARTH, "Throw Banana",  1);
        CombatCard throwBananaHarder = new CombatCard(12, ElementType.EARTH, "Throw Banana Harder",  1);
        ArrayList<CombatCard> enemy2Cards = new ArrayList<>();
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(eatBanana);
        enemy2Cards.add(throwBananaHarder);
        Enemy enemy2 = new Enemy("Monkey", 80, ElementType.EARTH, enemy2Cards,1);

        randomEnemies1.add(enemy2);

        CombatCard splash = new CombatCard(7, ElementType.WATER, "Splash",  1);
        CombatCard regenerate = new CombatCard(ElementType.WATER, "Regenerate", "Heals 4 hp for 3 turns",1, -4,3);
        CombatCard drench = new CombatCard(8, ElementType.WATER, "Drench", "Deals 8 water damage and slows player for 1 turn", 1, 2);
        ArrayList<CombatCard> enemy3Cards = new ArrayList<>();
        enemy3Cards.add(splash);
        enemy3Cards.add(splash);
        enemy3Cards.add(regenerate);
        enemy3Cards.add(drench);
        Enemy enemy3 = new Enemy("Fish", 60, ElementType.WATER, enemy3Cards, 1);

        randomEnemies1.add(enemy3);

        CombatCard lavaSpit = new CombatCard(5, ElementType.FIRE, "Lava Spit", 1);
        CombatCard intensity = new CombatCard(6, "Intensity", 1);
        CombatCard fireShield = new CombatCard("Fire Shield", 1, "Deals 3 damage to attacker when damaged", 3,5);
        //CombatCard explode = new CombatCard(40, ElementType.FIRE, "Explode", 1);
        ArrayList<CombatCard> enemy4Cards = new ArrayList<>();
        enemy4Cards.add(lavaSpit);
        enemy4Cards.add(intensity);
        enemy4Cards.add(fireShield);
        enemy4Cards.add(fireShield);
        enemy4Cards.add(fireShield);
        Enemy enemy4 = new Enemy("Unstable Lava", 90,ElementType.FIRE,enemy4Cards,1);

        randomEnemies2.add(enemy4);


        CombatCard bite = new CombatCard(12, ElementType.EARTH, "Bite", 1);
        CombatCard camouflage = new CombatCard("Camouflage","Becomes harder to hit and gains an extra actionpoint next turn", 1, 1,1,-1);
        CombatCard venom = new CombatCard(ElementType.EARTH, "Venom", "Deals 9 damage for 2 turns", 1, 9, 2);
        ArrayList<CombatCard> enemy5Cards = new ArrayList<>();
        enemy5Cards.add(bite);
        enemy5Cards.add(bite);
        enemy5Cards.add(camouflage);
        enemy5Cards.add(venom);
        Enemy enemy5 = new Enemy("Venomous Snake", 70,ElementType.EARTH,enemy5Cards,1);

        randomEnemies2.add(enemy5);


    }

    public void generateBosses(){
        CombatCard headbutt = new CombatCard(10, ElementType.EARTH, "Headbutt",  1);
        CombatCard spores = new CombatCard(ElementType.EARTH, "Spores", "Deals 6 damage each turn", 1, 6, 3);
        CombatCard halloween = new CombatCard("Halloween", "33% chance to fear", 1,1);
        ArrayList<CombatCard> bossCards = new ArrayList<>();
        bossCards.add(headbutt);
        bossCards.add(headbutt);
        bossCards.add(headbutt);
        bossCards.add(spores);
        bossCards.add(spores);
        bossCards.add(halloween);

        Enemy boss1 = new Enemy("Pumpkin Man",130,ElementType.EARTH, bossCards,2);

        bosses.add(boss1);

        CombatCard ink = new CombatCard("Ink", "Blinds enemy for 2 turns",1,1,2);
        CombatCard devour = new CombatCard(16,ElementType.WATER,"Devour",1);
        CombatCard dive = new CombatCard(12,"Dive",1);
        CombatCard flail = new CombatCard(5,ElementType.WATER,"Flail",1);
        ArrayList<CombatCard> bossCards2 = new ArrayList<>();
        bossCards2.add(flail);
        bossCards2.add(flail);
        bossCards2.add(flail);
        bossCards2.add(flail);
        bossCards2.add(dive);
        bossCards2.add(devour);
        bossCards2.add(ink);

        Enemy boss2 = new Enemy("Kraken", 170, ElementType.WATER,bossCards2,2);
        bosses.add(boss2);

        CombatCard claw = new CombatCard(7,ElementType.FIRE, "Claw", 1);
        CombatCard incinerate = new CombatCard(ElementType.FIRE, "Incinerate", "Deals 7 damage for 3 turns", 1, 7, 3);
        CombatCard ascend = new CombatCard(2,"Ascend", "Becomes untargetable and charges an attack");
        //CombatCard descend = new CombatCard(26,ElementType.FIRE, "Descend", 2);
        ArrayList<CombatCard> bossCards3 = new ArrayList<>();
        bossCards3.add(claw);
        bossCards3.add(claw);
        bossCards3.add(claw);
        bossCards3.add(incinerate);
        bossCards3.add(incinerate);
        bossCards3.add(ascend);

        Enemy boss3 = new Enemy("Dragon", 180, ElementType.FIRE, bossCards3,2);
        bosses.add(boss3);
    }

    public CombatCard[] rewardCards(ArrayList<CombatCard> rewardCards) {
        Collections.shuffle(rewardCards);
        CombatCard rewardOption1=rewardCards.get(0);
        CombatCard rewardOption2=rewardCards.get(1);
        CombatCard rewardOption3=rewardCards.get(2);
        CombatCard[] rewards = {rewardOption1, rewardOption2, rewardOption3};
        return rewards;

    }

    public ArrayList<CombatCard> generateRewardCards() {
        ArrayList<CombatCard> rewardCards = new ArrayList<>();
        CombatCard meteor = new CombatCard(24, ElementType.FIRE, "Meteor",  3);
        CombatCard heat = new CombatCard(7, "Heat","Heals 7 damage and gain an action point next turn", 2, -1);
        CombatCard rain = new CombatCard(8, 8, ElementType.WATER, "Rain", "Deals 8 water damage, Heals 8 hp", 2);
        CombatCard healingWater = new CombatCard(14, "Healing Water",  2);
        CombatCard boulder = new CombatCard(14, ElementType.EARTH, "Boulder",  2);
        CombatCard regenerate = new CombatCard(ElementType.WATER, "Regenerate", "Heals 4 hp for 3 turns",1, -4,3);
        CombatCard splash = new CombatCard(6, ElementType.WATER, "Splash",  1);
        CombatCard burn = new CombatCard(ElementType.FIRE, "Burn", "Deals 6 fire damage for 3 turns", 1,6,3);
        CombatCard fireTornado = new CombatCard(14, ElementType.FIRE, "Fire Tornado",  2);
        CombatCard fireBlast = new CombatCard(6, ElementType.FIRE, "Fireblast",  1);
        CombatCard sprinkle = new CombatCard(3, ElementType.WATER, "Sprinkle",  0);
        CombatCard glassOfWater = new CombatCard(3, "Glass of Water",  0);

        rewardCards.add(sprinkle);
        rewardCards.add(glassOfWater);
        rewardCards.add(regenerate);
        rewardCards.add(splash);
        rewardCards.add(burn);
        rewardCards.add(fireTornado);
        rewardCards.add(fireBlast);
        rewardCards.add(meteor);
        rewardCards.add(heat);
        rewardCards.add(rain);
        rewardCards.add(healingWater);
        rewardCards.add(boulder);

        return rewardCards;
    }

    public void generateBossRewards(){
        CombatCard armour = new CombatCard("Armour", "Raises Max hp by 30",-1);
        CombatCard boots = new CombatCard("Boots","Grants an extra Max Actionpoint",-1);
        CombatCard bandages = new CombatCard("Bandages", "Recovers 10 hp after each combat",-1);
        bossRewards1=new CombatCard[]{armour,boots,bandages};


    }
    public void initializeCombat(ArrayList<Enemy> randomEnemies){
        Random r = new Random();
        int randomNum = r.nextInt(randomEnemies.size());
        System.out.println(randomEnemies.get(randomNum).getName());
        Combat combat = new Combat(player, randomEnemies.get(randomNum));
        combat.startCombat();
        randomEnemies.get(randomNum).setCurrentHealth(randomEnemies.get(randomNum).getMaxHealth());
    }
    public void initializeBossCombat(){
        System.out.println(bosses.get(bossCounter).getName());
        Combat combat = new Combat(player, bosses.get(bossCounter));
        combat.startCombat();
        bosses.get(bossCounter).setCurrentHealth(bosses.get(bossCounter).getMaxHealth());
        bossCounter++;
    }
    public void rewardScreen(CombatCard combatCard1, CombatCard combatCard2, CombatCard combatCard3){
        frame.removeHandPanel();
        frame.removeCombatPanel();

        frame.setRewardScreen(combatCard1,combatCard2,combatCard3, this);
        frame.repaint();
        //wait for player input
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("e");

            }
        }
        frame.removeRewardScreen();
    }
    public void checkInventory(){
        if (player.inventory.size()!=0) {
            for (int i = 0; i < player.inventory.size(); i++) {
                if (player.inventory.get(i).getCardName().equals("Armour")) {
                    player.setMaxHealth(130);
                    player.setCurrentHealth(player.getMaxHealth());
                    player.inventory.remove(i);
                }
                else if (player.inventory.get(i).getCardName().equals("Boots")){
                    player.setMaxActionPoints(4);
                    player.inventory.remove(i);
                }else if(player.inventory.get(i).getCardName().equals("Bandages")){
                    player.setHeal(10);
                    player.inventory.remove(i);
                }
            }
        }
        player.addHealth(player.getHeal());
    }


    public int getLayer(){
        return layer;
    }
}

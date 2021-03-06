package Game;

import GUI.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameController {

    public static Player player; // Player
    public static Frame frame; // Game window with GUI.
    private ArrayList<Enemy> randomEnemies1 = new ArrayList<>(); // List of enemies the player can face.
    private ArrayList<Enemy> randomEnemies2 = new ArrayList<>(); // List of enemies the player can face after the first boss.
    private ArrayList<Enemy> randomEnemies3 = new ArrayList<>(); // List of enemies the player can face after the second boss.
    private ArrayList<Enemy> bosses = new ArrayList<>(); // List of bosses the player can face.
    ArrayList<Card> rewardCards = new ArrayList<>(); // List of rewards the player can get after a combat.
    private Card[] bossRewards1, bossRewards2; // List of boss rewards after a boos is defeated.
    private int layer; // Number of combats completed. high score.
    private int bossCounter; // Number of bosses defeated.
    private String playerName;

    public GameController() {
        initialiseGC();
        frame = new Frame(player, this);
        frame.setOverviewScreen();
    }

    public void startGame() {

        //New Game
        newGameScreen();
        initialiseGC();

        generateEnemies(); // Generates the enemies the player will face
        generateRewardCards();
        generateBosses();
        generateBossRewards();

        //GamePlay loop
        while (player.getCurrentHealth() > 0) {

            checkInventory();

            if (layer % Balance.BOSS_LAYER == 0) { // Checks if the layer is a boss layer or a normal combat.
                initializeBossCombat();
                if (player.getCurrentHealth() > 0) { // Player wins
                    if(bossCounter==1) {
                        rewardScreen(bossRewards1[0], bossRewards1[1], bossRewards1[2]);
                    }
                    else {
                        rewardScreen(bossRewards2[0], bossRewards2[1], bossRewards2[2]);
                    }
                    player.setCurrentHealth(player.getMaxHealth());
                }

            } else { // Normal combat
                if(bossCounter==0) { // First tier of enemies before the boss
                    initializeCombat(randomEnemies1);
                    if (player.getCurrentHealth() > 0) {
                        Card[] rewards = rewardCards(rewardCards);
                        //OnceCombatFinishes Close combat and open reward screen
                        rewardScreen(rewards[0], rewards[1], rewards[2]);
                    }

                } else if(bossCounter==1) { // Second tier of enemies after the first boss
                    initializeCombat(randomEnemies2);
                    if (player.getCurrentHealth() > 0) {
                        Card[] rewards = rewardCards(rewardCards);
                        //OnceCombatFinishes Close combat and open reward screen
                        rewardScreen(rewards[0], rewards[1], rewards[2]);
                    }

                } else if(bossCounter==2) { // Third tier of enemies after the second boss.
                    initializeCombat(randomEnemies3);
                    if (player.getCurrentHealth() > 0) {
                        Card[] rewards = rewardCards(rewardCards);
                        //OnceCombatFinishes Close combat and open reward screen
                        rewardScreen(rewards[0], rewards[1], rewards[2]);
                    }
                }
            }

            if (player.getCurrentHealth()>0) //If still alive, go up in level
                layer++;
            frame.removeHandPanel();
            frame.removeCombatPanel();
            frame.repaint();
            runOverview(); // Open OverView

        }
        // Lose game
        System.out.println("Score: " + layer);

    } // ------------------ StartGame() end -------------------

    private void newGameScreen() { // Show startup screen
        frame.getNewGameScreen().setVisible(true);
        synchronized (this) {
            try {
                frame.setNewGameScreen();
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("e");
            }
        }
        frame.getNewGameScreen().setVisible(false);
    }

    public void runOverview() { // Show overview screen
        frame.getOverviewScreen().getCardPanel().createCardsOverview();
        frame.getOverviewScreen().getCardPanel().drawCardsOverview();
        synchronized (this) {
            try {
                frame.getOverviewScreen().setVisible(true);
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("e");
            }
        }
    }

    public void generateEnemies() { // Generates the enemies the player will face in the game

        Card burn = new Card(ElementType.FIRE, "Burn", "Deals 5 fire damage for 3 turns", 1, 5, 3, 3);
        Card fireTornado = new Card(14, ElementType.FIRE, "Fire Tornado", 1);
        Card fireBlast = new Card(7, ElementType.FIRE, "Fireblast", 1);
        ArrayList<Card> enemy1Cards = new ArrayList<>();
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireTornado);
        enemy1Cards.add(burn);

        Enemy enemy1 = new Enemy("Fire Lizard", 70, ElementType.FIRE, enemy1Cards, 1, "/resources/firelizard.png");
        randomEnemies1.add(enemy1);

        Card eatBanana = new Card(7, "Eat Banana", 1);
        Card throwBanana = new Card(6, ElementType.EARTH, "Throw Banana", 1);
        Card throwBananaHarder = new Card(12, ElementType.EARTH, "Throw Banana Harder", 1);
        ArrayList<Card> enemy2Cards = new ArrayList<>();
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(throwBanana);
        enemy2Cards.add(eatBanana);
        enemy2Cards.add(throwBananaHarder);

        Enemy enemy2 = new Enemy("Monkey", 80, ElementType.EARTH, enemy2Cards, 1,"/resources/monkey.tif");
        randomEnemies1.add(enemy2);

        Card splash = new Card(7, ElementType.WATER, "Splash", 1);
        Card regenerate = new Card(ElementType.WATER, "Regenerate", "Heals 4 hp for 3 turns", 1, -4, 3, 3);
        Card drench = new Card(8, ElementType.WATER, "Drench", "Deals 8 water damage and slows player for 1 turn", 1, 2);
        ArrayList<Card> enemy3Cards = new ArrayList<>();
        enemy3Cards.add(splash);
        enemy3Cards.add(splash);
        enemy3Cards.add(regenerate);
        enemy3Cards.add(drench);

        Enemy enemy3 = new Enemy("Fish", 60, ElementType.WATER, enemy3Cards, 1,"/resources/fish.tif");
        randomEnemies1.add(enemy3);

        Card lavaSpit = new Card(6, ElementType.FIRE, "Lava Spit", 1);
        Card intensity = new Card(7, "Intensity", 1);
        Card fireShield = new Card("Fire Shield", 1, "Deals 3 damage to attacker when damaged", 3, 5);
        ArrayList<Card> enemy4Cards = new ArrayList<>();
        enemy4Cards.add(lavaSpit);
        enemy4Cards.add(intensity);
        enemy4Cards.add(fireShield);
        enemy4Cards.add(fireShield);
        enemy4Cards.add(fireShield);

        Enemy enemy4 = new Enemy("Unstable Lava", 110, ElementType.FIRE, enemy4Cards, 1);
        randomEnemies2.add(enemy4);

        Card bite = new Card(13, ElementType.EARTH, "Bite", 1);
        Card camouflage = new Card("Camouflage", "Becomes harder to hit and gains an extra actionpoint next turn", 1, 1, 1, -1);
        Card venom = new Card(ElementType.EARTH, "Venom", "Deals 9 damage for 2 turns", 1, 9, 2, 2);
        ArrayList<Card> enemy5Cards = new ArrayList<>();
        enemy5Cards.add(bite);
        enemy5Cards.add(bite);
        enemy5Cards.add(camouflage);
        enemy5Cards.add(venom);

        Enemy enemy5 = new Enemy("Venomous Snake", 90, ElementType.EARTH, enemy5Cards, 1);
        randomEnemies2.add(enemy5);

        Card pinch = new Card(9, ElementType.WATER, "Pinch", 1);
        Card regrow = new Card(30, "Regrow", "gives one less actionpoint next turn", 1, 0, 3, 1);
        Card lacerate = new Card(ElementType.WATER, "Lacerate", "Deals 5 damage for 5 turns", 1, 5, 5, 5);
        ArrayList<Card> enemy6Cards = new ArrayList<>();
        enemy6Cards.add(pinch);
        enemy6Cards.add(pinch);
        enemy6Cards.add(pinch);
        enemy6Cards.add(regrow);
        enemy6Cards.add(lacerate);
        enemy6Cards.add(lacerate);
        enemy6Cards.add(lacerate);

        Enemy enemy6 = new Enemy("Giant Crab", 120, ElementType.WATER, enemy6Cards, 1);
        randomEnemies2.add(enemy6);

        Card fireBreath = new Card(10, ElementType.FIRE, "Fire Breath", 1);
        Card flash = new Card("Flash", "Blinds target for 2 turns", 1, 1, 2);
        Card flameWhirl = new Card(16, ElementType.FIRE, "Flame Whirl", 1);
        Card lightUp = new Card(4, "Light Up", "gain an extra actionpoint next turn",1, -1, 3);
        ArrayList<Card> enemy7Cards = new ArrayList<>();
        enemy7Cards.add(fireBreath);
        enemy7Cards.add(fireBreath);
        enemy7Cards.add(flash);
        enemy7Cards.add(flameWhirl);
        enemy7Cards.add(lightUp);
        Enemy enemy7 = new Enemy("Pyromancer", 130, ElementType.FIRE, enemy7Cards,1);
        randomEnemies3.add(enemy7);

        Card spores = new Card(ElementType.EARTH, "Spores", "Deals 8 damage for 3 turns", 1, 8,3,3);
        Card sapLife = new Card(4, 10, ElementType.EARTH, "Sap Life", "Deals 4 damage and heals 10 damage", 1);
        Card naturalDefense = new Card("Natural Defense", 1, 4, "Deals 4 damage to attacker when damaged", 5);
        ArrayList<Card> enemy8Cards = new ArrayList<>();
        enemy8Cards.add(spores);
        enemy8Cards.add(spores);
        enemy8Cards.add(sapLife);
        enemy8Cards.add(naturalDefense);
        enemy8Cards.add(naturalDefense);
        Enemy enemy8 = new Enemy("Poisonous Plant", 160, ElementType.EARTH, enemy8Cards, 1);
        randomEnemies3.add(enemy8);

        Card lash = new Card(10, ElementType.WATER, "Lash", 1);
        Card electrocute = new Card(8, ElementType.WATER, "Electrocute", 1, 2);
        Card charge = new Card("Charge", "Increases damage from attacks by 40% for 5 turns", 1, 1.40f, 5, 6);
        ArrayList<Card> enemy9Cards = new ArrayList<>();
        enemy9Cards.add(lash);
        enemy9Cards.add(lash);
        enemy9Cards.add(electrocute);
        enemy9Cards.add(charge);
        enemy9Cards.add(charge);

        Enemy enemy9 = new Enemy("Electric Eel", 140, ElementType.WATER, enemy9Cards, 1);
        randomEnemies3.add(enemy9);
    }

    public void generateBosses() { // Generates the bosses the player will face.
        Card pumpkinPunch = new Card(6, ElementType.EARTH, "Pumpkin Punch", 1);
        Card headbutt = new Card(10, ElementType.EARTH, "Headbutt", 1);
        Card haunt = new Card(ElementType.EARTH, "Haunt", "Deals 6 damage for 3 turns", 1, 6, 3, 3);
        Card halloween = new Card("Halloween", "33% chance to fear", 3, 1);
        ArrayList<Card> bossCards = new ArrayList<>();
        bossCards.add(headbutt);
        bossCards.add(pumpkinPunch);
        bossCards.add(pumpkinPunch);
        bossCards.add(pumpkinPunch);
        bossCards.add(haunt);
        bossCards.add(haunt);
        bossCards.add(halloween);

        Enemy boss1 = new Enemy("Pumpkin Man", 130, ElementType.EARTH, bossCards, 2, "/resources/p1.png");
        bosses.add(boss1);

        Card ink = new Card("Ink", "Blinds target for 2 turns", 1, 1, 2);
        Card devour = new Card(16, ElementType.WATER, "Devour", 1);
        Card dive = new Card(12, "Dive", 1);
        Card flail = new Card(5, ElementType.WATER, "Flail", 1);
        ArrayList<Card> bossCards2 = new ArrayList<>();
        bossCards2.add(flail);
        bossCards2.add(flail);
        bossCards2.add(flail);
        bossCards2.add(flail);
        bossCards2.add(dive);
        bossCards2.add(devour);
        bossCards2.add(ink);

        Enemy boss2 = new Enemy("Kraken", 170, ElementType.WATER, bossCards2, 2);
        bosses.add(boss2);

        Card claw = new Card(9, ElementType.FIRE, "Claw", 1);
        Card incinerate = new Card(ElementType.FIRE, "Incinerate", "Deals 7 damage for 3 turns", 1, 7, 3, 3);
        Card ascend = new Card(2, 3, "Ascend", "Becomes untargetable and charges an attack");
        //Game.Card descend = new Game.Card(26,Game.ElementType.FIRE, "Descend", 2);
        ArrayList<Card> bossCards3 = new ArrayList<>();
        bossCards3.add(claw);
        bossCards3.add(claw);
        bossCards3.add(claw);
        bossCards3.add(incinerate);
        bossCards3.add(incinerate);
        bossCards3.add(ascend);

        Enemy boss3 = new Enemy("Dragon", 220, ElementType.FIRE, bossCards3, 2);
        bosses.add(boss3);
    }

    public void generateRewardCards() { // Generates the cards the player can get after a combat
        Card heat = new Card(7, "Heat", "gain an extra action point next turn", 2, -1, 0);
        Card rain = new Card(8, 8, ElementType.WATER, "Rain", "Deals 8 water damage, Heals 8 hp", 2);
        Card boulder = new Card(14, ElementType.EARTH, "Boulder", 2);
        Card regenerate = new Card(ElementType.WATER, "Regenerate", "Heals 4 hp for 3 turns", 1, -4, 3, 3);
        Card splash = new Card(6, ElementType.WATER, "Splash", 1);
        Card burn = new Card(ElementType.FIRE, "Burn", "Deals 5 fire damage for 3 turns", 1, 5, 3, 3);
        Card fireTornado = new Card(14, ElementType.FIRE, "Fire Tornado", 2);
        Card fireBlast = new Card(6, ElementType.FIRE, "Fireblast", 1);
        Card sprinkle = new Card(3, ElementType.WATER, "Sprinkle", 0);
        Card glassOfWater = new Card(3, "Glass of Water", 0);
        Card meteor = new Card(22, ElementType.FIRE, "Meteor", 3);
        Card venom = new Card(ElementType.EARTH, "Venom", "Deals 9 damage for 2 turns", 2, 9, 2, 2);
        Card healingWater = new Card(14, "Healing Water", 2);
        Card charge = new Card("Charge", "Increases damage from attacks by 30% for 5 turns", 2, 1.30f, 5, 0);
        Card camouflage = new Card("Camouflage", "Becomes harder to hit and gains an extra actionpoint next turn", 1, 1, 1, -1);
        Card lacerate = new Card(ElementType.WATER, "Lacerate", "Deals 4 damage for 5 turns", 2, 4, 5, 0);

        rewardCards.add(charge);
        rewardCards.add(healingWater);
        rewardCards.add(venom);
        rewardCards.add(meteor);
        rewardCards.add(sprinkle);
        rewardCards.add(glassOfWater);
        rewardCards.add(regenerate);
        rewardCards.add(splash);
        rewardCards.add(burn);
        rewardCards.add(fireTornado);
        rewardCards.add(fireBlast);
        rewardCards.add(heat);
        rewardCards.add(rain);
        rewardCards.add(boulder);
        rewardCards.add(camouflage);
        rewardCards.add(lacerate);
    }

    public Card[] rewardCards(ArrayList<Card> rewardCards) { // Gets three random rewards card to show the player
        Collections.shuffle(rewardCards);
        Card rewardOption1 = rewardCards.get(0);
        Card rewardOption2 = rewardCards.get(1);
        Card rewardOption3 = rewardCards.get(2);
        Card[] rewards = {rewardOption1, rewardOption2, rewardOption3};
        return rewards;
    }

    public void rewardScreen(Card card1, Card card2, Card card3) {
        frame.removeHandPanel();
        frame.removeCombatPanel();

        frame.setRewardScreen(card1, card2, card3, this);
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

    public void generateBossRewards() { // Generates the cards the player can get after a boss is defeated
        Card armour = new Card("Armour", "Raises Max hp by 30", -1);
        Card boots = new Card("Boots", "Grants an extra Max Actionpoint", -1);
        Card bandages = new Card("Bandages", "Recovers 10 hp after each combat", -1);

        bossRewards1 = new Card[]{armour, boots, bandages};

        Card enhancedArmour = new Card("Enhanced Armour", "Raises Max hp by 50", -1);
        Card sword = new Card("Sword", "Grants 25% increased damage", -1);
        Card phdInMedicalScience = new Card("PhD In Medical Science", "Grants 25% increased healing", -1);

        bossRewards2 = new Card[]{enhancedArmour, sword, phdInMedicalScience};
    }

    public void initializeCombat(ArrayList<Enemy> randomEnemies) { // Selects the enemy the player will fight
        Random r = new Random();
        int randomNum = r.nextInt(randomEnemies.size());
        System.out.println(randomEnemies.get(randomNum).getName());
        Combat combat = new Combat(player, randomEnemies.get(randomNum));
        combat.startCombat();
        randomEnemies.get(randomNum).setCurrentHealth(randomEnemies.get(randomNum).getMaxHealth());
    }

    public void initializeBossCombat() { // Selects the boss the player will fight
        System.out.println(bosses.get(bossCounter).getName());
        Combat combat = new Combat(player, bosses.get(bossCounter));
        combat.startCombat();
        bosses.get(bossCounter).setCurrentHealth(bosses.get(bossCounter).getMaxHealth());
        bossCounter++;
    }

    public void checkInventory() { // Boss rewards check
        if (player.inventory.size() != 0) {
            for (int i = 0; i < player.inventory.size(); i++) {
                if (player.inventory.get(i).getCardName().equals("Armour")) {
                    player.setMaxHealth(130);
                    player.setCurrentHealth(player.getMaxHealth());
                    player.inventory.remove(i);
                } else if (player.inventory.get(i).getCardName().equals("Boots")) {
                    player.setMaxActionPoints(4);
                    player.inventory.remove(i);
                } else if (player.inventory.get(i).getCardName().equals("Bandages")) {
                    player.setHeal(10);
                    player.inventory.remove(i);
                } else if (player.inventory.get(i).getCardName().equals("Sword")){
                    player.setSwordDamageIncrease(1.25f);
                    player.inventory.remove(i);
                } else if (player.inventory.get(i).getCardName().equals("PhD In Medical Science")){
                    player.setPhDHealingIncrease(1.25f);
                    player.inventory.remove(i);
                } else if (player.inventory.get(i).getCardName().equals("Enhanced Armour")) {
                    player.setMaxHealth(player.getMaxHealth()+50);
                    player.setCurrentHealth(player.getMaxHealth());
                    player.inventory.remove(i);
                }
            }
        }
        player.addHealth(player.getHeal());
    }

    public void initialiseGC() {
        player = new Player(Balance.MAX_PLAYER_HEALTH, Balance.MAX_ACTION_POINTS);
        player.setName(playerName);
        layer = 1;
        bossCounter = 0;
        generateEnemies(); // Generates the enemies the player will face
        rewardCards.clear();
        generateBossRewards();
        generateBosses();
        generateBossRewards();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getLayer() {
        return layer;
    }
}

package Test;

import java.util.ArrayList;
import java.util.Random;

public class GameController {

    static final Player player = new Player(Balance.MAX_PLAYER_HEALTH, Balance.MAX_ACTION_POINTS); // Player creation;
    static Frame frame;
    private ArrayList<Enemy> randomEnemies = new ArrayList<>(); // List of enemies the player can face.
    private int Layer = 0; //number of combats completed.

    public void startGame() {

        generateEnemies(); // Generates the enemies the player will face


        frame = new Frame(player);


        //GamePlay loop
        while(player.getCurrentHealth() > 0) { //TODO something empty, fill it again or avoid error.

            Random r = new Random();
            int randomNum = r.nextInt(randomEnemies.size());

            Combat combat = new Combat(player,randomEnemies.get(randomNum));
            combat.startCombat();
            randomEnemies.get(randomNum).setCurrentHealth(randomEnemies.get(randomNum).getMaxHealth());
            Layer++;
        }

        //game over high score? Layer?

    }
    public void runOverview(){

    }

    public void generateEnemies(){

        CombatCard fireIntensity = new CombatCard(6, "Intensity", "Heals 3 damage", 1);
        CombatCard fireTornado = new CombatCard(10, "FIRE", "Fire Tornado", "Deals 5 fire damage", 1);
        CombatCard fireBlast = new CombatCard(6, "FIRE", "Fireblast", "Deals 3 fire damage", 1);
        ArrayList<CombatCard> enemy1Cards = new ArrayList<>();
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireTornado);
        enemy1Cards.add(fireIntensity);
        Enemy enemy1 = new Enemy(100,"FIRE", enemy1Cards);
        randomEnemies.add(enemy1);
        //----------------------- enemy # 1 created -------------------------

        //add cards
        //ArrayList<CombatCard> enemy2Cards = new ArrayList<>();
        //CombatEntity enemy2 = new Enemy(100, "WATER", enemy2Cards);
        //randomEnemies.add(enemy2);


    }


}

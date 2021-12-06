import java.util.ArrayList;
import java.util.Random;

public class GameController {

    public ArrayList<CombatEntity> randomEnemies = new ArrayList<>();
    int Layer = 0; //number of combats completed.

    public void startGame() {

        //add cards
        CombatCard fireIntensity = new CombatCard(3, "Intensity", "Heals 3 damage", 1);
        CombatCard fireTornado = new CombatCard(5, "FIRE", "Fire Tornado", "Deals 5 fire damage", 1);
        CombatCard fireBlast = new CombatCard(3, "FIRE", "Fireblast", "Deals 3 fire damage", 1);
        ArrayList<CombatCard> enemy1Cards = new ArrayList<>();
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireBlast);
        enemy1Cards.add(fireTornado);
        enemy1Cards.add(fireIntensity);
        CombatEntity enemy1 = new Enemy(100,"FIRE", enemy1Cards);
        randomEnemies.add(enemy1);

        //add cards
        //ArrayList<CombatCard> enemy2Cards = new ArrayList<>();
        //CombatEntity enemy2 = new Enemy(100, "WATER", enemy2Cards);
        //randomEnemies.add(enemy2);

        CombatEntity player = new Player(100,5);

        //Overview overview = new Overview();

        while(player.getCurrentHealth() > 0) {

            Random random = null;
            int randomNum = random.nextInt(randomEnemies.size()-1);

            Combat combat = new Combat(player,randomEnemies.get(randomNum));
            combat.startCombat();


        }

        //game over high score? Layer?

    }

    public void runOverview(){



    }


}

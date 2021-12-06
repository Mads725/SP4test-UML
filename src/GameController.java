import java.util.ArrayList;
import java.util.Random;

public class GameController {

    public ArrayList<CombatEntity> randomEnemies = new ArrayList<>();
    int Layer = 0; //number of combats completed.

    public void startGame() {

        //add cards
        ArrayList<CombatCard> enemy1Cards = new ArrayList<>();
        CombatEntity enemy1 = new Enemy(100,"FIRE", enemy1Cards);
        randomEnemies.add(enemy1);

        //add cards
        ArrayList<CombatCard> enemy2Cards = new ArrayList<>();
        CombatEntity enemy2 = new Enemy(100, "WATER", enemy2Cards);
        randomEnemies.add(enemy2);

        Player player = new Player(100);

        //Overview overview = new Overview();

        Random random = null;
        int randomNum = random.nextInt(randomEnemies.size()-1);

        Combat combat = new Combat(player,randomEnemies.get(randomNum));
        combat.startCombat();

    }

}

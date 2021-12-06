import java.util.ArrayList;

public class Enemy extends CombatEntity{

    public Enemy(int Health, String type, ArrayList cards) {
        this.maxHealth = Health;
        this.currentHealth = Health;
        this.type = type;

    }

    @Override
    public void takeTurn() {


    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }
}

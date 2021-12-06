public abstract class CombatEntity {

int currentHealth;
int maxHealth;
String type;
int currentActionPoints;

abstract public CombatCard takeTurn();

    public void addHealth(int heal) {
        currentHealth = currentHealth + heal;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }
    public void removeHealth(int damage) {
        currentHealth = currentHealth - damage;
    }

abstract public int getCurrentHealth();


}


public abstract class CombatEntity {

int currentHealth;
int maxHealth;
String type;

abstract public void takeTurn();

abstract public int getCurrentHealth();
}


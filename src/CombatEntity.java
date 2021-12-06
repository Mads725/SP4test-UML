public abstract class CombatEntity {

int currentHealth;
int maxHealth;
String type;
int currentActionPoints;

abstract public CombatCard takeTurn();

abstract public int getCurrentHealth();
}


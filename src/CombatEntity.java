public abstract class CombatEntity {

    int currentHealth;
    int maxHealth;
    int currentActionPoints;
    int maxActionPoints;
    String type;

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
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentActionPoints(int currentActionPoints) {
        this.currentActionPoints = currentActionPoints;
    }
    public int getCurrentActionPoints() {
        return currentActionPoints;
    }
    public int getMaxActionPoints() {
        return maxActionPoints;
    }
}


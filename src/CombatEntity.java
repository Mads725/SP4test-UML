public abstract class CombatEntity {

    private int currentHealth;
    private int maxHealth;
    private int currentActionPoints;
    private int maxActionPoints;
    private String type;

    abstract public CombatCard takeTurn();

    public void addHealth(int heal) { // Adds health when damage is healed.
        currentHealth = currentHealth + heal;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public void removeHealth(int damage) { // Removes health when damage is taken.
        currentHealth = currentHealth - damage;
    }

    // -------------------------- Getters and setters ---------------------------------------

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

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxActionPoints(int maxActionPoints) {
        this.maxActionPoints = maxActionPoints;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getType() {
        return type;
    }
}


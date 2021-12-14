package Game;

public abstract class CombatEntity {

    private int currentHealth;
    private int maxHealth;
    private int currentActionPoints;
    private int maxActionPoints;
    private ElementType element;
    private String name;

    abstract public Card takeTurn(); // Abstract method what can be done in the entities turn.

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

    public void setElement(ElementType element) {
        this.element = element;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public ElementType getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


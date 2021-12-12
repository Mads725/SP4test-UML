public class CombatCard {

    int damage;
    int heal;
    private ElementType element;
    private String cardName;
    private String cardText;
    int actionPointsCost;
    int slow;
    int dot;
    int dotTurns;
    int fear;
    int blind;
    int blindTurns;
    int returnDamage;
    int returnDamageTurns;
    boolean invisible=false;
    boolean hasBeenPlayedThisTurnStun=false;
    boolean hasBeenPlayedLastTurnStun=false;
    boolean hasBeenPlayedThisTurnDot=false;
    boolean hasBeenPlayedLastTurnDot=false;
    boolean isAscend=false;
    boolean canExplode=false;

    public CombatCard(int damage, ElementType element, String cardName,  int actionPointsCost){ // Damage card.
        this.damage=damage;
        this.element=element;
        this.cardName = cardName;
        this.cardText = "Deals " + damage + " " + element + " damage" ;
        this.actionPointsCost = actionPointsCost;
    }
    public CombatCard(int heal, String cardName, int actionPointsCost){ // Healing card.
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = "Heals for " + heal + " damage";
        this.actionPointsCost = actionPointsCost;
    }
    public CombatCard(int damage, ElementType element, String cardName, String cardText, int actionPointsCost, int slow){
        this.damage=damage;
        this.element=element;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.slow=slow;
    }
    public CombatCard(int heal, String cardName, String cardText, int actionPointsCost, int slow){
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.slow=slow;
    }
    public CombatCard(int damage,int heal,ElementType element, String cardName, String cardText, int actionPointsCost){
        this.damage=damage;
        this.element=element;
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
    }
    public CombatCard(ElementType element, String cardName, String cardText, int actionPointsCost, int dot, int dotTurns){
        this.element=element;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.dot=dot;
        this.dotTurns=dotTurns;
        hasBeenPlayedLastTurnDot=true;
        hasBeenPlayedThisTurnDot=true;
    }
    public CombatCard(String cardName, String cardText,int fear, int actionPointsCost){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.fear=fear;
        this.hasBeenPlayedThisTurnStun=true;
    }
    public CombatCard(String cardName, String cardText, int actionPointsCost, int blind, int blindTurns){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.blind=blind;
        this.blindTurns=blindTurns;
        this.hasBeenPlayedThisTurnStun=true;
    }
    public CombatCard(String cardName, String cardText, int actionPointsCost){
        this.cardName=cardName;
        this.cardText=cardText;
        this.actionPointsCost=actionPointsCost;
    }
    public CombatCard(String cardName, String cardText, int actionPointsCost, int blind, int blindTurns, int slow){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.blind=blind;
        this.blindTurns=blindTurns;
        this.slow=slow;
        this.hasBeenPlayedLastTurnStun=true;
    }
    public CombatCard(int actionPointsCost, String cardName, String cardText){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.invisible=true;
        this.isAscend=true;
    }
    public CombatCard(String cardName, int actionPointsCost, String cardText, int returnDamage, int returnDamageTurns){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.returnDamage=returnDamage;
        this.canExplode=true;
        this.returnDamageTurns=returnDamageTurns;
    }

    public ElementType getElement() {
        return element;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardText() {
        return cardText;
    }

    @Override
    public String toString() {
        return "CombatCard{" +
                "cardName='" + cardName + '\'' +
                ", cardText='" + cardText + '\'' +
                '}';
    }
}

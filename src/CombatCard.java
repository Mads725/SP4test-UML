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
    }
    public CombatCard(String cardName, String cardText,int fear, int actionPointsCost){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.fear=fear;
    }
    public CombatCard(String cardName, String cardText, int actionPointsCost, int blind, int blindTurns){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.blind=blind;
        this.blindTurns=blindTurns;
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

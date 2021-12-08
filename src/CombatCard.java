public class CombatCard {

    int damage;
    int heal;
    private String element;
    private String cardName;
    private String cardText;
    int actionPointsCost;
    int slow;
    int dot;
    int dotTurns;
    int fear=0;

    public CombatCard(int damage, String element, String cardName, String cardText, int actionPointsCost){ // Damage card.
        this.damage=damage;
        this.element=element;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
    }
    public CombatCard(int heal, String cardName, String cardText, int actionPointsCost){ // Healing card.
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
    }
    public CombatCard(int damage, String element, String cardName, String cardText, int actionPointsCost, int slow){
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
    public CombatCard(int damage,int heal,String element, String cardName, String cardText, int actionPointsCost){
        this.damage=damage;
        this.element=element;
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
    }
    public CombatCard(String element, String cardName, String cardText, int actionPointsCost, int dot, int dotTurns){
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

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    @Override
    public String toString() {
        return "CombatCard{" +
                "cardName='" + cardName + '\'' +
                ", cardText='" + cardText + '\'' +
                '}';
    }
}

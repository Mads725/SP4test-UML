public class CombatCard {

    int damage;
    int heal;
    private String element;
    private String cardName;
    private String cardText;
    int actionPointsCost;


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

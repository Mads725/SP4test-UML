public class CombatCard {

    int damage;
    int heal;
    //String strongDamageType;
    //int strongDamage;
    String element;
    String cardName;
    String cardText;
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
}

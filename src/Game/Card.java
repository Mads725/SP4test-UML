package Game;

public class Card {

    private int damage;
    private int heal;
    private ElementType element;
    private String cardName;
    private String cardText;
    private int actionPointsCost;
    private int slow;
    private int dot;
    private int dotTurns;
    private int fear;
    private int blind;
    private int blindTurns;
    private int returnDamage;
    private int returnDamageTurns;
    private int stun;
    private int increasedDamageTurns;
    private int delay;
    private int delayDot;
    private float increasedDamage;
    private boolean invisible=false;
    private boolean hasBeenPlayedThisTurnStun=false;
    private boolean hasBeenPlayedLastTurnStun=false;
    private boolean hasBeenPlayedThisTurnDot=false;
    private boolean isAscend=false;
    private boolean canExplode=false;

    public Card(int damage, ElementType element, String cardName, int actionPointsCost){ // Damage card.
        this.damage=damage;
        this.element=element;
        this.cardName = cardName;
        this.cardText = "Deals " + damage + " " + element + " damage" ;
        this.actionPointsCost = actionPointsCost;
    }
    public Card(int heal, String cardName, int actionPointsCost){ // Healing card.
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = "Heals for " + heal + " damage";
        this.actionPointsCost = actionPointsCost;
    }
    public Card(int damage, ElementType element, String cardName, String cardText, int actionPointsCost, int slow){
        this.damage=damage;
        this.element=element;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.slow=slow;
    }
    public Card(int heal, String cardName, String cardText, int actionPointsCost, int slow, int delay){
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = "Heals for " + heal + " damage and " + cardText;
        this.actionPointsCost = actionPointsCost;
        this.slow=slow;
        this.delay=delay;
    }
    public Card(int damage, int heal, ElementType element, String cardName, String cardText, int actionPointsCost){
        this.damage=damage;
        this.element=element;
        this.heal=heal;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
    }
    public Card(ElementType element, String cardName, String cardText, int actionPointsCost, int dot, int dotTurns, int delayDot){
        this.element=element;
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.dot=dot;
        this.dotTurns=dotTurns;
        this.delayDot=delayDot;
        hasBeenPlayedThisTurnDot=true;
    }
    public Card(String cardName, String cardText, int fear, int actionPointsCost){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.fear=fear;
        this.hasBeenPlayedThisTurnStun=true;
    }
    public Card(String cardName, String cardText, int actionPointsCost, int blind, int blindTurns){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.blind=blind;
        this.blindTurns=blindTurns;
        this.hasBeenPlayedThisTurnStun=true;
    }
    public Card(String cardName, String cardText, int actionPointsCost){
        this.cardName=cardName;
        this.cardText=cardText;
        this.actionPointsCost=actionPointsCost;
    }
    public Card(String cardName, String cardText, int actionPointsCost, int blind, int blindTurns, int slow){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.blind=blind;
        this.blindTurns=blindTurns;
        this.slow=slow;
        this.hasBeenPlayedLastTurnStun=true;
    }
    public Card(int actionPointsCost, int delay, String cardName, String cardText){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.delay=delay;
        this.invisible=true;
        this.isAscend=true;
    }
    public Card(String cardName, int actionPointsCost, String cardText, int returnDamage, int returnDamageTurns){
        this.cardName = cardName;
        this.cardText = cardText;
        this.actionPointsCost = actionPointsCost;
        this.returnDamage=returnDamage;
        this.canExplode=true;
        this.returnDamageTurns=returnDamageTurns;
    }
    public Card(int damage, ElementType element, String cardName, int actionPointsCost, int stun){ // Damage card.
        this.damage=damage;
        this.element=element;
        this.cardName = cardName;
        this.cardText = "Deals " + damage + " " + element + " damage" ;
        this.actionPointsCost = actionPointsCost;
        this.stun=stun;
        this.hasBeenPlayedLastTurnStun=true;
    }
    public Card(String cardName, String cardText, int actionPointsCost, float increasedDamage, int increasedDamageTurns, int delay){
        this.cardName=cardName;
        this.cardText=cardText;
        this.actionPointsCost=actionPointsCost;
        this.increasedDamage=increasedDamage;
        this.increasedDamageTurns=increasedDamageTurns;
        this.delay=delay;
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
        return "Game.Card{" +
                "cardName='" + cardName + '\'' +
                ", cardText='" + cardText + '\'' +
                '}';
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getActionPointsCost() {
        return actionPointsCost;
    }

    public void setActionPointsCost(int actionPointsCost) {
        this.actionPointsCost = actionPointsCost;
    }

    public int getSlow() {
        return slow;
    }

    public void setSlow(int slow) {
        this.slow = slow;
    }

    public int getDot() {
        return dot;
    }

    public void setDot(int dot) {
        this.dot = dot;
    }

    public int getDotTurns() {
        return dotTurns;
    }

    public void setDotTurns(int dotTurns) {
        this.dotTurns = dotTurns;
    }

    public int getFear() {
        return fear;
    }

    public void setFear(int fear) {
        this.fear = fear;
    }

    public int getBlind() {
        return blind;
    }

    public void setBlind(int blind) {
        this.blind = blind;
    }

    public int getBlindTurns() {
        return blindTurns;
    }

    public void setBlindTurns(int blindTurns) {
        this.blindTurns = blindTurns;
    }

    public int getReturnDamage() {
        return returnDamage;
    }

    public void setReturnDamage(int returnDamage) {
        this.returnDamage = returnDamage;
    }

    public int getReturnDamageTurns() {
        return returnDamageTurns;
    }

    public void setReturnDamageTurns(int returnDamageTurns) {
        this.returnDamageTurns = returnDamageTurns;
    }

    public int getStun() {
        return stun;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    public int getIncreasedDamageTurns() {
        return increasedDamageTurns;
    }

    public void setIncreasedDamageTurns(int increasedDamageTurns) {
        this.increasedDamageTurns = increasedDamageTurns;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelayDot() {
        return delayDot;
    }

    public void setDelayDot(int delayDot) {
        this.delayDot = delayDot;
    }

    public float getIncreasedDamage() {
        return increasedDamage;
    }

    public void setIncreasedDamage(float increasedDamage) {
        this.increasedDamage = increasedDamage;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isHasBeenPlayedThisTurnStun() {
        return hasBeenPlayedThisTurnStun;
    }

    public void setHasBeenPlayedThisTurnStun(boolean hasBeenPlayedThisTurnStun) {
        this.hasBeenPlayedThisTurnStun = hasBeenPlayedThisTurnStun;
    }

    public boolean isHasBeenPlayedLastTurnStun() {
        return hasBeenPlayedLastTurnStun;
    }

    public void setHasBeenPlayedLastTurnStun(boolean hasBeenPlayedLastTurnStun) {
        this.hasBeenPlayedLastTurnStun = hasBeenPlayedLastTurnStun;
    }

    public boolean isHasBeenPlayedThisTurnDot() {
        return hasBeenPlayedThisTurnDot;
    }

    public void setHasBeenPlayedThisTurnDot(boolean hasBeenPlayedThisTurnDot) {
        this.hasBeenPlayedThisTurnDot = hasBeenPlayedThisTurnDot;
    }

    public boolean isAscend() {
        return isAscend;
    }

    public void setAscend(boolean ascend) {
        isAscend = ascend;
    }

    public boolean isCanExplode() {
        return canExplode;
    }

    public void setCanExplode(boolean canExplode) {
        this.canExplode = canExplode;
    }
}

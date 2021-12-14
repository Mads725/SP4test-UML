package Game;

import java.util.Random;

public class Combat {

    Player player;
    Enemy activeEnemy;
    private int combatRound; // Round counter. Even is the players turn. Odd is the enemies turn.
    private int playerSlow=0, enemySlow=0; // Slow is reduced action points.
    private int playerDot=0, enemyDot=0, enemyDot2=0; // Dot is damage over time, damage at the start of the turn.
    private int playerDotTurns=0, enemyDotTurns=0, enemyDotTurns2; // How long the dot will last.
    private int playerReturnDamage=0, enemyReturnDamage=0; // Takes damage when you deal damage.
    private int playerReturnDamageTurns=0, enemyReturnDamageTurns=0; // How many turns you will return damage.
    private int delay=0, delayDot=0; // Delay for playing the same card again.
    private int playerBlindedTurns=0, enemyBlindedTurns=0; // How long you are blinded.
    private int playerDamageIncreaseTurns=0, enemyDamageIncreaseTurns=0; // How long the increased damage lasts.
    private float playerDamageIncrease=0, enemyDamageIncrease=0; // Damage increase multiplies the damage done.
    private boolean playerBlinded=false, enemyBlinded=false; // Chance to cards to miss and not work.
    private boolean playerFeared=false, enemyFeared=false; // Skips turn as you cower in fear.
    private boolean dontPlayThisTurnStun=false, dontPlayNextTurnStun=false, wasPlayedLastTurnStun=false; // Prevents the enemy from using the same stun card repeatedly.
    private boolean dontPlayThisTurnDot=false; // Prevents the enemy from using the same dot card on the same turn
    private boolean descend=false; // Becomes true if ascend has been played, and sets the next enemy card as descend.
    private boolean enemyIsInvisible=false; // Enemy is invisible and cannot take any damage.
    private boolean willExplode=false, hasExploded=false; // Checks if and when explode card will be played.
    private boolean playerStunned=false, enemyStunned=false; // Skips turn as you recover from a stunning blow.

    public Combat(Player activePlayer, Enemy activeEnemy) { // Game.Combat constructor
        this.player = activePlayer;
        this.activeEnemy = activeEnemy;
        this.combatRound = 0;
    }

    public void startCombat() { // Game.Combat start and loop.
        //Initialise Game.Combat Panel
        GameController.frame.setCombatPanel(this);
        GameController.frame.setHandPanel();

        player.shuffleDeck();

        while(player.getCurrentHealth()>0 && activeEnemy.getCurrentHealth()>0) { // Game.Combat loop
            if (combatRound % 2 == 0) { // Game.Player turn start

                player.setCurrentActionPoints(player.getMaxActionPoints()-playerSlow);
                statusEffectsPlayer();
                player.drawHand();

                while (player.getCurrentActionPoints()  >= 0 && activeEnemy.getCurrentHealth()>0) {
                    Card usedCard = player.takeTurn();

                    if (usedCard !=null) {
                        megaLogic(usedCard);
                    }
                }

            } else if (combatRound % 2 == 1) { // Game.Enemy turn start, player turn end.
                activeEnemy.resetPlayedCard();
                activeEnemy.setCurrentActionPoints(activeEnemy.getMaxActionPoints()-enemySlow);

                statusEffectsEnemy();

                while (activeEnemy.getCurrentActionPoints()  > 0) {
                    Card usedEnemyCard = activeEnemy.takeTurn();
                    usedEnemyCard=checkCard(usedEnemyCard);
                    megaLogic(usedEnemyCard);
                }
            } // Game.Enemy turn end.

            combatRound++; // Next round
        }
        //Discard hand and add them to deck
        player.playerCards.addAll(player.playerHand);
        player.playerHand.clear();

        GameController.frame.removeCombatPanel();
    }

    private void megaLogic(Card playedCard) { // Calculates what every card played in the game does
        playerBlinded=false;
        enemyBlinded=false;
        if (combatRound%2 == 0) {
            if (playerBlindedTurns > 0) {
                Random r = new Random();
                int randomNum = r.nextInt(3);
                if (randomNum == 0) {
                    playerBlinded = true;
                    System.out.println(player.getName() + " missed");
                }
            }
        } else if (combatRound%2 == 1) {
            if (enemyBlindedTurns > 0) {
                Random r = new Random();
                int randomNum = r.nextInt(3);
                if (randomNum == 0) {
                    enemyBlinded = true;
                    System.out.println(activeEnemy.getName() + " missed");
                }
            }
        }
        if (playedCard.getDamage() != 0) {
            if (combatRound%2 == 0 && !playerBlinded && !enemyIsInvisible) {

                activeEnemy.removeHealth(playerModifier(playedCard.getElement(), activeEnemy.getElement(), playedCard.getDamage()));
                if (playerReturnDamageTurns>0){
                    player.addHealth(-playerReturnDamage);
                }
            } else if (combatRound%2 == 1 && !enemyBlinded) {
                player.removeHealth(enemyModifier(playedCard.getDamage()));
                if (enemyReturnDamageTurns>0){
                    activeEnemy.addHealth(-enemyReturnDamage);
                }
            }
        }

        if (playedCard.getHeal() != 0) {
            if (combatRound%2 == 0 && !playerBlinded) {
                player.addHealth(playedCard.getHeal());
            } else if (combatRound%2 == 1 && !enemyBlinded) {
                activeEnemy.addHealth(playedCard.getHeal());
            }
        }

        if (playedCard.getSlow() != 0){
            if (combatRound%2 == 0 && !playerBlinded && !enemyIsInvisible){
                if (playedCard.getSlow() > 0) {
                    enemySlow = playedCard.getSlow();
                }else {
                    playerSlow=playedCard.getSlow();
                }
            }else if (combatRound%2 == 1 && !enemyBlinded){
                if (playedCard.getSlow() <0) {
                    enemySlow = playedCard.getSlow();
                }else {
                    playerSlow=playedCard.getSlow();
                }
            }
        }

        if (playedCard.getDot() != 0){
            if (combatRound%2==0 && !playerBlinded && !enemyIsInvisible){
                if (enemyDot != 0) {
                    if (playedCard.getDot() > 0) {
                        enemyDot2 = playedCard.getDot();
                        enemyDotTurns2 = playedCard.getDotTurns();
                    } else {
                        playerDot = playedCard.getDot();
                        playerDotTurns = playedCard.getDotTurns();
                    }
                }
                if (enemyDot == 0) {
                    if (playedCard.getDot() > 0) {
                        enemyDot = playedCard.getDot();
                        enemyDotTurns = playedCard.getDotTurns();
                    } else {
                        playerDot = playedCard.getDot();
                        playerDotTurns = playedCard.getDotTurns();
                    }
                }
            }else if (combatRound%2 == 1 && !enemyBlinded){
                if (playedCard.getDot()<0){
                    enemyDot=playedCard.getDot();
                    enemyDotTurns=playedCard.getDotTurns();
                } else {
                    playerDot=playedCard.getDot();
                    playerDotTurns=playedCard.getDotTurns();
                }
            }
        }

        if (playedCard.getFear() != 0) {
            Random r = new Random();
            int randomNum = r.nextInt(playedCard.getFear());
            if (randomNum == 0) {
                if (combatRound % 2 == 0 && !playerBlinded && !enemyIsInvisible) {
                    enemyFeared = true;
                } else if (combatRound % 2 == 1 && !enemyBlinded) {
                    playerFeared = true;
                }
            } else {
                System.out.println("Fear failed");
            }
        }
        if (playedCard.getStun() != 0) {
            Random r = new Random();
            int randomNum = r.nextInt(playedCard.getStun());
            if (randomNum == 0) {
                if (combatRound % 2 == 0 && !playerBlinded && !enemyIsInvisible) {
                    enemyStunned = true;
                } else if (combatRound % 2 == 1 && !enemyBlinded) {
                    playerStunned = true;
                }
            } else {
                System.out.println("Stun failed");
            }
        }

        if (playedCard.getBlind() != 0) {
                if (combatRound % 2 == 0 && !playerBlinded && !enemyIsInvisible) {
                    enemyBlindedTurns=playedCard.getBlindTurns();
                } else if (combatRound % 2 == 1 && !enemyBlinded) {
                    playerBlindedTurns=playedCard.getBlindTurns();
                }
            }
        if (playedCard.getReturnDamage()!=0){
            if (combatRound % 2 == 0) {
                enemyReturnDamage=playedCard.getReturnDamage();
                enemyReturnDamageTurns=playedCard.getReturnDamageTurns();
            } else if (combatRound % 2 == 1) {
                playerReturnDamage=playedCard.getReturnDamage();
                playerReturnDamageTurns=playedCard.getReturnDamageTurns();
            }
        }

        if (playedCard.isAscend()){
            descend=true;
            delay=playedCard.getDelay();
        }
        if (playedCard.isInvisible()){
            enemyIsInvisible=true;
        }
        if (playedCard.isCanExplode()){
            willExplode=true;
        }
        if (hasExploded){
            System.out.println(activeEnemy.getName()+" exploded itself, dealing 40 damage");
            hasExploded=false;
            activeEnemy.setCurrentHealth(0);
        }
        if (playedCard.getIncreasedDamage()!=0){
            if (combatRound % 2 == 0) {
                playerDamageIncrease = playedCard.getIncreasedDamage();
                playerDamageIncreaseTurns = playedCard.getIncreasedDamageTurns();
            }else if (combatRound % 2 == 1) {
                enemyDamageIncrease = playedCard.getIncreasedDamage();
                enemyDamageIncreaseTurns = playedCard.getIncreasedDamageTurns();
            }
        }
        if (combatRound % 2 == 1) {
            if (playedCard.isHasBeenPlayedLastTurnStun()){
                wasPlayedLastTurnStun=true;
            }
            if (playedCard.isHasBeenPlayedThisTurnStun()){
                dontPlayThisTurnStun=true;
            }

            if (playedCard.isHasBeenPlayedThisTurnDot()){
                dontPlayThisTurnDot=true;
            }

            if (playedCard.getDelay() != 0) {
                delay = playedCard.getDelay();
            }

            if (playedCard.getDelayDot() != 0) {
                delayDot = playedCard.getDelayDot();
            }
        }
        //reduceAction Points
        if (playedCard.getActionPointsCost() != 0) {
            if (combatRound%2 == 0) {
                player.setCurrentActionPoints( player.getCurrentActionPoints() - playedCard.getActionPointsCost() );
            } else if (combatRound%2 == 1) {
                activeEnemy.setCurrentActionPoints( activeEnemy.getCurrentActionPoints() - playedCard.getActionPointsCost() );
                if (!playedCard.getCardName().equals("Shuffle")) {
                    activeEnemy.setCurrentActionPoints(activeEnemy.getCurrentActionPoints() -
                            playedCard.getActionPointsCost());
                }
            }
        }

        System.out.println(playedCard);

    } // -------------------------------------- megaLogic end ----------------------------------------------

    public int playerModifier(ElementType thisElement, ElementType targetElement, int damage){ // Sets player damage modifier
        if (playerDamageIncrease != 0) {
            damage= (int) (damage * playerDamageIncrease);
        }
        if (player.getSwordDamageIncrease() != 0){
            damage= (int) (damage * player.getSwordDamageIncrease());
        }
        if((thisElement.equals(ElementType.WATER) && targetElement.equals(ElementType.FIRE)||
                (thisElement.equals(ElementType.FIRE) && targetElement.equals(ElementType.EARTH))||
                (thisElement.equals(ElementType.EARTH) && targetElement.equals(ElementType.WATER))))
            damage = damage * Balance.ELEMENT_DAMAGE_MODIFIER;

        return damage;
    }

    public int enemyModifier(int damage){
        if (enemyDamageIncrease!=0) {
            damage= (int) (damage * enemyDamageIncrease);
        }
        return damage;
    }

    public void statusEffectsPlayer(){ // Sets the status effects on the player
        playerSlow=0;
        if(playerDotTurns>0) {
            player.addHealth(-playerDot);
            playerDotTurns--;
        }
        if(playerFeared){
            System.out.println("Game.Player is feared");
            player.setCurrentActionPoints(0);
            playerFeared=false;
        }
        if(playerStunned){
            System.out.println("Game.Player is stunned");
            player.setCurrentActionPoints(0);
            playerStunned=false;
        }
        if (enemyIsInvisible){
            System.out.println(activeEnemy.getName()+ " cannot be hit by any card this turn");
        }
        if (enemyReturnDamageTurns>0){
            enemyReturnDamageTurns--;
        }
        if(playerDamageIncreaseTurns==0){
            playerDamageIncrease=0;
        }
        if (playerDamageIncreaseTurns>0){
            playerDamageIncreaseTurns--;
        }
    }

    public void statusEffectsEnemy(){ // Sets the status effects on the enemy
        enemySlow=0;
        if(dontPlayNextTurnStun){
            wasPlayedLastTurnStun=false;
        }
        dontPlayNextTurnStun=false;
        dontPlayThisTurnStun=false;
        dontPlayThisTurnDot=false;
        enemyIsInvisible=false;

        if(enemyDotTurns > 0) {
            activeEnemy.addHealth(-enemyDot);
            enemyDotTurns--;
        }
        if(enemyDotTurns2 > 0) {
            activeEnemy.addHealth(-enemyDot2);
            enemyDotTurns2--;
        }
        if(enemyFeared){
            System.out.println("Game.Enemy is feared");
            activeEnemy.setCurrentActionPoints(0);
            enemyFeared=false;
        }
        if(enemyStunned){
            System.out.println("Game.Enemy is stunned");
            activeEnemy.setCurrentActionPoints(0);
            enemyStunned=false;
        }
        if(playerBlindedTurns>0){
            playerBlindedTurns--;
        }
        if(wasPlayedLastTurnStun){
            dontPlayNextTurnStun=true;
        }

        if (delay>0){
            delay--;
        }

        if (delayDot>0){
            delayDot--;
        }

        if (playerReturnDamageTurns>0){
            playerReturnDamageTurns--;
        }
        if (playerReturnDamageTurns==0){
            playerReturnDamage=0;
        }
        if(enemyDamageIncreaseTurns==0){
            enemyDamageIncrease=0;
        }
        if (enemyDamageIncreaseTurns>0){
            enemyDamageIncreaseTurns--;
        }
    }

    public Card checkCard(Card checkedCard){ // Checks if the enemy card is valid to play or try again.

        while((checkedCard.isHasBeenPlayedLastTurnStun() && dontPlayNextTurnStun) || (checkedCard.isHasBeenPlayedThisTurnStun() && dontPlayThisTurnStun)){
            checkedCard = activeEnemy.takeTurn();
        }
        while((checkedCard.getDelayDot() > 0 && delayDot > 0) || (checkedCard.isHasBeenPlayedThisTurnDot() && dontPlayThisTurnDot)){
            checkedCard = activeEnemy.takeTurn();
        }
        while(checkedCard.getDelay() > 0 && delay>0){
            checkedCard = activeEnemy.takeTurn();
        }
        while(checkedCard.getReturnDamage()!=0 && playerReturnDamage>0){
            checkedCard = activeEnemy.takeTurn();
        }
        while(checkedCard.getHeal() != 0 && activeEnemy.getCurrentHealth() == activeEnemy.getMaxHealth()){
            checkedCard = activeEnemy.takeTurn();
        }
        if(descend){
            checkedCard=new Card(26, ElementType.FIRE, "Descend", 2);
            enemySlow++;
            descend=false;
        }
        if (willExplode && combatRound>12){
            checkedCard=new Card(40, ElementType.FIRE, "Explode", 1) ;
            hasExploded=true;
        }
        return checkedCard;
    }


    // ---------- Getters and setters -----------------

    public Player getPlayer() {
        return player;
    }

    public Enemy getActiveEnemy() {
        return activeEnemy;
    }
}

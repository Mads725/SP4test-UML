import java.util.Random;

public class Combat {

    Player player;
    Enemy activeEnemy;
    private int combatRound; // Round counter. Even is the players turn. Odd is the enemies turn.
    private int playerSlow=0;
    private int enemySlow=0;
    private int playerDot=0;
    private int enemyDot=0;
    private int playerDotTurns=0;
    private int enemyDotTurns=0;
    private int playerBlindedTurns=0;
    private int enemyBlindedTurns=0;
    private int delay=0;
    private int playerReturnDamage=0;
    private int enemyReturnDamage=0;
    private int playerReturnDamageTurns=0;
    private int enemyReturnDamageTurns=0;
    private int playerDamageIncreaseTurns=0;
    private int enemyDamageIncreaseTurns=0;
    private float playerDamageIncrease=0;
    private float enemyDamageIncrease=0;
    private boolean playerFeared=false;
    private boolean enemyFeared=false;
    private boolean playerBlinded=false;
    private boolean enemyBlinded=false;
    private boolean dontPlayThisTurnStun=false;
    private boolean dontPlayNextTurnStun=false;
    private boolean wasPlayedLastTurnStun=false;
    private boolean dontPlayThisTurnDot=false;
    private boolean dontPlayNextTurnDot=false;
    private boolean wasPlayedLastTurnDot=false;
    private boolean descend=false;
    private boolean enemyIsInvisible=false;
    private boolean willExplode=false;
    private boolean hasExploded=false;
    private boolean playerStunned=false;
    private boolean enemyStunned=false;

    public Combat(Player activePlayer, Enemy activeEnemy) {
        this.player = activePlayer;
        this.activeEnemy = activeEnemy;
        this.combatRound = 0;
    }

    public void startCombat() { // Combat start and loop.
        //Initialise Combat Panel
        GameController.frame.setCombatPanel(this);
        GameController.frame.setHandPanel();

        player.shuffleDeck();

        while(player.getCurrentHealth()>0 && activeEnemy.getCurrentHealth()>0) {
            if (combatRound % 2 == 0) {
                System.out.println("Player turn: ");

                player.setCurrentActionPoints(player.getMaxActionPoints()-playerSlow);

                statusEffectsPlayer();
                
                System.out.println("Player health: " + player.getCurrentHealth() + "... Enemy health: " + activeEnemy.getCurrentHealth());

                player.drawHand();

                while (player.getCurrentActionPoints()  >= 0 && activeEnemy.getCurrentHealth()>0) {
                    CombatCard usedCard = player.takeTurn();


                    if (usedCard !=null) {

                        megaLogic(usedCard);
                    }
                }

            } else if (combatRound % 2 == 1) { // Enemy turn start.
                System.out.println("Enemy turn: ");
                activeEnemy.setCurrentActionPoints(activeEnemy.getMaxActionPoints()-enemySlow);

                statusEffectsEnemy();

                while (activeEnemy.getCurrentActionPoints()  >= 0) {
                    CombatCard usedEnemyCard = activeEnemy.takeTurn();
                    usedEnemyCard=checkCard(usedEnemyCard);
                    megaLogic(usedEnemyCard);
                }
            } // Enemy turn end.

            combatRound++;
            //System.out.println("Player health: " + player.getCurrentHealth() + "... Enemy health: " + activeEnemy.getCurrentHealth());
        }

        GameController.frame.removeCombatPanel();
    }

    private void megaLogic(CombatCard playedCard) {
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
        if (playedCard.damage != 0) {
            if (combatRound%2 == 0 && !playerBlinded && !enemyIsInvisible) {

                activeEnemy.removeHealth(playerModifier(playedCard.getElement(), activeEnemy.getElement(), playedCard.damage));
                if (playerReturnDamageTurns>0){
                    player.addHealth(-playerReturnDamage);
                }
            } else if (combatRound%2 == 1 && !enemyBlinded) {
                player.removeHealth(enemyModifier(playedCard.damage));
                if (enemyReturnDamageTurns>0){
                    activeEnemy.addHealth(-enemyReturnDamage);
                }
            }
        }

        if (playedCard.heal != 0) {
            if (combatRound%2 == 0 && !playerBlinded) {
                player.addHealth(playedCard.heal);
            } else if (combatRound%2 == 1 && !enemyBlinded) {
                activeEnemy.addHealth(playedCard.heal);
            }
        }

        if (playedCard.slow != 0){
            if (combatRound%2 == 0 && !playerBlinded && !enemyIsInvisible){
                if (playedCard.slow > 0) {
                    enemySlow = playedCard.slow;
                }else {
                    playerSlow=playedCard.slow;
                }
            }else if (combatRound%2 == 1 && !enemyBlinded){
                if (playedCard.slow <0) {
                    enemySlow = playedCard.slow;
                }else {
                    playerSlow=playedCard.slow;
                }
            }
        }

        if (playedCard.dot != 0){
            if (combatRound%2==0 && !playerBlinded && !enemyIsInvisible){
                if (playedCard.dot>0){
                 enemyDot=playedCard.dot;
                 enemyDotTurns=playedCard.dotTurns;
                } else {
                playerDot=playedCard.dot;
                playerDotTurns=playedCard.dotTurns;
                }
            }else if (combatRound%2 == 1 && !enemyBlinded){
                if (playedCard.dot<0){
                    enemyDot=playedCard.dot;
                    enemyDotTurns=playedCard.dotTurns;
                } else {
                    playerDot=playedCard.dot;
                    playerDotTurns=playedCard.dotTurns;
                }
            }
        }

        if (playedCard.fear != 0) {
            Random r = new Random();
            int randomNum = r.nextInt(playedCard.fear);
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
        if (playedCard.stun != 0) {
            Random r = new Random();
            int randomNum = r.nextInt(playedCard.stun);
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

        if (playedCard.blind != 0) {
                if (combatRound % 2 == 0 && !playerBlinded && !enemyIsInvisible) {
                    enemyBlindedTurns=playedCard.blindTurns;
                } else if (combatRound % 2 == 1 && !enemyBlinded) {
                    playerBlindedTurns=playedCard.blindTurns;
                }
            }
        if (playedCard.returnDamage!=0){
            if (combatRound % 2 == 0) {
                enemyReturnDamage=playedCard.returnDamage;
                enemyReturnDamageTurns=playedCard.returnDamageTurns;
            } else if (combatRound % 2 == 1) {
                playerReturnDamage=playedCard.returnDamage;
                playerReturnDamageTurns=playedCard.returnDamageTurns;
            }
        }

        if (playedCard.hasBeenPlayedLastTurnStun){
            wasPlayedLastTurnStun=true;
        }
        if (playedCard.hasBeenPlayedThisTurnStun){
            dontPlayThisTurnStun=true;
        }
        if (playedCard.hasBeenPlayedLastTurnDot){
            wasPlayedLastTurnDot=true;
        }
        if (playedCard.hasBeenPlayedThisTurnDot){
            dontPlayThisTurnDot=true;
        }

        if (playedCard.isAscend){
            descend=true;
            delay=3;
        }
        if (playedCard.invisible){
            enemyIsInvisible=true;
        }
        if (playedCard.canExplode){
            willExplode=true;
        }
        if (hasExploded){
            System.out.println(activeEnemy.getName()+" exploded itself, dealing 40 damage");
            hasExploded=false;
            activeEnemy.setCurrentHealth(0);
        }
        if (playedCard.increasedDamage!=0){
            if (combatRound % 2 == 0) {
                playerDamageIncrease = playedCard.increasedDamage;
                playerDamageIncreaseTurns = playedCard.increasedDamageTurns;
            }else if (combatRound % 2 == 1) {
                enemyDamageIncrease = playedCard.increasedDamage;
                enemyDamageIncreaseTurns = playedCard.increasedDamageTurns;
            }
        }
        if (playedCard.delay != 0){
            delay=playedCard.delay;
        }

        //reduceAction Points
        if (playedCard.actionPointsCost != 0) {
            if (combatRound%2 == 0) {
                player.setCurrentActionPoints( player.getCurrentActionPoints() - playedCard.actionPointsCost );
            } else if (combatRound%2 == 1) {
                activeEnemy.setCurrentActionPoints( activeEnemy.getCurrentActionPoints() - playedCard.actionPointsCost );
                if (!playedCard.getCardName().equals("Shuffle")) {
                    activeEnemy.setCurrentActionPoints(activeEnemy.getCurrentActionPoints() -
                            playedCard.actionPointsCost);
                }
            }
        }

        System.out.println(playedCard);

    }// megaLogic end

    public int playerModifier(ElementType thisElement, ElementType targetElement, int damage){
        if (playerDamageIncrease!=0) {
            damage= (int) (damage * playerDamageIncrease);
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

    public void statusEffectsPlayer(){
        playerSlow=0;
        if(playerDotTurns>0) {
            player.addHealth(-playerDot);
            playerDotTurns--;
        }
        if(playerFeared){
            System.out.println("Player is feared");
            player.setCurrentActionPoints(0);
            playerFeared=false;
        }
        if(playerStunned){
            System.out.println("Player is stunned");
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

    public void statusEffectsEnemy(){
        enemySlow=0;
        if(dontPlayNextTurnStun){
            wasPlayedLastTurnStun=false;
        }
        if(dontPlayNextTurnDot){
            wasPlayedLastTurnDot=false;
        }
        dontPlayNextTurnStun=false;
        dontPlayThisTurnStun=false;
        dontPlayThisTurnDot=false;
        dontPlayNextTurnDot=false;
        enemyIsInvisible=false;

        if(enemyDotTurns>0) {
            activeEnemy.addHealth(-enemyDot);
            enemyDotTurns--;
        }
        if(enemyFeared){
            System.out.println("Enemy is feared");
            activeEnemy.setCurrentActionPoints(0);
            enemyFeared=false;
        }
        if(enemyStunned){
            System.out.println("Enemy is stunned");
            activeEnemy.setCurrentActionPoints(0);
            enemyStunned=false;
        }
        if(playerBlindedTurns>0){
            playerBlindedTurns--;
        }
        if(wasPlayedLastTurnStun){
            dontPlayNextTurnStun=true;
        }
        if(wasPlayedLastTurnDot){
            dontPlayNextTurnDot=true;
        }
        if (delay>0){
            delay--;
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
    public CombatCard checkCard(CombatCard checkedCard){
        while((checkedCard.hasBeenPlayedLastTurnStun && dontPlayNextTurnStun) || (checkedCard.hasBeenPlayedThisTurnStun && dontPlayThisTurnStun)){
            checkedCard = activeEnemy.takeTurn();
        }
        while((checkedCard.hasBeenPlayedLastTurnDot && dontPlayNextTurnDot) || (checkedCard.hasBeenPlayedThisTurnDot && dontPlayThisTurnDot)){
            checkedCard = activeEnemy.takeTurn();
        }
        while(checkedCard.isAscend && delay>0){
            checkedCard = activeEnemy.takeTurn();
        }
        while(checkedCard.increasedDamage!=0 && delay>0){
            checkedCard = activeEnemy.takeTurn();
        }
        while(checkedCard.returnDamage!=0 && playerReturnDamage>0){
            checkedCard = activeEnemy.takeTurn();
        }
        while(checkedCard.heal != 0 && activeEnemy.getCurrentHealth() == activeEnemy.getMaxHealth()){
            checkedCard = activeEnemy.takeTurn();
        }
        if(descend){
            checkedCard=new CombatCard(26,ElementType.FIRE, "Descend", 2);
            enemySlow++;
            descend=false;
        }
        if (willExplode && combatRound>12){
            checkedCard=new CombatCard(40, ElementType.FIRE, "Explode", 1) ;
            hasExploded=true;
        }
        return checkedCard;
    }
}

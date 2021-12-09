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
    private boolean playerFeared=false;
    private boolean enemyFeared=false;

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
                System.out.println(player.getCurrentActionPoints());
                playerSlow=0;
                if(playerDotTurns>0) {
                    player.setCurrentHealth(player.getCurrentHealth() - playerDot);
                    playerDotTurns--;
                }
                if(playerFeared==true){
                    player.setCurrentActionPoints(0);
                    playerFeared=false;
                }

                player.drawHand();

                while (player.getCurrentActionPoints()  >= 0) {

                    CombatCard usedCard = player.takeTurn();
                    if (usedCard !=null)
                        megaLogic(usedCard);

                }

            } else if (combatRound % 2 == 1) {
                System.out.println("Enemy turn: ");
                if(enemyDotTurns>0) {
                    activeEnemy.setCurrentHealth(activeEnemy.getCurrentHealth() - enemyDot);
                    enemyDotTurns--;
                }
                if(enemyFeared==true){
                    activeEnemy.setCurrentActionPoints(0);
                    enemyFeared=false;
                }
                megaLogic(activeEnemy.takeTurn());
            }
            combatRound++;
            System.out.println("Player health: " + player.getCurrentHealth() + "... Enemy health: " + activeEnemy.getCurrentHealth());
        }

        GameController.frame.removeCombatPanel();
    }

    private void megaLogic(CombatCard playedCard) {


        if (playedCard.damage != 0) {
            if (combatRound%2 == 0) {

                activeEnemy.removeHealth(modifier(playedCard.getElement(), activeEnemy.getElement(), playedCard.damage));

            } else if (combatRound%2 == 1) {
                player.removeHealth(playedCard.damage);
            }
        }

        if (playedCard.heal != 0) {
            if (combatRound%2 == 0) {
                player.addHealth(playedCard.heal);
            } else if (combatRound%2 == 1) {
                activeEnemy.addHealth(playedCard.heal);
            }
        }

        if (playedCard.slow != 0){
            if (combatRound%2==0){
                if (playedCard.slow >0) {
                    enemySlow = playedCard.slow;
                }else {
                    playerSlow=playedCard.slow;
                }
            }else if (combatRound%2 == 1){
                if (playedCard.slow <0) {
                    enemySlow = playedCard.slow;
                }else {
                    playerSlow=playedCard.slow;
                }
            }
        }
        if (playedCard.dot != 0){
            if (combatRound%2==0){
                if (playedCard.dot>0){
                 enemyDot=playedCard.dot;
                 enemyDotTurns=playedCard.dotTurns;
                }
            else {
                playerDot=playedCard.dot;
                playerDotTurns=playedCard.dotTurns;
            }
            }else if (combatRound%2 == 1){
                if (playedCard.dot<0){
                    enemyDot=playedCard.dot;
                    enemyDotTurns=playedCard.dotTurns;
                }
                else {
                    playerDot=playedCard.dot;
                    playerDotTurns=playedCard.dotTurns;
                }
            }
        }
        if (playedCard.fear != 0) {
            Random r = new Random();
            int randomNum = r.nextInt(3);
            if (randomNum == 0) {
                if (combatRound % 2 == 0) {
                    enemyFeared = true;
                } else if (combatRound % 2 == 1) {
                    playerFeared = true;
                }
            }else{
                System.out.println("Fear failed");
            }
        }
        if (playedCard.actionPointsCost != 0) {
            if (combatRound%2 == 0) {
                player.setCurrentActionPoints( player.getCurrentActionPoints() - playedCard.actionPointsCost );
            } else if (combatRound%2 == 1) {
                // Maybe boss actionPoints?

                if (!playedCard.getCardName().equals("Shuffle")) {
                    activeEnemy.setCurrentActionPoints(activeEnemy.getCurrentActionPoints() -
                            playedCard.actionPointsCost);
                }
            }
        }
        System.out.println(playedCard);

    }// megaLogic end



    public int modifier(String thisElement, String targetElement, int damage){
        if((thisElement.equals("WATER") && targetElement.equals("FIRE"))||(thisElement.equals("FIRE") && targetElement.equals("EARTH"))||(thisElement.equals("EARTH") && targetElement.equals("WATER")))
            damage=damage*2;

        return damage;
    }




}

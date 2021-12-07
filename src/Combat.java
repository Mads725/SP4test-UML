public class Combat {

    Player player;
    Enemy activeEnemy;
    private int combatRound; // Round counter. Even is the players turn. Odd is the enemies turn.

    public Combat(Player activePlayer, Enemy activeEnemy) {
        this.player = activePlayer;
        this.activeEnemy = activeEnemy;
        this.combatRound = 0;
    }

    public void startCombat() { // Combat start and loop.

        player.shuffleDeck();

        while(player.getCurrentHealth()>0 && activeEnemy.getCurrentHealth()>0) {
            if (combatRound % 2 == 0) {
                System.out.print("Player turn: ");
                player.setCurrentActionPoints(player.getMaxActionPoints());
                player.drawHand();

                while (player.getCurrentActionPoints()  >= 0) {

                    megaLogic(player.takeTurn());

                }

            } else if (combatRound % 2 == 1) {
                System.out.print("Enemy turn: ");
                megaLogic(activeEnemy.takeTurn());
            }
            combatRound++;
            System.out.println("Player health: " + player.getCurrentHealth() + "... Enemy health: " + activeEnemy.getCurrentHealth());
        }

        // end combat

    }

    private void megaLogic(CombatCard playedCard) {

        if (playedCard.damage != 0) {
            if (combatRound%2 == 0) {
                activeEnemy.removeHealth(playedCard.damage);
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

    }

    /*
    public int modifier(String thisElement, String targetElement, in){
        if(thisElement == "WATER" && Fire




        return int;
    }
    */
}

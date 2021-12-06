import java.util.ArrayList;

public class Combat {

    ArrayList<CombatEntity> activeCombat = new ArrayList<>();
    int combatRound = 0;

    public Combat(CombatEntity activePlayer, CombatEntity activeEnemy) {
        activeCombat.add(activePlayer);
        activeCombat.add(activeEnemy);
    }

    public void startCombat() {

        while(activeCombat.get(0).getCurrentHealth()>0 && activeCombat.get(1).getCurrentHealth()>0) {
            if (combatRound % 2 == 0) {
                while (activeCombat.get(0).currentActionPoints != 0) {
                    megaLogic(activeCombat.get(0).takeTurn());

                }
            } else if (combatRound % 2 == 1) {
                megaLogic(activeCombat.get(1).takeTurn());

            }
            combatRound++;
        }

        // end combat

    }

    public void megaLogic(CombatCard playedCard) {

        if (playedCard.damage != 0) {
            if (combatRound%2 == 0) {
                activeCombat.get(1).removeHealth(playedCard.damage);
            } else if (combatRound%2 == 1) {
                activeCombat.get(0).removeHealth(playedCard.damage);
            }
        }

        if (playedCard.heal != 0) {
            if (combatRound%2 == 0) {
                activeCombat.get(0).addHealth(playedCard.heal);
            } else if (combatRound%2 == 1) {
                activeCombat.get(1).addHealth(playedCard.heal);
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

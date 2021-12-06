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
                // while
                megaLogic(activeCombat.get(0).takeTurn());


                // while end
            } else if (combatRound % 2 == 1) {
                megaLogic(activeCombat.get(1).takeTurn());

            }
            combatRound++;
        }

    }

    public void megaLogic(CombatCard playedCard) {

        if (playedCard.damage != 0) {

        }
        if (playedCard.heal != 0) {

        }
        if (playedCard.strongDamageType != null) {

        }

    }
}

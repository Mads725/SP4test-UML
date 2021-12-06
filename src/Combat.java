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
                activeCombat.get(0).takeTurn();



            } else if (combatRound % 2 == 1) {
                activeCombat.get(1).takeTurn();
            }
            combatRound++;
        }

    }

    public void megaLogic() {

        if ()



    }


}




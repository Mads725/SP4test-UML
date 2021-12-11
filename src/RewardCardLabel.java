import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RewardCardLabel extends CardLabel {
    GameController gc;

    public RewardCardLabel(CombatCard card, GameController gc) {
        super(card);
        this.gc = gc;

    }


    @Override
    public void mouseClicked(MouseEvent e) {


        synchronized (gc) {
            gc.notifyAll();

        }
        if (gc.getLayer() % 5 == 0){
            gc.player.inventory.add(this.card);
        }else {
            gc.player.playerCards.add(this.card);
        }
    }

}

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

//        synchronized (Main.gc) {
//            Main.gc.notifyAll();
//        }
        synchronized (gc) {
            gc.notifyAll();

        }
        gc.player.playerCards.add(this.card);
    }

}

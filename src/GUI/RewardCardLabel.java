package GUI;

import GUI.CardLabel;
import Game.Card;
import Game.GameController;

import java.awt.event.MouseEvent;

public class RewardCardLabel extends CardLabel {
    GameController gc;

    public RewardCardLabel(Card card, GameController gc) {
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

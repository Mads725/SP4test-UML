import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {

    Player player;

    public Panel(Player player) {
        this.player = player;
        setBounds(0, 1200, 1200, 200);
        drawHand(player.playerCards);
    }

    public ArrayList<CardLabel> drawHand(ArrayList<CombatCard> hand) {
        ArrayList<CardLabel> cards = new ArrayList<>();
        int x = 100;
        for (CombatCard card : hand) {
            CardLabel cardLabel = new CardLabel(card);
            cardLabel.setBounds(x, 0, 50, 100);
            cards.add(cardLabel);
            x += 50;
            this.add(cardLabel);
        }
        return cards;
    }

}
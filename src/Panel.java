import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    static ArrayList<CardLabel> hand = new ArrayList<>();
    Player player;

    public Panel(Player player) {
        this.player = player;
        setBounds(0, 1200, 1200, 200);
        Label label = new Label("this is a label");
        add(label);

    }

    public void drawHand() {

        int x = 100;
        for (CardLabel card : hand) {
            card.setBounds(x, 0, 50, 100);
            x += 50;
            this.add(card);
        }
        //return cards;
    }

    public void updateHand() {
        for (CardLabel card : hand) {
            remove(card);
        }
        hand.clear();
        for (CombatCard card : player.playerHand) {
            CardLabel tempCard = new CardLabel(card);
            hand.add(tempCard);
            add(tempCard);
        }
    }

}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Frame extends JFrame {
    Panel panel;
    Player player;

    public Frame(Player player) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.player = player;
        setLayout(null);
        this.setLocationRelativeTo((Component) null);
        this.setVisible(true);
        setResizable(false);
        this.setSize(1200, 800);

        panel = new Panel(player);
        this.add(panel);


    }


    public ArrayList<CardLabel> drawHand(ArrayList<CombatCard> hand) {
        ArrayList<CardLabel> cardLabels = new ArrayList<>();
        int x = 100;
        int size = 50;
        for (CombatCard card : hand) {
            CardLabel cardLabel = new CardLabel(card);
            cardLabel.setBounds(x, 0, size, 100);
            cardLabels.add(cardLabel);
            this.add(cardLabel);
            x += size;
        }
        return cardLabels;
    }

}
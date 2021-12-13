import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayersHandPanel extends JPanel implements ActionListener {
    private static ArrayList<CardLabel> hand = new ArrayList<>();
    private Player player;
    private JButton button;

    public PlayersHandPanel(Player player) {

        this.player = player;
        button = new JButton("End Turn");
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBounds(0, 300, getWidth(), 50);
        button.setSize(1000, 50);
        button.setPreferredSize(new Dimension(1000, 50));
        button.addActionListener(this);
        this.add(button);

    }

    public void updateHand() {

        for (CardLabel card : hand) {
           remove(card);
       }
        hand.clear();
        int index = 0;
        for (CombatCard card : player.playerHand) {
            CardLabel tempCard = new CardLabel(card);
            tempCard.index = index;
            hand.add(tempCard);
            add(tempCard);
            index++;
        }

        drawHand();
    }

    public void drawHand() {

        int x = 100;
        for (CardLabel card : hand) {
            card.setBounds(x, 0, 200, 300);
            x += 200;
            this.add(card);
        }
        //return cards;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {

            player.setCurrentActionPoints(-1);
            synchronized (GameController.player) {
                GameController.player.notifyAll();
            }
        }
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayersHandPanel extends JPanel implements ActionListener {
    static ArrayList<CardLabel> hand = new ArrayList<>();
    Player player;
    JButton button;


    public PlayersHandPanel(Player player) {
        this.player = player;
        button = new JButton("end turn");
        button.setBounds(0, 300, getWidth(), 100);
        button.setSize(1000, 100);
        button.setPreferredSize(new Dimension(1000, 100));
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

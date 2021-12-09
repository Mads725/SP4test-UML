import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Frame extends JFrame {
    PlayersHandPanel panel;
    Player player;
    CombatPanel combatPanel;
    RewardScreen rewardScreen;


    public Frame(Player player) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.player = player;
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1200, 900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.toFront();
        
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

    public void setHandPanel() {
        panel = new PlayersHandPanel(player);
        panel.setBounds(0, getHeight() - 400, getWidth(), 400);
        panel.setPreferredSize(new Dimension(1200, 400));
        this.add(panel);


    }

    public void setRewardScreen(CombatCard card1, CombatCard card2, CombatCard card3, GameController gc) {
        rewardScreen = new RewardScreen(card1,card2,card3,gc);
        rewardScreen.setPreferredSize(new Dimension(1200,1000));
        rewardScreen.setVisible(true);
        rewardScreen.setBounds(0,0,1200,1000);
        this.add(rewardScreen);


    }

    public void setCombatPanel(Combat combat) {
        combatPanel = new CombatPanel(combat);
        //combatPanel.setBounds(0,0,getWidth(),500);
        combatPanel.setSize(new Dimension(getWidth(), 500));
        add(combatPanel);

    }

    public void removeCombatPanel() {
        remove(combatPanel);
    }

    public void removeHandPanel() {
        remove(panel);
    }

    public void removeRewardScreen(){
        remove(rewardScreen);
    }

}
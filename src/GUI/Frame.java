package GUI;

import Game.Card;
import Game.Combat;
import Game.GameController;
import Game.Player;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Player player;
    PlayersHandPanel panel;
    CombatPanel combatPanel;
    RewardScreen rewardScreen;
    NewGamePanel newGameScreen;
    Overview overviewScreen;
    GameController gc;

    public Frame(Player player, GameController gc) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.player = player;
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1200, 900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.toFront();
        this.gc = gc;
        newGameScreen = new NewGamePanel(gc,this);
        add(newGameScreen);
    }

    public void setOverviewScreen() {
        overviewScreen = new Overview(gc);
        add(overviewScreen);
    }

    public void setHandPanel() {
        panel = new PlayersHandPanel(GameController.player);
        panel.setBounds(0, getHeight() - 400, getWidth(), 400);
        panel.setPreferredSize(new Dimension(1200, 400));
        this.add(panel);
    }

    public void setRewardScreen(Card card1, Card card2, Card card3, GameController gc) {
        rewardScreen = new RewardScreen(card1,card2,card3,gc);
        rewardScreen.setPreferredSize(new Dimension(1200,1000));
        rewardScreen.setVisible(true);
        rewardScreen.setBounds(0,0,1200,1000);
        this.add(rewardScreen);
    }

    public void setCombatPanel(Combat combat) {
        combatPanel = new CombatPanel(combat);
        combatPanel.setSize(new Dimension(getWidth(), 500));
        add(combatPanel);
    }

    public void setNewGameScreen(){
        newGameScreen.setVisible(true);
        this.repaint();
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

    // ---------- Getters and setters -----------------

    public PlayersHandPanel getPanel() {
        return panel;
    }

    public NewGamePanel getNewGameScreen() {
        return newGameScreen;
    }

    public Overview getOverviewScreen() {
        return overviewScreen;
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Frame extends JFrame {
    PlayersHandPanel panel;
    Player player;
    CombatPanel combatPanel;

    public Frame(Player player) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.player = player;
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1200, 900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.toFront();

        panel = new PlayersHandPanel(player);
        panel.setBounds(0,getHeight()-400,getWidth(),400);
        panel.setPreferredSize( new Dimension(1200,400));
        this.add(panel);

    }

    public void setCombatPanel(Combat combat) {
        combatPanel = new CombatPanel(combat);
        //combatPanel.setBounds(0,0,getWidth(),500);
        combatPanel.setSize(new Dimension(getWidth(),500));
        add(combatPanel);

    }

    public void removeCombatPanel() {
        remove(combatPanel);
    }
}
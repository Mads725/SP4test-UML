import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OcardPanel extends JPanel {
    
    ArrayList<CombatCard> overviewCards;
    ArrayList<OverviewCardLabel> overviewCardLabel = new ArrayList<>();
    GameController gc;
    public OcardPanel(GameController gc) {
        this.gc = gc;
        this.setBounds(GameController.frame.getWidth()/4, 0, (GameController.frame.getWidth()/4)*3, GameController.frame.getHeight()-150);
        this.setLayout(null);
        this.setBackground(Color.ORANGE);
        

        createCardsOverview();
        drawCardsOverview();

    }

    public void createCardsOverview() {
        this.removeAll();
        overviewCardLabel.removeAll(overviewCardLabel);
        overviewCards = GameController.player.playerCards;
        for (CombatCard c: overviewCards) {
                overviewCardLabel.add(new OverviewCardLabel(c));
                //System.out.println(overviewCardLabel.get(idx));
                //idx++;
            }
    }



    public void drawCardsOverview() {
        
        int x = 50;
        int y = 100;
        int cnt = 0;
        for (OverviewCardLabel oc : overviewCardLabel) {
            oc.setBounds(x, y, 200, 100);
            x += 200;
            cnt++;
            if (cnt % 4 == 0) {
                y += 100;
                x = 50;
            }
            this.add(oc);
        }
    }

}
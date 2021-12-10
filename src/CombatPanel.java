import javax.swing.*;
import java.awt.*;

public class CombatPanel extends JPanel {
    Graphics2D g2d;
    int Width = 1185, Height = 450;
    Combat combat;

    public CombatPanel(Combat combat) {
        //this.setBounds(0,0,1200,450);
        this.combat = combat;

    }

    @Override
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));
        g2d.drawRect(0, 0, Width, 500);

        g2d.drawString("Action points: " + combat.player.getCurrentActionPoints(), 100, 50);

        g2d.drawString(combat.activeEnemy.getName(), Width - 160, 40);
        g2d.drawString("Element: " + combat.activeEnemy.getElement(), Width - 160, 50);

        //g2d.setBackground( Color.gray);
        //g2d.drawString( );


        drawHealthBar(100, 100, combat.player);
        drawHealthBar(Width - 200, 100, combat.activeEnemy);

    }

    public void drawHealthBar(int x, int y, CombatEntity unit) {
        int height = 300;
        g2d.setColor(Color.red);
        g2d.fillRect(x, y, 100, height);
        g2d.setColor(Color.green);
        g2d.fillRect(x, (int) (y + height * ((1 - ((float) unit.getCurrentHealth() / (float) unit.getMaxHealth())))), 100, (int) (height * ((float) unit.getCurrentHealth() / (float) unit.getMaxHealth())));
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(x, y, 100, 300);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("" + unit.getCurrentHealth() + " / " + unit.getMaxHealth(), x + 20, y + height - 20); // x variable to center text (100/100)vs 1/100

        drawPlayedCards(Width- 200, 50);

    }

    public void drawPlayedCards(int x, int y) {

        g2d.drawString("Enemy Played Cards", x, y);
        int y1 = y +10, size =50;
        g2d.setColor(Color.DARK_GRAY);


        for (CombatCard playedCard : combat.activeEnemy.getPlayedCards()) {
            g2d.drawString(playedCard.getCardText(), x,y1);
            if (playedCard.getElement() == ElementType.WATER)
                g2d.setColor(new Color(0, 130, 255));
            else if(playedCard.getElement() == ElementType.EARTH)
                g2d.setColor(Color.green);
            else if(playedCard.getElement() ==ElementType.FIRE)
                g2d.setColor(Color.red);

            g2d.fillRect(x,y1,size,size);

            y1+=y1+10;

        }
    }

}




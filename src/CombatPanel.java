import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CombatPanel extends JPanel {
    Graphics2D g2d;
    int Width = 1185, Height = 450;
    Combat combat;
    BufferedImage enemyImage;

    public CombatPanel(Combat combat) {
        //this.setBounds(0,0,1200,450);
        this.combat = combat;

    }

    @Override
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;

        try {
            g2d.drawImage(combat.activeEnemy.image, Width/2 -512/2, 0, 512,512, this);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        g2d.setStroke(new BasicStroke(10));
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawRect(0, 0, Width, 500);

        g2d.drawString("Action points: " + combat.player.getCurrentActionPoints(), 30, 60);
        String string = "Element: " + combat.activeEnemy.getElement();
        g2d.drawString(string, (int) (Width - 130 - string.length() * 3.5), 50);


        drawHealthBar(30, 100, combat.player);
        drawHealthBar(Width - 130, 100, combat.activeEnemy);
        drawPlayedCards(Width - 350, 100);


    }

    public void drawHealthBar(int x, int y, CombatEntity unit) {
        int height = 300;
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(unit.getName(), x, y);
        g2d.setColor(Color.red);
        g2d.fillRect(x, y + 20, 100, height);
        g2d.setColor(Color.green);
        g2d.fillRect(x, (int) (y + 20 + height * ((1 - ((float) unit.getCurrentHealth() / (float) unit.getMaxHealth())))), 100, (int) (height * ((float) unit.getCurrentHealth() / (float) unit.getMaxHealth())));
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(x, y + 20, 100, 300);
        g2d.drawString("" + unit.getCurrentHealth() + " / " + unit.getMaxHealth(), x + 20, y + height - 20); // x variable to center text (100/100)vs 1/100


    }

    public void drawPlayedCards(int x, int y) {
        g2d.setColor(Color.black);
        g2d.drawString("Enemy Played Cards", x, y);
        int size = 110, y1 = y + 10, width = 150;


        for (CombatCard playedCard : combat.activeEnemy.getPlayedCards()) {
            if (playedCard.getElement() == ElementType.WATER) {

                g2d.setColor(new Color(0, 130, 255));
            } else if (playedCard.getElement() == ElementType.EARTH) {
                g2d.setColor(Color.green);
            } else if (playedCard.getElement() == ElementType.FIRE) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.LIGHT_GRAY);
            }
            g2d.fillRect(x, y1, width, size);

            g2d.setColor(Color.black);
//            g2d.drawString(playedCard.getCardText(), x,y1);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 12));
            g2d.drawRect(x, y1, width, size);

            //Print card name
            g2d.drawString(playedCard.getCardName(), x + 10, y1 + size / 2 - 30);


            //Print the CardText
            String strings[] = playedCard.getCardText().split(" ");
            String line = "";
            ArrayList<String> cardText = new ArrayList<>();
            {

                for (int i = 0; i < strings.length; i++) {
                    String newLine = line + strings[i];
                    if (newLine.length() > 20) { //if text is too long
                        cardText.add(line);
                        line = strings[i] + " ";

                    } else {
                        line = newLine + " ";
                    }
                    if (i == strings.length - 1)
                        cardText.add(line);
                }

                for (int i = 0; i < cardText.size(); i++)
                    g2d.drawString(cardText.get(i), x + 10, y1 + size / 2 + i * 15);

                y1 += y1 + 10 + size;

            }
        }

    }
}




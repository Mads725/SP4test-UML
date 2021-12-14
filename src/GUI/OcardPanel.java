package GUI;

import GUI.OverviewCardLabel;
import Game.Card;
import Game.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class OcardPanel extends JPanel {
    
    private ArrayList<Card> overviewCards;
    private ArrayList<OverviewCardLabel> overviewCardLabel = new ArrayList<>();
    private GameController gc;

    BufferedImage background;

    public OcardPanel(GameController gc) {
        this.gc = gc;
        this.setBounds(GameController.frame.getWidth()/4, 0, (GameController.frame.getWidth()/4)*3, GameController.frame.getHeight()-150);
        this.setLayout(null);
        
        try {
            background = ImageIO.read(getClass().getResource("/resources/background3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createCardsOverview();
        drawCardsOverview();

    }

    public void paintComponent(Graphics G){
        G.drawImage(background,0,0,null);
        G.setFont(new Font("Ariel", Font.BOLD, 40));
        G.drawString("Player Cards", GameController.frame.getWidth()/4 + 10, 55);
    }

    public void createCardsOverview() {
        this.removeAll();
        overviewCardLabel.removeAll(overviewCardLabel);
        overviewCards = GameController.player.playerCards;
        for (Card c: overviewCards) {
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
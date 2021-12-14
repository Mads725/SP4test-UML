package GUI;

import Game.GameController;
import Game.Player;

import javax.swing.*;
import java.awt.*;

public class OhpPanel extends JPanel {
   private GameController gc;
   private Graphics2D g2d;
   private int Width = GameController.frame.getWidth()/4, Height = GameController.frame.getHeight()-150;
   private Player player;

    public OhpPanel(GameController gc) {
        this.gc = gc;
        this.player = GameController.player;
        this.setBounds(0, 0, Width, Height);
    }

    @Override
    public void paint(Graphics g){
        this.player = GameController.player;
        g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Player health: " + player.getCurrentHealth(), 50,50);
        g2d.drawString("Dungeon layer " + gc.getLayer(),50,75);

        drawHealthBar(100,100, player);
       
    }

    public void drawHealthBar(int x, int y, Player player){
        int height = 300;

        g2d.setColor(Color.red);
        g2d.fillRect(x,y,100,height);
        g2d.setColor(Color.green);
        g2d.fillRect(x,(int) (y + height*((1-((float)player.getCurrentHealth()/(float)player.getMaxHealth())))),100,(int) (height*((float)player.getCurrentHealth()/(float)player.getMaxHealth())));
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke( new BasicStroke(5));
        g2d.drawRect(x,y,100,300);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("" + player.getCurrentHealth(),x + 40,y+height-20);

    }

}
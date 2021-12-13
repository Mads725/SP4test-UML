import javax.swing.*;
import java.awt.*;

public class OhpPanel extends JPanel {

    Graphics2D g2d;
    //int Width = 1185, Height = 450;
    int Width = GameController.frame.getWidth()/4, Height = GameController.frame.getHeight()-150;
    Player player;

    public OhpPanel() {
        //this.player = GameController.player;
        //this.setBounds(0,0,1200,450);
        this.player = player;
        this.setBounds(0, 0, Width, Height);
    }

    @Override
    public void paint(Graphics g){
        this.player = GameController.player;
        g2d = (Graphics2D) g;
        //g2d.setStroke( new BasicStroke(5));
        //g2d.drawRect(0,0,Width,500);

        g2d.drawString("Player health: " + player.getCurrentHealth(), 100,50);

        //g2d.drawString(combat.activeEnemy.getName(), Width-160-40,40);
        //g2d.drawString("Element: " + combat.activeEnemy.getElement(), Width -160-40, 50 );

        //g2d.setBackground( Color.gray);
        //g2d.drawString( );


        drawHealthBar(100,100, player);
        //drawHealthBar(Width-200,100, combat.activeEnemy);

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
        g2d.drawString("" + player.getCurrentHealth(),x + 40,y+height-20);

        // g2d.fillRect(x,y+(height-(player.getCurrentHealth()*height)/(player.getMaxHealth()*height)),100,300-height-(player.getCurrentHealth()*height)/(player.getMaxHealth()*height));


    }

}
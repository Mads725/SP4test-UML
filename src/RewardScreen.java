import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RewardScreen extends JPanel {
    RewardCardLabel rewardCard1;
    RewardCardLabel rewardCard2;
    RewardCardLabel rewardCard3;
    BufferedImage img;

    public RewardScreen(CombatCard rewardCard1, CombatCard rewardCard2, CombatCard rewardCard3 , GameController gc) {


        this.rewardCard1 = new RewardCardLabel(rewardCard1, gc);  this.rewardCard1.setBounds(GameController.frame.getWidth()/2-350,200,200,300);
        this.rewardCard2 = new RewardCardLabel(rewardCard2, gc);    this.rewardCard2.setBounds(GameController.frame.getWidth()/2-100,200,200,300);
        this.rewardCard3 = new RewardCardLabel(rewardCard3, gc);    this.rewardCard3.setBounds(GameController.frame.getWidth()/2+150,200,200,300);
        this.setLayout(null);

        JLabel label = new JLabel("Choose a Reward");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 35));
        label.setBounds(GameController.frame.getWidth()/2-150,100,300,100);
        add(label);

        add(this.rewardCard1);
        add(this.rewardCard2);
        add(this.rewardCard3);

        try {
            img = ImageIO.read(getClass().getResource("/resources/background4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void paintComponent(Graphics G){

        G.drawImage(img,0,0,GameController.frame.getWidth()*2,GameController.frame.getHeight(),null);

    }
}

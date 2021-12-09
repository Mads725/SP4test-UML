import javax.swing.*;

public class RewardScreen extends JPanel {
    CardLabel rewardCard1;
    CardLabel rewardCard2;
    CardLabel rewardCard3;

    public RewardScreen(CombatCard rewardCard1, CombatCard rewardCard2, CombatCard rewardCard3 , GameController gc) {
        this.rewardCard1 = new RewardCardLabel(rewardCard1, gc);
        this.rewardCard2 = new RewardCardLabel(rewardCard2, gc);
        this.rewardCard3 = new RewardCardLabel(rewardCard3, gc);
        add(this.rewardCard1);
        add(this.rewardCard2);
        add(this.rewardCard3);

    }


}

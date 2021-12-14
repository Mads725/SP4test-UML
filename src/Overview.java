import javax.swing.*;

public class Overview extends JPanel {

    OcardPanel cardPanel;
    OhpPanel hpPanel;
    ObtnPanel btnPanel;
    GameController gc;

    public Overview(GameController gc) {
        this.gc = gc;

        this.setLayout(null);
        this.setSize(GameController.frame.getWidth(), GameController.frame.getHeight());
        this.setLocation(0, 0);
        this.setVisible(false);


        btnPanel = new ObtnPanel(gc);
        hpPanel = new OhpPanel(gc);
        cardPanel = new OcardPanel(gc);

        this.add(btnPanel);
        this.add(hpPanel);
        this.add(cardPanel);

    }


}
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
        //btnPanel.setBackground(Color.GREEN);

        hpPanel = new OhpPanel();
        //hpPanel.setBackground(Color.BLUE);

        cardPanel = new OcardPanel(gc);
        //cardPanel.setBackground(Color.ORANGE);


        this.add(btnPanel);
        this.add(hpPanel);
        this.add(cardPanel);

    }

    /*
    public Overview(Player player) {

        this.player = player;

        overview = new JFrame();
        overview.setTitle("Overview window");
        overview.setSize(GameController.frame.getWidth(), GameController.frame.getHeight());
        overview.setLocation(GameController.frame.getX(), GameController.frame.getY());

        hpPanel = new JPanel();
        hpPanel.setBounds(0, 0, overview.getWidth()/2, overview.getHeight());
        hpPanel.setBackground(Color.ORANGE);

        cardPanel = new JPanel();
        cardPanel.setBounds(overview.getWidth()/2, 0, overview.getWidth()/2, overview.getHeight());
        cardPanel.setBackground(Color.blue);

        //setHPPanel();


        overview.add(hpPanel);
        overview.add(cardPanel);
        overview.setLayout(null);
        overview.setVisible(false);

    }
*/


}
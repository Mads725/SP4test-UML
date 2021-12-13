import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OcardPanel extends JPanel {

    Player player;
    ArrayList<CombatCard> overviewCards;
    ArrayList<OverviewCardLabel> overviewCardLabel = new ArrayList<>();
    int oldLabelSizeValue = 0;
    GameController gc;
    public OcardPanel(GameController gc) {

        this.setBounds(GameController.frame.getWidth()/4, 0, (GameController.frame.getWidth()/4)*3, GameController.frame.getHeight()-150);
        this.setLayout(null);

        this.setBackground(Color.ORANGE);
        //this.player = player;
        //overviewCards = player.playerCards;


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

        /*
        int idx = 0;
        oldLabelSizeValue = overviewCardLabel.size();
        System.out.println("overviewCardLabel.size() = " + overviewCardLabel.size());
        System.out.println("overviewCards.size() = " + overviewCards.size());

        if (overviewCardLabel.size() < overviewCards.size()) {
            for (int i = overviewCardLabel.size(); i < overviewCards.size(); i++) {
                overviewCardLabel.add(new OverviewCardLabel(overviewCards.get(i)));
                //System.out.println(overviewCardLabel.get(idx));
                //idx++;
            }
        }
        System.out.println("overviewCardLabel.size() = " + overviewCardLabel.size());
*/
    }



    public void drawCardsOverview() {

        //int x = 100;
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


/*
    public void drawCardsOverview() {
        //int x = 100;
        int x = 50;
        int y = 100;
        int cnt = 0;
        for (int i = oldLabelSizeValue; i < overviewCards.size(); i++) {
            overviewCardLabel.get(i).setBounds(x, y, 200, 100);
            x += 200;
            cnt++;
            if (cnt % 4 == 0) {
                y = 100 + (int)(i/4) * 100;
                x = 50;
            }
            this.add(overviewCardLabel.get(i));
        }
        System.out.println("overviewCardLabel.size() efter draw-fkt = " + overviewCardLabel.size());

    }
*/


/*
    Player player;
    //CombatCard card;

    JLabel l = new JLabel();

    public OcardPanel(Player player) {

        //this.setBounds(GameController.frame.getWidth() / 2, 0, GameController.frame.getWidth() / 2, GameController.frame.getHeight() - 150);
        this.player = player;

        l.setOpaque(true);
        l.setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);
        String str = player.playerCards.toString() + "\n";

        //l.setVerticalTextPosition(JLabel.TOP);
        //l.setHorizontalTextPosition(JLabel.TOP);

        l.setText(str);
        l.setSize(200, 300);

    }


    /*
    public OcardPanel(CombatCard card) {
        setOpaque(true);
        setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);
        String string = card.getCardName() + "\n" + card.getCardText() + "\nElement: " + card.getElement() + "\nActionpoints: " + card.actionPointsCost;
        this.card = card;
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
        this.setHorizontalTextPosition(JLabel.CENTER);

        //this.setText(card.getCardName() + "\n" + card.getCardText() + "\n" + card.getElement());
        setText("<html>" + string.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        if (card.getElement() == "WATER")
            setBackground(new Color(0, 110, 255));
        if (card.getElement() == "EARTH")
            setBackground(Color.green);
        if (card.getElement() == "FIRE") {
            setBackground(Color.red);
        }
        this.setSize(200, 300);
        this.setPreferredSize(new Dimension(200,300));
        addMouseListener(this);




    }
    */

}
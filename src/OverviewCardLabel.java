import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class OverviewCardLabel extends JLabel {
    CombatCard card;

    public OverviewCardLabel(CombatCard card) {
        setOpaque(true);
        setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);
        String string = card.getCardName() + "\n" + card.getCardText() + "\nElement: " + card.getElement() + "\nActionpoints: " + card.actionPointsCost;
        this.card = card;
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
        this.setHorizontalTextPosition(JLabel.CENTER);

        
        setText("<html>" + string.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        if (card.getElement() == ElementType.WATER)
            setBackground(new Color(0, 110, 255));
        if (card.getElement() == ElementType.EARTH)
            setBackground(Color.green);
        if (card.getElement() == ElementType.FIRE) {
            setBackground(Color.red);
        }
        
        this.setSize(200, 100);
        this.setPreferredSize(new Dimension(200,100));

    }

}
package GUI;

import Game.Card;
import Game.ElementType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OverviewCardLabel extends JLabel {
    Card card;
    String string;

    public OverviewCardLabel(Card card) {
        setOpaque(true);
        setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);
        if(card.getElement()!=null) {
            string = card.getCardName() + "\n" + card.getCardText() + "\nElement: " + card.getElement() + "\nActionpoints: " + card.getActionPointsCost();
        }else{
            string = card.getCardName() + "\n" + card.getCardText() + "\nActionpoints: " + card.getActionPointsCost();
        }

        this.card = card;
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
        this.setHorizontalTextPosition(JLabel.CENTER);

        
        setText("<html>" + string.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        if (card.getElement() == ElementType.WATER)
            setBackground(new Color(0, 130, 255));
        if (card.getElement() == ElementType.EARTH)
            setBackground(new Color(63, 127, 52));
        if (card.getElement() == ElementType.FIRE) {
            setBackground(Color.red);
        }
        
        this.setSize(200, 100);
        this.setPreferredSize(new Dimension(200,100));

    }

}
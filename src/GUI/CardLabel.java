package GUI;

import Game.Card;
import Game.ElementType;
import Game.GameController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardLabel extends JButton implements MouseListener {
    int sizeX = 100, sizeY = 200;
    int index;
    Card card;

    public CardLabel(Card card) {
        setOpaque(true);
        setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);
        String string="";
        if(card.getElement()!=null) {
            string = card.getCardName() + "\n" + card.getCardText() + "\nElement: " + card.getElement() + "\nActionpoints: " + card.getActionPointsCost();
        } else if (card.getActionPointsCost()>=0) {
            string = card.getCardName() + "\n" + card.getCardText() + "\nActionpoints: " + card.getActionPointsCost();
        }else{
            string = card.getCardName() + "\n" + card.getCardText();
        }
        this.card = card;
        this.setFont(new Font("Arial", Font.BOLD, 18));
        setForeground(Color.BLACK);
        this.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
        this.setHorizontalTextPosition(JLabel.CENTER);

        setText("<html>" + string.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        if (card.getElement() == ElementType.WATER)
            setBackground(new Color(0, 130, 255)); // Light blue
        if (card.getElement() == ElementType.EARTH)
            setBackground(new Color(63, 127, 52));
        if (card.getElement() == ElementType.FIRE) {
            setBackground(Color.red);
        }

        this.setSize(200, 300);
        this.setPreferredSize(new Dimension(200, 300));
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (GameController.player.getCurrentActionPoints() >= card.getActionPointsCost()) {
            GameController.player.setUsedCard(card);
            synchronized (GameController.player) {
                GameController.player.notifyAll();
            }
            GameController.player.removeCardFromHand(index);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
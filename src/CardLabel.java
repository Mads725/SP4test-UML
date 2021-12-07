import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardLabel extends JLabel implements MouseListener {
    int sizeX = 100, sizeY = 200;
    ;

    CombatCard card;

    public CardLabel(CombatCard card) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);
        this.card = card;
        this.setVerticalTextPosition(JLabel.TOP); //set text TOP,CENTER, BOTTOM of imageicon
        this.setHorizontalTextPosition(JLabel.CENTER);

        this.setText(card.getCardName() + "\n" + card.getCardText() + "\n" + card.getElement());

        if (card.getElement() == "WATER")
            setBackground(Color.blue);
        if (card.getElement() == "GRASS")
            setBackground(Color.green);
        if (card.getElement() == "FIRE") {
            setBackground(Color.red);
        }
        setSize(100, 200);
        addMouseListener(this);
        setOpaque(true);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //Do the thing
        Player.setUsedCard(card);
        synchronized (GameController.player) {
            GameController.player.notifyAll();
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
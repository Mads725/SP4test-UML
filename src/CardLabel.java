import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardLabel extends JButton implements MouseListener {
    int sizeX = 100, sizeY = 200;
    int index;

    CombatCard card;

    public CardLabel(CombatCard card) {
        setOpaque(true);
        setVisible(true);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);
        String string="";
        if(card.getElement()!=null) {
            string = card.getCardName() + "\n" + card.getCardText() + "\nElement: " + card.getElement() + "\nActionpoints: " + card.actionPointsCost;
        } else{
            string = card.getCardName() + "\n" + card.getCardText() + "\nActionpoints: " + card.actionPointsCost;

        }
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

    @Override
    public void mouseClicked(MouseEvent e) {

        //Do the thing

        if( GameController.player.getCurrentActionPoints() >= card.actionPointsCost){
        GameController.player.setUsedCard(card);
        synchronized (GameController.player) {
            GameController.player.notifyAll();
        }
        GameController.player.removeCardFromHand(index);}
        else{
            //todo: drawtheActionPoints
            System.out.println("not enough action points");

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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGamePanel extends JPanel implements ActionListener {
GameController gc;
    public NewGamePanel(GameController gc) {
    int s = 300;
    JButton newGameButton = new JButton("New Game");
    newGameButton.setSize(s,100);
    newGameButton.addActionListener(this);
    newGameButton.setBounds((1200-s)/2,800/2,s,100);
    add(newGameButton);

    this.gc = gc;
    //setPreferredSize(new Dimension(1000, 50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Start Game
        GameController.frame.remove(this);
        synchronized (gc){
            gc.notifyAll();
        }
    }
}

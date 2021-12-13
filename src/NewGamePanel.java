import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGamePanel extends JPanel implements ActionListener {

    GameController gc;
    JButton newGameButton;

    public NewGamePanel(GameController gc, Frame frame) {
        setLayout(null);
        int s = 300;
        newGameButton = new JButton("New Game");
        newGameButton.setSize(s, 100);
        newGameButton.addActionListener(this);
        newGameButton.setBounds((1200 - s) / 2, 800 / 2, s, 100);
        add(newGameButton);
        this.setVisible(true);
        this.gc = gc;
        setSize(new Dimension(frame.getWidth(), frame.getHeight()));
        newGameButton.setPreferredSize(new Dimension(s, 100));
        setBackground(Color.lightGray);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Start Game
        //GameController.frame.remove(this);
        synchronized (gc) {
            this.setVisible(false);
            gc.notifyAll();
        }
    }
}

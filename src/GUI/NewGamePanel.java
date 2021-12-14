package GUI;

import Game.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.IOException;

public class NewGamePanel extends JPanel implements ActionListener, TextListener {

    GameController gc;
    JButton newGameButton;
    Image img;
    JTextField textField;
    public NewGamePanel(GameController gc, Frame frame) {
        setLayout(null);
        int s = 300;
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Ariel", Font.PLAIN, 40));
        newGameButton.setSize(s, 100);
        newGameButton.addActionListener(this);
        newGameButton.setBounds((1200 - s) / 2, 800 / 2, s, 100);
        newGameButton.setPreferredSize(new Dimension(s, 100));
        add(newGameButton);
        this.setVisible(true);

        this.gc = gc;
        setSize(new Dimension(frame.getWidth(), frame.getHeight()));
        setBackground(Color.lightGray);

        try {
            img = ImageIO.read(getClass().getResource("/resources/background2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        textField = new JTextField("Player");
        textField.setBounds(500,510,200,25);
       // textField.addTextListener(this);
        add(textField);

    }
    @Override
    public void paintComponent(Graphics G){
        G.drawImage(img,-300,0,getWidth()+600,getHeight(),null);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //when you press on new game there are no other buttons on this screen
        //Start Game
        synchronized (gc) {
            this.setVisible(false);
            gc.notifyAll();
            gc.setPlayerName(textField.getText());
        }
    }

    @Override
    public void textValueChanged(TextEvent e) {
        gc.setPlayerName(textField.getText());
    }
}

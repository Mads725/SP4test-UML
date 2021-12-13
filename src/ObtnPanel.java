import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObtnPanel extends JPanel implements ActionListener {

    JButton nextBtn;
    JButton exitBtn;
    GameController gc;
    public ObtnPanel(GameController gc) {
        this.gc = gc;
        this.setBounds(0, GameController.frame.getHeight()-150, GameController.frame.getWidth(), 150);
        this.setLayout(null);
        this.setBackground(Color.GREEN);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(0, 0, GameController.frame.getWidth()/2, 100);
        nextBtn.setSize((GameController.frame.getWidth()/2), 100);
        nextBtn.setPreferredSize(new Dimension(GameController.frame.getWidth()/2, 100));
        nextBtn.setVerticalAlignment(JButton.CENTER);
        nextBtn.addActionListener(this);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(GameController.frame.getWidth()/2, 0, GameController.frame.getWidth()/2, 100);
        exitBtn.setSize(GameController.frame.getWidth()/2, 100);
        exitBtn.setPreferredSize(new Dimension(GameController.frame.getWidth()/4, 100));
        exitBtn.setVerticalAlignment(JButton.CENTER);
        exitBtn.addActionListener(this);

        this.add(nextBtn);
        this.add(exitBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextBtn) {

            synchronized (gc) {
                GameController.frame.overviewScreen.setVisible(false);
                GameController.frame.repaint();
                gc.notifyAll();
            }
            //GameController.o.overview.setVisible(false);
        } else if (e.getSource() == exitBtn) {

            System.exit(0);

        }

    }
}
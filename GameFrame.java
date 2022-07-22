import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GameFrame(){
        // constructing the frame
        this.add(new GamePanel());
        this.setTitle("HumanVsGoblin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setBackground(Color.black);
        this.setLocationRelativeTo(null);
    }
}

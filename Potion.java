import java.awt.*;
import java.util.Random;

public class Potion extends Rectangle {


    Potion(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, height, width);
    }
}

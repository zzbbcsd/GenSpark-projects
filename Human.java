import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Human extends Rectangle {

    int yVelocity;
    int xVelocity;
    int speed = 10;
    int strength;
    Human(int x, int y, int HUMAN_WIDTH, int HUMAN_HEIGHT){
        super(x,y,HUMAN_WIDTH,HUMAN_HEIGHT);
        Random r = new Random();
        this.strength=1;
    }


    public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_D) {
                setXDirection(speed);
            }
                if(e.getKeyCode()==KeyEvent.VK_A) {
                    setXDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(speed);
                }
    }
    public void keyReleased(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_D) {
                setXDirection(0);
            }
                if(e.getKeyCode()==KeyEvent.VK_A) {
                    setXDirection(0);
                }

    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }
    public void move() {
        y= y + yVelocity;
        x=x+xVelocity;
    }
    public void draw(Graphics g) {
            g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }
    public void drawStrength(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,20));
        g.drawString("Your Strength: " +String.valueOf(strength), 20, 40);

    }
}



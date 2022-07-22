import java.awt.*;
import java.util.Random;

public class Goblin extends Rectangle{
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;
    int strength;
    Goblin(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);
        this.strength = random.nextInt(10);
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    public void move() {
        x += xVelocity*strength;
        y += yVelocity*strength;
    }
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, height, width);
    }
    public void drawGoblinStrength(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,20));
        g.drawString("Current Goblin Strength: " +String.valueOf(strength), 20, 70);

    }
}



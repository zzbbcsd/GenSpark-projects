import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int GOBLIN_DIAMETER = 30;
    static final int HUMAN_WIDTH = 25;

    static final int POTION_DIAMETER = 10;

    static final int HUMAN_HEIGHT = 50;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Human human1;
    Goblin goblin;
    Score score;
    Potion potion;
    boolean gameOn = true;
    GamePanel(){
        newHuman();
        newGoblin();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newGoblin() {
        random = new Random();
        goblin = new Goblin((GAME_WIDTH/2)-(GOBLIN_DIAMETER /2),random.nextInt(GAME_HEIGHT- GOBLIN_DIAMETER), GOBLIN_DIAMETER, GOBLIN_DIAMETER);
    }
    //potion boost human strength
    public void newPotion(){
        potion = new Potion(random.nextInt(GAME_WIDTH- POTION_DIAMETER),random.nextInt(GAME_HEIGHT- POTION_DIAMETER), POTION_DIAMETER, POTION_DIAMETER);
    }
    public void newHuman() {
        human1 = new Human(0,(GAME_HEIGHT/2)-(HUMAN_HEIGHT /2), HUMAN_WIDTH, HUMAN_HEIGHT);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());

        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        human1.draw(g);
        human1.drawStrength(g);
        goblin.draw(g);
        goblin.drawGoblinStrength(g);
        score.draw(g);
        if (potion!=null){
            potion.draw(g);
        }
        if (gameOn==false){
            score.drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation

    }
    public void move() {
        human1.move();
        goblin.move();
    }
    public void checkCollision() {

        //bounce goblin off edges
        // everytime it bounces, a potion might be generated
        if(goblin.y <=0) {
            goblin.setYDirection(-goblin.yVelocity);
            generatePotion();
        }
        if(goblin.y >= GAME_HEIGHT- GOBLIN_DIAMETER) {
            goblin.setYDirection(-goblin.yVelocity);
            generatePotion();
        }
        if((goblin.x<=0) || (goblin.x >= GAME_WIDTH- GOBLIN_DIAMETER)){
            goblin.setXDirection(-goblin.xVelocity);
        }

        //if human intersects with goblin, if the goblin strength is stronger, the human will win, else lose
        if(goblin.intersects(human1)) {
            if (human1.strength>goblin.strength){
            score.scoreboard1++;
            newGoblin();
            human1.strength++;}
            else gameOn=false;

        }

        // if picked up the potion, human strength will increase
        if (potion!=null){
        if(human1.intersects(potion)){
            human1.strength++;
            generatePotion();
        }}

        //stops human from moving outside of the window edges
        if(human1.y<=0)
            human1.y=0;
        if(human1.y >= (GAME_HEIGHT- HUMAN_HEIGHT))
            human1.y = GAME_HEIGHT- HUMAN_HEIGHT;
        if(human1.x<=0)
            human1.x=0;
        if (human1.x >= (GAME_WIDTH-HUMAN_WIDTH))
            human1.x=GAME_WIDTH-HUMAN_WIDTH;
    }

    //generate potion by 1/20 chance
    public void generatePotion(){
        int generateNum = random.nextInt(20);
        if (generateNum==3){
            newPotion();

       ;}
    }

    // game loop
    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(gameOn) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            human1.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            human1.keyReleased(e);
        }
    }
}



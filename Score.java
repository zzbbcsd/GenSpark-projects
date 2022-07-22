import java.awt.*;

public class Score extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int scoreboard1;

    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,30));

        g.drawString("Goblins defeated: " +String.valueOf(scoreboard1), 670, 50);

    }
    public void drawGameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Consolas",Font.PLAIN,50));
        g.drawString("You got eaten by the Goblin, SAD! ", 100, 400);
    }
}

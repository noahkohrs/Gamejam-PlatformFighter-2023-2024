package info3.game.gametimer;

import java.awt.Graphics;
import java.io.IOException;

public class GameTimer {
    private GameTimerView view;
    public static final int FINAL_TIMER=210000;//ms == 3 min30
    int time_left;

    public GameTimer() throws IOException{
        view=new GameTimerView();
        this.time_left=FINAL_TIMER;
    }

    static int getMinute(int time){
        return time/60000; 
    }
    static int GetSeconde(int time){
        return (time%60000)/1000;
    }

    public void tick(long elapsed) {
        time_left--;
    }
    public void showGameTimer(Graphics g){
        view.paint(g, time_left);
    }
}

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

    int getMinute(){
        return time_left/60000; 
    }
    int GetSeconde(){
        return (time_left%60000)/1000;
    }

    boolean isTimeOver(){
        if(time_left<0) return true;
        else{
            return false;
        }
    }

    public void tick(long elapsed) {
        time_left-=elapsed;
    }
    public void showGameTimer(Graphics g){
        view.paint(g,this);
    }
}

package info3.game.gametimer;

import java.awt.Graphics;
import java.io.IOException;
import info3.game.Game;
import info3.game.GameSession;

public class GameTimer {
    private GameTimerView view;
    public static final int FINAL_TIMER = 150000;// ms == 3 min30
    private int time_left;
    public boolean notEqualKills;
    public int equalTime = 5000;
    public int showWinnerTime = 10000;
    public boolean end;

    public GameTimer() throws IOException {
        view = new GameTimerView(this);
        this.time_left = FINAL_TIMER;
    }

    public int getMinute() {
        return time_left / 60000;
    }

    public int GetSeconde() {
        return (time_left % 60000) / 1000;
    }

    public boolean hasEnded(){
        return end;
    }
    boolean isTimeOver() {
        if (time_left <= 0)
            return true;
        else
            return false;
    }

    public void tick(long elapsed) {
        time_left -= elapsed;
        if (isTimeOver()) {
            restart();
            equalTime--;
        }
    }

    public void showGameTimer(Graphics g) {
        view.paint(g);
    }

    private void restart() {
        int killPlayer1 = GameSession.gameSession.player1.kills;
        int killPlayer2 = GameSession.gameSession.player2.kills;
        if (killPlayer1 == killPlayer2) {
            notEqualKills = false;

        } else if (killPlayer1 != killPlayer2) {
            notEqualKills = true;
            end = true;
            GameSession.gameSession.removeEntity(GameSession.gameSession.player1);
            GameSession.gameSession.removeEntity(GameSession.gameSession.player2);
            GameSession.gameSession.removeEntity(GameSession.gameSession.player1.weapon);
            GameSession.gameSession.removeEntity(GameSession.gameSession.player2.weapon);
            if (GameSession.gameSession.restart) {
                try {
                    Game.game.newGameSession();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

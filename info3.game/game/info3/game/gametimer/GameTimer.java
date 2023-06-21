package info3.game.gametimer;

import java.awt.Graphics;
import java.io.IOException;
import info3.game.Game;
import info3.game.GameSession;

public class GameTimer {
    private GameTimerView view;
    public static final int FINAL_TIMER = 8000;// ms == 3 min30
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
            if (end)
                showWinnerTime--;
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
            if (showWinnerTime <= 0) {
                try {
                    Game.m_game_session = new GameSession(Game.game, "level.json", Game.gal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

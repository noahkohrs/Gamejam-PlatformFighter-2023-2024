package info3.game.gametimer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.Game;
import info3.game.GameSession;
import info3.game.entity.Player;
import info3.game.graphics.GameCanvas;

public class GameTimerView {
    BufferedImage[] m_images;
    GameTimer gameTimer;
    int mul = 1;

    int x;
    int y;

    GameTimerView(GameTimer gameTimer) throws IOException {
        m_images = Player.loadSprite("resources/time.png", 1, 1);
        this.gameTimer = gameTimer;
    }

    /**
     * @param g
     * @param gameTimer
     */
    public void paint(Graphics g) {
        GameCanvas c=Game.game.m_canvas;
        FontMetrics fontMetrics;
        x = (Game.game.m_canvas.getWidth() - m_images[0].getWidth() * mul) / 2;
        g.drawImage(m_images[0], x, y, m_images[0].getWidth() * mul, m_images[0].getHeight() * mul, null);
        if (!gameTimer.isTimeOver()) {
            int min = gameTimer.getMinute();
            int sec = gameTimer.GetSeconde();

            g.setColor(Color.BLACK);
            int size = 14;
            g.setFont(new Font("Arial", Font.BOLD, size)); // Définir la police de dessin
            g.drawString("" + min, x + 8, y * mul + size + 8);
            g.drawString("" + sec, x + 32 + 4, y * mul + size + 8);
        } 
        else if(!gameTimer.notEqualKills && gameTimer.equalTime>=0){
            fontMetrics =g.getFontMetrics();
            String message="Last kill wins";
        

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 52));
            g.drawString(message, c.getWidth()/2-fontMetrics.stringWidth(message)/2,c.getHeight()/2);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 52));
            g.drawString(message, c.getWidth()/2-fontMetrics.stringWidth(message)/2-2,c.getHeight()/2-5);
        }
        else if(gameTimer.end){
            Player player1=GameSession.gameSession.player1;
            Player player2=GameSession.gameSession.player2;
            Player playerWithMostKills = player1.kills > player2.kills ? player1 : player2;
            
            String message="Player "+playerWithMostKills.team+" wins";
            fontMetrics =g.getFontMetrics();

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 52));
            g.drawString(message, c.getWidth()/2-fontMetrics.stringWidth(message)/2,c.getHeight()/2);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 52));
            g.drawString(message, c.getWidth()/2-fontMetrics.stringWidth(message)/2-2,c.getHeight()/2-5);
        
        }
        else {
            g.setColor(Color.BLACK);
            int size = 14;
            g.setFont(new Font("Arial", Font.BOLD, size)); // Définir la police de dessin
            g.drawString("0", x + 14, y * mul + size + 8);
            g.drawString("0", x + 32 + 4, y * mul + size + 8);
        }

    }
}

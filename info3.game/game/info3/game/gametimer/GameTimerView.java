package info3.game.gametimer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.Game;
import info3.game.entity.Player;

public class GameTimerView {
    BufferedImage[] m_images;
    int mul = 1;

    int x;
    int y;

    GameTimerView() throws IOException {
        m_images = Player.loadSprite("resources/time3.png", 1, 1);
    }

    /**
     * @param g
     * @param Gametime
     */
    public void paint(Graphics g, int time_left) {
            x = (Game.game.m_canvas.getWidth() - m_images[0].getWidth() * mul) / 2;
            g.drawImage(m_images[0], x, y, m_images[0].getWidth() * mul, m_images[0].getHeight() * mul, null);
            if (time_left > 0) {
                int min = GameTimer.getMinute(time_left);
                int sec = GameTimer.GetSeconde(time_left);

                g.setColor(Color.BLACK);
                int size = 14;
                g.setFont(new Font("Arial", Font.BOLD, size)); // Définir la police de dessin
                g.drawString("" + min, x + 8, y * mul + size + 8);
                g.drawString("" + sec, x + 32 + 4, y * mul + size + 8);
            } else {
                g.setColor(Color.BLACK);
                int size = 14;
                g.setFont(new Font("Arial", Font.BOLD, size)); // Définir la police de dessin
                g.drawString("0", x + 8, y * mul + size + 8);
                g.drawString("0", x + 32 + 4, y * mul + size + 8);
        }

    }
}

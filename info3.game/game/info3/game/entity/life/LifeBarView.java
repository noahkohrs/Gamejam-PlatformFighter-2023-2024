package info3.game.entity.life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.Game;
import info3.game.entity.Player;

public class LifeBarView {
	BufferedImage[] m_images1;
	BufferedImage[] m_images2;
	//position
	static int x1=0; // toujours sur le bord
	int x2;
	int y;

	int mul=3; //grossisement

	LifeBarView() throws IOException{
		m_images1 = Player.loadSprite("resources/LifeBar_l.png", 1, 1);
		m_images2 = Player.loadSprite("resources/LifeBar_r.png", 1, 1);
	}


	public void paint(Graphics g, LifeBar lifeBar) {
		y=Game.game.m_canvas.getHeight()-m_images1[0].getHeight()*mul; //même position
		int ratio=lifeBar.life.ratio();
		if(lifeBar.team==1) {
			change_cloueur(g,ratio,lifeBar.team,m_images1[0],x1,y);
			g.drawImage(m_images1[0], x1, y, m_images1[0].getWidth()*mul, m_images1[0].getHeight()*mul, null);
		}else if(lifeBar.team==2) {
			x2=Game.game.m_canvas.getWidth()-m_images2[0].getWidth()*mul;
			change_cloueur(g,ratio,lifeBar.team,m_images2[0],x2,y);
			g.drawImage(m_images2[0], x2, y, m_images2[0].getWidth()*mul, m_images2[0].getHeight()*mul, null);
		}else {
			throw new RuntimeException("joueur différent de 1 et 2");		
		}

	}


	private void change_cloueur(Graphics g, int ratio, int team, BufferedImage bufferedImage, int x, int y) {
		//choix de la couleur
		if (ratio >= 75) {
	        g.setColor(Color.GREEN);
	    } else if (ratio >= 50) {
	        g.setColor(Color.YELLOW);
	    } else if (ratio >= 25) {
	        g.setColor(Color.ORANGE);
	    } else {
	        g.setColor(Color.RED);
	    }
		int wid=(bufferedImage.getWidth()*mul*ratio)/100;
		if(team==1)g.fillRect(x, y,wid, bufferedImage.getHeight()*mul);
		if(team==2)g.fillRect(x-wid+bufferedImage.getWidth()*mul, y,wid, bufferedImage.getHeight()*mul);

	}
}

package info3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.Game;

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


	public void paint(Graphics g, int ratio, int id) {
		y=Game.game.m_canvas.getHeight()-m_images1[0].getHeight()*mul; //même position

		if(id==1) {
			change_cloueur(g,ratio,id,m_images1[0],x1,y);
			g.drawImage(m_images1[0], x1, y, m_images1[0].getWidth()*mul, m_images1[0].getHeight()*mul, null);
		}else if(id==2) {
			x2=Game.game.m_canvas.getWidth()-m_images2[0].getWidth()*mul;
			change_cloueur(g,ratio,id,m_images2[0],x2,y);
			g.drawImage(m_images2[0], x2, y, m_images2[0].getWidth()*mul, m_images2[0].getHeight()*mul, null);
		}else {
			throw new RuntimeException("joueur différent de 1 et 2");		
		}

	}


	private void change_cloueur(Graphics g, int ratio, int id, BufferedImage bufferedImage, int x, int y) {
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
		if(id==1)g.fillRect(x, y,wid, bufferedImage.getHeight()*mul);
		if(id==2)g.fillRect(x-wid+bufferedImage.getWidth()*mul, y,wid, bufferedImage.getHeight()*mul);

	}
}

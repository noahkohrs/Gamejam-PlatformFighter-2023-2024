package info3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class LifeBar {
	Player player;
	LifeBarView bar;
	int ratio;

	LifeBar(Player p) throws IOException {
		this.player=p;
		ratio=100;
		bar=new LifeBarView();
	}
	
	void paint(Graphics g) {
		player.maxHP=100;
		player.HP=100; // pour les testes
		ratio=(int)((player.HP)*100/player.maxHP);
		bar.paint(g,ratio, player.m_team);
	}

}

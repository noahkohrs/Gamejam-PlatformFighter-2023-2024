package info3.game.entity.life;

import java.awt.Graphics;
import java.io.IOException;

import info3.game.entity.Player;


public class LifeBar {
	private Player player;
	private LifeBarView view;

	public LifeBar(Player player) throws IOException{
		this.player = player;
		this.view=new LifeBarView();
	}
	void showLifeBar(Graphics g) {
		view.paint(g, player.life);
	}

}

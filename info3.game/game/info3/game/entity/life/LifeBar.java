package info3.game.entity.life;

import java.awt.Graphics;
import java.io.IOException;

public class LifeBar {
	public Life life;
	public int team; //1 ou 2
	private LifeBarView view;

	public LifeBar(int team) throws IOException {
		this.life = new Life();
		this.view = new LifeBarView();
		this.team=team;
	}

	public void showLifeBar(Graphics g) {
		view.paint(g, this);
	}

}

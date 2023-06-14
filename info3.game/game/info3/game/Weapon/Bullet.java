package info3.game.Weapon;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.Entity;
import info3.game.Viewport;

public class Bullet extends Entity{
  HitBox hitbox;
  Direction dir = Direction.BOTTOM;

  public Bullet(int x, int y, int width, int height){
    super(x,y,width,height);
    this.hitbox = new HitBox(-5, -5, 128, 128, this);

  }


  public boolean tick() {
    if (!hitbox.inCollision(dir)) {
      m_x += dir.x;
      m_y += dir.y;
      return false;
    }
    else
    {
      return true;
    }
  }

  public void paint(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(m_x, m_y, m_width, m_height);
    hitbox.paint(g);

  }

  public void viewportView(Graphics g, double scale) {
    hitbox.viewportView(g, scale);
    g.setColor(Color.RED);
    g.fillRect(Viewport.OnCamViewX(m_x, scale), Viewport.OnCamViewY(m_y, scale), Viewport.realSize(m_width, scale),
        Viewport.realSize(m_height, scale));

  }
}

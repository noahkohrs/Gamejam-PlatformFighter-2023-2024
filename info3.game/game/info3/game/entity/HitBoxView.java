package info3.game.entity;

import java.awt.Graphics;

import info3.game.Camera;

public class HitBoxView {

    HitBox hitBox;

    public HitBoxView(HitBox hitBox)
    {
        this.hitBox=hitBox;
    }

    public void paint(Graphics g) {
        Camera.drawRect(g, hitBox.getTopX(), hitBox.getTopY(), hitBox.width, hitBox.height);
      }

      
}

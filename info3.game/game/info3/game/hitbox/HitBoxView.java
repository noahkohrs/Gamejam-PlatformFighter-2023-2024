package info3.game.hitbox;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.Camera;

public class HitBoxView {

    HitBox hitBox;

    public HitBoxView(HitBox hitBox)
    {
        this.hitBox=hitBox;
    }

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        Camera.drawRect(g, hitBox.getTopLeftX(), hitBox.getTopLeftY(), hitBox.width, hitBox.height);
      }

}

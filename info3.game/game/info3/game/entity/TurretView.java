package info3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.Camera;

public class TurretView extends EntityView{
    TurretView(BufferedImage[] images, Entity entity) {
        super(images, entity);
        //TODO Auto-generated constructor stub
    }
    public TurretView(String filename, int nrows, int ncols,Turret turret) {
        super(filename,nrows,ncols,turret);
    }
    @Override
    public void tick(long elapsed) {       
    }
    
    @Override
    public void paint(Graphics g){
        entity.hitbox.showHitBox(g);
        Camera.drawEntity(entity, g);
    }
}

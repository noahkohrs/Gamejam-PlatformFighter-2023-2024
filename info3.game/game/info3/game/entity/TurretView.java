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
        //entity.hitbox.showHitBox(g);
        Camera.drawEntity(entity, g);
        showLifeBar(g);
    }

        public void showLifeBar(Graphics g) {
        int lifeBarSize = 60;
        int barX = Camera.centeredCoordinateX(entity) - lifeBarSize/2;
        int barY = Camera.centeredCoordinateY(entity) - 25;
        g.setColor(java.awt.Color.GRAY);
        Camera.fillRect(g, barX, barY, lifeBarSize, 5);
        g.setColor(java.awt.Color.RED);
        lifeBarSize = (int) (lifeBarSize*(((Turret)entity).getLifePercentage()))-2;
        Camera.fillRect(g, barX+1, barY+1, lifeBarSize, 4);
    }
}

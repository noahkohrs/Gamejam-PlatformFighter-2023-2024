package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.Camera;
import info3.game.GameSession;
import info3.game.entity.Block;
import info3.game.entity.DynamicEntity;
import info3.game.entity.TEAM;
import info3.game.weapon.Bullet;
import info3.game.weapon.Weapon;


public class PortalBlock extends DynamicEntity {
    private long teleporterCooldown = 0 ;
    private static PortalBlock prev ;
    PortalBlock linkedPortal ;
    public PortalBlock(int x, int y) throws IOException {
        super(x, y, TEAM.NONE, "resources/blocks/portal.png", 1, 1);
        solid = false ;
        if (prev == null) {
            prev = this ;
        } else {
            linkedPortal = prev ;
            prev.linkedPortal = this ;
            prev = null ;
        }
    }

    @Override
    public String toString() {
        return "PortalBlock";
    }

    @Override
    public
    void tick(long elapsed) {
        if (teleporterCooldown > 0) {
            teleporterCooldown -= elapsed ;
            return ;
        }
        for (DynamicEntity e : GameSession.gameSession.entities) {
            if (e.team != TEAM.NONE && !(e instanceof Weapon) && distanceTo(e)<25) {
                e.x = Camera.centeredCoordinateX(linkedPortal) - e.getWidth()/2; 
                e.y = Camera.centeredCoordinateY(linkedPortal)- e.getHeight()/2 ;
                teleporterCooldown = 1000 ;
                linkedPortal.teleporterCooldown = 1000 ;
            }
        }
    } 
}

package info3.game.entity.blocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Port;

import info3.game.Camera;
import info3.game.GameSession;
import info3.game.entity.Block;
import info3.game.entity.DynamicEntity;
import info3.game.entity.TEAM;
import info3.game.weapon.Bullet;
import info3.game.weapon.Weapon;
import java.awt.Graphics;


public class PortalBlock extends DynamicEntity {
    private long teleporterCooldown = 0 ;
    private static List<PortalBlock> unlinkedPortals ;
    private int id ;
    PortalBlock linkedPortal ;
    private int deltatime;
    public PortalBlock(int x, int y, int id) throws IOException {
        super(x, y, TEAM.NONE, "resources/blocks/portal.png", 2, 2);
        if (unlinkedPortals == null) {
            unlinkedPortals = new ArrayList<PortalBlock>(); ;
        }
        this.id = id ;
        PortalBlock p = linkablePortal() ;
        if (p == null) {
            unlinkedPortals.add(this) ;
        } else {
            linkedPortal = p ;
            p.linkedPortal = this ;
            unlinkedPortals.remove(p) ;
        }
        solid = false ;
    }

    private PortalBlock linkablePortal() {
        for (PortalBlock p : unlinkedPortals) {
            if (p.id == id) {
                return p ;
            }
        }
        return null ;
    }

    @Override
    public String toString() {
        return "PortalBlock";
    }

    @Override
    public void tick(long elapsed) {
        deltatime += elapsed;
        if(deltatime>150){
            deltatime=0;
            view.imageIndex = (view.imageIndex+1)%4;
        }
        if (teleporterCooldown>0){
            teleporterCooldown -= elapsed ;
            return ;
        }
        
        
        for (DynamicEntity e : GameSession.gameSession.entities) {
            if (e.team != TEAM.NONE && !(e instanceof Weapon) && distanceTo(e)<40) {
                e.x = Camera.centeredCoordinateX(linkedPortal) - e.getWidth()/2; 
                e.y = Camera.centeredCoordinateY(linkedPortal)- e.getHeight()/2 ;
                teleporterCooldown = 1000 ;
                linkedPortal.teleporterCooldown = 1000 ;
            }
        }
    } 
}

package info3.level.editor;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Port;

import org.json.JSONObject;


public class PortalBlock extends AnimatedEntity {
    static int maxId ;
    static PortalBlock last ;
    private PortalBlock linked ; 
    int id ;
    public PortalBlock() throws IOException {
        super("resources/blocks/portal.png");
        if (last == null) {
            last = this ;
            maxId++;
            id = maxId ;
            System.out.println("Creating unlinked portal of id = " + id);
        } else {
            last.linked = this ;
            id = last.id ;
            linked = last ;
            last = null ;
            System.out.println("Creating linked portal of id = " + id);
        }
    }
    public PortalBlock(int id) throws IOException {
        super("resources/blocks/portal.png");
        if (id > maxId) {
            maxId = id ;
        } else {
            this.id = id ;
        }
    }

    public void setTags(JSONObject tags) {
        tags.put("id", id);
    }

    @Override
    public String toString() {
        return "PortalBlock";
    }

    // @Override
    // public void paint(Graphics g, int x, int y, float scale) {
    //     super.paint(g, x, y, scale);
    //     if (linked == null) {
    //         g.drawLine(tileRealSize(scale)*x, tileRealSize(scale)*y, tileRealSize(scale), tileRealSize(scale));
    //     }
    // }

    
}

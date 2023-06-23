package info3.level.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Port;

import org.json.JSONObject;

public class PortalBlock extends AnimatedEntity {
    static int maxId;
    static PortalBlock last;
    public PortalBlock linked;
    int id;

    public PortalBlock() throws IOException {
        super("resources/blocks/levelPortal.png");
        if (last == null) {
            last = this;
            maxId++;
            id = maxId;
            System.out.println("Creating unlinked portal of id = " + id);
        } else {
            last.linked = this;
            id = last.id;
            linked = last;
            last = null;
            System.out.println("Creating unlinked portal of id = " + id);
        }
    }

    public PortalBlock(int id) throws IOException {
        super("resources/blocks/levelPortal.png");
        if (id > maxId) {
            maxId = id;
        }
        this.id = id;
    }

    public void setTags(JSONObject tags) {
        tags.put("id", id);
    }

    @Override
    public String toString() {
        return "PortalBlock";
    }

    @Override
    public void paint(Graphics g, int x, int y, float scale) {
        super.paint(g, x, y, scale);
        // Set the color by id to make it easier to see which portal is linked to which
        g.setColor(new Color((id * 40) % 255, Math.abs((255 - id * 27) % 255), (id * 63) % 255));
        g.fillRect(tileRealSize(scale) * x, tileRealSize(scale) * y, tileRealSize(scale), tileRealSize(scale));
    }

}

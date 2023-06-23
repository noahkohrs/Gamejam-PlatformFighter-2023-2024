package info3.level.editor;

import java.awt.Graphics;
import java.io.IOException;

import org.json.JSONObject;

public class VoidBlock extends Block {

    public VoidBlock() throws IOException {
        super("");
    }

    public String toString() {
        return "VoidBlock";
    }

    public JSONObject toJSON() {
        return null;
    }

    @Override
    public void paint(Graphics g, int x, int y, float scale) {
        g.setColor(java.awt.Color.darkGray);
        g.fillRect(tileRealSize(scale) * x, tileRealSize(scale) * y, tileRealSize(scale) - 1, tileRealSize(scale) - 1);
    }

    @Override
    public void paintSelector(Graphics g, int x, int y, float scale) {
        g.setColor(java.awt.Color.darkGray);
        g.fillRect(tileRealSize(scale) * x, tileRealSize(scale) * y, tileRealSize(scale) - 1, tileRealSize(scale) - 1);
    }

}

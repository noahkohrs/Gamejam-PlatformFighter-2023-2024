package info3.level.editor;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Window;
import java.io.IOException;

public class ElementList {
    List<ElementContainer> elems;
    int x;
    int y;
    final int SCALE = 2;

    public ElementList(int x, int y) throws IOException {
        this.x = x;
        this.y = y;

        fillElems();
    }

    void fillElems() throws IOException {
        elems = new ArrayList<ElementContainer>();
        elems.add(new ElementContainer(new VoidBlock(), 0, 0, true));
        elems.add(new ElementContainer(new GrassBlock(), 0, 1));
        elems.add(new ElementContainer(new GroundBlock(), 0, 2));
        elems.add(new ElementContainer(new MovingHorizontalPlatform(), 0, 3));
        elems.add(new ElementContainer(new MovingVerticalPlatform(), 0, 4));
        elems.add(new ElementContainer(new PowerUpBlock(), 0, 5));
        elems.add(new ElementContainer(new MalusBlock(), 0, 6));
        elems.add(new ElementContainer(new SpawnerPoint(), 0, 7));
        elems.add(new ElementContainer(new PortalBlock(-1), 0, 7));
    }

    public ElementContainer select(int x, int y) {
        int i = 0;
        for (ElementContainer elem : elems) {
            if ((Element.tileRealSize(SCALE) * i < x) && (x < (i + 1) * Element.tileRealSize(SCALE))) {
                System.out.println("Selected " + elem.toString());
                return elem;
            }
            i++;
        }
        return null;
    }

    void paint(Graphics g) {
        Graphics g2 = g.create(x, y, getRealWidth(), getRealHeight());
        int i = 0;
        for (ElementContainer elem : elems) {
            elem.paintSelector(g2, i, 0, SCALE);
            i++;
        }
    }

    public int getRealWidth() {
        return SCALE * Element.DEFAULT_SIZE * elems.size() + 1;
    }

    public int getRealHeight() {
        return SCALE * Element.DEFAULT_SIZE + 1;
    }

    public void tick(long elapsed) {
        int winWidth = LevelEditor.levelEditor.m_canvas.getWidth();
        x = winWidth - getRealWidth() - 40;

        int winHeight = LevelEditor.levelEditor.m_canvas.getHeight();
        y = winHeight - getRealHeight() - 40;
    }

}

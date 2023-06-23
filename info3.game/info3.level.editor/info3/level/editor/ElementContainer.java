package info3.level.editor;

import java.awt.Graphics;

import org.json.JSONObject;

public class ElementContainer {
    Element m_element ;
    int m_x ;
    int m_y ;
    boolean displayVoid ;

    public ElementContainer(Element elem, int x, int y) {
        m_element = elem ;
        m_x = x ;
        m_y = y ;
        this.displayVoid = false ;
    }
    public ElementContainer(Element elem, int x, int y, boolean displayVoid) {
        this(elem, x, y);
        this.displayVoid = displayVoid ;
        
    }


    void changeElement(Element elem) {
        m_element = elem ;
    }

    public void paint(Graphics g, int i, int j, float scale) {
        if (!(m_element instanceof VoidBlock && !displayVoid))
            m_element.paint(g, i, j, scale);
            g.setColor(java.awt.Color.black);
        int imgSize = Element.tileRealSize(scale);
        g.drawRect(imgSize*i, imgSize*j, imgSize-1, imgSize-1);
    }
    public void paintSelector(Graphics g, int i, int j, float scale) {
        if (!(m_element instanceof VoidBlock && !displayVoid))
            m_element.paintSelector(g, i, j, scale);
        if (LevelEditor.levelEditor.selected == this)
            g.setColor(java.awt.Color.blue);
        else 
            g.setColor(java.awt.Color.black);
        int imgSize = Element.tileRealSize(scale);
        g.drawRect(imgSize*i, imgSize*j, imgSize-1, imgSize-1);
    }

    public void remove() {
        if (m_element instanceof PortalBlock)
            ((PortalBlock)m_element).linked.linked = null ;
    }

    @Override
    public String toString() {
        return m_element.toString();
    }     
}

package info3.game.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MenuListener implements MouseListener, MouseMotionListener {

    Menu m_menu;

    MenuListener(Menu menu) {
        m_menu = menu;
    }

    public void windowOpened() {
        System.out.println("Window opened");
    }

    public void exit() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
        System.out.println("   modifiers=" + e.getModifiersEx());
        System.out.println("   buttons=" + e.getButton());
        System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
        System.out.println("   modifiers=" + e.getModifiersEx());
        System.out.println("   buttons=" + e.getButton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // do nothing
    }

}

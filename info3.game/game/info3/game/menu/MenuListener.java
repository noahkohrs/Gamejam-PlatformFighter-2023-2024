package info3.game.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;

public class MenuListener implements GameCanvasListener {

    Menu m_menu;

    MenuListener(Menu menu) {
        m_menu = menu;
    }

    @Override
    public void tick(long elapsed) {
        m_menu.tick(elapsed);
    }

    @Override
    public void paint(Graphics g) {
        m_menu.paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // do nothing
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

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    @Override
    public void windowOpened() {
        // do nothing
    }

    @Override
    public void exit() {
        // do nothing
    }

    @Override
    public void endOfPlay(String name) {
        // do nothing
    }

    @Override
    public void expired() {
        // do nothing
    }

}

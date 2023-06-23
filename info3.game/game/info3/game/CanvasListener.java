/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package info3.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.automate.condition.Key;
import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
  Game m_game;
  // int[] IsPressed = new int[]{0, 0, 0, 0};

  CanvasListener(Game game) {
    m_game = game;
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
    System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
    System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
    System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    System.out.println("Mouse entered: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
    System.out.println("Mouse entered: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
    System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
    System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
    System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // System.out.println("Key typed: " + e.getKeyChar() + " code=" +
    // e.getKeyCode());
    // System.out.println("Key typed: " + e.getKeyChar() + " code=" +
    // e.getKeyCode());
  }

  @Override
  public void keyPressed(KeyEvent e) {

    for (Key key : GameSession.gameSession.keys) {
      // a - z
      if (key.name.startsWith(new String(e.getKeyChar() + "").toLowerCase())) {
        key.pressed = true;
      }
      // 0 - 9
      if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57)
          if (key.name.equals("" + (e.getKeyCode() - 48)))
            key.pressed = true;

      // Touches directionnelles
      if ((e.getKeyCode() == KeyEvent.VK_UP) && key.name.equals("FU"))
        key.pressed = true;
      
      if ((e.getKeyCode() == KeyEvent.VK_DOWN) && key.name.equals("FD"))
        key.pressed = true;
      
      if ((e.getKeyCode() == KeyEvent.VK_LEFT) && key.name.equals("FL"))
        key.pressed = true;
      
      if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && key.name.equals("FR"))
        key.pressed = true;
      
      // Autres touches
      
      if ((e.getKeyCode() == KeyEvent.VK_SPACE) && key.name.equals("SPACE"))
        key.pressed = true;
      
      if ((e.getKeyCode() == KeyEvent.VK_ENTER) && key.name.equals("ENTER"))
        key.pressed = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
      GameSession.gameSession.camera.toggleDebugMode();
}

  @Override
  public void keyReleased(KeyEvent e) {

    for (Key key : GameSession.gameSession.keys) {
      // a - z
      if (key.name.startsWith(new String(e.getKeyChar() + "").toLowerCase())) {
        key.pressed = false;
      }
      // 0 - 9
      if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57)
          if (key.name.equals("" + (e.getKeyCode() - 48)))
            key.pressed = false;

      // Touches directionnelles
      if ((e.getKeyCode() == KeyEvent.VK_UP) && key.name.equals("FU"))
        key.pressed = false;
      
      if ((e.getKeyCode() == KeyEvent.VK_DOWN) && key.name.equals("FD"))
        key.pressed = false;
      
      if ((e.getKeyCode() == KeyEvent.VK_LEFT) && key.name.equals("FL"))
        key.pressed = false;
      
      if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && key.name.equals("FR"))
        key.pressed = false;
      
      // Autres touches
      
      if ((e.getKeyCode() == KeyEvent.VK_SPACE) && key.name.equals("SPACE"))
        key.pressed = false;
      
      if ((e.getKeyCode() == KeyEvent.VK_ENTER) && key.name.equals("ENTER"))
        key.pressed = false;

      if(e.getKeyCode()== KeyEvent.VK_T && GameSession.gameSession.gametime.hasEnded())
        GameSession.gameSession.restart=true;
    }
  }

  @Override
  public void tick(long elapsed) {
    m_game.tick(elapsed);
  }

  @Override
  public void paint(Graphics g) {
    m_game.paint(g);
  }

  @Override
  public void windowOpened() {
    m_game.loadMusic();
    // m_game.m_canvas.setTimer(6000);
  }

  @Override
  public void exit() {
  }

  // boolean m_expired;
  @Override
  public void endOfPlay(String name) {
    // if (!m_expired) // only reload if it was a forced reload by timer
    // m_game.loadMusic();
    // m_expired = false;
  }

  @Override
  public void expired() {
    // will force a change of music, after 6s of play
    // System.out.println("Forcing an ealy change of music");
    // m_expired = true;
    // m_game.loadMusic();
  }

}

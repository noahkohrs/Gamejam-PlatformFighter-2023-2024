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
package info3.level.editor;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
  LevelEditor levelEditor;

  CanvasListener(LevelEditor levelEditor) {
    this.levelEditor = levelEditor;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse clicked: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mousePressed(MouseEvent e) {
    System.out.println("Mouse pressed: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
    LevelEditor.levelEditor.updateSelected(e.getX(), e.getY());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    System.out.println("Mouse released: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    System.out.println("Mouse entered: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    System.out.println("Mouse exited: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    System.out.println("Mouse dragged: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
    try {
      LevelEditor.levelEditor.paintingManager(e.getX(), e.getY());
    } catch (InstantiationException e1) {
      // TODO Auto-generated catch block
      System.out.println("AAAAAAA");
    } catch (IllegalAccessException e1) {
      // TODO Auto-generated catch block
      System.out.println("AAAAAAA");
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    System.out.println("Mouse moved: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }


  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Key typed: "+e.getKeyChar()+" code="+e.getKeyCode());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Key pressed: "+e.getKeyChar()+" code="+e.getKeyCode());
    switch (e.getKeyChar()) {
      case '+' : // up
        levelEditor.level.scaleChange = 1.05f;
        break;
      case '-' : // down
        levelEditor.level.scaleChange = 0.95f;
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("Key released: "+e.getKeyChar()+" code="+e.getKeyCode());
    switch (e.getKeyChar()) {
      case '+' : // up
      case '-' : // down
        levelEditor.level.scaleChange = 0;
        break;
      case 's' :
        levelEditor.level.exportJson();
        break;
    }
  }



  @Override
  public void tick(long elapsed) {
    levelEditor.tick(elapsed);
  }

  @Override
  public void paint(Graphics g) {
    levelEditor.paint(g);
  }

  @Override
  public void windowOpened() {
    levelEditor.loadMusic();
//    m_game.m_canvas.setTimer(6000);
  }

  @Override
  public void exit() {
    System.out.println("Exit");
  }

//  boolean m_expired;
  @Override
  public void endOfPlay(String name) {
//    if (!m_expired) // only reload if it was a forced reload by timer
    //levelEditor.loadMusic();
//    m_expired = false;
  }

  @Override
  public void expired() { 
    // will force a change of music, after 6s of play
//    System.out.println("Forcing an ealy change of music");
//    m_expired = true;
//    m_game.loadMusic();    
  }

}

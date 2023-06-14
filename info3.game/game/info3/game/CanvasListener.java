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

import info3.game.entity.Direction;
import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
  Game m_game;
    int[] IsPressed = new int[]{0, 0, 0, 0};

  CanvasListener(Game game) {
    m_game = game;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mousePressed(MouseEvent e) {
    System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    System.out.println("Mouse entered: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
    System.out.println("   modifiers=" + e.getModifiersEx());
    System.out.println("   buttons=" + e.getButton());
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Key typed: " + e.getKeyChar() + " code=" + e.getKeyCode());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Key pressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
    switch (e.getKeyCode()) {
      case KeyEvent.VK_SPACE:
        GameSession.gameSession.camera.toggleDebugMode();
        break;
      case KeyEvent.VK_Z:
        IsPressed[2] = 1;
        break;
      case KeyEvent.VK_D:
        IsPressed[0] = 1;
        break;
        case KeyEvent.VK_Q:
        IsPressed[1] = 1;
        break;
      case KeyEvent.VK_T:
        GameSession.gameSession.player1.takeDamage(10);
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("Key released: " + e.getKeyChar() + " code=" + e.getKeyCode());
     switch(e.getKeyCode()){
      case KeyEvent.VK_Z:
        IsPressed[2] = 0;
        GameSession.gameSession.player1.IsJumping = false;
        break;
      case KeyEvent.VK_D:
        IsPressed[0] = 0;
        break;
        case KeyEvent.VK_Q:
        IsPressed[1] = 0;
        break;
    }
    
  }

  @Override
  public void tick(long elapsed) {
    m_game.tick(elapsed);
    if(IsPressed[0] == 1){
      GameSession.gameSession.player1.SetVelX(2);
      GameSession.gameSession.player1.FaceRight();
    }
    if(IsPressed[1] == 1){
      GameSession.gameSession.player1.SetVelX(2);
      GameSession.gameSession.player1.FaceLeft();
    } 
    if(IsPressed[2] == 1){
        GameSession.gameSession.player1.IsJumping = true;
        GameSession.gameSession.player1.StartJump();
    } if(IsPressed[0] == 0 && IsPressed[1] == 0 && IsPressed[2] == 0)
    {
      GameSession.gameSession.player1.reSetVelX();
     GameSession.gameSession.player1.Idle();
    }
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
    m_game.loadMusic();
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

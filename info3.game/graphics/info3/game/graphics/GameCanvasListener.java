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
package info3.game.graphics;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import info3.game.sound.AudioPlayer;

/*
 * A single listener grouping all the callbacks from the GameCanvas.
 */
public interface GameCanvasListener extends MouseListener, MouseMotionListener, KeyListener {

  /*
   * Called the first time the window becomes visible on the screen.
   * This is the real start of the game, from now on, the execution
   * is solely about reacting to events
   */
  public void windowOpened();

  /*
   * Called at the period specified in GameCanvas.TICK_PERIOD
   */
  public void tick(long elapsed);

  /*
   * Usually called 30 times per second,
   * unless you modified GameCanvas.FPS.
   */
  public void paint(Graphics g);

  /*
   * Last callback, the window has been closed, 
   * this is the end of the game, after this call,
   * the Java Runtime Environment will exit.
   */
  public void exit();
  
  /*
   * The music that was playing stopped playing, because the player 
   * reached the end of the music and looping was not requested.
   */
  public void endOfPlay(String name);

  /*
   * Timer set up on the canvas expired.
   */
  public void expired();

}

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

import java.io.RandomAccessFile;
import info3.game.graphics.GameCanvas;
import info3.game.sound.RandomFileInputStream;

public class Sound {

  public long duration;
  public float volume;

  private GameCanvas canvas;
  private String name;
  private RandomAccessFile file;
  private RandomFileInputStream fis;

  public Sound(GameCanvas canvas, String name, String filename, long duration, float volume) {
    this.canvas = canvas;
    this.name = name;
    this.duration = duration;
    this.volume = volume;

    try {
      this.file = new RandomAccessFile(filename, "r");
      this.fis = new RandomFileInputStream(file);
    } catch (Exception e) {
      // TODO Auto-generated catch blockd
      e.printStackTrace();
    }
  }

  public void playSound() {
    canvas.playSound(name, fis, duration, volume);
  }
}

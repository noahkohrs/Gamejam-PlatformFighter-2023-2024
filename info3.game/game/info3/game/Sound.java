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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import info3.game.graphics.GameCanvas;
public class Sound {
  GameCanvas m_canvas;
  String m_name;
  String m_filename;

  Sound(GameCanvas canvas) {
    m_canvas = canvas;
  }

  public void load(String name, String filename, long duration, float volume) throws IOException {
    m_name = name;
    m_filename = filename;
    File file = new File(filename);
    FileInputStream fis = new FileInputStream(file);
    m_canvas.playSound(name, fis, duration,volume);
  }
}

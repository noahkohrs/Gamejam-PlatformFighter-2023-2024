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
package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import automate.Automate;
import info3.game.Camera;
import info3.game.Game;
import info3.game.GameSession;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Player extends Entity {
  long m_imageElapsed;

  public Player() throws IOException {
    super(10, 10, new Automate(), "resources/winchester-4x6.png", 4, 6);
  }

  /*
   * Simple animation here, the cowbow
   */
  public void tick(long elapsed) {
    m_imageElapsed += elapsed;
    if (m_imageElapsed > 200) {
      m_imageElapsed = 0;
      imageIndex = (imageIndex + 1) % m_images.length;
    }
    moveElapsed += elapsed;
    if (moveElapsed > 24) {
      moveElapsed = 0;
      move("right");
    }
  }

  @Override
  public void paint(Graphics g) {
    BufferedImage img = m_images[imageIndex];
    g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
  }


  @Override
  public void move(String direction) {
    x = (x + 2) % GameSession.gameSession.map.realWidth();
  }

  @Override
  public void wizz() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'wizz'");
  }

  @Override
  public void pop() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'pop'");
  }

}

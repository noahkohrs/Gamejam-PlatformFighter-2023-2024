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
package info3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import info3.game.Camera;
import info3.game.GameSession;
import info3.game.automate.Automate;
import info3.game.entity.life.LifeBar;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Player extends Entity {
  long m_imageElapsed;
  private LifeBar lifeBar;
  

  public Player() throws IOException {
    super(10, 10, new Automate(), "resources/winchester-4x6.png", 4, 6);
    hitbox = new HitBox(12, 8, 22, 35, this);
    this.lifeBar = new LifeBar();
    view = new PlayerView("resources/winchester-4x6.png", 4, 6) ;
  }

  public Player(int team) throws IOException {
    super(10, 10, new Automate(), "resources/winchester-4x6.png", 4, 6);
    view = new PlayerView("resources/winchester-4x6.png", 4, 6);
    this.lifeBar = new LifeBar(team);
    hitbox = new HitBox(12, 8, 22, 35, this);
  }

  public void takeDamage(int ammount)
  {
    lifeBar.life.removeHealth(ammount);
  }

  /*
   * Simple animation here, the cowbow
   */
  public void tick(long elapsed) {
    Movement.Walk(this);
    Movement.jump(this, elapsed);
    System.out.println(hitbox.inCollision(Direction.RIGHT));
    view.tick(elapsed);
    moveElapsed += elapsed;
    if (moveElapsed > 24) {
      moveElapsed = 0;
    }
  }

  @Override
  public void paint(Graphics g) {
    BufferedImage img = getImage();
    Camera.drawImage(g, img, x, y, getWidth(), getHeight());
    hitbox.showHitBox(g);
    lifeBar.showLifeBar(g);
    // OR
    // Camera.drawEntity(this, g);
  }

  

  @Override
  public void move(Direction direction) {
    if (!hitbox.inCollision(direction)) {
      x += direction.x*8;
      y += direction.y*8;
    }
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

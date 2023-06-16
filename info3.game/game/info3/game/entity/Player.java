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

import java.io.IOException;

import info3.game.Camera;
import info3.game.GameSession;
import info3.game.Weapon.Weapon;
import info3.game.automate.Automate;
import info3.game.entity.life.LifeBar;
import info3.game.hitbox.HitBox;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Player extends DynamicEntity {
  long m_imageElapsed;

  public LifeBar lifeBar;
  public Weapon weapon;
  public Direction facingDirection;


  long deltatime;

  public Player() throws IOException {
    this(1);
  }

  public Player(int team) throws IOException {
    super(10, 10, team, "resources/winchester-4x6.png", 4, 6);
    view = new PlayerView("resources/winchester-4x6.png", 4, 6, this);
    this.lifeBar = new LifeBar(team);
    hitbox = new HitBox(12, 8, 22, 35, this);
    weapon = new Weapon(this);
    this.facingDirection = Direction.RIGHT;
  }

  public void takeDamage(int ammount) {
    lifeBar.life.removeHealth(ammount);
  }

  /*
   * Simple animation here, the cowbow
   */
  public void tick(long elapsed) {
    deltatime = elapsed;
    // Movement.Walk(this);
    // Movement.jump(this, elapsed);
    weapon.tick(elapsed);
    //System.out.println(y);
    // if (!hitbox.inCollision(Direction.BOTTOM))
    //   y=(int) (y-PhysicConstant.gravity);
    try {
      this.automate.step(this);
    } catch (Exception e) {
      System.out.println("Normally we should not reach here");
      e.printStackTrace();
    }
    Movement.jump(this, deltatime);
    Movement.Walk(this);
  }

  @Override
  public void move(Direction direction) {
    this.facingDirection = direction;
    if (direction == Direction.RIGHT) {
      this.SetVelX(5);
      this.FaceRight();
    } else if (direction == Direction.LEFT) {
      this.SetVelX(5);
      this.FaceLeft();
    } else if (direction == Direction.UPPER) {
        this.IsJumping = true;
        // this.StartJump();
      }
       else {
        this.reSetVelX();
      }
    }




  @Override
  public void wizz() {
    System.out.println("wizz");
  }

}

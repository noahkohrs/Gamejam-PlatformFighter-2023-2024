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

import java.util.ArrayList;
import java.util.List;
import info3.game.GameSession;
import info3.game.automate.Automate;
import info3.game.entity.blocks.PowerUpBlock;
import info3.game.entity.life.LifeBar;
import info3.game.hitbox.HitBox;
import info3.game.weapon.Weapon;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Player extends DynamicEntity {
  long m_imageElapsed;

  public LifeBar lifeBar;
  public Weapon weapon;
  PowerUpBlock powerUpBlock;
  List<PowerUp> ListPowerUp = new ArrayList<PowerUp>();
  long deltatime;

  public Player() throws IOException {
    this(1);
  }

  public Player(int team) throws IOException {
    super(40, 40, team, Getchar(team) + "PlayerSprite.png", 2, 2);
    view = new PlayerView(Getchar(team) + "PlayerSprite.png", 2, 2, this);
    this.lifeBar = new LifeBar(team);
    hitbox = new HitBox(12, 8, 15, 21, this);
    weapon = new Weapon(this);
    this.facingDirection = Direction.RIGHT;
    jumpAmount = 2;
    jumpCounter = jumpAmount;
  }

  public Player(int team, String filename) throws IOException {
    super(40, 40, team, filename, 4, 6);
    view = new PlayerView(filename, 4, 6, this);
    this.lifeBar = new LifeBar(team);
    hitbox = new HitBox(12, 8, 20, 35, this);
    weapon = new Weapon(this);
    this.facingDirection = Direction.RIGHT;
    jumpAmount = 2;
    jumpCounter = jumpAmount;
  }

  static private String Getchar(int team) {
    if (team == 1) {
      return "resources/Mexicain/";
    } else {
      return "resources/Ingenieur/";
    }
  }

  public void takeDamage(int amount) {
    lifeBar.life.removeHealth(amount);
  }

  /*
   * Simple animation here, the cowbow
   */
  public void tick(long elapsed) {
    timer += elapsed;
    TimerPowerEffect();
    System.out.println(Math.max(PhysicConstant.maxVelX, this.personalValX));
    jumpCooldown -= elapsed;
    deltatime = elapsed;
    try {
      movingDirection = Direction.IDLE;
      this.automate.step(this);
      if (movingDirection.x != 0)
        facingDirection = movingDirection;
      if (facingDirection != movingDirection)
        accelerationX = 0.1;
    } catch (Exception e) {
      System.out.println("Normally we should not reach here");
      e.printStackTrace();
    }
    view.tick(deltatime);
    Movement.Walk(this);
    Movement.affectGravity(this);
  }

  @Override
  public void move(Direction direction) {
    accelerationX += 0.04;
    movingDirection = direction;
    if (direction.y == Direction.UPPER.y)
      Movement.Jump(this);
  }

  @Override
  public void wizz() {
    System.out.println("wizz");
  }

  @Override
  public boolean cell(Direction direction, String category) {
    if (category.equals("P")) {

      Block[] blocksBottom = hitbox.recupBlockMap();

      for (int i = 0; i <= 1; i++) {
        if (blocksBottom[i] != null) {
          if (blocksBottom[i].getClass().getSimpleName().equals("PowerUpBlock")) {
            List<PowerUpBlock> powerUpBlocks = GameSession.gameSession.map.powerUpBlocks;
            for (PowerUpBlock p : powerUpBlocks) {
              if (p.x == blocksBottom[i].x && p.y == blocksBottom[i].y) {
                powerUpBlock = p;
              }
            }
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void pick() {
    PowerUp powerUp = powerUpBlock.powerUp;
    if (powerUp != null) {
      switch (powerUp.name) {
        case "ammo":
          break;
        case "speed":
          personalValX += PhysicConstant.maxVelX / 2;
          ListPowerUp.add(powerUp);
          powerUp.timer = timer;
          break;
        case "shield":
          break;
        case "power":
          break;
      }
      System.out.println(powerUp.name);

      powerUpBlock.deletePowerUp();
    }

  }

  public void TimerPowerEffect() {
    for (PowerUp p : ListPowerUp) {
      // System.out.println(timer - p.timer);
      if (timer - p.timer >= 5000) {
        switch (p.name) {
          case "ammo":
            break;
          case "speed":
            personalValX -= PhysicConstant.maxVelX / 2;
            break;
          case "shield":
            break;
          case "power":
            break;
        }
      }
    }

  }

}

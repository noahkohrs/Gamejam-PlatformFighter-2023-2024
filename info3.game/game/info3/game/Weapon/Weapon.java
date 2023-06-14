package info3.game.Weapon;

import java.awt.Graphics;

import info3.game.Block;
import info3.game.Game;

public class Weapon {

    public final int cooldown; // in tick
    public final int reloadAmmo;
    public int ammo;
    public int currentCooldown;
    public int clips;
    public int damage;

    private Bullet[] bullets;

    public Weapon() {
        this.cooldown = 100;
        this.currentCooldown = 0;
        this.reloadAmmo = 15;
        this.ammo = 15;
        this.clips = 3;
        damage = 25;
        this.bullets = new Bullet[this.ammo];
    }

    public void reload() {
        if (clips-- > 0) {
            ammo = reloadAmmo;
            currentCooldown = 1000;
        }
    }

    public void shoot(int starx, int starty) {
        if (currentCooldown <= 0) {
            if (ammo-- > 0) {
                bullets[ammo] = new Bullet(starx, starty, 5, 5);
            }
            currentCooldown = cooldown;
        }
    }

    public void tick() {
        if (currentCooldown > 0)
            currentCooldown--;
            for (int i = 0; i < this.reloadAmmo; i++) {
                Bullet b = bullets[i];
                if (b != null) {
                    b.tick();
                }
            }
        
    }

    public void paint(Graphics g) {
        for (int i = 0; i < this.reloadAmmo; i++) {
            if (bullets[i] != null) {
                Bullet b = bullets[i];
                b.paint(g);
            }
        }
    }

    public void viewportView(Graphics g, double scale) {
        for (int i = 0; i < this.reloadAmmo; i++) {
            if (bullets[i] != null) {
                Bullet b = bullets[i];
                b.viewportView(g, scale);
            }
        }
    }

}

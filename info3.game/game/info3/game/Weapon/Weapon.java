package info3.game.Weapon;

import java.io.IOException;

import info3.game.entity.Direction;
import info3.game.entity.Entity;
import info3.game.entity.Player;

public class Weapon extends Entity {

    private Player player;

    private final int cooldown; // in ms
    private int currentCooldown;
    private int clips;

    private int damage;

    private final int clipSize;
    private int ammo;

    private Bullet[] bullets;

    public Weapon(Player player) throws IOException {
        super(0, 0, "", 0, 0);
        cooldown = 500;
        clipSize = 15;
        ammo = clipSize;
        clips = 3;
        damage = 25;
        currentCooldown = 0;
        bullets = new Bullet[clipSize];
        this.player = player;
    }

    public Weapon(int cooldown, int clips, int damage, int clipSize, Player player) throws IOException {
        super(0, 0, "", 0, 0);
        this.cooldown = cooldown;
        this.clips = clips;
        this.damage = damage;
        this.clipSize = clipSize;
        this.currentCooldown = 0;
        this.ammo = clipSize;
        bullets = new Bullet[clipSize];
    }

    public void reload() {
        if (clips-- > 0) {
            ammo = clipSize;
            currentCooldown = cooldown;
        }
    }

    private void createBullet(int startx, int starty) {
        try {
            bullets[ammo] = new Bullet(startx, starty, Direction.RIGHT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void shoot() {
        if (currentCooldown <= 0) {
            if (ammo-- > 0) {
                createBullet(player.x, player.y);
            }
            currentCooldown = cooldown;
        }
    }

    public void tick(long elapsed) {
        if (currentCooldown > 0)
            currentCooldown -= elapsed;

    }

    @Override
    public void move(Direction direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
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

    @Override
    public void egg() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'egg'");
    }

    @Override
    public boolean GotPower() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }
}

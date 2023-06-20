package info3.game.weapon;

import java.io.IOException;
import info3.game.Camera;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.Player;

public class Weapon extends DynamicEntity {

    public Player player;

    private final int cooldown; // in ms
    private int currentCooldown;
    private int clips;

    private final int clipSize;
    public int ammo;
    private int damage;

    public Weapon(Player player) throws IOException {
        super(0, 0, player.team);
        cooldown = 500;
        clipSize = 15;
        ammo = clipSize;
        clips = 3;
        damage = 25;
        currentCooldown = 0;
        this.player = player;
        this.view = new WeaponView(this);
    }

    public Weapon(int cooldown, int clips, int damage, int clipSize, Player player) throws IOException {
        super(0, 0, player.team);
        this.cooldown = cooldown;
        this.clips = clips;
        this.damage = damage;
        this.clipSize = clipSize;
        this.currentCooldown = 0;
        this.ammo = clipSize;
        this.player = player;
        this.view = new WeaponView(this);
    }

    public void reload() {
        if (currentCooldown <= 0) {
            if (clips > 0) {
                ammo = clipSize;
                currentCooldown = cooldown;
                clips--;
            }
        }
    }

    public void reset(){
        ammo = clipSize;
        clips = 3;
    }
    private void createBullet(int startx, int starty) {
        try {
            new Bullet(startx, starty, damage, player.facingDirection, player.team);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void shoot() {
        if (player.facingDirection != Direction.IDLE) {
            if (currentCooldown <= 0) {
                if (ammo > 0) {
                    createBullet(Camera.centeredCoordinateX(player), Camera.centeredCoordinateY(player));
                    ammo--;
                }
                currentCooldown = cooldown;
            }
        }
    }

    public void tick(long elapsed) {
        try {
            automate.step(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (currentCooldown > 0)
            currentCooldown -= elapsed;

    }

    @Override
    public void move(Direction direction) {
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void wizz(String Direction) {
        reload();
    }

    @Override
    public void pop() {
        shoot();
    }

    @Override
    public boolean gotPower() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gotPower'");
    }

    @Override
    public void turn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'turn'");
    }

    @Override
    public void egg(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'egg'");
    }

}

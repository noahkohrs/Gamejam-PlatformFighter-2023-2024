package info3.game.entity;

import java.io.IOException;

import info3.game.weapon.Bazooka;
import info3.game.weapon.Rifle;
import info3.game.weapon.Weapon;

public class Engineer extends Player {
    int turretCooldown;
    int bazookaCooldown;

    boolean BazookaUsing;

    public int oldClips;
    public int oldAmmo;

    public Engineer() throws IOException {
        super();
    }

    public Engineer(int team) throws IOException {
        super(team);
    }

    public Engineer(int team, String filename) throws IOException {
        super(team, filename);
    }


    @Override
    public void tick(long elapsed) {
        turretCooldown -= elapsed;
        bazookaCooldown -= elapsed;

        if(weapon instanceof Bazooka && bazookaCooldown<900 && bazookaCooldown>800){
            weapon.createBullet(x, y);
            weapon.kill();
            try{
                this.weapon = new Rifle(this, oldClips, oldAmmo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            };
        super.tick(elapsed);
    }

    @Override
    public void wizz(String Direction) {
        turretCooldown = 1000;
        try {
            new Turret(this.x, this.y, this.team);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pop() {
        bazookaCooldown = 1000;
        try {
            oldClips = weapon.clips;
            oldAmmo = weapon.ammo;
            weapon.kill();
            weapon = new Bazooka(this);
            BazookaUsing = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean gotPower() {
        return turretCooldown <= 0;
    }

    @Override
    public boolean gotStuff() {
        return bazookaCooldown <= 0;
    }

    @Override
    public boolean MyDir(String direction) {
        return facingDirection.equals(Direction.fromString(direction));
    }
}

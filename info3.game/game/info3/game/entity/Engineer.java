package info3.game.entity;

import java.io.IOException;

import info3.game.weapon.Bazooka;

public class Engineer extends Player {
    int turretCooldown;
    int bazookaCooldown;

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
        super.tick(elapsed);
    }

    @Override
    public void egg(Entity entity) {
        turretCooldown = 1000;
        try {
            new Turret(this.x, this.y, this.team);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void pop() {
        bazookaCooldown = 1000;
        try {
            weapon = new Bazooka();
        } catch (IOException e) {
            // TODO Auto-generated catch block
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

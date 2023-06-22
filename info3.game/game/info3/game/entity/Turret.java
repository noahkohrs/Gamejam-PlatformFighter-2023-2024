package info3.game.entity;

import java.io.IOException;

import info3.game.Camera;
import info3.game.GameSession;
import info3.game.entity.blocks.MovingHorizontalPlatform;
import info3.game.hitbox.HitBox;
import info3.game.weapon.Bullet;

public class Turret extends DynamicEntity {
    private int time = 3000;
    int ammo;
    Player ennemy;
    int currentCooldown = 0;

    public Turret(int x, int y, int team) throws IOException {
        super(x, y, team);
        this.ammo = 50;
        if (GameSession.gameSession.player1.team == team) {
            ennemy = GameSession.gameSession.player2;
        } else
            ennemy = GameSession.gameSession.player1;
        hitbox = new HitBox(4, 2, 24, 28, this);
        while (hitbox.inCollision(Direction.BOTTOM))
            this.y -= 1;
        this.facingDirection = Direction.RIGHT;
        view = new TurretView("resources/turret.png", 1, 1, this);
    }

    @Override
    public void tick(long elapsed) {
        jumpCooldown -= elapsed;
        time -= elapsed;
        view.tick(elapsed);
        if (currentCooldown > 0)
            currentCooldown -= elapsed;
        view.tick(elapsed);
        try {
            movingDirection = Direction.IDLE;
            this.automate.step(this);
        } catch (Exception e) {
            System.out.println("Normally we should not reach here");
            e.printStackTrace();
        }
        Movement.affectGravity(this);
    }

    @Override
    public boolean gotPower() {
        return (time > 0 && ammo > 0);
    }

    @Override
    public void wizz(String direction) {
        this.facingDirection = Direction.fromString(direction);
        if (currentCooldown <= 0) {
            if (ammo > 0) {
                try {
                    new Bullet(Camera.centeredCoordinateX(this), Camera.centeredCoordinateY(this), 50,
                            this.facingDirection, this.team);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ammo--;
            }
            currentCooldown = 100;
        }

    }

    @Override
    public boolean cell(Direction direction, String category) {
        return distanceTo(ennemy) <= 500;
    }

    @Override
    public boolean MyDir(String direction) {
        double angle = -Math.atan2(ennemy.y - this.y, ennemy.x - this.x);
        boolean res;
        if(ennemy.y>this.y+10)
            return false;

        if ((angle < Math.PI / 5 && angle >= 0) || (angle <= 0 && ennemy.x > this.x))
            res = direction.equals("E");
        else if (angle < 2 * Math.PI / 5 && angle >= 0)
            res = direction.equals("NE");
        else if (angle < 3 * Math.PI / 5 && angle >= 0)
            res = direction.equals("N");
        else if (angle < 4 * Math.PI / 5 && angle >= 0)
            res = direction.equals("NW");
        else if ((angle < 5 * Math.PI / 5 && angle >= 0) || (angle <= 0 && ennemy.x < this.x))
            res = direction.equals("W");
        else
            res = false;

        return res;
    }
}

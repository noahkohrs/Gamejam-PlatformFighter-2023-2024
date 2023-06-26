package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.hitbox.HitBox;

public class Raptor extends DynamicEntity {

    private int time = 20000;
    private static final int maxTime = 20000;
    Player ennemy;

    public Raptor(int x, int y, int team, Direction direction) throws IOException {
        super(x, y, team);
        if (GameSession.gameSession.player1.team == team) {
            ennemy = GameSession.gameSession.player2;
        } else
            ennemy = GameSession.gameSession.player1;
        jumpAmount = 1;
        jumpCounter = 2;
        hitbox = new HitBox(4, 16, 48, 16, this); // 64-48-4=12
        view = new RaptorView("resources/raptor-2x8.png", 2, 8, this);
        while (hitbox.inCollision(Direction.BOTTOM))
            this.y -= 1;
        this.facingDirection = direction;
    }

    @Override
    public void tick(long elapsed) {
        jumpCooldown -= elapsed;
        time -= elapsed;
        view.tick(elapsed);
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
    public boolean gotPower() {
        return time > 0;
    }

    @Override
    public void wizz(String direction) {
        if(nearestEnemyEntity()==null)
            return;
        nearestEnemyEntity().takeDamage(100);
    }

    @Override
    public boolean cell(Direction direction, String category) {
        if (category.equals("O")) {
            this.x += direction.x;
            boolean res = hitbox.inCollision(direction);
            this.x -= direction.x;
            return res;
        } else {
            if(nearestEnemyEntity()==null)
                return false;
            else if (distanceTo(nearestEnemyEntity()) <= 150) {
                ((RaptorView) this.view).attack = true;
                Movement.Jump(this, 6);

            } else
                ((RaptorView) this.view).attack = false;
            return distanceTo(nearestEnemyEntity()) <= 33;
        }
    }

    double getLifePercentage() {
        return (double) time / (double) maxTime;
    }

    @Override
    public boolean MyDir(String direction) {
        boolean res = this.facingDirection.equals(Direction.fromString(direction));
        return res;
    }

    @Override
    public void takeDamage(int damage) {
        time -= damage*150;
    }
}

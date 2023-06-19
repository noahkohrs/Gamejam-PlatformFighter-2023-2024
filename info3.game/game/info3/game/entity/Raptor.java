package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.hitbox.HitBox;

public class Raptor extends DynamicEntity {

    private int time = 20000;
    Player ennemi;

    public Raptor(int x, int y, int team, Direction direction) throws IOException {
        super(x, y, team, "resources/raptor-2x8.png", 2, 8);
        if (GameSession.gameSession.player1.team == team) {
            ennemi = GameSession.gameSession.player2;
        } else
            ennemi = GameSession.gameSession.player1;
        hitbox = new HitBox(4, 16, 48, 16, this); //64-48-4=12
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
    }

    @Override
    public boolean gotPower() {
        return time > 0;
    }

    @Override
    public void pop() {
        ennemi.takeDamage(25);
    }

    @Override
    public boolean cell(Direction direction, String category) {
        if (category.equals("O")) {
            this.x += direction.x;
            boolean res = hitbox.inCollision(direction);
            this.x -= direction.x;
            return res;
        } else {
            if (distanceTo(ennemi) <= 100)
                ((RaptorView) this.view).attack = true;
            return distanceTo(ennemi) <= 33;
        }
    }

    @Override
    public boolean MyDir(String direction) {
        boolean res = this.facingDirection.equals(Direction.fromString(direction));
        return res;
    }
}

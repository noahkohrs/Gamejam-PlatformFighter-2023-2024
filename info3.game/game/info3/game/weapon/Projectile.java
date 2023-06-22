package info3.game.weapon;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Player;

public abstract class Projectile extends DynamicEntity {

    private Player ennemy;
    private int damage;

    public Projectile(int x, int y, Direction direction, int team, int damage, int speed, String filename, int nrows,
            int ncols) throws IOException {
        super(x, y, team, filename, nrows, ncols);
        this.movingDirection = direction;
        this.damage = damage;
        if (team == GameSession.gameSession.player1.team)
            ennemy = GameSession.gameSession.player2;
        else
            ennemy = GameSession.gameSession.player1;
        this.velX = speed;
        this.velY = speed;
    }

    public void tick(long elapsed) {
        try {
            automate.step(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void move(Direction direction) {
        if (!hitbox.inCollision(movingDirection)) {
            int nextX = x + (int) (movingDirection.x * velX);
            int nextY = y + (int) (movingDirection.y * velY);
            System.out.println("Ennemys team="+ennemy.team);
            if (hitbox.inPlayerVectorCollision(nextX, nextY, movingDirection)) {
                System.out.print("Ouch");
                ennemy.takeDamage(damage);
                kill();
            }
            x = nextX;
            y = nextY;
        } else {
            kill();
        }
    }
}

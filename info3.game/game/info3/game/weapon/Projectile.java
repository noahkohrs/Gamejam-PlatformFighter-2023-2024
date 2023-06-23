package info3.game.weapon;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.entity.Direction;
import info3.game.entity.DynamicEntity;
import info3.game.entity.Player;
import info3.game.entity.Raptor;

public abstract class Projectile extends DynamicEntity {

    private Player ennemy;
    private int damage;

    public Projectile(int x, int y, Direction direction, int team, int damage, int speed, String filename, int nrows,
            int ncols) throws IOException {
        super(x, y, team, filename, nrows, ncols);
        this.movingDirection = direction;
        this.facingDirection = direction;
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
            int nextX = x + (int) (movingDirection.x * velX);
            int nextY = y + (int) (movingDirection.y * velY);
            x = nextX;
            y = nextY;
    }
    
    @Override
    public boolean cell(Direction direction, String category) {
        if (category.equals("O")) {
            this.x += direction.x;
            boolean res = hitbox.inCollision(movingDirection);
            this.x -= direction.x;
            return res;
        } else {
            System.out.println(nearestEnemyEntity().getClass().getSimpleName());
            if(nearestEnemyEntity() instanceof Raptor)
                return distanceTo(nearestEnemyEntity()) <= 50;
            return distanceTo(nearestEnemyEntity()) <= 25;
        }
    }
    @Override
    public void hit(String direction){
        nearestEnemyEntity().takeDamage(damage);
    }
}

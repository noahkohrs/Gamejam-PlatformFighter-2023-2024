package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.hitbox.HitBox;

public class Raptor extends DynamicEntity {

    private int time = 20000; // 30sec
    Player ennemi;

    public Raptor(int x, int y, int team, String filename, int nrows, int ncols) throws IOException {
        super(x, y, team, filename, nrows, ncols);
        if (GameSession.gameSession.player1.team == team) {
            ennemi = GameSession.gameSession.player2;
        } else
            ennemi = GameSession.gameSession.player1;
        hitbox = new HitBox(12, -2, 22, 35, this);
        view = new RaptorView(filename, 2, 8, this);
    }

    @Override
    public void tick(long elapsed) {
        time -= elapsed;
        view.tick(elapsed);
        affectTor();
        // System.out.println(y);
        if (!hitbox.inCollision(Direction.BOTTOM))
            y = (int) (y - PhysicConstant.gravity);
        try {
            this.automate.step(this);
        } catch (Exception e) {
            System.out.println("Normally we should not reach here");
            e.printStackTrace();
        }
    }

    @Override
    public void move(Direction direction) {
        ((RaptorView)this.view).direction=direction;
        if (direction == Direction.RIGHT) {
            this.SetVelX(5);
            this.FaceRight();
        }
        if (direction == Direction.LEFT) {
            this.SetVelX(5);
            this.FaceLeft();
        }

        if (!hitbox.inCollision(direction)) {

            if (direction == Direction.UPPER) {
                this.IsJumping = true;
                this.StartJump();
                Movement.jump(this, 1);
                y--;
                // Movement.Walk(this);
            } else {
                x += direction.x * 3;
                y += direction.y;
            }
        }
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
        if(category.equals("O")){
        return hitbox.inCollision(direction);
        }
        else{
            int nextX = x + direction.x * 8;
            int nextY = y + direction.y ;
            if(hitbox.vectorInPLayerCollision(nextX*2,nextY+10,direction))
                ((RaptorView)this.view).attack=true;
            return hitbox.vectorInPLayerCollision(nextX,nextY,direction);
        }
    }
}

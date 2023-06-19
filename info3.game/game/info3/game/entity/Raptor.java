package info3.game.entity;

import java.io.IOException;

import info3.game.GameSession;
import info3.game.hitbox.HitBox;

public class Raptor extends DynamicEntity {

    private int time = 20000;
    Player ennemi;
    Direction movingDirection;
    public Raptor(int x, int y, int team, String filename, int nrows, int ncols,Direction direction) throws IOException {
        super(x, y, team, filename, nrows, ncols);
        if (GameSession.gameSession.player1.team == team) {
            ennemi = GameSession.gameSession.player2;
        } else
            ennemi = GameSession.gameSession.player1;
        hitbox = new HitBox(4, 16, 48, 16, this);
        view = new RaptorView(filename, 2, 8, this);
        this.movingDirection=direction;
    }

    @Override
    public void tick(long elapsed) {
        time -= elapsed;
        view.tick(elapsed);
        affectTor();
        try {
            Direction prevDir = facingDirection;
            this.facingDirection = Direction.IDLE;
            this.automate.step(this);
            if (facingDirection != prevDir)
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
        ((RaptorView) this.view).direction = direction;
        // if (direction == Direction.RIGHT || direction == Direction.LEFT)
        // this.facingDirection = direction;

        // if (!hitbox.inCollision(direction)) {
        // x += direction.x * 3;
        // }
        accelerationX += 0.04;
        facingDirection = direction;
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
            if (distanceTo(ennemi)<=100)
                ((RaptorView) this.view).attack = true;
            return distanceTo(ennemi)<=33;
        }
    }

    @Override
    public boolean MyDir(String direction){
        System.out.println("Movind Direction"+this.movingDirection.toString()+"and MyDir(direction)"+Direction.fromString(direction).toString());
        boolean res=this.movingDirection.equals(Direction.fromString(direction));
        return res;
    }
}

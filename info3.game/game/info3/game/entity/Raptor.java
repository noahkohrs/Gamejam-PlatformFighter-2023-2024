package info3.game.entity;

import java.io.IOException;

import info3.game.hitbox.HitBox;

public class Raptor extends DynamicEntity {

    private int time=20000; //30sec

    public Raptor(int x, int y, int team, String filename, int nrows, int ncols) throws IOException {
        super(x, y, team, filename, nrows, ncols);
        hitbox = new HitBox(12, -2, 22, 35, this);
        view = new RaptorView(filename,2,8,this);
    }

    @Override
    public void tick(long elapsed) {
        time-=elapsed;
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
                x += direction.x;
                y += direction.y;
            }
        }
    }

    @Override
    public boolean gotPower(){
        return time>0;
    }

    @Override
    public void pop(){
        
    }
}

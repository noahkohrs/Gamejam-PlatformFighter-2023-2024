package info3.game.entity;

import java.io.IOException;

import info3.game.Camera;

public class Mexican extends Player {
    int raptorCooldown;

    public Mexican() throws IOException {
        super();
    }

    public Mexican(int team) throws IOException {
        super(team);
    }

    public Mexican(int team, String filename) throws IOException {
        super(team, filename);
    }

    @Override
    public void tick(long elapsed) {
        raptorCooldown-=elapsed;
        super.tick(elapsed);
    }

    @Override
    public void wizz(String direction) {
        raptorCooldown = 1000;
        try {
            new Turret(this.x,this.y,this.team);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
    }

    @Override
    public boolean gotPower() {
        return raptorCooldown <= 0;
    }

    @Override
    public boolean MyDir(String direction){
        return facingDirection.equals(Direction.fromString(direction));
    }
}

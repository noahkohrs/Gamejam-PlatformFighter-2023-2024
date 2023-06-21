package info3.game.entity;

import java.io.IOException;

public class Engineer extends Player{
    int raptorCooldown;
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
        raptorCooldown-=elapsed;
        super.tick(elapsed);
    }

    @Override
    public void egg(Entity entity) {
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

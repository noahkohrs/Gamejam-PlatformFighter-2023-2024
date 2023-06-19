package info3.game.entity;

import java.io.IOException;

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
        System.out.println("wizz mexican avec movingDirection:"+facingDirection.toString());
        raptorCooldown = 1000;
        try {
            new Raptor(this.x, this.y, this.team, "resources/raptor-2x8.png", 2, 8,facingDirection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean gotPower() {
        return raptorCooldown <= 0;
    }

    @Override
    public boolean MyDir(String direction){
        System.out.println(direction+" "+facingDirection.toString());
        return facingDirection.equals(Direction.fromString(direction));
    }
}

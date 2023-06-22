package info3.game.entity;

import java.io.IOException;

import info3.game.Camera;

public class Mexican extends Player {
    int raptorCooldown;

    public Mexican(int team) throws IOException {
        super(team);
        this.view=new PlayerView("resources/Mexicain/PlayerSprite.png", 3, 2, this);
    }


    @Override
    public void tick(long elapsed) {
        raptorCooldown -= elapsed;
        super.tick(elapsed);
    }

    @Override
    public void wizz(String Direction) {
        raptorCooldown = 0000;

        try {
            new Raptor(Camera.centeredCoordinateX(this), Camera.centeredCoordinateY(this), this.team, this.facingDirection);
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
    public boolean gotStuff() {
        return raptorCooldown <= 0;
    }

    @Override
    public boolean MyDir(String direction) {
        return facingDirection.equals(Direction.fromString(direction));
    }
}

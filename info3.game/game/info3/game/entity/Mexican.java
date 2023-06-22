package info3.game.entity;

import java.io.IOException;

import info3.game.Camera;
import info3.game.entity.life.Life;

public class Mexican extends Player {
    int raptorCooldown;
    private int timeTequilla;
    public boolean tequillatequen=false;
    int timeDrink;

    public Mexican(int team) throws IOException {
        super(team);
        this.view=new PlayerView("resources/Mexicain/PlayerSprite.png", 3, 2, this);
    }


    @Override
    public void tick(long elapsed) {
        raptorCooldown -= elapsed;
        timeTequilla-=elapsed;
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

    @Override
    public boolean gotStuff() {
        return timeTequilla <= 0;
    }

    @Override
    public void pop(){
        tequillatequen=true;
        timeTequilla= 1000;
        timeDrink=0;
        Life life=this.lifeBar.life;
        life.addHealth(life.maxHealth);
        //mettre annimation
        //    public EntityView(String filename, int nrows, int ncols, Entity entity) {
    }
}

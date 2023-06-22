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
        timeTequilla -= elapsed;
        view.tick(elapsed);
        timer += elapsed;
        TimerEffect();

        if (isDead()) {
            this.dead = true;
            if (!respawned)
                respawnTimer -= elapsed;
            respawn();
            return;
        }
        respawned = false;
        jumpCooldown -= elapsed;
        deltatime = elapsed;

        // Dash handler
        try {
            movingDirection = Direction.IDLE;
            if (timeDrink <= 0)
            this.automate.step(this);
            // Dash handler
            if (DashTime > 0) {
                Movement.Dash(this);
            }
            if (movingDirection.x != 0)
                facingDirection = movingDirection;
            if (facingDirection != movingDirection)
                accelerationX = 0.1;
        } catch (Exception e) {
            System.out.println("Normally we should not reach here");
            e.printStackTrace();
        }
        System.out.println(DashCD);
        DashCD-=elapsed;
        view.tick(deltatime);
        Movement.Walk(this);
        Movement.affectGravity(this);

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

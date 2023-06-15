package info3.game.entity.blocks;

import java.io.IOException;

import info3.game.entity.DynamicEntity;
import info3.game.entity.Entity;
import info3.game.entity.PowerUp;
import java.util.Timer;


public class PowerUpBlock extends DynamicEntity{

    public PowerUp powerUp;
    private boolean pickup;
    int timer = 0;

    public PowerUpBlock(int x, int y, int nrows, int ncols) throws IOException{
        super(x, y, "resources/blocks/powerUpBlock.png", 1, 1);
    }


    @Override
    public void tick(long elapsed) {
        try {
            automate.step(this);
            // Timer chrono = new Timer();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    @Override
    public void egg(Entity e) {
        String powerUp=e.state.name;
        System.out.println(powerUp);
        // if(powerUp.equals("Init")) return;
        // if(powerUp.equals("Speed")){
        //     powerUp = "speed";
        // }
        // else if(powerUp.equals("Ammo")){
        //     powerUp = "ammo";
        // }
        // else if(powerUp.equals("Shield")){
        //     powerUp = "shield";
        // }
        // else if(powerUp.equals("Pow")){
        //     powerUp = "power";
        // }
        // String powerUpName = "resources/powerUp/"+powerUp+".png";
        // try {
            // this.powerUp = new PowerUp(x,y,powerUpName,1,1);
            // System.out.println("egg");
        // } catch (IOException c) {
        //     c.printStackTrace();
        // }
    }

    public boolean GotPower(){
        // if(timer==30) {
        //     timer = 0;
        //     return true;
        // }
        // System.out.println("GotPower");
        return true;
    }

    
}

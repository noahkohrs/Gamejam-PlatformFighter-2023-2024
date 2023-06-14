package info3.game.entity;

import info3.game.GameSession;

public class Movement {

    static public void Walk(Entity E) {
        if (E.stFaceLeft()) {
            if (E.velX > E.model.maxVelX) {
                E.velX = E.model.maxVelX;
            } // capping player speed

            E.x -= E.velX; // moving E
        }
        if (E.stFaceRight()) {
            if (E.velX > E.model.maxVelX) {
                E.velX = E.model.maxVelX;
            } // same as above except other direction

            E.x += E.velX;
        }

    }

    // here everything that handles jumping and gravity affectd movement
    static public void jump(Entity E, long deltatime) {
        if (E.statusJump()) { //regarde si la touche de saut est enfoncé
            if (E.jumptime == 0 && !E.jumpcd) {//regarde si la durée du saut courant est bien a 0 et qu'on a le droit de sauter
                InitJump(E, deltatime);//MALAKAS
            } else if (E.jumptime > 0) {//regarde si il nous reste du temps de saut//ici donc car apres check de touche saut enfoncé 
                LongJump(E, deltatime);
            } else {
                Gravity(E, deltatime);
            }
        //deccelerate jump if z key released early
        } else if (E.jumptime > 0) {
        LowJump(E, deltatime);
        } else {
            E.jumpcd = false;
            E.jumptime = 0;
            Gravity(E, deltatime);
            if(E.y > 400){
                E.velY =0;
            }
        }
        E.y -= E.velY;
    }


    static private void InitJump(Entity E, long deltatime){//initialise la vitesse au début du saut ainsi que le temps que va durer le saut
        E.jumpcd = true;
        E.jumptime = 100;
        E.velY = -PhysicConstant.gravity * PhysicConstant.lowJumpmultiplier * deltatime;
    }

    static private void Gravity(Entity E, long deltatime){
         E.velY += PhysicConstant.gravity * (PhysicConstant.fallmultiplier - 1) * deltatime;
    }

     static private void LongJump(Entity E, long deltatime){
        E.jumptime-= deltatime;
        E.velY = ((E.jumptime / 2.2f) * 1.1f);
     }

     static private void LowJump(Entity E, long deltatime){
        E.velY *= 0.35f;
        E.jumptime -=deltatime;
     }
}

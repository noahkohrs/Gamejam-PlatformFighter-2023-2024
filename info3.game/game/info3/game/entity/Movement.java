package info3.game.entity;

import info3.game.GameSession;

public class Movement {

    static public void Walk(Entity E) {
        if (E.stFaceLeft()) {
            if (E.velX > PhysicConstant.maxVelX) {
                E.velX = PhysicConstant.maxVelX;
            } // capping player speed
            if(E.hitbox.inCollision(Direction.LEFT)){
                E.velX = 0;
            }
            E.x -= E.velX; // moving E
        }
        if (E.stFaceRight()) {
            if (E.velX > PhysicConstant.maxVelX) {
                E.velX = PhysicConstant.maxVelX;
            } // same as above except other direction
            if(E.hitbox.inCollision(Direction.RIGHT)){
                E.velX = 0;
            }
            E.x += E.velX;
        }

    }

    // here everything that handles jumping and gravity affectd movement
    static public void jump(Entity E, long deltatime) {
        if (E.statusJump()) {
            System.out.println("Vely:"+E.velY+" ajd jumptime:"+E.jumptime+" and E.y:"+E.y+" cd:"+E.jumpcd);
            //affect jump when key pressed
            if (E.jumptime == 0 && !E.jumpcd) {
                E.jumpcd = true;
                E.jumptime = 60;
                E.velY = -PhysicConstant.gravity * PhysicConstant.lowJumpmultiplier * deltatime;
                //affect Y velocity if still wqnting to jump aka Z key not release
            } else if (E.jumptime > 0) {
                E.jumptime-= deltatime;
                E.velY = ((E.jumptime / 2.2f) * 1.1f);
                E.jumpcd = false;
                //E.velY=1;
            } else {
                //end of jump, starting to fall
                E.velY += PhysicConstant.gravity * (PhysicConstant.fallmultiplier - 1) * deltatime;
        if (E.statusJump()) { //regarde si la touche de saut est enfoncé
            if (E.jumptime == 0 && !E.jumpcd) {//regarde si la durée du saut courant est bien a 0 et qu'on a le droit de sauter

//  && (E.hitbox.inCollision(Direction.BOTTOM) || E.hitbox.inCollision(Direction.LEFT_BOTTOM) || E.hitbox.inCollision(Direction.RIGHT_BOTTOM)) condition a ajouter apres pour etre sur qu'on ne peut sauter que si l'on touche un sol
                InitJump(E, deltatime);//MALAKAS
            } else if (E.jumptime > 0) {//regarde si il nous reste du temps de saut//ici  car apres check de touche saut enfoncé donc on veut longjump
                LongJump(E, deltatime);
            } else {// il nous reste plus de temps de saut on tombe
                Gravity(E, deltatime);
            }
        } else if (E.jumptime > 0) {//on passe ici s'il nous reste du temps de saut et qu'on a relaché la touche de saut
        LowJump(E, deltatime);
        } else {//routine habituelle, touche relaché et/ou saut fini : on chute
            E.jumpcd = false;
            E.jumptime = 0;
            Gravity(E, deltatime);
        }
        //routine de check des collisions ici: tape le plafond /une plateforme
        if((E.hitbox.inCollision(Direction.UPPER) || E.hitbox.inCollision(Direction.RIGHT_TOP) || E.hitbox.inCollision(Direction.LEFT_TOP)) && E.velY > 0){
           E.velY = 0;
            Gravity(E, deltatime);
            E.jumptime = 0;
        }
        //modification de la position
        E.y -= E.velY;
    }


    static private void InitJump(Entity E, long deltatime){//initialise la vitesse au début du saut ainsi que le temps que va durer le saut
        E.jumpcd = true;
        E.jumptime = 112;
        E.velY = -PhysicConstant.gravity * PhysicConstant.lowJumpmultiplier * deltatime* 0.99f;
    }

    static private void Gravity(Entity E, long deltatime){//accélération de la gravité -> affectation au joueur
         E.velY += PhysicConstant.gravity * (PhysicConstant.fallmultiplier - 1) * deltatime;
        if(E.hitbox.inCollision(Direction.BOTTOM)){
            E.velY =0;
        }

        // if(E.y > 400){//juste la pour avoir un plancher pour ne aps tomber dans le vide lors des tests
        //         E.velY =0;
        //     }
    }

     static private void LongJump(Entity E, long deltatime){//décroissance de la vitesse quand on maintient la touche de saut enfoncé
        E.jumptime-= deltatime;
        E.velY = ((E.jumptime / 2.2f) * 1.1f);
     }

     static private void LowJump(Entity E, long deltatime){//décroissance de la vitesse quand on relache la touche de saut avant la fin du tamps alloué au saut(jumptime)
        E.velY *= 0.35f;
        E.jumptime -=deltatime;
     }
}

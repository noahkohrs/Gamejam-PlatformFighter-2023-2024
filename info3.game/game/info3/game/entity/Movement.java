package info3.game.entity;

import info3.game.GameSession;

public class Movement {

    static public void Walk(Entity E) {
        E.updateVelocityX();
        E.x += E.velX * E.facingDirection.x;
        while (E.hitbox.inCollision(Direction.LEFT))
            E.x += 1;
        while (E.hitbox.inCollision(Direction.RIGHT))
            E.x -= 1;

        E.affectTor();
    }

    // here everything that handles jumping and gravity affectd movement
    static public void jump(Entity E, long deltatime) {
        E.y -= Math.max(E.velY, -PhysicConstant.maxVelY);
        if (E.statusJump()) { // regarde si la touche de saut est enfoncé
            if (E.jumptime == 0 && !E.jumpcd && E.hitbox.inCollision(Direction.BOTTOM)) {// regarde si la durée du saut
                                                                                         // courant est bien a 0 et
                                                                                         // qu'on a le droit de sauter

                InitJump(E, deltatime);// MALAKAS

            } else if (E.jumptime > 0) {// regarde si il nous reste du temps de saut//ici car apres check de touche saut
                                        // enfoncé donc on veut longjump
                LongJump(E, deltatime);
            } else {// il nous reste plus de temps de saut on tombe

            if(!E.hitbox.inCollision(Direction.BOTTOM)){
                Gravity(E, deltatime);
            }
        }
        } else if (E.jumptime > 0) {// on passe ici s'il nous reste du temps de saut et qu'on a relaché la touche de
                                    // saut
            LowJump(E, deltatime);
        } else {// routine habituelle, touche relaché et/ou saut fini : on chute
            E.jumpcd = false;
            E.jumptime = 0;
            if(!E.hitbox.inCollision(Direction.BOTTOM))
                Gravity(E, deltatime);
        }
        // routine de check des collisions ici: tape le plafond /une plateforme
        if ((E.hitbox.inCollision(Direction.UPPER) || E.hitbox.inCollision(Direction.RIGHT_TOP)
                || E.hitbox.inCollision(Direction.LEFT_TOP)) && E.velY > 0) {
            E.velY = 0;
            Gravity(E, deltatime);
            E.jumptime = 0;
        }
        // modification de la position
        while (E.hitbox.inCollision(Direction.UPPER)) {
            
            E.y += 1 ;
            E.velY = 0 ;
        }
        while (E.hitbox.inCollision(Direction.BOTTOM)) {
            E.y -= 1 ;
            E.velY = 0 ;
        }
            
        E.affectTor();

    }

    static private void InitJump(Entity E, long deltatime) {// initialise la vitesse au début du saut ainsi que le temps
                                                            // que va durer le saut
        E.jumpcd = true;
        E.jumptime = 100;
        E.velY = -PhysicConstant.gravity * PhysicConstant.lowJumpmultiplier * deltatime;
    }

    static private void Gravity(Entity E, long deltatime) {// accélération de la gravité -> affectation au joueur
        E.velY += PhysicConstant.gravity * (PhysicConstant.fallmultiplier - 1) * deltatime;
        if (E.hitbox.inCollision(Direction.BOTTOM)) {
            E.velY = -.2f;
        }

        // if(E.y > 400){//juste la pour avoir un plancher pour ne aps tomber dans le
        // vide lors des tests
        // E.velY =0;
        // }
    }

    static private void LongJump(Entity E, long deltatime) {// décroissance de la vitesse quand on maintient la touche
                                                            // de saut enfoncé
        E.velY = ((E.jumptime / 2.2f) * 1.1f);
        E.jumptime -= deltatime;
    }

    static private void LowJump(Entity E, long deltatime) {// décroissance de la vitesse quand on relache la touche de
                                                           // saut avant la fin du tamps alloué au saut(jumptime)
        E.velY *= 0.35f;
        E.jumptime -= deltatime;
    }
}

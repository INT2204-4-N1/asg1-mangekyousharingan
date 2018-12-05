package com.msteam.gameobject;

import com.msteam.effect.Animation;

import java.awt.*;

public class Bom extends ParticularObject{

    public long timeToExplo = 16;
    public Animation exploAnimation, bomAnimation;
    public Bom(float x, float y, float width, float height, GameWorld gameWorld) {
        super(x, y, width, height, gameWorld);
    }

    @Override
    public void draw(Graphics2D g2) {



    }

    public void update(){
        super.update();
        if (timeToExplo > 0){
            timeToExplo--;
        }
        else {


        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }
}

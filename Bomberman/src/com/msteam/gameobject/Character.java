package com.msteam.gameobject;

import java.awt.*;

public abstract class Character extends ParticularObject{

    public Character(float x, float y, float width, float height, GameWorld gameWorld) {

        super(x, y, width, height, gameWorld);
        setState(ALIVE);
    }

    public abstract void run();

    @Override
    public void update(){

        super.update();

        if (getState() == ALIVE){

            setPosX(getPosX() + getSpeedX());
            setPosY(getPosY() + getSpeedY());

            if(getDirection() == DIR_LEFT &&
                    getGameWorld().map.impactWithLeft(getBoundForCollisonWithMap())!=null){

                Rectangle rectLeftWall = getGameWorld().map.impactWithLeft(getBoundForCollisonWithMap());
                setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
                System.out.println("Impact with left box");
            }
            if(getDirection() == DIR_RIGHT &&
                    getGameWorld().map.impactWithRight(getBoundForCollisonWithMap())!=null){

                Rectangle rectRightWall = getGameWorld().map.impactWithRight(getBoundForCollisonWithMap());
                setPosX(rectRightWall.x - getWidth()/2);
                System.out.println("Impact with right box");
            }
            if(getDirection() == DIR_UP &&
                    getGameWorld().map.impactWithUp(getBoundForCollisonWithMap())!=null){

                //Rectangle rectUpWall = getGameWorld().map.impactWithUp(getBoundForCollisonWithMap());
                //setPosY(rectUpWall.y + rectUpWall.height + getHeight()/2);
                System.out.println("Impact with up box");
            }
            if(getDirection() == DIR_DOWN &&
                    getGameWorld().map.impactWithRight(getBoundForCollisonWithMap())!=null){

                Rectangle rectDownWall = getGameWorld().map.impactWithRight(getBoundForCollisonWithMap());
                setPosY(rectDownWall.y - getHeight()/2 );
                System.out.println("Impact with down box");
            }
        }
    }

}

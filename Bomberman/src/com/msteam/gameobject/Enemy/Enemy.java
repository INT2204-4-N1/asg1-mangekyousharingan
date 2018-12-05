package com.msteam.gameobject.Enemy;

import com.msteam.gameobject.GameWorld;
import com.msteam.gameobject.ParticularObject;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends ParticularObject {

    public Random rd = new Random();

    public Enemy(float x, float y, float width, float height, GameWorld gameWorld) {
        super(x, y, width, height, gameWorld);
        setState(ALIVE);
    }

    public abstract void move();

    public void calculateMove(){
        int temp = rd.nextInt(4);
        switch (temp){
            case 0:
                setDirection(DIR_LEFT);
                break;
            case 1:
                setDirection(DIR_RIGHT);
                break;
            case 2:
                setDirection(DIR_UP);
                break;
            case 3:
                setDirection(DIR_DOWN);
                break;
        }
    }
    @Override
    public void update(){
        super.update();

        if (getState() == ALIVE){

            setPosX(getPosX() + getSpeedX());
            setPosY(getPosY() + getSpeedY());
            /*
            if(getDirection() == DIR_LEFT &&
                    getGameWorld().map.impactWithLeft(getBoundForCollisonWithMap())!=null){

                Rectangle rectLeftWall = getGameWorld().map.impactWithLeft(getBoundForCollisonWithMap());
                setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
                System.out.println("Enemy with left box");
                //calculateMove();
            }
            if(getDirection() == DIR_RIGHT &&
                    getGameWorld().map.impactWithRight(getBoundForCollisonWithMap())!=null){

                Rectangle rectRightWall = getGameWorld().map.impactWithRight(getBoundForCollisonWithMap());
                setPosX(rectRightWall.x - getWidth()/2);
                System.out.println("Enemy with right box");
                //calculateMove();
            }
            if(getDirection() == DIR_UP &&
                    getGameWorld().map.impactWithUp(getBoundForCollisonWithMap())!=null){

                Rectangle rectUpWall = getGameWorld().map.impactWithUp(getBoundForCollisonWithMap());
                setPosY(rectUpWall.y + rectUpWall.height + getHeight()/2);
                System.out.println("Enemy with up box");
                //calculateMove();
            }
            if(getDirection() == DIR_DOWN &&
                    getGameWorld().map.impactWithDown(getBoundForCollisonWithMap())!=null){

                Rectangle rectDownWall = getGameWorld().map.impactWithDown(getBoundForCollisonWithMap());
                setPosY(rectDownWall.y - getHeight()/2 );
                System.out.println("Enemy with down box");
                //calculateMove();
            }
            */
        }
    }
}

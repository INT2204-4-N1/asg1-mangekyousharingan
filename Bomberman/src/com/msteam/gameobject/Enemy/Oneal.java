package com.msteam.gameobject.Enemy;

import com.msteam.effect.Animation;
import com.msteam.effect.CacheDataLoader;
import com.msteam.gameobject.GameWorld;

import java.awt.*;

public class Oneal extends Enemy{

    private Animation leftAnimation, rightAnimation;

    public Oneal(float x, float y, GameWorld gameWorld) {
        super(x, y, 46, 46, gameWorld);
        setTeamType(ENEMY_TEAM);
        leftAnimation = CacheDataLoader.getInstance().getAnimation("onealleft");
        rightAnimation = CacheDataLoader.getInstance().getAnimation("onealright");
        setDirection(DIR_RIGHT);
    }

    @Override
    public void move() {

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2) {

        switch (getState()){
            case ALIVE:
                if (getDirection() == DIR_DOWN){
                    leftAnimation.update(System.nanoTime());
                    leftAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                if (getDirection() == DIR_UP){
                    rightAnimation.update(System.nanoTime());
                    rightAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                if (getDirection() == DIR_RIGHT){
                    rightAnimation.update(System.nanoTime());
                    rightAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                if (getDirection() == DIR_LEFT ){
                    leftAnimation.update(System.nanoTime());
                    leftAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                break;
            case DEATH:
                break;

        }
        //drawBoundCollisionWithMap(g2);
    }

    @Override
    public void update(){

        super.update();
    }
}

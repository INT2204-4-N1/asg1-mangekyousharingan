package com.msteam.gameobject.Enemy;

import com.msteam.effect.Animation;
import com.msteam.effect.CacheDataLoader;
import com.msteam.gameobject.GameWorld;

import java.awt.*;


public class Balloon extends Enemy {

    private Animation leftAnimation, rightAnimation, dieAnimation;
    private String[][] map;
    private float tileSizeEnemy;
    long timeToChangeDir;

    public Balloon(float x, float y, GameWorld gameWorld) {
        super(x, y, 46, 46, gameWorld);
        setTeamType(ENEMY_TEAM);
        leftAnimation = CacheDataLoader.getInstance().getAnimation("normalballoonleft");
        rightAnimation = CacheDataLoader.getInstance().getAnimation("normalballoonright");
        dieAnimation = CacheDataLoader.getInstance().getAnimation("normalballoondie");
        setDirection(DIR_LEFT);
        timeToChangeDir = 0;
    }

    public float detectedPosX(){
        map = CacheDataLoader.getInstance().getMap();
        for (int i = 0; i < CacheDataLoader.getInstance().get_height(); i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_width();  j++){
                if (map[i][j].equals("1")){
                    return j*tileSizeEnemy;
                }
            }
        }

        return Float.parseFloat(null);
    }

    public float detectedPosY(){
        map = CacheDataLoader.getInstance().getMap();
        for (int i = 0; i < CacheDataLoader.getInstance().get_height(); i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_width();  j++){
                if (map[i][j].equals("1")){
                    return i*tileSizeEnemy;
                }
            }
        }

        return Float.parseFloat(null);
    }

    @Override
    public void move() {
        if (getDirection() == DIR_LEFT) {
            setSpeedX(-2);
        }
        if (getDirection() == DIR_RIGHT) {
            setSpeedX(2);
        }
        if (getDirection() == DIR_UP) {
            setSpeedY(2);
        }
        if (getDirection() == DIR_DOWN) {
            setSpeedY(-2);
        }
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
                dieAnimation.update(System.nanoTime());
                dieAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),24,32,g2);
                break;

        }
        //drawBoundCollisionWithMap(g2);
    }

    @Override
    public void update(){
        super.update();
        if(getDirection() == DIR_LEFT &&
                getGameWorld().map.impactWithLeft(getBoundForCollisionWithMap())!=null){

            //Rectangle rectLeftWall = getGameWorld().map.impactWithLeft(getBoundForCollisonWithMap());
            //setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);
            //System.out.println("Enemy with left box");
            //calculateMove();
            if (System.nanoTime() - timeToChangeDir > 1000*10000000){
                calculateMove();
                timeToChangeDir = System.nanoTime();
            }
        }
        if(getDirection() == DIR_RIGHT &&
                getGameWorld().map.impactWithRight(getBoundForCollisionWithMap())!=null){

            //Rectangle rectRightWall = getGameWorld().map.impactWithRight(getBoundForCollisonWithMap());
            //setPosX(rectRightWall.x - getWidth()/2);
            //System.out.println("Enemy with right box");
            //calculateMove();
            if (System.nanoTime() - timeToChangeDir > 1000*10000000){
                calculateMove();
                timeToChangeDir = System.nanoTime();
            }
        }
        if(getDirection() == DIR_UP &&
                getGameWorld().map.impactWithUp(getBoundForCollisionWithMap())!=null){

            //Rectangle rectUpWall = getGameWorld().map.impactWithUp(getBoundForCollisonWithMap());
            //setPosY(rectUpWall.y + rectUpWall.height + getHeight()/2);
            //System.out.println("Enemy with up box");
            //calculateMove();
            if (System.nanoTime() - timeToChangeDir > 1000*10000000){
                calculateMove();
                timeToChangeDir = System.nanoTime();
            }
        }
        if(getDirection() == DIR_DOWN &&
                getGameWorld().map.impactWithDown(getBoundForCollisionWithMap())!=null){

            //Rectangle rectDownWall = getGameWorld().map.impactWithDown(getBoundForCollisonWithMap());
            //setPosY(rectDownWall.y - getHeight()/2 );
            //System.out.println("Enemy with down box");
            //calculateMove();
            if (System.nanoTime() - timeToChangeDir > 1000*10000000){
                calculateMove();
                timeToChangeDir = System.nanoTime();
            }
        }
        move();

    }
}

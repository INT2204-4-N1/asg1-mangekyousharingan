package com.msteam.gameobject;

import com.msteam.effect.Animation;
import com.msteam.effect.CacheDataLoader;

import java.awt.*;

public class BomberMan extends Character {

    public static int RUNSPEED = 3;

    private Animation leftAnimation, rightAnimation, upAnimation, downAnimation;

    /**
     * Hướng đặt bom
     * đặt ở đuôi hoặc ở đầu (bắn đạn)
     */

    // Có thể không dùng, vì bom đặt ngay dưới hoạt ảnh nhân vật

    GameWorld gameWorld;

    public BomberMan(float x, float y, GameWorld gameWorld) {

        super(x, y, 24, 32, gameWorld);
        setTeamType(LEAGUE_TEAM);
        leftAnimation = CacheDataLoader.getInstance().getAnimation("left");
        rightAnimation = CacheDataLoader.getInstance().getAnimation("right");
        upAnimation = CacheDataLoader.getInstance().getAnimation("up");
        downAnimation = CacheDataLoader.getInstance().getAnimation("down");
    }

    @Override
    public void run() {
        if (getDirection() == DIR_LEFT) {
            setSpeedX(-3);
        }
        if (getDirection() == DIR_RIGHT) {
            setSpeedX(3);
        }
        if (getDirection() == DIR_UP) {
            setSpeedY(-3);
        }
        if (getDirection() == DIR_DOWN) {
            setSpeedY(3);
        }
    }

    // Method nay o lop ParticularObj
    /*
    public Rectangle getBoundForCollisionWithMap(){

        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth())/2);
        bound.y = (int) (getPosY() - (getWidth())/2);
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }
    */
    @Override
    public void update() {
        super.update();


        //setPosX(getPosX() + getSpeedX());
        //setPosY(getPosY() + getSpeedY());



        //Rectangle future =
    }

    @Override
    public void draw(Graphics2D g2) {

        drawBoundCollisionWithMap(g2);
    }


    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }
}

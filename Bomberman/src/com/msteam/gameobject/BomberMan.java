package com.msteam.gameobject;

import com.msteam.effect.Animation;
import com.msteam.effect.CacheDataLoader;
import com.msteam.effect.FrameImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BomberMan extends Character {

    public static int RUNSPEED = 3;
    public static int ENCHANTED_RUNSPEED = 5;

    private Animation leftAnimation, rightAnimation, upAnimation, downAnimation, dieAnimation;

    GameWorld gameWorld;

    public BomberMan(float x, float y, GameWorld gameWorld) {

        super(x, y, 34, 45, gameWorld);
        setTeamType(LEAGUE_TEAM);
        leftAnimation = CacheDataLoader.getInstance().getAnimation("left");
        rightAnimation = CacheDataLoader.getInstance().getAnimation("right");
        upAnimation = CacheDataLoader.getInstance().getAnimation("up");
        downAnimation = CacheDataLoader.getInstance().getAnimation("down");
        dieAnimation = CacheDataLoader.getInstance().getAnimation("boomberdie");
    }

    @Override
    public void run() {
        if (getDirection() == DIR_LEFT) {
            setSpeedX(-5);
        }
        if (getDirection() == DIR_RIGHT) {
            setSpeedX(5);
        }
        if (getDirection() == DIR_UP) {
            setSpeedY(-5);
        }
        if (getDirection() == DIR_DOWN) {
            setSpeedY(5);
        }
    }

    @Override
    public void makeBomb() {


    }


    @Override
    public void update() {
        super.update();


        //setPosX(getPosX() + getSpeedX());
        //setPosY(getPosY() + getSpeedY());



        //Rectangle future =
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (getState()){
            case ALIVE:
                //BufferedImage startImage;
                //startImage = downAnimation.getImame(0);
                //FrameImage startFrame = new FrameImage();
                //startFrame.draw(g2,(int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight());
                if (getDirection() == DIR_DOWN && getSpeedY() > 0){
                    downAnimation.update(System.nanoTime());
                    downAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                else if(getDirection() == DIR_DOWN && getSpeedY() == 0){
                    BufferedImage currDownFrame;
                    currDownFrame = downAnimation.getCurrImage();
                    FrameImage frameImage1 = new FrameImage();
                    frameImage1.setImage(currDownFrame);
                    frameImage1.draw(g2,(int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight());
                }
                if (getDirection() == DIR_UP && getSpeedY() < 0){
                    upAnimation.update(System.nanoTime());
                    upAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                else if(getDirection() == DIR_UP && getSpeedY() == 0) {
                    BufferedImage currUpFrame;
                    currUpFrame = upAnimation.getCurrImage();
                    FrameImage frameImage2 = new FrameImage();
                    frameImage2.setImage(currUpFrame);
                    frameImage2.draw(g2, (int) getPosX() - (int) getGameWorld().camera.getPosX(), (int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight());
                }
                if (getDirection() == DIR_RIGHT && getSpeedX() > 0){
                    rightAnimation.update(System.nanoTime());
                    rightAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                else if(getDirection() == DIR_RIGHT && getSpeedY() == 0){
                    BufferedImage currRightFrame;
                    currRightFrame = rightAnimation.getCurrImage();
                    FrameImage frameImage3 = new FrameImage();
                    frameImage3.setImage(currRightFrame);
                    frameImage3.draw(g2,(int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight());
                }
                if (getDirection() == DIR_LEFT && getSpeedX() < 0){
                    leftAnimation.update(System.nanoTime());
                    leftAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                }
                else if(getDirection() == DIR_LEFT && getSpeedY() == 0) {
                    BufferedImage currLeftFrame;
                    currLeftFrame = leftAnimation.getCurrImage();
                    FrameImage frameImage4 = new FrameImage();
                    frameImage4.setImage(currLeftFrame);
                    frameImage4.draw(g2, (int) getPosX() - (int) getGameWorld().camera.getPosX(), (int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight());
                }
                break;
            case DEATH:
                dieAnimation.update(System.nanoTime());
                dieAnimation.draw((int) getPosX() - (int) getGameWorld().camera.getPosX(),(int) getPosY() - (int) getGameWorld().camera.getPosY(),(int)getWidth(),(int)getHeight(),g2);
                break;

        }
        //drawBoundCollisionWithMap(g2);
    }


    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }
}

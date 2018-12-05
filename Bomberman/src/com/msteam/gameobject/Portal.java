package com.msteam.gameobject;

import com.msteam.effect.CacheDataLoader;
import com.msteam.effect.FrameImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Portal extends ParticularObject {

    private String[][] map;
    private float tileSizePortal = 48;

    public Portal(float x, float y, GameWorld gameWorld) {

        super(x, y, 48, 48, gameWorld);
    }

    public float detectedPosX(){
        map = CacheDataLoader.getInstance().getMap();
        float posX;
        for (int i = 0; i < CacheDataLoader.getInstance().get_height(); i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_height();  j++){
                if (map[i][j].equals("x")){
                    return j*tileSizePortal;
                }
            }
        }

        return Float.parseFloat(null);
    }

    public float detectedPosY(){
        map = CacheDataLoader.getInstance().getMap();
        float posX;
        for (int i = 0; i < CacheDataLoader.getInstance().get_height(); i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_height();  j++){
                if (map[i][j].equals("x")){
                    return i*tileSizePortal;
                }
            }
        }

        return Float.parseFloat(null);
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2) {
        Camera camera = getGameWorld().camera;

        FrameImage portal = new FrameImage();
        portal = CacheDataLoader.getInstance().getFrameImage("portal");
        g2.drawImage(portal.getImage(),(int)(getPosX() - camera.getPosX()),(int)(getPosY() - camera.getPosY()),(int)getWidth(),(int)getHeight(),null);
    }
}

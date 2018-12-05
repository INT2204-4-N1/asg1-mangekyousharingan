package com.msteam.gameobject;

import com.msteam.effect.CacheDataLoader;
import com.msteam.effect.FrameImage;

import java.awt.*;

public class GraphicMap extends GameObject {

    private String[][] map;
    private int tileSize;

    public GraphicMap(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.tileSize = 48;
        map = CacheDataLoader.getInstance().getMap();
    }

    public void draw(Graphics2D g2){

        Camera camera = getGameWorld().camera;

        g2.setColor(Color.GRAY);
        for (int i = 0; i < CacheDataLoader.getInstance().get_height(); i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_width(); j++){
                if (map[i][j].equals("#")){
                    FrameImage wall = new FrameImage();
                    wall = CacheDataLoader.getInstance().getFrameImage("wall");
                    g2.drawImage(wall.getImage(),(int)getPosX() + j*tileSize - (int) camera.getPosX(),(int)getPosY() + i*tileSize - (int) camera.getPosY(), tileSize, tileSize,null );
                }
                else{
                    FrameImage grass = new FrameImage();
                    grass = CacheDataLoader.getInstance().getFrameImage("grass");
                    g2.drawImage(grass.getImage(),(int)getPosX() + j*tileSize - (int) camera.getPosX(),(int)getPosY() + i*tileSize - (int) camera.getPosY(), tileSize, tileSize,null );
                }
            }
        }
    }

    @Override
    public void update() {}
}

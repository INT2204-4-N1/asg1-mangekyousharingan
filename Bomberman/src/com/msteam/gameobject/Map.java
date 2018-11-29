package com.msteam.gameobject;

import com.msteam.effect.CacheDataLoader;
import com.msteam.exceptions.LoadLevelException;
import sun.misc.Cache;
import sun.plugin.cache.CacheUpdateHelper;

import java.awt.*;

public class Map {

    private String[][] map;
    private int tileSize;

    public float posX, posY;

    public Map(float x, float y){

        this.posX = x;
        this.posY = y;
        this.tileSize = 32;
        map = CacheDataLoader.getInstance().getMap();
    }

    public int getTileSize(){

        return this.tileSize;
    }

    public void draw(Graphics2D g2){


        g2.setColor(Color.GRAY);
        for (int i = 0; i < CacheDataLoader.getInstance().get_height(); i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_width(); j++){


                if (map[i][j].equals("#")){

                    g2.fillRect((int)posX + j*tileSize,(int)posY + i*tileSize, tileSize, tileSize );
                }
            }
        }
    }

    // don't care this, just for megaman =)))
    /*
    public Rectangle haveCollisionWithLand(Rectangle rect){

        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        int posY = (rect.y + rect.height)/tileSize;

        if (posX1 < 0){

            posX1 = 0;
        }

        if (posX2 > CacheDataLoader.getInstance().get_width()){

            posX2 = CacheDataLoader.getInstance().get_width() - 1;
        }

        for (int y = posY ; y < CacheDataLoader.getInstance().get_height() ; y++){

        }
        return null;

    }
    */
}
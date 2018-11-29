package com.msteam.gameobject;

import com.msteam.effect.CacheDataLoader;
import sun.misc.Cache;

import java.awt.*;

public class Map {

    private String[][] map;
    private int tileSize;

    public float x, y;

    public Map(float x, float y){

        this.x = x;
        this.y = y;
        this.tileSize = 16;
        map = CacheDataLoader.getInstance().getMap();
    }

    public int getTileSize(){

        return this.tileSize;
    }

    public void draw(Graphics2D g2){

        map = CacheDataLoader.getInstance().getMap();
        System.out.println(map[0][0]);
        g2.setColor(Color.GRAY);
        for (int i = 0; i < CacheDataLoader.getInstance().get_height()-5; i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_width()-5; j++){

                /*
                if (map[i][j].equals("#")){

                    g2.fillRect((int)x + j*tileSize,(int)y + i*tileSize, tileSize, tileSize );
                }
                */
                //System.out.println((CacheDataLoader.getInstance().getMap())[i][j]);

            }
            System.out.println();
        }
    }
}
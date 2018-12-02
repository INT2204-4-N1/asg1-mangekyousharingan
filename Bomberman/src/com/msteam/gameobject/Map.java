package com.msteam.gameobject;

import com.msteam.effect.CacheDataLoader;
import com.msteam.exceptions.LoadLevelException;
import sun.misc.Cache;
import sun.plugin.cache.CacheUpdateHelper;

import java.awt.*;

public class Map extends GameObject{

    private String[][] map;
    private int tileSize;


    public Map(float x, float y, GameWorld gameWorld){

        super(x,y,gameWorld);
        this.tileSize = 48;
        map = CacheDataLoader.getInstance().getMap();
    }

    public int getTileSize(){

        return this.tileSize;
    }

    public void draw(Graphics2D g2){

        Camera camera = getGameWorld().camera;

        g2.setColor(Color.GRAY);
        for (int i = 0; i < CacheDataLoader.getInstance().get_height(); i++){
            for (int j = 0; j < CacheDataLoader.getInstance().get_width(); j++){


                if (map[i][j].equals("#")){

                    g2.fillRect((int)getPosX() + j*tileSize - (int) camera.getPosX(),(int)getPosY() + i*tileSize - (int) camera.getPosY(), tileSize, tileSize );
                }
            }
        }
    }

    @Override
    public void update() {}

    public Rectangle impactWithDown(Rectangle rect){

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
            for (int x = posX1 ; x <= posX2 ; x++){

                if (map[y][x].equals("#")){

                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize,(int) getPosY()+ y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)){

                        return r;
                    }
                }
            }
        }
        return null;

    }

    public Rectangle impactWithUp(Rectangle rect){

        int posX1= rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        int posY = rect.y/tileSize;

        if (posX1 < 0){

            posX1 = 0;
        }
        if (posX2 >= CacheDataLoader.getInstance().get_height()){

            posX2 = CacheDataLoader.getInstance().get_height() - 1;
        }

        for (int y = posY; y >= 0; y--){
            for (int x = posX1; x <= posX2; x++){

                if (map[y][x].equals("#")){

                    Rectangle r = new Rectangle((int) getPosX() + x*tileSize,(int) getPosY() + y*tileSize,tileSize,tileSize);
                    if (rect.intersects(rect)){

                        return r;
                    }
                }
            }
        }

        return null;
    }

    public Rectangle impactWithRight(Rectangle rect){

        int posY1 = rect.y/tileSize;
        posY1 -= 2;
        int posY2 = rect.y/tileSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if (posX2 >= CacheDataLoader.getInstance().get_width()){

            posX2 = CacheDataLoader.getInstance().get_width() - 1;
        }

        if (posY1 < 0){

            posY1 = 0;
        }
        if (posY2 >= CacheDataLoader.getInstance().get_height()){

            posY2 = CacheDataLoader.getInstance().get_height() - 1;
        }

        for (int x = posX1; x <= posX2; x++){
            for (int y = posY1; y <= posY2; y++){

                if (map[y][x].equals("#")){

                    Rectangle r = new Rectangle((int) getPosX() + x*tileSize,(int) getPosY() + y*tileSize,tileSize,tileSize);
                    if (r.y < rect.y + rect.height - 1 && rect.intersects(r)){

                        return r;
                    }
                }
            }
        }
        return null;
    }

    public Rectangle impactWithLeft(Rectangle rect){

        int posY1 = rect.y/tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;
        if (posX2 < 0){

            posX2 = 0;
        }
        if (posY1 < 0){

            posY1 = 0;
        }
        if (posY2 >= CacheDataLoader.getInstance().get_height()){

            posY2 = CacheDataLoader.getInstance().get_height() - 1;
        }

        for (int x = posX1; x >= posX2; x--){
            for (int y = posY1; y <= posY2; y++){
                if (map[y][x].equals("#")){
                    Rectangle r = new Rectangle((int) getPosX() + x*tileSize,(int) getPosY() + y*tileSize,tileSize,tileSize);
                    if (r.y < rect.y + rect.height - 1 && rect.intersects(r)){

                        return r;
                    }

                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithBox(Rectangle rect){

        return null;
    }
}
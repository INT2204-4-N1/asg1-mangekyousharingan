package com.msteam.gameobject;


import java.awt.*;

public class GameWorld {

    public BomberMan bomberMan;

    public Map map;

    public GameWorld(){

        bomberMan = new BomberMan(300,300,24,32);
        map = new Map(0,0);
    }

    public void update(){

        bomberMan.update();
    }

    public void render(Graphics2D g2){

        bomberMan.draw(g2);
        map.draw(g2);
    }


}

package com.msteam.gameobject;


import com.msteam.userinterface.GameFrame;

import java.awt.*;

public class GameWorld{

    public BomberMan bomberMan;
    public Map map;
    public Camera camera;

    public GameWorld(){

        bomberMan = new BomberMan(80,80,this);
        map = new Map(0,0,this);
        camera = new Camera(0,0, GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,this);
    }

    public void update(){

        bomberMan.update();
        camera.update();
    }

    public void render(Graphics2D g2){

        map.draw(g2);
        bomberMan.draw(g2);
    }


}

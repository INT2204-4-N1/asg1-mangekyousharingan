package com.msteam.gameobject;


import com.msteam.gameobject.Enemy.Balloon;
import com.msteam.gameobject.Enemy.Oneal;
import com.msteam.userinterface.GameFrame;

import java.awt.*;

public class GameWorld{

    public BomberMan bomberMan;
    public GraphicMap graphicMap;
    public Map map;
    public Camera camera;
    public Portal portal;
    public Balloon balloon1;
    public Balloon balloon2;
    public Balloon balloon3;
    public Oneal oneal1;


    public Portal tempPortal = new Portal(0,0,this);

    public GameWorld(){

        bomberMan = new BomberMan(66,71,this);
        graphicMap = new GraphicMap(0,0,this);
        map = new Map(0,0,this);
        camera = new Camera(0,0, GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,this);
        portal = new Portal(tempPortal.detectedPosX(),tempPortal.detectedPosY(),this);
        balloon1 = new Balloon(600,70,this);
        balloon2 = new Balloon(885,165,this);
        balloon3 = new Balloon(1170,265,this);
        oneal1 = new Oneal(1175,165,this);
    }

    public void update(){

        bomberMan.update();
        camera.update();
        //balloon1.update();
    }

    public void render(Graphics2D g2){

        graphicMap.draw(g2);
        map.draw(g2);
        portal.draw(g2);
        bomberMan.draw(g2);
        balloon1.draw(g2);
        balloon2.draw(g2);
        balloon3.draw(g2);
        oneal1.draw(g2);
    }


}

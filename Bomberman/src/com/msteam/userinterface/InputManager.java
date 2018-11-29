package com.msteam.userinterface;

import com.msteam.gameobject.BomberMan;
import com.msteam.gameobject.GameWorld;

import java.awt.event.KeyEvent;

public class InputManager {

    private GameWorld gameWorld;

    public InputManager(GameWorld gameWorld){

        this.gameWorld = gameWorld;
    }

    /**
     * Event cho key nhấn từ bàn phím khi GIỮ PHÍM
     * @param keyCode: key đc nhấn từ bàn phím
     */
    public void processKeyPressed(int keyCode){

        switch (keyCode){

            case KeyEvent.VK_UP:
                System.out.println("UP");
                gameWorld.bomberMan.setDirection(BomberMan.DIR_UP);
                gameWorld.bomberMan.setSpeedY(-5);
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                gameWorld.bomberMan.setDirection(BomberMan.DIR_DOWN);
                gameWorld.bomberMan.setSpeedY(5);
                break;

            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                gameWorld.bomberMan.setDirection(BomberMan.DIR_LEFT);
                gameWorld.bomberMan.setSpeedX(-5);
                break;

            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                gameWorld.bomberMan.setDirection(BomberMan.DIR_LEFT);
                gameWorld.bomberMan.setSpeedX(5);
                break;

            case KeyEvent.VK_SPACE:
                System.out.println("SPACE");
                break;
        }
    }
    /**
     * Event cho key nhấn từ bàn phím khi THẢ PHÍM
     * @param keyCode: key đc nhấn từ bàn phím
     */
    public void processKeyReleased(int keyCode){

        switch (keyCode){

            case KeyEvent.VK_UP:
                System.out.println("UP Released");
                gameWorld.bomberMan.setSpeedY(0);
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("DOWN Released");
                gameWorld.bomberMan.setSpeedY(0);
                break;

            case KeyEvent.VK_LEFT:
                System.out.println("LEFT Released");
                gameWorld.bomberMan.setSpeedX(0);
                break;

            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT Released");
                gameWorld.bomberMan.setSpeedX(0);
                break;

            case KeyEvent.VK_SPACE:
                System.out.println("SPACE Released");
                break;
        }
    }
}

package com.msteam.userinterface;

import java.awt.event.KeyEvent;

public class InputManager {
    /**
     * Event cho key nhấn từ bàn phím khi GIỮ PHÍM
     * @param keyCode: key đc nhấn từ bàn phím
     */
    public void processKeyPressed(int keyCode){

        switch (keyCode){

            case KeyEvent.VK_UP:
                System.out.println("UP");
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                break;

            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                break;

            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
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
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("DOWN Released");
                break;

            case KeyEvent.VK_LEFT:
                System.out.println("LEFT Released");
                break;

            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT Released");
                break;

            case KeyEvent.VK_SPACE:
                System.out.println("SPACE Released");
                break;
        }
    }
}

package com.msteam.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;

    private boolean isRunning;              // Kiểm soát vòng lặp game

    private InputManager inputManager;

    public GamePanel(){

        inputManager = new InputManager();
    }

    /**
     * Tự động gọi khi add Panel vào Frame
     * @param g
     */
    @Override
    public void paint(Graphics g){

        g.setColor(Color.RED);
        g.fillRect(0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT);

    }

    /**
     * Chạy thread GamePanel (start)
     * Chạy thread -> gọi hàm Run
     */
    public void startGame(){
        if (thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {

        long FPS = 80;
        long period = (1000*1000000)/FPS;                 // Chu ky (nano giay)
        long beginTime;
        long sleepTime;

        int i=0;

        beginTime = System.nanoTime();                   // lay time he thong -> nano giay
        // Game loop
        while (isRunning){

            //update Game
            //render Game

            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            //System.out.println("i = "+(i++));
            try {
                if(sleepTime > 0){
                    Thread.sleep(sleepTime/1000000);
                }
                else {
                    Thread.sleep(period/2000000);
                }
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }

            beginTime = System.nanoTime();

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {            // callback method(1 lop nao khac goi)

        inputManager.processKeyPressed(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {

        inputManager.processKeyReleased(e.getKeyCode());

    }
}
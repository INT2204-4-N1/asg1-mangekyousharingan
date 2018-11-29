package com.msteam.userinterface;

import com.msteam.effect.Animation;
import com.msteam.effect.CacheDataLoader;
import com.msteam.effect.FrameImage;
import com.msteam.gameobject.BomberMan;
import com.msteam.gameobject.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread thread;

    private boolean isRunning;              // Kiểm soát vòng lặp game

    private InputManager inputManager;

    private BufferedImage bufImage;
    private Graphics2D bufG2D;

    FrameImage frame1, frame2, frame3, frame4, frame5, frame6, frame7;
    Animation anim;

    BomberMan bomberMan = new BomberMan(300,300,48,64);
    Map map = new Map(0,0);

    public GamePanel() {

        inputManager = new InputManager(this);

        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);

    }

    public void updateGame(){

        bomberMan.update();
    }
    public void RenderGame(){

        if (bufImage == null){

            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        }

        if (bufImage != null){

            bufG2D = (Graphics2D) bufImage.getGraphics();
        }

        if (bufG2D != null){

            bufG2D.setColor(Color.WHITE);
            bufG2D.fillRect(0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT);

            bufG2D.setColor(Color.darkGray);


            //draw game here
            //bomberMan.draw(bufG2D);
            map.draw(bufG2D);
        }
    }

    /**
     * Tự động gọi khi add Panel vào Frame
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        g.drawImage(bufImage, 0, 0, this);
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
        long period = 1000*1000000/FPS;                 // Chu ky (nano giay)
        long beginTime;
        long sleepTime;

        int i=0;

        beginTime = System.nanoTime();                   // lay time he thong -> nano giay
        // Game loop
        while (isRunning){

            //update Game
            //render Game
            updateGame();
            RenderGame();
            repaint();

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

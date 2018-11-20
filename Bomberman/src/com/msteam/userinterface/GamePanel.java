package com.msteam.userinterface;

import com.msteam.effect.Animation;
import com.msteam.effect.FrameImage;

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

    FrameImage frame1, frame2, frame3, frame4, frame5, frame6, frame7;
    Animation anim;


    public GamePanel() {

        inputManager = new InputManager();
        try {
            BufferedImage image = ImageIO.read(new FileImageInputStream(new File("Bomberman\\data\\enemy-sprite-png-orc.png")));
            BufferedImage image1 = image.getSubimage(10,205,40,50);
            frame1 = new FrameImage("standFrame1",image1);
            BufferedImage image2 = image.getSubimage(75,205,40,50);
            frame2 = new FrameImage("standFrame2",image2);
            BufferedImage image3 = image.getSubimage(140,205,40,50);
            frame3 = new FrameImage("standFrame3",image3);
            BufferedImage image4 = image.getSubimage(204,205,40,50);
            frame4 = new FrameImage("standFrame4",image4);
            BufferedImage image5 = image.getSubimage(267,205,40,50);
            frame5 = new FrameImage("standFrame5",image5);
            BufferedImage image6 = image.getSubimage(330,205,40,50);
            frame6 = new FrameImage("standFrame6",image6);
            BufferedImage image7 = image.getSubimage(395,205,40,50);
            frame7 = new FrameImage("standFrame7",image7);

            anim = new Animation();
            anim.add(frame1,200*1000000);
            anim.add(frame2,200*1000000);
            anim.add(frame3,200*1000000);
            anim.add(frame4,200*1000000);
            anim.add(frame5,200*1000000);
            anim.add(frame6,200*1000000);
            anim.add(frame7,200*1000000);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    /**
     * Tự động gọi khi add Panel vào Frame
     * @param g
     */
    @Override
    public void paint(Graphics g){

        g.setColor(Color.WHITE);
        g.fillRect(0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT);


        Graphics2D g2 = (Graphics2D) g;

        frame1.draw(g2,50,50);
        frame2.draw(g2,130,50);
        frame3.draw(g2,210,50);
        frame4.draw(g2,290,50);
        frame5.draw(g2,370,50);
        frame6.draw(g2,450,50);
        frame7.draw(g2,520,50);

        anim.update(System.nanoTime());
        anim.draw(50,150,g2);

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

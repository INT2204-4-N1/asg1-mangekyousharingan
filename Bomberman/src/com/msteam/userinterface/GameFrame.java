package com.msteam.userinterface;

import com.msteam.effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    public static final int TILE_SIZE = 48;
    public static final int SCREEN_WIDTH = TILE_SIZE * (31/2);
    public static final int SCREEN_HEIGHT = TILE_SIZE * 13;

    GamePanel gamePanel;

    public GameFrame(){

        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();      // dimension de luu 2 gia tri(lay gia tri cua manh inh height and width)
        this.setBounds((dimension.width - SCREEN_WIDTH)/2,(dimension.height - SCREEN_HEIGHT)/2,SCREEN_WIDTH,SCREEN_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try{

            CacheDataLoader.getInstance().LoadData();
        }catch (Exception e){

            e.printStackTrace();
        }

        gamePanel = new GamePanel();
        add(gamePanel);

        this.addKeyListener(gamePanel);

    }

    public void startGame(){

        gamePanel.startGame();

    }
    public static void main (String[] args){

        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
        gameFrame.startGame();

    }

}

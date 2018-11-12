package com.msteam.userinterface;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    public static final int SCREEN_WIDTH = 1000;            // final gan nhu hang const bien se k bi thay doi
    public static final int SCREEN_HEIGHT = 600;

    GamePanel gamePanel;

    public GameFrame(){

        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();      // dimension de luu 2 gia tri(lay gia tri cua manh inh height and width)
        this.setBounds((dimension.width - SCREEN_WIDTH)/2,(dimension.height - SCREEN_HEIGHT)/2,SCREEN_WIDTH,SCREEN_HEIGHT);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

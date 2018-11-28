package com.msteam.gameobject;

import java.awt.*;

public class BomberMan {

    private float posX;
    private float posY;

    private float width;
    private float height;

    private float speedX;
    private float speedY;
    /**
     * Hướng đặt bom
     * đặt ở đuôi hoặc ở đầu (bắn đạn)
     */
    public static int DIR_LEFT;
    public static int DIR_RIGHT;
    public static int DIR_UP;
    public static int DIR_DOWN;
    private int direction;
    // Có thể không dùng, vì bom đặt ngay dưới hoạt ảnh nhân vật
    public BomberMan(float posX, float posY, float width, float height){

        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void update(){

        setPosX(getPosX() + speedX);
    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.GREEN);
        g2.fillRect((int) posX,(int) posY,(int) width,(int) height);
    }

    public void setPosX(float posX) {

        this.posX = posX;
    }

    public float getPosX() {

        return posX;
    }

    public void setPosY(float posY) {

        this.posY = posY;
    }

    public float getPosY() {

        return posY;
    }

    public void setWidth(float width) {

        this.width = width;
    }

    public float getWidth() {

        return width;
    }

    public void setHeight(float height) {

        this.height = height;
    }

    public float getHeight() {

        return height;
    }

    public void setSpeedX(float speedX) {

        this.speedX = speedX;
    }

    public float getSpeedX() {

        return speedX;
    }

    public void setSpeedY(float speedY) {

        this.speedY = speedY;
    }

    public float getSpeedY() {

        return speedY;
    }

    public void setDirection(int direction) {

        this.direction = direction;
    }

    public int getDirection() {

        return direction;
    }
}

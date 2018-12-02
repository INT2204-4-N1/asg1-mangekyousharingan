package com.msteam.gameobject;

import java.awt.*;

public abstract class ParticularObject extends GameObject{

    public static final int LEAGUE_TEAM = 1;
    public static final int ENEMY_TEAM = 2;

    public static final int ALIVE = 0;
    public static final int DEATH = 1;

    public static int DIR_LEFT = 1;
    public static int DIR_RIGHT = 2;
    public static int DIR_UP = 3;
    public static int DIR_DOWN = 4;
    private int direction;

    private int state = ALIVE;

    private float width;
    private float height;
    private float speedX;
    private float speedY;

    private int blood;

    private int damage;

    private int teamType;

    // Đoạn nhân vật bị nháy nháy k nhận dame
    private long startTimeNoHurt;
    private long timeForNoHurt;

    public ParticularObject(float x, float y, float width, float height, GameWorld gameWorld){

        super(x,y,gameWorld);
        setWidth(width);
        setHeight(height);
        //setBlood(blood);
    }

    public void setDirection(int direction) {

        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setSpeedX(float speedX) {

        this.speedX = speedX;
    }

    public float getSpeedX() {

        return speedX;
    }

    public void setSpeedY(float speedY){

        this.speedY = speedY;
    }

    public float getSpeedY() {

        return speedY;
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

    public void setTeamType(int teamType) {

        this.teamType = teamType;
    }

    public int getTeamType() {

        return teamType;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return state;
    }

    public void setBlood(int blood) {

        this.blood = blood;
    }

    public int getBlood() {

        return blood;
    }

    public void setDamage(int damage) {

        this.damage = damage;
    }

    public int getDamage() {

        return damage;
    }

    public Rectangle getBoundForCollisonWithMap(){

        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    public abstract void draw(Graphics2D g2);

    @Override
    public void update() {
        switch (state){
            case ALIVE:

                break;
            case DEATH:

                break;
        }
    }

    public void drawBoundCollisionWithMap(Graphics2D g2){

        Rectangle rect = getBoundForCollisonWithMap();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(),rect.y - (int) getGameWorld().camera.getPosY(),rect.width,rect.height);
    }

    public void drawBoundCollisionWithEnemy(Graphics2D g2){

        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(),rect.y - (int) getGameWorld().camera.getPosY(),rect.width,rect.height);

    }
}

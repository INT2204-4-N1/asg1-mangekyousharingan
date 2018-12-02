package com.msteam.gameobject;

public abstract class GameObject {

    private float posX;
    private float posY;
    private GameWorld gameWorld;

    public GameObject(float posX, float posY, GameWorld gameWorld){

        this.posX = posX;
        this.posY = posY;
        this.gameWorld = gameWorld;
    }

    public abstract void update();

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

    public void setGameWorld(GameWorld gameWorld) {

        this.gameWorld = gameWorld;
    }

    public GameWorld getGameWorld() {

        return gameWorld;
    }
}

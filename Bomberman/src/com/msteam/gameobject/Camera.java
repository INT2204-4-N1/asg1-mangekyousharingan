package com.msteam.gameobject;

public class Camera extends GameObject{

    private float widthView;
    private float heightView;

    private boolean isLooked = false;

    public Camera (float x, float y, float widthView, float heightView, GameWorld gameWorld){

        super(x,y,gameWorld);
        this.widthView = widthView;
        this.heightView = heightView;
    }

    public void lock(){

        isLooked = true;
    }
    public void unlock(){

        isLooked = false;
    }
    @Override
    public void update() {

        if (!isLooked){

            BomberMan mainCharacter = getGameWorld().bomberMan;

            if (mainCharacter.getPosX() - getPosX() > 400){
                setPosX(mainCharacter.getPosX() - 400);
            }
            if (mainCharacter.getPosX() - getPosX() < 200){
                setPosX(mainCharacter.getPosX() - 200);
            }
            if (mainCharacter.getPosY() - getPosY() > 400){
                setPosY(mainCharacter.getPosY() - 400);
            }
            else if (mainCharacter.getPosY() - getPosY() < 250){
                setPosY(mainCharacter.getPosY() - 250);
            }
        }
    }

    public void setHeightView(float heightView) {

        this.heightView = heightView;
    }

    public float getHeightView() {

        return heightView;
    }

    public void setWidthView(float widthView) {

        this.widthView = widthView;
    }

    public float getWidthView() {

        return widthView;
    }
}

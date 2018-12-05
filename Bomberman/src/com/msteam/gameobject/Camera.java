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
        BomberMan mainCharacter = getGameWorld().bomberMan;
        if (!isLooked){

            //BomberMan mainCharacter = getGameWorld().bomberMan;

            if (mainCharacter.getPosX() - getPosX() > 645){
                setPosX(mainCharacter.getPosX() - 645);
            }
            if (mainCharacter.getPosX() - getPosX() < 60){
                setPosX(mainCharacter.getPosX() - 60);
            }
            if (mainCharacter.getPosY() - getPosY() > 530){
                setPosY(mainCharacter.getPosY() - 530);
            }
            else if (mainCharacter.getPosY() - getPosY() < 60){
                setPosY(mainCharacter.getPosY() - 60);
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

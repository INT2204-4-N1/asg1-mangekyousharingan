package com.msteam.effect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameImage {

    private String name;
    private BufferedImage image;

    public FrameImage(){

        name = null;
        image = null;
    }

    public FrameImage(String name, BufferedImage image){

        this.name = name;
        this.image = image;

    }

    public FrameImage(FrameImage frameImage){

        image = new BufferedImage(frameImage.getImageWidth(),frameImage.getImageHeight(),frameImage.getImage().getType());
        Graphics g = image.getGraphics();   // get Graphics tu bat ky doi tuong nao g se la cai but chi ve len cai doi tuong do
        g.drawImage(frameImage.getImage(),0,0,null);

    }

    public int getImageWidth(){

        return image.getWidth();

    }

    public int getImageHeight(){

        return image.getHeight();

    }

    public void draw(Graphics2D g2, int x, int y){

        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2,null);

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getName() {

        return name;

    }

    public void setImage(BufferedImage image) {

        this.image = image;

    }

    public BufferedImage getImage() {

        return image;

    }
}

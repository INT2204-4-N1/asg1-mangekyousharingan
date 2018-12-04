package com.msteam.effect;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    private String name;

    private boolean isRepeated;

    private ArrayList<FrameImage> frameImages;
    private int currFrame;

    private ArrayList<Boolean> ignoreFrames;

    private ArrayList<Double> delayFrames;
    private long beginTime;

    private boolean drawRectFrame;

    public Animation(){

        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currFrame = 0;

        ignoreFrames = new ArrayList<Boolean>();

        frameImages = new ArrayList<FrameImage>();

        drawRectFrame = false;

        isRepeated = true;
    }

    public Animation(Animation animation){

        beginTime = animation.beginTime;
        currFrame = animation.currFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;

        delayFrames = new ArrayList<Double>();
        for (Double df : animation.delayFrames){
            delayFrames.add(df);
        }

        ignoreFrames = new ArrayList<Boolean>();
        for (boolean i : animation.ignoreFrames){
            ignoreFrames.add(i);
        }

        frameImages = new ArrayList<FrameImage>();
        for (FrameImage fi : animation.frameImages){
            frameImages.add(new FrameImage(fi));
        }

    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return this.name;
    }


    public void setFrameImages(ArrayList<FrameImage> frameImages) {

        this.frameImages = frameImages;
    }

    public ArrayList<FrameImage> getFrameImages() {

        return this.frameImages;
    }

    public void setCurrFrame(int currFrame) {

        if (currFrame >=0 && currFrame < frameImages.size()){

            this.currFrame = currFrame;
        }
        else{

            this.currFrame = 0;
        }
    }

    public int getCurrFrame() {

        return this.currFrame;
    }

    public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {

        this.ignoreFrames = ignoreFrames;
    }

    public ArrayList<Boolean> getIgnoreFrames() {

        return this.ignoreFrames;
    }

    public void setDelayFrames(ArrayList<Double> delayFrames) {

        this.delayFrames = delayFrames;
    }

    public ArrayList<Double> getDelayFrames() {

        return this.delayFrames;
    }

    public void setBeginTime(long beginTime) {

        this.beginTime = beginTime;
    }

    public long getBeginTime() {

        return this.beginTime;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {

        this.drawRectFrame = drawRectFrame;
    }

    public boolean getDrawRectFrame() {

        return this.drawRectFrame;
    }

    public void setIsRepeated(boolean repeated) {

        isRepeated = repeated;
    }

    public boolean getIsRepeated() {

        return this.isRepeated;
    }

    public boolean isIgnoreFrame (int id){

        return ignoreFrames.get(id);
    }

    public void setIgnoreFrame(int id){

        if (id > 0 && id < ignoreFrames.size()){

            ignoreFrames.set(id,true);
        }
    }

    public void unIgnoreFrame(int id){

        if (id > 0 && id < ignoreFrames.size()){

            ignoreFrames.set(id,false);
        }
    }

    /**
     * Chuyển động tác Animation
     */
    public void reset(){

        currFrame = 0;
        beginTime = 0;

        for (int i=0; i < ignoreFrames.size(); i++){

            ignoreFrames.set(i,false);
        }
    }

    public void add(FrameImage frameImage, double timeToNextFrame){

        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double(timeToNextFrame));
    }

    /**
     * Lấy Image Frame
     * @return - trả về ảnh
     */
    public BufferedImage getCurrImage(){

        return frameImages.get(currFrame).getImage();
    }

    /**
     * Chuyển sang Frame tiếp theo
     * @param currTime - time hiện tại
     */
    public void update(long currTime){

        if (beginTime == 0){

            beginTime = currTime;
        }
        else {

            if (currTime - beginTime > delayFrames.get(currFrame)){
                nextFrame();
                beginTime = currTime;
            }
        }
    }

    private void nextFrame(){

        if (currFrame >= frameImages.size() - 1){

            if (isRepeated){
                currFrame = 0;
            }
        }
        else{
            currFrame++;
        }

        if (ignoreFrames.get(currFrame)){
            nextFrame();
        }
    }

    /**
     * Ktra Animetion chạy xong chưa để chuyển trạng thái (VD nhảy xong -> đứng yên)
     * @return - true hoặc false
     */
    public boolean isLastFrame(){

        if (currFrame == frameImages.size() - 1){

            return true;
        }
        else{

            return false;
        }
    }


    public void draw(int x, int y, int _width, int _height, Graphics2D g2){

        BufferedImage image = getCurrImage();

        g2.drawImage(image, x - _width/2, y - _height/2,_width,_height,null);
        if (drawRectFrame){

            g2.drawRect(x - image.getWidth()/2, y - image.getHeight()/2,_width, _height);
        }
    }
}


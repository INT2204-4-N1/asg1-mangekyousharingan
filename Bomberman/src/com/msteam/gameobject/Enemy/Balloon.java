package com.msteam.gameobject.Enemy;

import com.msteam.effect.Animation;
import com.msteam.effect.CacheDataLoader;
import com.msteam.gameobject.GameWorld;
import com.msteam.gameobject.ParticularObject;

import java.awt.*;

public class Balloon extends ParticularObject {

    private Animation leftMove, rightMove;

    private long timeToChangeDirection;

    private double x1,x2;


    public Balloon(float x, float y, float width, float height, GameWorld gameWorld) {
        super(x, y, width, height, gameWorld);
        setTeamType(ENEMY_TEAM);
        leftMove = CacheDataLoader.getInstance().getAnimation("left");
        rightMove = CacheDataLoader.getInstance().getAnimation("right");
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
            //Rectangle rect = getBoundForCollisionWithMap();
            //rect.x += 20;
            //rect.width -= 40;
            //// TODO: chưa rõ chức năng của số 20 và 40
            return null;
    }



    @Override
    public void draw(Graphics2D g2) {
        drawBoundCollisionWithMap(g2);

    }
}

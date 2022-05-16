package com.mygdx.animacions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    public static final int SPEED = 500;
    public static final int DEFAULT_Y = 40;
    private static Texture texture;

    float x, y;
    int speed, direction;
    public boolean remove = false;

    public Bullet(float x, float y, int direction){
        this.x = x +40;
        this.y = y +40;
        this.speed = SPEED;
        this.direction = direction;

        if(texture == null){
            texture = new Texture("fireball.png");
        }
    }

    public void update(float deltaTime) {
        if(direction == 1){
            this.x -= SPEED * deltaTime;
        }
        else if(direction == 2){
            this.x += SPEED * deltaTime;
        }
        else if(direction == 3){
            this.y += SPEED * deltaTime;
        }
        else if(direction == 4){
            this.y -= SPEED * deltaTime;
        }

        if (this.y > Gdx.graphics.getHeight() || this.x > Gdx.graphics.getWidth()) {
            remove = true;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x ,y);
    }

}

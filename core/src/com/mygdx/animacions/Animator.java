package com.mygdx.animacions;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animator extends Game {

    public static final int WIDTH = 800, HEIGHT = 600;

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create(){

        batch = new SpriteBatch();

        font = new BitmapFont();
        this.setScreen(new menuGame(this));

    }

}

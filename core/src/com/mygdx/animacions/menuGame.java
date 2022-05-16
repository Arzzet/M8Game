package com.mygdx.animacions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class menuGame implements Screen {

    final Animator game;
    OrthographicCamera camera;
    Texture fondo;
    public menuGame(Animator game) {
        this.game = game;
        fondo = new Texture(Gdx.files.internal("background2.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //IntroBack.png
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(fondo, 0, 0);
        game.font.draw(game.batch, "Click to start!", 290, 200);
        game.batch.end();

        if ( (Gdx.input.isTouched())){
            game.setScreen(new mainGame(game));
            dispose();
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
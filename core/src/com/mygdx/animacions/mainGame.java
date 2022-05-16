package com.mygdx.animacions;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class mainGame extends ApplicationAdapter implements Screen {

    CastleDungeonII game;
    TextureAtlas textureAtlas;
    ArrayList<Bullet> bullets;

    private static final int FRAME_COLS = 14, FRAME_ROWS = 8;

    OrthographicCamera camera;
    Animation<TextureRegion> attackUpAnimate;
    Animation<TextureRegion> runUpAnimate;
    Animation<TextureRegion> attackDownAnimate;
    Animation<TextureRegion> runDownAnimate;
    Animation<TextureRegion> attackLeftAnimate;
    Animation<TextureRegion> runLeftAnimate;
    Animation<TextureRegion> attackRightAnimate;
    Animation<TextureRegion> runRightAnimate;



    private Texture runSpriteSheet;
    private SpriteBatch spriteBatch;
    private Rectangle animator;

    int fonsx, fonsy, x, y, carx, cary, test, balax, balay, timerb, db;
    boolean bullet = false;

    private Texture fondo = new Texture(Gdx.files.internal("background3.jpg"));
    private int nFondo = 1;

    float stateTime;

    public mainGame(CastleDungeonII game) {

        this.game = game;

        bullets = new ArrayList<Bullet>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, CastleDungeonII.WIDTH, CastleDungeonII.HEIGHT);

        spriteBatch = new SpriteBatch();
        runSpriteSheet = new Texture(Gdx.files.internal("warriorSheet.png"));

        TextureRegion[][] tmp = TextureRegion.split(runSpriteSheet,
                runSpriteSheet.getWidth() / FRAME_COLS,
                runSpriteSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] attackUp = new TextureRegion[FRAME_COLS];
        TextureRegion[] runUp = new TextureRegion[FRAME_COLS];
        TextureRegion[] attackDown = new TextureRegion[FRAME_COLS];
        TextureRegion[] runDown = new TextureRegion[FRAME_COLS];
        TextureRegion[] attackLeft = new TextureRegion[FRAME_COLS];
        TextureRegion[] runLeft = new TextureRegion[FRAME_COLS];
        TextureRegion[] attackRight = new TextureRegion[FRAME_COLS];
        TextureRegion[] runRight = new TextureRegion[FRAME_COLS];

        int index = 0;

        for  (int i = 0; i < FRAME_ROWS; i++){
            index = 0;
            for(int j = 0; j < FRAME_COLS; j++){

                if (i == 0)
                    attackUp[index++] = tmp[i][j];
                if (i == 1)
                    runUp[index++] = tmp[i][j];
                if (i == 2)
                    attackDown[index++] = tmp[i][j];
                if (i == 3)
                    runDown[index++] = tmp[i][j];
                if (i == 4)
                    attackLeft[index++] = tmp[i][j];
                if (i == 5)
                    runLeft[index++] = tmp[i][j];
                if (i == 6)
                    attackRight[index++] = tmp[i][j];
                if (i == 7)
                    runRight[index++] = tmp[i][j];
            }
        }

        attackUpAnimate = new Animation<TextureRegion>(0.025f, attackUp);
        runUpAnimate = new Animation<TextureRegion>(0.025f, runUp);
        attackDownAnimate = new Animation<TextureRegion>(0.025f, attackDown);
        runDownAnimate = new Animation<TextureRegion>(0.025f, runDown);
        attackLeftAnimate = new Animation<TextureRegion>(0.025f, attackLeft);
        runLeftAnimate = new Animation<TextureRegion>(0.025f, runLeft);
        attackRightAnimate = new Animation<TextureRegion>(0.025f, attackRight);
        runRightAnimate = new Animation<TextureRegion>(0.025f, runRight);

        animator = new Rectangle();
        animator.x = 100;
        animator.y = 100;
        animator.width = 120;
        animator.height = 120;

        stateTime = 0f;
        x=0;
        y=0;

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        TextureRegion currentFrame = runLeftAnimate.getKeyFrame(stateTime, true);
        if(nFondo == 1 && ((animator.x >= 715 ||animator.y >= 500))){
            fondo = new Texture(Gdx.files.internal("background.jpg"));

            animator.x = 100;
            nFondo = 2;

        }
        if(nFondo == 2 && ((animator.x >= 715 ||animator.y >= 500) || (animator.x <= 0 ||animator.y <= 0))){
            fondo = new Texture(Gdx.files.internal("background3.jpg"));
            animator.x = 715;
            nFondo = 1;

        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();


        game.batch.draw(fondo, 0, 0);

        int posicion = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            currentFrame = runLeftAnimate.getKeyFrame(stateTime, true);
            game.batch.draw(runLeftAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.x -= 100 * Gdx.graphics.getDeltaTime();
            posicion = 1;
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                currentFrame = attackLeftAnimate.getKeyFrame(stateTime, true);
                game.batch.draw(attackLeftAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                bullets.add(new Bullet(animator.x, animator.y, 1));
            }
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            currentFrame = runRightAnimate.getKeyFrame(stateTime, true);
            game.batch.draw(runRightAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.x += 100 * Gdx.graphics.getDeltaTime();
            posicion = 2;
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                currentFrame = attackRightAnimate.getKeyFrame(stateTime, true);
                game.batch.draw(attackRightAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                bullets.add(new Bullet(animator.x, animator.y, 2));
            }
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            currentFrame = runUpAnimate.getKeyFrame(stateTime, true);
            game.batch.draw(runUpAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.y += 100 * Gdx.graphics.getDeltaTime();
            posicion = 3;
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                currentFrame = attackUpAnimate.getKeyFrame(stateTime, true);
                game.batch.draw(attackUpAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                bullets.add(new Bullet(animator.x, animator.y, 3));
            }
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            currentFrame = runDownAnimate.getKeyFrame(stateTime, true);
            game.batch.draw(runDownAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.y -= 100 * Gdx.graphics.getDeltaTime();
            posicion = 4;
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                currentFrame = attackDownAnimate.getKeyFrame(stateTime, true);
                game.batch.draw(attackDownAnimate.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                bullets.add(new Bullet(animator.x, animator.y, 4));
            }
        }

        else{

            game.batch.draw(runDownAnimate.getKeyFrames()[1], animator.x, animator.y, animator.width, animator.height);
        }


        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for (Bullet bullet: bullets) {
            bullet.update(delta);
            if(bullet.remove){
                bulletsToRemove.add(bullet);
            }
        }
        bullets.remove(bulletsToRemove);


        for (Bullet bullet: bullets){
            bullet.render(game.batch);
        }
        game.batch.end();

    }
    public void createBala () {


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
        spriteBatch.dispose();
    }
}

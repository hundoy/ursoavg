package com.urso.avg.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by zohar on 2016/11/20.
 */
public class CardGame extends ApplicationAdapter {
    SpriteBatch batch;
    TextureAtlas atlas;
    Sprite front;
    Sprite back;
    OrthographicCamera cam;

    public final static float CARD_WIDTH = 1f;
    public final static float CARD_HEIGHT = CARD_WIDTH * 125f/200f;
    public final static float MINIMUM_VIEWPORT_SIZE = 5f;


    @Override
    public void create(){
        cam = new OrthographicCamera();

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.local("data/graphics/packtest.atlas"));

        front = atlas.createSprite("front");
        back = atlas.createSprite("back");

        front.setSize(CARD_WIDTH, CARD_HEIGHT);
        back.setSize(CARD_WIDTH, CARD_HEIGHT);
        front.setPosition(-1,1);
        back.setPosition(1,1);
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        front.draw(batch);
        back.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
    }

    @Override
    public void resize(int width, int height) {
        if (width>height){
            cam.viewportHeight = MINIMUM_VIEWPORT_SIZE;
            cam.viewportWidth = cam.viewportHeight * width*1.0f / height;
        } else{
            cam.viewportWidth = MINIMUM_VIEWPORT_SIZE;
            cam.viewportHeight = cam.viewportWidth * height*1.0f / width;
        }
        cam.update();
    }
}

package com.urso.avg.test;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by zohar on 2016/11/27.
 */
public class TestGameScreen implements Screen {
    TestCore game;
    TestGameWorld gameWorld;
    public TestGameScreen(TestCore game) {
        this.game = game;
        gameWorld = new TestGameWorld();
        Gdx.input.setCursorCatched(true);
    }
    @Override
    public void render(float delta) {
        gameWorld.render(delta);
    }
    @Override
    public void resize(int width, int height) {
        gameWorld.resize(width, height);
    }
    @Override
    public void dispose() {
        gameWorld.dispose();
    }

    @Override
    public void show() {

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
}

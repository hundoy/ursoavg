package com.urso.avg.input;

import com.badlogic.gdx.InputProcessor;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 2016/7/7.
 */
public class UrsoInpro implements InputProcessor {

    private UrsoAvgGame g;

    public UrsoInpro(UrsoAvgGame g){
        super();
        this.g = g;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        if (button == Input.Buttons.LEFT && g.logic.getWaitTime()== LogicCtrl.WAIT_CLICK){
//            g.logic.stopWait();
//        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

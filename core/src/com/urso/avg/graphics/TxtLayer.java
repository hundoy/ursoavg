package com.urso.avg.graphics;

import com.badlogic.gdx.math.Rectangle;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 2016/7/18.
 */
public class TxtLayer extends PicLayer {
    private Rectangle txtRect;
    private float lineSpace = 2;
    private boolean nowait = false;

    public TxtLayer(UrsoAvgGame g, int uid, String uname) {
        super(g, uid, uname);

        priority = uid*1000000;
    }

    public Rectangle getTxtRect() {
        return txtRect;
    }

    public void setTxtRect(Rectangle txtRect) {
        this.txtRect = txtRect;
    }

    public float getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(float lineSpace) {
        this.lineSpace = lineSpace;
    }

    public boolean isNowait() {
        return nowait;
    }

    public void setNowait(boolean nowait) {
        this.nowait = nowait;
    }
}

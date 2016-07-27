package com.urso.avg.graphics;

import com.badlogic.gdx.math.Rectangle;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 2016/7/18.
 */
public class TxtLayer extends PicLayer {
    private Rectangle txtRect;
    private float wordSpace = 0;
    private float lineSpace = 2;
    private boolean nowait = false;
    private float interTime = 0.2f;
    private float scaleX = 1f;
    private float scaleY = 1f;

    // ctrl variables
    private String curText = "";
    private int textIndex = 0;
    private boolean needDraw = false;

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

	public float getInterTime() {
		return interTime;
	}

	public void setInterTime(float interTime) {
		this.interTime = interTime;
	}

    public String getCurText() {
        return curText;
    }

    public void setCurText(String curText) {
        this.curText = curText;
        setTextIndex(0);
    }

    public int getTextIndex() {
        return textIndex;
    }

    public void setTextIndex(int textIndex) {
        this.textIndex = textIndex;
    }

    public void nextTextIndex(){
        this.textIndex++;
    }

    public boolean needDraw() {
        return needDraw;
    }
}

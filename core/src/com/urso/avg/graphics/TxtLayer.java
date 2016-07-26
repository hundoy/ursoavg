package com.urso.avg.graphics;

import com.badlogic.gdx.math.Rectangle;
import com.urso.avg.UrsoAvgGame;

import java.util.ArrayList;
import java.util.List;

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
    private int colNum;
    private int rowNum;

    public TxtLayer(UrsoAvgGame g, int uid, String uname) {
        super(g, uid, uname);

        priority = uid*1000000;
    }

    /**
     * caculate curText's eyescan and breath
     */
    private void caculate() {
        float curSizeX = game.font.getInitSize()*scaleX;
        float curSizeY = game.font.getInitSize()*scaleY;
        colNum = (int)Math.floor((txtRect.getWidth()+wordSpace)/(curSizeX+wordSpace));
        rowNum = (int)Math.floor((txtRect.getHeight()+lineSpace)/(curSizeY+lineSpace));
    }

    public List<String> curSentences(){
        List<List<String[]>> sentences = new ArrayList<List<String[]>>();
        int rowIndex = textIndex/colNum;
        int colIndex = textIndex%colNum;
        rowIndex = rowIndex%rowNum;
        for (int i=0; i<=rowIndex; i++){
            int jmax = colNum;
            if (i==rowIndex){
                jmax = colIndex;
            }
            List<String[]> sentence = new ArrayList<String[]>();
            for (int j=0; j<=jmax; j++){
            }


//            if (i==rowIndex){
//                sentences.add(curText.substring(i*colNum, textIndex+1));
//            } else{
//                sentences.add(curText.substring(i*colNum, i*colNum+colNum));
//            }
        }
        return null;
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
        caculate();
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
}

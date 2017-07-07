package com.urso.avg.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 2016/7/18.
 */
public class TxtLayer extends PicLayer {
    public final static int PRIORITY_RATE = 1000;

    private Rectangle txtRect;
    private float lineSpace = 2;
    private boolean nowait = false;
    private float interTime = 0.05f;
    private float scaleX = 1f;
    private float scaleY = 1f;

    // ctrl variables
    private String curText = "";
    private int blockStartIndex = 0;
    private int textIndex = 0;
    private String command = "";

    public TxtLayer(UrsoAvgGame g, int priority, String uname) {
        super(g, priority, uname);
    }

    /**
     * draw text
     */
    public void draw() {
        game.font.color(Color.RED);
        game.font.draw(txtRect.getX(), txtRect.getY());
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
        textIndex = 0;
        blockStartIndex = 0;
    }

    public void addCurText(String addText){
        curText += addText;
    }

    public int getTextIndex() {
        return textIndex;
    }

    public void setTextIndex(int textIndex) {
        this.textIndex = textIndex;
    }

    public void nextTextIndex(){
        this.textIndex++;

        if (textIndex>=curText.length()){
            // reach text end
//            game.logic.stopSay();
        } else{
            // check whether get out of region or meet stop command
            String curWord = "";
            boolean isCommand = false;
            char curChar = curText.charAt(textIndex);
            if (curChar=='\\' && textIndex!=curText.length()-1){
                textIndex++;
                curWord = String.valueOf(curChar) + String.valueOf(curText.charAt(textIndex));
                isCommand = true;
                command = curWord;
            } else{
                curWord = String.valueOf(curChar);
                command = "";
            }

            if (isCommand){
                if (command.equalsIgnoreCase("\\l")){
//                    game.logic.waitAction(LogicCtrl.WAIT_CLICK);
                } else if (curWord.equalsIgnoreCase("\\p")){
                    // next block
                    blockStartIndex = textIndex+1;
                }
            } else{
                String msg = curText.substring(blockStartIndex, textIndex+1);
                float preHeight = game.font.preDrawText(msg, txtRect.width, Align.left);
                Gdx.app.debug("text", msg+" ("+preHeight+")");
                if (preHeight>txtRect.height){
                    textIndex--;
//                    game.logic.waitAction(LogicCtrl.WAIT_CLICK);
                }
            }
        }
    }

    public void afterWait() {
        if (command.length()>0){

        }

        nextTextIndex();
    }
}

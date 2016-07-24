package com.koko.core;

import com.badlogic.gdx.utils.TimeUtils;
import com.koko.bean.KokoStory;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.graphics.TxtLayer;
import com.zohar.common.util.FileUtil;
import org.json.JSONObject;

import java.util.Map;

import static com.urso.avg.tool.ToolUtil.isnnb;

/**
 * KokoSayer
 * author: hundoy
 * date: 2016.05.11 16:43:32
 * description:
 */
public class KokoSayer {
    public final static String EXT_NAME = "sty";
    public final static int WAIT_NO = 0;
    public final static int WAIT_TIMER = 1;
    public final static int WAIT_CLICK = 2;
    public final static int WAIT_STH = 3;

    private UrsoAvgGame game;
    private String scriptPath = "";
    private String startStoryName;
    private KokoThinker thinker;
    private Map<String, KokoStory> stories;
    private JSONObject configJson;

    private KokoStory curStory;
    private String curText = "";
    private int textIndex = 0;
    private float lastWordTime;

    // 0-nowait 1-wait time 2-wait click 3-wait forever
    private int waitType = WAIT_NO;
    private float waitTime = 0;
    private float startWaitTime = 0;

    public KokoSayer(String configFile){
        String configStr = FileUtil.getStringFromFile(configFile);
        configJson = new JSONObject(configStr);
    }

    public void init(String scriptPath, String startStoryName){
        this.scriptPath = scriptPath;
        this.startStoryName = startStoryName.toLowerCase();
        think();
    }

    public void start(UrsoAvgGame game){
        this.game = game;
        say(startStoryName);
    }


    public void update(){
        if (waitType==WAIT_TIMER && time() - startWaitTime > waitTime){
            goonPlease();
        }
        
        if (curText.length()>0){
        	TxtLayer tl = game.layer.getFocusLayer();
        	if (tl!=null && !tl.isNowait() && time()-lastWordTime > tl.getInterTime()){
        		textIndex++;
        	}
        }
    }

    // wait time
    public void waitPlease(float t){
        startWaitTime = time();
        waitTime = t;
        waitType = WAIT_TIMER;
    }

    // wait click or forever
    public void waitPlease(boolean isClick){
        if (isClick) waitType = WAIT_CLICK;
        else waitType = WAIT_STH;
    }

    public void goonPlease(){
        waitType = WAIT_NO;
        curStory.goon(game);
    }

    private void say(String storyName) {
        if (stories.containsKey(storyName)){
            curStory = stories.get(storyName);
            curStory.start(game);
        } else{
            KokoException.error(KokoExType.FILE_NOT_FOUND, scriptPath+storyName+".sty");
        }
    }

    private void think() {
    	
        thinker = new KokoThinker();
        String textErr = thinker.setTextDef(configJson.getString("textDefine"));
        if (isnnb(textErr)) KokoException.warn(KokoExType.NO_DEF_CLASS, textErr);
        String err = thinker.setLineDefine(configJson.getJSONObject("lineDefine"));
        if (isnnb(err)) KokoException.warn(KokoExType.NO_DEF_CLASS, err);
        stories = thinker.init(scriptPath, EXT_NAME);
    }

    // getter and setter
    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public int getWaitType() {
        return waitType;
    }

    public float time(){
        return TimeUtils.nanoTime()*1.0f/1000000000f;
    }
    public void setCurText(String txt){
    	curText = txt;
    	textIndex = -1;
    	lastWordTime = time();
    }
}

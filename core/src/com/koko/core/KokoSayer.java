package com.koko.core;

import com.badlogic.gdx.utils.TimeUtils;
import com.koko.bean.KokoLine;
import com.koko.bean.KokoStory;
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

//    private UrsoAvgGame game;
    private String scriptPath = "";
    private String startStoryName;
    private KokoThinker thinker;
    private Map<String, KokoStory> stories;
    private JSONObject configJson;

    private KokoStory curStory;

    // 0-nowait 1-wait time 2-wait click 3-wait forever
    private int waitType = WAIT_NO;
    private boolean isSaying = false;

    public KokoSayer(String configFile){
        String configStr = FileUtil.getStringFromFile(configFile);
        configJson = new JSONObject(configStr);
    }

    public void init(String scriptPath, String startStoryName){
        this.scriptPath = scriptPath;
        this.startStoryName = startStoryName.toLowerCase();
        think();
    }

    public void start(){
        say(startStoryName);
    }

    private void say(String storyName) {
        if (stories.containsKey(storyName)){
            curStory = stories.get(storyName);
            curStory.start();
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

    public KokoLine curLine() {
        return curStory.getCurPage().getCurLine();
    }

    public KokoLine nextLine(){
        curStory.getCurPage().nextLine();
        while (curStory.getCurPage().isReachEnd()){
            curStory.nextPage();

            if (curStory.isReachEnd()){
                return null;
            }
        }
        return curLine();
    }
}

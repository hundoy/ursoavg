package com.koko.core;

import com.koko.bean.KokoStory;
import com.urso.avg.UrsoAvgGame;
import com.zohar.common.util.FileUtil;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.urso.avg.tool.ToolUtil.isnnb;
import static com.zohar.common.util.ToolUtil.*;

/**
 * KokoSayer
 * author: hundoy
 * date: 2016.05.11 16:43:32
 * description:
 */
public class KokoSayer {
    public final static String EXT_NAME = "sty";

    private UrsoAvgGame game;
    private String scriptPath = "";
    private String startStoryName;
    private KokoThinker thinker;
    private Map<String, KokoStory> stories;
    private JSONObject configJson;

    private KokoStory curStory;

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

    public void next() {

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

    public static void main(String[] args){
        KokoSayer sayer = new KokoSayer("data/config/kokoconfig.json");
        sayer.init("data/scenario/", "first");
//        sayer.start();
    }
}

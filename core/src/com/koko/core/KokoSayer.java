package com.koko.core;

import com.koko.bean.KokoLine;
import com.koko.bean.KokoStory;
import com.koko.helper.Aktoro;
import com.koko.helper.Horlogho;
import com.koko.helper.Muzikisto;
import com.zohar.common.util.FileUtil;
import org.json.JSONObject;

import java.util.Map;

import static com.zohar.common.util.ToolUtil.*;

/**
 * KokoSayer
 * author: hundoy
 * date: 2016.05.11 16:43:32
 * description: sayer is the only main control of all scenarios.
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

    // helpers
    public Aktoro aktoro;
    public Muzikisto muzikisto;
    public Horlogho horlogho;

    private KokoStory curStory;

    // 0-nowait 1-wait time 2-wait click 3-wait forever
    private int waitType = WAIT_NO;
    private boolean isSaying = false;

    public KokoSayer(String configFile){
        // get json config file
        String configStr = FileUtil.getStringFromFile(configFile);
        configJson = new JSONObject(configStr);
    }

    public void initHelpers(Aktoro aktoro, Muzikisto muzikisto, Horlogho horlogho){
        this.aktoro = aktoro;
        this.muzikisto = muzikisto;
        this.horlogho = horlogho;
    }

    public void init(String scriptPath, String startStoryName){
        this.scriptPath = scriptPath;
        this.startStoryName = startStoryName.toLowerCase();
        think();
    }

    public void start(){
        // check helpers
        if (!isnn(aktoro,muzikisto,horlogho)){
            KokoException.error(KokoExType.NO_HELPER, "aktoro, muzikisto, horlogho");
        }

        // start to tell the first story
        tell(startStoryName);
    }

    private void tell(String storyName) {
        if (stories.containsKey(storyName)){
            curStory = stories.get(storyName);
            curStory.start();
        } else{
            KokoException.error(KokoExType.FILE_NOT_FOUND, scriptPath+storyName+".sty");
        }
    }

    private void think() {
        thinker = new KokoThinker(this);
        String textErr = thinker.setTextDef(configJson.getString("textDefine"));
        if (isnob(textErr)) KokoException.warn(KokoExType.NO_DEF_CLASS, textErr);
        String err = thinker.setLineDefine(configJson.getJSONObject("lineDefine"));
        if (isnob(err)) KokoException.warn(KokoExType.NO_DEF_CLASS, err);
        stories = thinker.init(scriptPath, EXT_NAME);
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


    // getter and setter
    public KokoThinker getThinker(){
        return thinker;
    }
    public Aktoro getAktoro(){
        return aktoro;
    }
    public Horlogho getHorlogho(){
        return horlogho;
    }
    public Muzikisto getMuzikisto(){
        return muzikisto;
    }
}

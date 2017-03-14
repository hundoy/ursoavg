package com.koko.core;

import com.koko.bean.KokoLine;
import com.koko.bean.KokoStory;
import com.zohar.common.util.FileUtil;
import com.zohar.common.util.RegExpUtil;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.urso.avg.tool.ToolUtil.isnnb;
import static com.zohar.common.util.ToolUtil.isNullOrEmpty;

/**
 * KokoThinker
 * author: hundoy
 * date: 2016.05.11 16:43:42
 * description:
 */
public class KokoThinker {
    private Map<String, String> scriptMap;
    private Map<String, Class> lineDefMap;
    private Class textDef;
    private KokoSayer sayer;

    public KokoThinker(KokoSayer sayer){
        this.sayer = sayer;
    }

    /**
     * get all the script files in scriptPath with extName. Generate them into story beans
     * @param scriptPath
     * @param extName
     * @return
     */
    public Map<String, KokoStory> init(String scriptPath, String extName) {
        getScripts(scriptPath, extName);

        Map<String, KokoStory> stories = new HashMap<String, KokoStory>();
        if (scriptMap!=null){
            for (String k: scriptMap.keySet()){
                String script = scriptMap.get(k);
                KokoStory story = new KokoStory();
                story.init(this, "sty_"+k, k, script);
                stories.put(k, story);
            }
        }
        return stories;
    }

    private void getScripts(String scriptPath, String extName) {
        scriptMap = new HashMap<String, String>();
        List<File> files = FileUtil.dirFiles(scriptPath, extName);
        if (!isNullOrEmpty(files)){
            for (File f: files){
                scriptMap.put(FileUtil.getLowFileHead(f.getName()), FileUtil.getStringFromFile(f));
            }
        }
    }

    public String setLineDefine(JSONObject lineDefine) {
        lineDefMap = new HashMap<String, Class>();
        StringBuilder sb = new StringBuilder();
        for (String type: lineDefine.keySet()){
            String clsname = lineDefine.getString(type);
            try {
                Class clz = Class.forName(clsname);
                if (type.indexOf(",")>-1){
                    String[] typeArr = type.split(",");
                    for (String oneType: typeArr){
                        lineDefMap.put(oneType, clz);
                    }
                } else{
                    lineDefMap.put(type, clz);
                }
            } catch (ClassNotFoundException e) {
                sb.append("No class ").append(clsname).append(" is defined for line ").append("type").append("\n");
            }
        }
        return sb.toString();
    }

    public KokoLine genLine(String script){
        String type = RegExpUtil.regFindFirstByFirstGroup("\\[(\\S+)", script);
        Class clz = null;
        if (isnnb(type)){
            // [xxx ]
            clz = lineDefMap.get(type);
        } else {
            // text
            clz = textDef;
        }
        try {
            KokoLine line = (KokoLine)clz.newInstance();
            return line;
        } catch (Exception e) {
        }

        return null;
    }

    public String setTextDef(String textDefine) {
        StringBuilder sb = new StringBuilder();
        try {
            Class clz = Class.forName(textDefine);
            textDef = clz;
        } catch (ClassNotFoundException e) {
            sb.append("No class ").append(textDefine).append(" is defined for text line!");
        }

        return sb.toString();
    }
}

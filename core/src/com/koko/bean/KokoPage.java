package com.koko.bean;

import com.zohar.common.util.ToolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hundoy on 16-5-16.
 */
public class KokoPage extends KokoBaseBean {
    private List<KokoLine> lines;
    private KokoLine curLine;
    private int index;
    private KokoStory story;

    @Override
    protected void analyze() {
        lines = new ArrayList<KokoLine>();
        String[] lineArr = oriScript.split("\n");
        for (String lineStr: lineArr){
            if (!ToolUtil.isNullOrBlank(lineStr)){
                lineStr = lineStr.trim();
                if (!lineStr.startsWith("//")){ // skip comment
                    KokoLine line = thinker.genLine(lineStr);
                    if (line!=null){
                        line.init(thinker, "", "", lineStr);
                        lines.add(line);
                    }
                }
            }
        }
    }

    public void start() {
        index = -1;
        nextLine();
    }

    public void nextLine() {
        index++;
        if (index>lines.size()-1){
            // do nothing
        } else{
            curLine = lines.get(index);
        }
    }

    public boolean reachEnd(){
        return index>lines.size()-1;
    }
}

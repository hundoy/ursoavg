package com.koko.bean;

import com.urso.avg.UrsoAvgGame;
import com.zohar.common.util.RegExpUtil;
import com.zohar.common.util.ToolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hundoy on 16-5-11.
 */
public class KokoStory extends KokoBaseBean{
    private List<KokoPage> pages;
    private KokoPage curPage;

    @Override
    protected void analyze() {
        pages = new ArrayList<KokoPage>();
        // *label|labeName\ncontent
        List<List<String>> rs = RegExpUtil.regFindAll("\\*([^|\\n]+)\\|?(.+)?\\n([^*]*)", oriScript);
        if (!ToolUtil.isNullOrEmpty(rs)){
            for (List<String> pageInfo: rs){
                KokoPage page = new KokoPage();
                page.init(thinker, pageInfo.get(0), pageInfo.get(1), pageInfo.get(2));
                pages.add(page);
            }
        }
    }

    public void start(UrsoAvgGame game) {
        for (KokoPage page: pages){
            curPage = page;
            curPage.start(game);
        }
    }

    public void startFrom(String pageLabel){

    }
}

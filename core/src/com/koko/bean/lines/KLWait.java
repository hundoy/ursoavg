package com.koko.bean.lines;

import com.koko.bean.KokoLine;
import com.koko.bean.KokoPage;
import com.urso.avg.UrsoAvgGame;
import com.zohar.common.util.ToolUtil;

/**
 * Created by Administrator on 2016/7/5.
 */
public class KLWait extends KokoLine {
    @Override
    public void process(UrsoAvgGame game, KokoPage kpage) {
        if (ToolUtil.isNum(dp)){
            // wait specific time
            game.sayer.waitPlease(Float.parseFloat(dp));
        } else if (dp.equalsIgnoreCase("c") || dp.equalsIgnoreCase("click")){
            // wait click
            game.sayer.waitPlease(true);
        } else{
            // wait forever
            game.sayer.waitPlease(false);
        }

    }
}

package com.urso.avg.lines;

import com.koko.bean.KokoLine;
import com.urso.avg.UrsoAvgGame;
import com.zohar.common.util.ToolUtil;

/**
 * Created by Administrator on 2016/7/5.
 */
public class KLWait extends KokoLine {
    // wait click   [wt/wait c/click]
    // wait time(seconds)  [wt 3]
    // wait forever [wt]
    @Override
    public void process(UrsoAvgGame g) {
        if (ToolUtil.isNum(dp)){
            // wait specific time
            g.logic.waitTime(Float.parseFloat(dp));
        } else if (dp.equalsIgnoreCase("c") || dp.equalsIgnoreCase("click")){
            // wait click
            g.logic.waitAction();
        } else{
            // wait forever
            g.logic.waitAction();
        }

    }

    @Override
    public void afterWait(UrsoAvgGame g) {
    }
}

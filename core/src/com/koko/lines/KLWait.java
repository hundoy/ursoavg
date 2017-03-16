package com.koko.lines;

import com.koko.bean.KokoLine;
import com.zohar.common.bean.DicBean;
import com.zohar.common.util.ToolUtil;

/**
 * Created by Administrator on 2016/7/5.
 */
public class KLWait extends KokoLine {
    // wait click   [wt/wait c/click]
    // wait time(seconds)  [wt 3]
    // wait forever [wt]
    @Override
    public void process() {
        DicBean dic = new DicBean();
        if (ToolUtil.isNum(dp)){
            // wait specific time
//            g.logic.waitTime(Float.parseFloat(dp));
            dic.put("type", "time");
            dic.put("time", Float.parseFloat(dp));
        } else if (dp.equalsIgnoreCase("c") || dp.equalsIgnoreCase("click")){
            // wait click
//            g.logic.waitAction(LogicCtrl.WAIT_CLICK);
            dic.put("type", "click");
        } else{
            // wait forever
//            g.logic.waitAction(LogicCtrl.WAIT_FOREVER);
            dic.put("type", "action");
        }

        sayer.getHorlogho().waitAction(dic);
    }

    @Override
    public void afterWait() {
    }
}

package com.koko.lines;

import com.koko.bean.KokoLine;
import com.zohar.common.bean.DicBean;

/**
 * Created by hundoy on 16-5-19.
 */
public class KLText extends KokoLine {
    @Override
    public void process() {
        // XXX display text...
        System.out.println(oriScript);
//        g.layer.getFocusLayer().addCurText(oriScript);
//        g.logic.say();
        DicBean dic = new DicBean();
        dic.put("text", oriScript);
        sayer.getAktoro().talk(dic);
    }

    @Override
    public void afterWait() {
//        g.layer.getFocusLayer().afterWait();

    }
}

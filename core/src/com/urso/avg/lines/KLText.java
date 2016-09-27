package com.urso.avg.lines;

import com.koko.bean.KokoLine;
import com.koko.bean.KokoPage;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 16-5-19.
 */
public class KLText extends KokoLine {
    @Override
    public void process(UrsoAvgGame g) {
        // XXX display text...
        System.out.println(oriScript);
        g.layer.getFocusLayer().addCurText(oriScript);
        g.logic.say();
    }

    @Override
    public void afterWait(UrsoAvgGame g) {
        g.layer.getFocusLayer().afterWait();
    }
}

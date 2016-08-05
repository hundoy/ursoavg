package com.koko.bean.lines;

import com.koko.bean.KokoLine;
import com.koko.bean.KokoPage;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 16-5-19.
 */
public class KLText extends KokoLine {
    @Override
    public void process(UrsoAvgGame g, KokoPage kpage) {
        // XXX display text...
        System.out.println(oriScript);
        g.layer.getFocusLayer().addCurText(oriScript);
        g.sayer.saySentence();
    }
}

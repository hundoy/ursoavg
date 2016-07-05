package com.koko.bean.lines;

import com.koko.bean.KokoLine;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 16-5-19.
 */
public class KLText extends KokoLine {
    @Override
    public void process(UrsoAvgGame game) {
        // XXX display text...
        System.out.println(oriScript);
    }
}

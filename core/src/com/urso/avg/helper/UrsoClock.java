package com.urso.avg.helper;

import com.koko.helper.Horlogho;
import com.urso.avg.UrsoAvgGame;
import com.zohar.common.bean.DicBean;

/**
 * Created by hundoy on 2017/3/30.
 */
public class UrsoClock implements Horlogho{
    private UrsoAvgGame g;

    public UrsoClock(UrsoAvgGame g) {
        this.g = g;
    }

    @Override
    public float nowSec() {
        return 0;
    }

    @Override
    public void waitAction(DicBean dic) {

    }
}

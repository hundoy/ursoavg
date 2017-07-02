package com.urso.avg.helper;

import com.koko.helper.Muzikisto;
import com.urso.avg.UrsoAvgGame;
import com.zohar.common.bean.DicBean;

/**
 * Created by hundoy on 2017/3/30.
 */
public class UrsoMusician implements Muzikisto {
    private UrsoAvgGame g;

    public UrsoMusician(UrsoAvgGame g) {
        this.g = g;
    }

    @Override
    public void play(DicBean params) {

    }
}

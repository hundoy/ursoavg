package com.urso.avg.helper;

import com.koko.helper.Aktoro;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.graphics.PicLayer;
import com.zohar.common.bean.DicBean;

/**
 * Created by hundoy on 2017/3/30.
 */
public class UrsoActor implements Aktoro{
    private UrsoAvgGame g;

    public UrsoActor(UrsoAvgGame g) {
        this.g = g;
    }

    @Override
    public void paint(DicBean params) {
        PicLayer layer = g.layer.getPicLayer(params.get("id"));

    }

    @Override
    public void talk(DicBean params) {

    }

    @Override
    public void talkOneWord(String word) {

    }

    @Override
    public void textset(DicBean pdic) {

    }

    @Override
    public float getWordInterSec() {
        return 0;
    }
}

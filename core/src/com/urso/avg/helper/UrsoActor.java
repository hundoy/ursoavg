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
        g.layer.addPicLayer(params.get("id"));
        g.layer.setPicLayer(params);
    }

    @Override
    public void talk(DicBean params) {

    }

    @Override
    public void talkOneWord(String word) {

    }

    @Override
    public void textset(DicBean params) {
        // text layer will be used repeatedly, so remain it and change it instead of freeing it if there is no necessary.
        if (!g.layer.hasLayer(params.get("id"))){
            g.layer.addTxtLayer(params.get("id"));
        }
        g.layer.setTxtLayer(params);
    }

    @Override
    public float getWordInterSec() {
        return 0;
    }
}

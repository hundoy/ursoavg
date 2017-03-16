package com.koko.lines;

import com.koko.bean.KokoLine;

/**
 * Created by hundoy on 16-5-19.
 */
public class KLLayer extends KokoLine {
    @Override
    protected void analyze() {
        super.analyze();
    }

    @Override
    public void process() {
//        int page = LayerCtrl.LAYER_FORE;
//        if (pdic.get("page").equalsIgnoreCase("back")){
//            page = LayerCtrl.LAYER_BACK;
//        }
//        g.layer.addPicLayer(page, dp);
        pdic.put("id", dp);
        sayer.getAktoro().paint(pdic);
//        g.layer.setPicLayer(pdic);
    }
}

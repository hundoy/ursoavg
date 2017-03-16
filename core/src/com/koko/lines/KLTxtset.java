package com.koko.lines;

import com.koko.bean.KokoLine;

/**
 * Created by hundoy on 2016/7/18.
 */
public class KLTxtset extends KokoLine {
    @Override
    public void process() {
//        int page = LayerCtrl.LAYER_FORE;
//        if (pdic.get("page").equalsIgnoreCase("back")){
//            page = LayerCtrl.LAYER_BACK;
//        }
//
//        g.layer.addTxtLayer(page, dp);
        pdic.put("id", dp);
        sayer.getAktoro().textset(pdic);
//        g.layer.setTxtLayer(pdic);

//        kpage.nextLine(g);
    }
}

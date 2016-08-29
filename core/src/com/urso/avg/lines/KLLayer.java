package com.urso.avg.lines;

import com.koko.bean.KokoLine;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.ctrl.LayerCtrl;

/**
 * Created by hundoy on 16-5-19.
 */
public class KLLayer extends KokoLine {
    @Override
    protected void analyze() {
        super.analyze();
    }

    @Override
    public void process(UrsoAvgGame g) {
        int page = LayerCtrl.LAYER_FORE;
        if (pdic.get("page").equalsIgnoreCase("back")){
            page = LayerCtrl.LAYER_BACK;
        }
        g.layer.addPicLayer(page, dp);
        pdic.put("id", dp);
        g.layer.setPicLayer(pdic);
    }
}

package com.urso.avg.lines;

import com.koko.bean.KokoLine;
import com.koko.bean.KokoPage;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.ctrl.LayerCtrl;

/**
 * Created by hundoy on 2016/7/18.
 */
public class KLTxtset extends KokoLine {
    @Override
    public void process(UrsoAvgGame g, KokoPage kpage) {
        int page = LayerCtrl.LAYER_FORE;
        if (pdic.get("page").equalsIgnoreCase("back")){
            page = LayerCtrl.LAYER_BACK;
        }

        g.layer.addTxtLayer(page, dp);
        pdic.put("id", dp);
        g.layer.setTxtLayer(pdic);

        kpage.nextLine(g);
    }
}

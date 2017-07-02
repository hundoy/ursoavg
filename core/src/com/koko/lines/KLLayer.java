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
        pdic.put("id", dp);
        sayer.getAktoro().paint(pdic);
    }
}

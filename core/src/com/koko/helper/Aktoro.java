package com.koko.helper;

import com.zohar.common.bean.DicBean;

/**
 * Created by hundoy on 2017/3/14.
 * interface about graphics display
 */
public interface Aktoro {

    // paint a graphics
    public void paint(DicBean params);

    // paint text
    // text = [string] a line of text.
    public void talk(DicBean params);

    // talk only one word.
    public void talkOneWord(String word);

    // text set
    public void textset(DicBean pdic);

    // get the focus text layer's defined word interval time(second)
    public float getWordInterSec();

    // say part of word
}

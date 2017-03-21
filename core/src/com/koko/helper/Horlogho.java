package com.koko.helper;

import com.zohar.common.bean.DicBean;

/**
 * Created by Administrator on 2017/3/14.
 * interface about time control
 */
public interface Horlogho {

    // get current seconds
    public float nowSec();

    // wait action
    // type = [string] time/click/action
    // time = [float] second of wait. Only available when type=time
    public void waitAction(DicBean dic);
}

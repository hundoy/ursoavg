package com.urso.avg.ctrl;

import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 2016/8/24.
 */
public class LogicCtrl {
    private UrsoAvgGame g;

    public LogicCtrl(UrsoAvgGame g) {
        this.g = g;
    }

    public void start(){
        g.sayer.start();
    }

    public void update() {

    }
}

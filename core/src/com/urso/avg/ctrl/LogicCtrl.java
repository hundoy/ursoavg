package com.urso.avg.ctrl;

import com.badlogic.gdx.utils.TimeUtils;
import com.koko.bean.KokoLine;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 2016/8/24.
 */
public class LogicCtrl {
    private UrsoAvgGame g;

    private float waitTime = 0; // if -1, wait forever
    private float lastTime = 0;

    public LogicCtrl(UrsoAvgGame g) {
        this.g = g;
    }

    public void start(){
        g.sayer.start();
    }

    public void update() {
        while (waitTime==0 || (waitTime >0 && now()>waitTime+lastTime)){
            // wait end, call afterWait function
            waitTime=0;
            KokoLine line = g.sayer.curLine();
            line.afterWait(g);

            // if line reach end, process next line
            if (line.reachEnd()){
                line = g.sayer.nextLine();
                line.process(g);
            }
        }
    }

    public void waitTime(float t){
        waitTime = t;
        lastTime = now();
    }

    public void waitAction(){
        waitTime = -1;
        lastTime = now();
    }

    public float now(){
        return TimeUtils.nanoTime()*1.0f/1000000000f;
    }
}

package com.urso.avg.ctrl;

import com.badlogic.gdx.utils.TimeUtils;
import com.koko.bean.KokoLine;
import com.urso.avg.UrsoAvgGame;

/**
 * Created by hundoy on 2016/8/24.
 */
public class LogicCtrl {
    public static final int WAIT_CLICK = -2;
    public static final int WAIT_FOREVER = -1;

    private UrsoAvgGame g;

    private float waitTime = 0; // -2 wait click -1, wait forever;  0, no wait;  >0, wait some time
    private float lastTime = 0;

    public LogicCtrl(UrsoAvgGame g) {
        this.g = g;
    }

    public void start(){
        g.sayer.start();
    }

    public void update() {
        // core idea: read line by line until meet wait
        while (waitTime==0 || (waitTime >0 && now()>waitTime+lastTime)){
            // wait end, call afterWait function
            waitTime=0;
            KokoLine line = g.sayer.curLine();
            line.afterWait(g);

            // if line reach end, process next line
            if (line.isReachEnd()){
                line = g.sayer.nextLine();
                line.process(g);
            }
        }
    }

    public void waitTime(float t){
        waitTime = t;
        lastTime = now();
    }

    public void waitAction(int type){
        waitTime = type;
        lastTime = now();
    }

    public void stopWait(){
        waitTime = 0;
        KokoLine line = g.sayer.curLine();
        line.afterWait(g);
    }

    public float getWaitTime() {
        return waitTime;
    }public void stopSay() {
    }

    public void say() {
        waitTime = g.layer.getFocusLayer().getInterTime();
        lastTime = now();
    }

    public float now(){
        return TimeUtils.nanoTime()*1.0f/1000000000f;
    }


    
}

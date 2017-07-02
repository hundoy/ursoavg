package com.urso.avg.desktop;

import com.badlogic.gdx.tools.hiero.Hiero;

/**
 * Created by zohar on 2016/10/4.
 */
public class ToolLauncher {
    public static void main(String[] args){
        try {
            Hiero.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

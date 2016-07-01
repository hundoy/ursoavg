package com.urso.avg.bean;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hundoy on 2016/6/27.
 */
public class PicBean {
    private PicsrcBean picsrc;
    private Sprite sp;
    private String aname;

    public PicBean(PicsrcBean picsrc, String aname){
        this.picsrc = picsrc;
        this.aname = aname;
        genSp();
    }

    private void genSp() {
        if (picsrc.getType()==PicsrcBean.TYPE_TEXTURE){
            sp = new Sprite(picsrc.getTexture());
        } else if (picsrc.getType()==PicsrcBean.TYPE_ATLAS){
            sp = picsrc.getAtlas().createSprite(aname);
        }
    }

    public void release(){
        picsrc.release(this);
    }

    public void dispose() {
    }

    public PicsrcBean getPicsrc() {
        return picsrc;
    }

    public void setPicsrc(PicsrcBean picsrc) {
        this.picsrc = picsrc;
    }

    public Sprite getSp() {
        return sp;
    }

    public void setSp(Sprite sp) {
        this.sp = sp;
    }
}

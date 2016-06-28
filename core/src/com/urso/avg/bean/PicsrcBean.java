package com.urso.avg.bean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Hundoy on 2016/6/27.
 */
public class PicsrcBean {
    public static final int TYPE_TEXTURE = 0;
    public static final int TYPE_ATLAS = 1;

    private String lowName;
    private String fileName;
    private String filePath;
    private int type; // 0- texture 1-atlas
    private Texture texture;
    private TextureAtlas atlas;

    private Array<PicBean> picArr; // Cache picBean(sprite) in use

    public PicsrcBean(String lowName, String fileName, String filePath, int type){
        this.lowName = lowName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.type = type;
        picArr = new Array<PicBean>();
    }

    // create a sprite with texture
    public PicBean createPic(){
        loadTexture();
        PicBean pic = new PicBean(this, null);
        picArr.add(pic);
        return pic;
    }

    private void loadTexture() {
        if (texture == null){
            texture = new Texture(Gdx.files.local(filePath + fileName));
        }
    }

    // create a sprite with atlas name
    public PicBean createPic(String name){
        loadAtlas();
        PicBean pic = new PicBean(this, name);
        picArr.add(pic);
        return pic;
    }

    private void loadAtlas() {
        if (atlas==null){
            atlas = new TextureAtlas(filePath + fileName);
        }
    }

    // release all the source in this bean
    public void release() {
        for (PicBean pic: picArr){
            pic.dispose();
        }
        if (texture!=null){
            texture.dispose();
        }
        if (atlas!=null){
            atlas.dispose();
        }
    }


    public String getLowName() {
        return lowName;
    }

    public int getType() {
        return type;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
}

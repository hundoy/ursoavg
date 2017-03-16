package com.koko.bean;

import com.koko.core.KokoSayer;

/**
 * Created by hundoy on 16-5-16.
 */
public class KokoBaseBean {
    protected String oriScript;
    protected String id;
    protected String name;
    protected KokoSayer sayer;

    public String getOriScript() {
        return oriScript;
    }

    public void setOriScript(String oriScript) {
        this.oriScript = oriScript;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(KokoSayer sayer, String id, String name, String oriScript){
        this.sayer = sayer;
        setId(id);
        setName(name);
        setOriScript(oriScript);

        analyze();
    }

    protected void analyze() {

    }
}

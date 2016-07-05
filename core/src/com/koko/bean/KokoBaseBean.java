package com.koko.bean;

import com.koko.core.KokoThinker;

/**
 * Created by hundoy on 16-5-16.
 */
public class KokoBaseBean {
    protected String oriScript;
    protected String id;
    protected String name;
    protected KokoThinker thinker;

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

    public void init(KokoThinker thinker, String id, String name, String oriScript){
        this.thinker = thinker;
        setId(id);
        setName(name);
        setOriScript(oriScript);

        analyze();
    }

    protected void analyze() {

    }
}

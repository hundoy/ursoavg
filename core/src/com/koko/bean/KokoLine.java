package com.koko.bean;

import com.zohar.common.bean.DicBean;
import com.zohar.common.util.RegExpUtil;
import com.zohar.common.util.ToolUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hundoy on 16-5-16.
 */
public class KokoLine extends KokoBaseBean{
    protected String command;
    protected String dp;
    protected Map<String,String> kps;
    protected DicBean pdic;

    @Override
    protected void analyze() {
        // common format: [command dp k1=v1 k2=v2 k3=v3...]
        kps = new HashMap<String, String>();
        List<String> rs = RegExpUtil.regFindFirstByAllGroup("\\[(\\S+)(\\s+([^\\]\\s]+))?(\\s+([^\\]]+))*", oriScript);
        if (rs.size()>=1 && !ToolUtil.isNullOrBlank(rs.get(0))) command = rs.get(0);
        if (rs.size()>=3 && !ToolUtil.isNullOrBlank(rs.get(2))) dp = rs.get(2);
        String kpsStr = "";
        if (rs.size()>=5 && !ToolUtil.isNullOrBlank(rs.get(4))) kpsStr = rs.get(4);
        if (!ToolUtil.isNullOrBlank(kpsStr)){
            List<List<String>> kprs = RegExpUtil.regFindAll("\\s*([^=\\s]+)=(\\S+)", kpsStr);
            for (List<String> kv: kprs){
                if (kv.size()==2 && !ToolUtil.isNullOrBlank(kv.get(0)) && !ToolUtil.isNullOrBlank(kv.get(1)))
                kps.put(kv.get(0), kv.get(1));
            }
        }
        pdic = new DicBean(kpsStr);
    }

    // only run once, when read this line
    public void process(){

    }

    public void afterWait(){

    }

    public boolean isReachEnd(){
        return true;
    }
}

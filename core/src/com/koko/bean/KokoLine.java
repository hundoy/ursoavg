package com.koko.bean;

import com.urso.avg.UrsoAvgGame;
import com.urso.avg.bean.DicBean;
import com.zohar.common.util.RegExpUtil;
import com.zohar.common.util.ToolUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hundoy on 16-5-16.
 */
public class KokoLine extends KokoBaseBean{
    protected String dp;
    protected Map<String,String> kps;
    protected DicBean pdic;

    @Override
    protected void analyze() {
        kps = new HashMap<String, String>();
        List<String> rs = RegExpUtil.regFindFirstByAllGroup("\\[(\\S+)(\\s+(\\S+))?(\\s+([^\\]]+))*", oriScript);
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

    public void process(UrsoAvgGame game){

    }
}

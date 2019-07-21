package com.midgroup.smallbook.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
/**
 * Author:ZZY
 * Date:2019-6-15
 * Target:
 */
public class LayuiUtil {
    public static JSONObject ToLayJson(ArrayList list)
    {
        JSONObject rootObject = new JSONObject();
        rootObject.put("code", 0);
        rootObject.put("msg", "1");
        if(list==null)
        {
            rootObject.put("count", 0);
            rootObject.put("data",null);
        }
        else
        {
            rootObject.put("count", list.size());
            rootObject.put("data",list);
        }

        return rootObject;
    }
}

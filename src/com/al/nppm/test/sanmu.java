package com.al.nppm.test;

import com.al.nppm.common.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangBaoQiang
 * @ClassName: sanmu
 * @description: TODO
 * @date 2019/11/1420:03
 * @Version 1.0
 */
public class sanmu {
    public static void main(String[] args) {
        Map map = new HashMap<>();
       long longType = Long.parseLong(StringUtil.isEmpty(map.get("prodInstId"))?"0":String.valueOf(map.get("prodInstId")));
        if (longType == 0) {
            System.out.println(longType);
        }

    }
}

package com.al.nppm.test;

import com.al.nppm.common.utils.PropertiesUtil;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WangBaoQiang
 * @ClassName: sanmu
 * @description: TODO
 * @date 2019/11/1420:03
 * @Version 1.0
 */
public class sanmu {
    private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
    private static Logger logger = Logger.getLogger(sanmu.class);
    @Autowired
    OrdBillMapper ordBillMapperDao;
    public static void main(String[] args) {
        /*String url=propertiesUtil.readProperty("bon3.servInfo.url");
        JSONObject jsonObject=new JSONObject();
        JSONObject jsonObjectList = new JSONObject();
        jsonObject.put("areaCode","0431");
        jsonObject.put("valueType","1");
        jsonObject.put("value","17390026401");
        jsonObject.put("queryType","1");
        jsonObjectList.put("stdCcrQueryServ", jsonObject);
        Boolean flag = false;
        try {
            logger.info("场景三的用户信息查询请求报文：" + jsonObjectList.toJSONString());
            String result = HttpRequestUtil.callRemoteForPostByJSON(url, jsonObjectList.toJSONString());
            logger.info("场景三的用户信息查询返回报文：" + result);
            JSONObject json = (JSONObject) JSONObject.parse(result);
            if (!"0".equals(json.getString("errorCode"))) {
                logger.error("调用接口失败，返回码："+json.getString("errorCode"));
            }
            JSONArray array = json.getJSONObject("stdCcaQueryServRes").getJSONArray("stdCcaQueryServList");
            JSONObject resultJson = array.getJSONObject(0);
            String abmState = resultJson.getString("servState");
            *//*if ("0".equals(abmState)) {
                flag = true;
            }*//*
            String stopType = "0";
            String state = "100000";
            if ((("0".equals(stopType) && "100000".equals(state)) ||
                    ("130001".equals(stopType) && "120000".equals(state)))
                            &&flag) {
                System.out.println(flag);
            }
        }catch (Exception ex){
            logger.error("调用接口失败，返回码："+ex.getMessage());
        }*/
        String str = "OFFERINS_ERROR_001=取商品实例路由表失败【offerInstId】:304121160660。处理时长33ms";
        int pos = str.indexOf("。");
        String offerInstId = str.substring(43, pos);
        System.out.println(offerInstId);
    }
}

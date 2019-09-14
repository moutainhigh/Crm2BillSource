package com.al.nppm.business.inter.http.impl;

import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.core.SynMapContextHolder;
import com.al.nppm.business.inter.service.impl.PlcaProdInstService;
import com.al.nppm.model.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * plca首话单激活、停复机状态激活、用户属性修改
 */

@Controller
@RequestMapping(value="billing")
public class PlcaProdInstController {
    private static Logger logger = Logger.getLogger(PlcaProdInstController.class);

    @Autowired
    private PlcaProdInstService plcaProdInstService;
    @Autowired
    public IProdInstMapper prodinstDao;
    /**
     * 停复机接口
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/plca/tingfuji",method = RequestMethod.POST )
    public Result tingfuji(HttpServletRequest req, @RequestBody String requestBody) {
        SynMapContextHolder.remove();
        SynMapContextHolder.init();
        Map<String, List<?>> synMap = new HashMap<String, List<?>>();
        SynMapContextHolder.initSynMap(synMap);
// 定义个ARCH_GRP_ID  plca+时间+随机数  新建序列 SEQ_PLCA_ARCH_GRP_ID 100000000开始

        Result result=new Result();
        JSONObject jsonData = JSON.parseObject(requestBody);
        String orderType= String.valueOf(jsonData.get("orderType"));

        JSONArray array=(JSONArray)jsonData.get("chgState");
        try {
            plcaProdInstService.tingfuji(array, result);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        logger.debug("请求报文："+requestBody+",处理结果："+result.getStatus());
        return result;
    }

    /**
     * 首话单激活
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/plca/shouhuadan",method = RequestMethod.POST )
    public Result shouhuadan(HttpServletRequest req, @RequestBody String requestBody) {
        SynMapContextHolder.remove();
        SynMapContextHolder.init();
        Map<String, List<?>> synMap = new HashMap<String, List<?>>();
        SynMapContextHolder.initSynMap(synMap);

        Result result=new Result();
        JSONObject jsonData = JSON.parseObject(requestBody);
        String orderType= String.valueOf(jsonData.get("orderType"));

        JSONArray array=(JSONArray)jsonData.get("chgState");
        try{
            plcaProdInstService.shouhuadan(array,result);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        logger.debug("请求报文："+requestBody+",处理结果："+result.getStatus());
        return result;
    }

    /**
     * 用户属性新增、修改
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/plca/prodInstAttr",method = RequestMethod.POST )
    public Result prodInstAttr(HttpServletRequest req, @RequestBody String requestBody) {
        Result result=new Result();
        JSONObject jsonData = JSON.parseObject(requestBody);
        JSONArray array=(JSONArray)jsonData.get("prodInstAttr");
        try {
            plcaProdInstService.prodInstAttr(array, result);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        logger.debug("请求报文："+requestBody+",处理结果："+result.getStatus());
        return result;
    }


}

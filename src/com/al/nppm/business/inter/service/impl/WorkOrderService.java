package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.dao.PlcaProdInstMapper;
import com.al.nppm.business.inter.service.IHisService;
import com.al.nppm.business.inter.service.IRouteService;
import com.al.nppm.business.order.dao.WorkOrderMapper;
import com.al.nppm.model.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 停复机工单处理--长流程
 */

@Service("workorderservice")
public class WorkOrderService {

    private static Logger logger = Logger.getLogger(WorkOrderService.class);

    private static SimpleDateFormat yyyyMMddHHmmss_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    private PlcaProdInstMapper plcaProdInstMapper;
    @Autowired
    public IProdInstMapper prodinstDao;
    @Autowired
    public IRouteService routeDao;
    @Autowired
    public IHisService hisService;
    @Autowired
    public WorkOrderMapper workOrderMapper;
    @Autowired
    private PlcaProdInstService plcaProdInstService;

    public void scanWorkOrder(String[] args){
        Date date = new Date();
        logger.debug("----------定时执行CRMTABLE---------------" + yyyyMMddHHmmss_sdf.format(date).toString());
        Map queryMap = new HashMap();
        List<Map<String, Object>> list = workOrderMapper.selectWorkOrder(queryMap);//获取工单表数据 取10E状态数据处理，成功的修改为10F
        if (list.size() > 0) {
            for(Map workOrderMap:list){
                Result result=new Result();

                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                JSONArray array=new JSONArray();
                try {
                    JSONObject json=new JSONObject();
                    array.add(json);
                    String oweBusinessTypeId=String.valueOf(workOrderMap.get("oweBusinessTypeId"));
                    if("27".equals(oweBusinessTypeId)||"28".equals(oweBusinessTypeId)){
                        //断网属性
                        json.put("attrId",1001);//断网属性attrId=1001省份确认有没有变动
                        if("27".equals(oweBusinessTypeId)){
                            json.put("attrValue",1);
                        }else if("28".equals(oweBusinessTypeId)){
                            json.put("attrValue",0);
                        }
                        json.put("prodInstId",workOrderMap.get("servId"));
//                        json.put("opTime",d.format(workOrderMap.get("createdDate")));
//                        json.put("effDate",sdf.format(workOrderMap.get("createdDate")));
                        json.put("effDate",sdf.format(new Date()));
                        json.put("expDate","3000-01-01 00:00:00");
                        plcaProdInstService.updateProdInstAttr(array, result);
                    }else{
                        setStopTypeAndState(json,oweBusinessTypeId);
                        json.put("prodInstId",workOrderMap.get("servId"));
                        json.put("opTime",sdf.format(new Date()));
                        plcaProdInstService.tingfuji(array, result);
                    }

                }catch (Exception ex){
                    logger.error(ex.getMessage());
                }
                if("0".equals(result.getStatus())){
                    workOrderMap.put("procState","10F");
                    transactionManager.commit(status);
                }else{
                    workOrderMap.put("procState","10G");//失败状态置为10G
                    transactionManager.rollback(status);
                }
                //workOrderMap设置状态
                workOrderMapper.updateWorkOrder(workOrderMap);
            }
        }
        logger.debug("----------定时任务完成---------------" + yyyyMMddHHmmss_sdf.format(date).toString());
    }

    public void setStopTypeAndState(JSONObject json,String oweBusinessTypeId){
        if("8".equals(oweBusinessTypeId)||"9".equals(oweBusinessTypeId)){
            json.put("stopType","0");
            json.put("bssState","100000");
        }else if("6".equals(oweBusinessTypeId)){
            json.put("stopType","130001");
            json.put("bssState","120000");
        }else if("2".equals(oweBusinessTypeId)){
            json.put("stopType","130002");
            json.put("bssState","120000");
        }else if("32".equals(oweBusinessTypeId)){
            json.put("stopType","150121");
            json.put("bssState","120000");
        }else if("10".equals(oweBusinessTypeId)){
            json.put("stopType","0");
            json.put("bssState","110002");
        }else if("33".equals(oweBusinessTypeId)){
            json.put("stopType","200000");
            json.put("bssState","120000");
        }else if("34".equals(oweBusinessTypeId)){
            json.put("stopType","150002");
            json.put("bssState","120000");
        }else if("35".equals(oweBusinessTypeId)){
            json.put("stopType","150004");
            json.put("bssState","120000");
        }
    }
}

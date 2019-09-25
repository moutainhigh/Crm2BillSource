package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.inter.service.IErrorDealService;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;
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
 * @author WangBaoQiang
 * @ClassName: ErrorDealService
 * @description: 处理错单程序
 * @date 2019/9/1710:29
 * @Version 1.0
 */
@Service("errorDealService")
public class ErrorDealService implements IErrorDealService {
    @Autowired
    OrdBillMapper ordBillMapperDao;
    private static Logger logger = Logger.getLogger(ErrorDealService.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void errorDealServiceTask(String[] args) throws Exception {

        Date date = new Date();
        logger.debug("----------定时执行errorDealServiceTask---------------" + sdf.format(date).toString());
        //处理业务逻辑。  flag=1处理成功，其他失败
        int flag = -1;
        Message msg = new Message();
        Map<String, Object> updateMap = new HashMap<String, Object>();
        //处理账务关系不存在的错单，过户的工单有的时候会有三条，需要删掉一条。
        List<Map<String, Object>> orderList = ordBillMapperDao.selectOrdBillError("PRODACCT_ERROR_013");
        if (orderList.size() > 0) {
            for (Map<String, Object> orderMap : orderList) {
                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try {
                    flag = doErrorDeal(orderMap, msg);
                    String strResultmsgString;
                    if (flag < 0) {
                        // 回滚
                        transactionManager.rollback(status);
                    } else {
                        transactionManager.commit(status);
                    }
                    //}

                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback(status);
                    logger.error("处理失败：" + e.getMessage());
                }

            }

        }
    }

    @Override
    public int doErrorDeal(Map itemMap, Message msg) throws Exception {
        long archGrpId = Long.parseLong(itemMap.get("ARCH_GRP_ID").toString());
        if (ordBillMapperDao.delOrdBillProdInstAcctRel(archGrpId) < 0) {
            return -1;
        }
        return 1;
    }
}

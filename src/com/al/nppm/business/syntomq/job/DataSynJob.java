package com.al.nppm.business.syntomq.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.al.nppm.business.syntomq.mapper.DataSynMapper;
import com.al.nppm.common.utils.DateUtils;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;

@Component
public class DataSynJob {
	@Resource
	private DataSynMapper dataSynMapper;
	@Autowired
	public OrdBillMapper  ordBillDao;
	
	@Transactional
	public void task(){
		System.out.println( "----------------------oooooooooooooooooo" );
		Date bDate = DateUtils.getMinuteDate( -30 );
		Date eDate = DateUtils.getCurrentDate();
//		List<Map<String, Object>> list =dataSynMapper.queryOrdBill( bDate,eDate );
		List<Map<String, Object>> list =ordBillDao.queryOrdBill( bDate,eDate );
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "bDate", bDate );
		map.put( "eDate", eDate );
		map.put( "orderType", 1);
		map.put( "receiveCnt", list.size() );
		map.put( "rejCnt", 0 );
		map.put( "errCnt", 0 );
		map.put( "rejRate", "0%" );
		dataSynMapper.insertDataSynStatistics( map );
		
	}
}

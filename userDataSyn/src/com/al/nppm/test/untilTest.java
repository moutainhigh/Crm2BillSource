package com.al.nppm.test;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.ConvertUtils;

import com.al.nppm.common.utils.StringUtil;

public class untilTest {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * Date date = new Date();
		 * 
		 * Map map1 = new HashMap(); map1.put("id", 11);
		 * 
		 * Map map2 = new HashMap(); map2.put("name", "test");
		 * List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		 * list1.add(map2); map1.put("cust", list1);
		 * 
		 * 
		 * List<Map<String,Object>> list = (List<Map<String,Object>>)
		 * map1.get("cust"); Map m = list.get(0);
		 * System.out.println(m.get("name"));
		 */

		// //, ORDER_ITEM_ID, CUST_ID, TAX_PAYER_ID, PARTY_ID, CUST_NAME,
		// CUST_NUMBER
		// String str = "TAX_PAYER_ID,"
		// +"TAX_ID,"
		// +"TAX_NAME,"
		// +"TAX_PROVINCE_CODE,"
		// +"TAX_RELA_TEL,"
		// +"TAX_RELA_ADDR,"
		// +"TAX_BANK_NAME,"
		// +"TAX_BANK_ACCT,"
		// +"START_DATE,"
		// +"TAX_END_DATE,"
		// +"GENERAL_TAXPAYER_FLAG,"
		// +"VAT_INVOICES_FLAG,"
		// +"BILL_DELIVER_WAY,"
		// +"BILL_DELIVER_ADDR,"
		// +"EFF_DATE,"
		// +"EXP_DATE,"
		// +"OPER_TYPE";
		//
		// String[] sArray=str.split(",");
		// for(int i=0;i<sArray.length;i++)
		// {
		// String s = sArray[i].toLowerCase();
		// //System.out.println(sArray[i].toLowerCase());
		//
		// String a[] = s.split("_");
		// String bb="";
		// for(int k=0;k<a.length;k++)
		// {
		//
		// if(k>0)
		// {
		// String ai =a[k].substring(0, 1).toUpperCase();
		// String bi = a[k].substring(1);
		// bb=bb+""+ai+""+bi;
		//
		// }else if (k==0){
		// bb=bb+""+a[0];
		// }
		//
		// }
		// System.out.print(bb+",");
		// }
		//
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String DateStr = d.format(date);

		System.out.println(DateStr);
		System.out.println();
	}

	public <T> T handData(T model, Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (!checkComplexData(entry.getValue())) {
				Field field = null;
				try {
					field = model.getClass().getDeclaredField(entry.getKey());
				} catch (Exception e) {
					// logger.info("异常继续循环!");
					continue;
				}
				Object defualtValue = entry.getValue();
				// 设置可读
				field.setAccessible(true);

				// 对date 类型需要把字符串转成时间类型2018-03-22 15:46:10
				if (field.getType() == java.sql.Date.class
						|| field.getType() == java.util.Date.class) {
					// 20170301000000
					defualtValue = StringUtil.strToDate(defualtValue + "",
							"yyyy-MM-dd-HH:mm:ss");
				} else {
					// 需要进行数据转换
					if (field.getName() == "statusCd") {
						// if("1000".equals(defualtValue+"")){
						// defualtValue =1;
						// }else if("1100".equals(defualtValue+"")){
						// defualtValue =2;
						// }else if("1200".equals(defualtValue+"")){
						// defualtValue =3;
						// }
					}
					defualtValue = ConvertUtils.convert(defualtValue,
							field.getType());
				}
				try {
					field.set(model, defualtValue);
				} catch (Exception e) {
					// logger.info(entry.getKey()+"=="+entry.getValue()+"转换成"+model.getClass().getName()+"发生异常");
				}
			}
		}
		return model;

	}

	/**
	 * 验证该数据是否位复杂对象，比如JsonObject,JsonArray,集合等数据定义为复杂对象
	 * 
	 * @param object
	 * @return
	 */
	public static boolean checkComplexData(Object object) {
		boolean flag = false;
		if (object != null) {
			if (JSONObject.class.isAssignableFrom(object.getClass())) {
				flag = true;
			} else if (Collection.class.isAssignableFrom(object.getClass())) {
				flag = true;
			}
		}
		return flag;
	}

}

package com.al.nppm.common.utils;

import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private DateUtils() throws Exception{
		throw new Exception("do not instance it");
	}
	
	public static String yyyyMM = "yyyyMM";
	public static String yyyyMMdd = "yyyyMMdd";
	public static String yyyyMMddTIME = "yyyyMMddHHmmss";
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	/**
	 * <p>获取 当前日期 时间位根据pattern 确定  分别为 0==00:00:00 59==23:59:59</p>
	 * @return
	 * @author HL
	 * @since 2017年9月25日 下午2:23:45
	 * <p>modify mark:</p>
	 */
	public static Date getCurrentDay(Integer pattern){
		Calendar current = Calendar.getInstance();
		return setPattern(current,pattern);
	}
	/**
	 * <p>获取 昨天日期 时间位根据pattern 确定  分别为 00:00:00 23:59:59</p>
	 * @param
	 * @return
	 * @throws
	 * @author HL
	 * @since 2017年11月28日 下午4:18:37
	 * <p>modify mark:</p>
	 */
	public static Date getYestoDay(Integer pattern){
		Calendar current = Calendar.getInstance();
		current.add( Calendar.DAY_OF_MONTH, -1 );
		return setPattern(current,pattern);
	}
	private static Date setPattern(Calendar calendar,Integer pattern){
		if(pattern.compareTo( 0 )==0){
			calendar = setZero(calendar);
		}else {
			calendar = set59(calendar);
		}
		return calendar.getTime();
	}
	
	public static Date getMonthFirstDay(Integer offset,Integer pattern){
		Calendar current = Calendar.getInstance();
		current.add( Calendar.MONTH, offset );
		current.set( Calendar.DAY_OF_MONTH, 1 );
		return setPattern(current,pattern);
	}
	public static Date getMonthLastDay(Integer offset,Integer pattern){
		Calendar current = Calendar.getInstance();
		current.add( Calendar.MONTH, offset );
		int num = current.getActualMaximum( Calendar.DATE );
		current.set( Calendar.DAY_OF_MONTH, num );
		return setPattern(current,pattern);
	}
	
	public static Date getCurrentDate(){
		return new Date();
	}
	/**
	 * <p>获取当前日期的周一</p>
	 * @return
	 * @author HL
	 * @since 2017年10月19日 下午2:38:39
	 * <p>modify mark:</p>
	 */
	public static Date getMonDay(Integer offset,Integer pattern){
		Calendar calendar = Calendar.getInstance();
		calendar.add( Calendar.WEEK_OF_YEAR, offset );
		calendar.set( Calendar.DAY_OF_WEEK, Calendar.MONDAY );
		return setPattern(calendar,pattern);
	}
	/**
	 * <p>获取当前日期的周日</p>
	 * @return
	 * @author HL
	 * @since 2017年10月19日 下午2:38:39
	 * <p>modify mark:</p>
	 */
	public static Date getSunDay(Integer offset,Integer pattern){
		Calendar calendar = Calendar.getInstance();
		calendar.add( Calendar.WEEK_OF_YEAR, offset );
		calendar.setFirstDayOfWeek( Calendar.MONDAY );
		calendar.set( Calendar.DAY_OF_WEEK, Calendar.SUNDAY );
		return setPattern(calendar,pattern);
	}
	public static String toYYYYMMDDString(Date date){
		return dateString(date,yyyyMMdd);
	}
	public static String dateString(Date date,String format){
		simpleDateFormat.applyPattern( format );
		return simpleDateFormat.format( date );
	}
	public static Date getBillingCycle(Integer offset){
		Date date = getMonthFirstDay( offset, 0 );
		return date;
	}
	//按分钟  offset 分钟偏移量
	public static Date getMinuteDate(Integer offset){
		long l  = getCurrentDate().getTime();
		Date date = new Date( l+offset*60*1000 );
		return date;
	}
	
	/*public static Date localDatetoDate(LocalDate lDate){
		ZonedDateTime zdt = lDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from( zdt.toInstant() );
	}*/
	/*********************************************************************/
	//设置时间位00：00：00
	private static Calendar setZero(Calendar current){
		current.set( Calendar.HOUR_OF_DAY, 0 );
		current.set( Calendar.MINUTE, 0 );
		current.set( Calendar.SECOND, 0 );
		return current;
	}
	//设置时间位23：59：59
	private static Calendar set59(Calendar current){
		current.set( Calendar.HOUR_OF_DAY, 23 );
		current.set( Calendar.MINUTE, 59 );
		current.set( Calendar.SECOND, 59 );
		return current;
	}
	
	/**
	 * 获取下个月第一天
	 * @param date
	 * @return
	 */
	public static Date getNextMonthFirstDay(Date date){
        Calendar current = Calendar.getInstance();
        current.setTime(date);
        current.add( Calendar.MONTH, 1 );
        current.set( Calendar.DAY_OF_MONTH, 1 );
        return setPattern(current,0);
    }
	/**
	 * 当前日期往后推迟几天
	 * @param date
	 * @return
	 */
	public static Date getNextSomeDay(Date date,int dayCnt){
        Calendar current = Calendar.getInstance();
        current.setTime(date);
        current.add( Calendar.DAY_OF_MONTH, dayCnt );
        return setPattern(current,0);
    }
	public static void main(String[] args) {
		System.out.println(getNextMonthFirstDay(new Date()) );
	}
}

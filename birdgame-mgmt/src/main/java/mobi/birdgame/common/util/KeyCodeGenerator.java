package mobi.birdgame.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 保密码、序列号生成工具
 *
 */
public class KeyCodeGenerator {
	public static final DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final DateFormat dateFormat3 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final DateFormat dateFormat4 = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 获取当前时间数值，精确到秒
	 * @return int 秒
	 */
	public static int getCurrentTimestamp(){
		return (int)(System.currentTimeMillis()/1000);
	}
	/**
	 * 获取结束时间,截止到23:59:59
	 * @param date
	 * @return
	 */
	public static int getEndTimestamp(final Date date){
		Calendar cal = Calendar.getInstance();
		if(date == null){
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return (int)(cal.getTimeInMillis()/1000);
	}
	/**
	 * 获取开始时间,从0:00:00
	 * @param date
	 * @return
	 */
	public static int getBeginTimestamp(final Date date){
		Calendar cal = Calendar.getInstance();
		if(date == null){
			cal.setTime(new Date());
		} else {
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return (int)(cal.getTimeInMillis()/1000);
	}
	/**
	 * 根据尝试次数、最后尝试时间计算本次是否应该推迟重试
	 * 【发送时间点：0,5,20,45,80,125,360,735,1280】
	 * @param tryTimes int 尝试次数
	 * @param lastTryTime int 最后尝试时间
	 * @return boolean 如果推迟尝试，返回true，否则返回false
	 */
	public static boolean delayTry(int tryTimes, int lastTryTime, byte status) {
		if (status == 0) {
			return false; //状态为0不延迟
		}
		int currentTime = KeyCodeGenerator.getCurrentTimestamp();
		int delayMinute = tryTimes * tryTimes * 5 *(Math.max(1,(tryTimes-4)));
		if ((currentTime - lastTryTime) / 60 < delayMinute) {
			return true;
		}
		return false;
	}
	/**
	 * int类型时间转化为时间串
	 * @param intTime
	 * @return
	 */
	public static String parseTime2String(final int intTime, final DateFormat dateFormat){
		if(intTime <= 0){
			return dateFormat.format(new Date());
		}
		long time = 1000L * intTime;
		Date date = new Date(time);
		
		return dateFormat.format(date);
	}
	public static String parseTime2String(final int intTime){
		return parseTime2String(intTime, dateFormat1);
	}
	public static final char[] chars = new char[]{'a','b','c','d','e','f','g','h','i','j',
		'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5',
		'6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
		'Q','R','S','T','U','V','W','X','Y','Z'};
	
	public static String produceCode(int length){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i ++){
			sb.append(chars[(int)(((long)(System.currentTimeMillis() * Math.random())) % (1L*chars.length))]);
		}
		return sb.toString();
	}
}

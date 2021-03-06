package mobi.birdgame.common.util;

public class ArraysUtil {
	
    public static Long[] toLongArr(String[] strArr){
        Long[] longArr = new Long[strArr.length]; 
        for(int i = 0; i < strArr.length; i++){
            longArr[i] = Long.parseLong(strArr[i]);
        }
        return longArr;
    }
    
    public static Long[] toLongArr(long[] longArr){
        Long[] rs = new Long[longArr.length]; 
        for(int i = 0; i < longArr.length; i++){
            rs[i] = Long.valueOf(longArr[i]);
        }
        return rs;
    }
    
    public static long[] toLongArr(Long[] longArr){
        long[] rs = new long[longArr.length];
        for(int i = 0; i < longArr.length; i++){
            rs[i] = Long.valueOf(longArr[i]);
        }
        return rs;
    }
    
    public static String toRawString(Long[] ids, String separator){
    	if(ids==null || ids.length==0){
    		return "";
    	}
    	StringBuilder builder = new StringBuilder();
    	
    	for(Long id : ids){
    		if(id != null){
    			builder.append(id.longValue()).append(separator);
    		}
    	}
    	
    	if(builder.length() > 0){
    		return builder.substring(0, builder.length()-separator.length());
    	}
    	
    	return builder.toString();
    }
    
    public static void main(String[] args) {
		Long[] ids = new Long[]{1001L, 1002L, 1003L, 1004L};
		ids = new Long[]{1L};
		System.out.println(toRawString(ids, ","));
	}
}

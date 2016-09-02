package mobi.birdgame.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class MD5Util {
	/*private static ThreadLocal<MessageDigest> digester;
	static {
		digester = new ThreadLocal<MessageDigest>(){
			@Override
			protected MessageDigest initialValue() {
				// TODO Auto-generated method stub
				MessageDigest dig = null;
				try {
					dig = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return dig;
			}
		};
	}*/
	
	/**
	 * 暂时用，访问量大需要修改为threadLocal
	 * @param input
	 * @return
	 */
	public static String digest(String input){
		if(input == null){
			return null;
		}
		return DigestUtils.md5Hex(input.getBytes());
	}
	
	public static String getMD5String(){
		String input = UUID.randomUUID().toString();
		return digest(input);
	}
	
	public static void main(String[] args) {
		String input = UUID.randomUUID().toString();
		String result = digest(input);
		System.out.println(result.length());
		System.out.println(result);
	}
}

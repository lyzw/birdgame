package mobi.birdgame.common.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 *
 * 加密解密工具
 * Created by zhouwei on 2016/8/22.
 */
public class CodecUtil {
    private static final String DESEDE = "DESede"; //　定义　加密算法,可用

    /**
     * DESede 3des加密
     * @param keybyte   秘钥
     * @param src   源字符串byte数组
     * @return  加密后的byte数组
     */
    public static byte[] encryptDESede(byte[] keybyte, byte[] src){
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, DESEDE);
            // 加密
            Cipher c1 = Cipher.getInstance(DESEDE);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }


    // keybyte为加密密钥，长度为24字节
    // src为加密后的缓冲区

    /**
     *
     * @param keybyte keybyte为加密密钥，长度为24字节
     * @param src src为加密后的缓冲区
     * @return 源字符串数组
     */
    public static byte[] decryptDESede(byte[] keybyte, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, DESEDE);
            // 解密
            Cipher c1 = Cipher.getInstance(DESEDE);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // 转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + ":";
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) {

        // 添加新安全算法,如果用JCE就要把它添加进去
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        /*final byte[] keyBytes = {0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
                0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
                0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
                (byte) 0xE2}; // 24字节的密钥*/
        final byte[] keyBytes = "secretkey2141fasdf211112".getBytes();
                String szSrc = "This is a 3DES test. 测试";
        System.out.println("加密前的字符串:" + szSrc);

        byte[] encoded = encryptDESede(keyBytes, szSrc.getBytes());
        System.out.println("加密后的字符串:" + Hex.encodeHexString(encoded));

        byte[] srcBytes = decryptDESede(keyBytes, encoded);
        System.out.println("解密后的字符串:" + (new String(srcBytes)));
    }
}

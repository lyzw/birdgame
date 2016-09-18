package mobi.birdgame.mgmt.exception;

import java.security.MessageDigest;

/**
 * Created by zhouwei on 2016/9/6.
 */
public class BizException extends Exception {
    private int code ;

    public BizException(int code){
        super();
        this.code = code;
    }

    public BizException(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

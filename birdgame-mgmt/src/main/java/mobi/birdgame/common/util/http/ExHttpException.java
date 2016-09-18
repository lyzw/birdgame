package mobi.birdgame.common.util.http;

/**
 * Created by zhouwei on 2016/9/5.
 */
public class ExHttpException extends Exception {
    private int code = 0;

    public ExHttpException(int code) {
        this(code,String.valueOf(code));
    }

    public ExHttpException(int code, String message) {
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

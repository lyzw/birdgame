package mobi.birdgame.mgmt.web.ouput;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import mobi.birdgame.mgmt.exception.BizException;

/**
 * 基础json输出
 * Created by zhouwei on 2016/9/6.
 */
public class BaseJSONOutput {
    @JSONField(name = "code")
    private int code = 200 ;

    @JSONField(name = "message")
    private String message = "";

    @JSONField(name = "data")
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseJSONOutput success(Object data){
        BaseJSONOutput result = new BaseJSONOutput();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static BaseJSONOutput fail(BizException exception){
        BaseJSONOutput result = new BaseJSONOutput();
        result.setCode(exception.getCode());
        result.setMessage(exception.getMessage());
        return result;
    }


    public static BaseJSONOutput fail(int code,String message){
        BaseJSONOutput result = new BaseJSONOutput();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static BaseJSONOutput fail(){
        BaseJSONOutput result = new BaseJSONOutput();
        result.setCode(500);
        result.setMessage("系统异常");
        return result;
    }

    public String toJSONString(){
        return JSON.toJSONString(this);
    }
}

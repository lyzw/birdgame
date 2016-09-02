package mobi.birdgame.web.util;

import mobi.birdgame.mgmt.persistent.domain.WxUsers;
import sun.net.www.http.HttpClient;

import java.util.ResourceBundle;

/**
 * Created by zhouwei on 2016/9/2.
 */
public class AccoutServerRequestUtil {
    private static String secrectKey ;
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("config");

    }

    public static String registerHuanxinUser(HttpClient client ,WxUsers wxUsers){

        return  null;
    }
}

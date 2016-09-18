package mobi.birdgame.mgmt.web.util;

import mobi.birdgame.common.util.http.ExHttpException;
import mobi.birdgame.common.util.http.HttpClientTools;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 账号服务器请求工具l类
 * Created by zhouwei on 2016/9/2.
 */
public class AccoutServerRequestUtil implements InitializingBean {


    private static Logger logger = LogManager.getLogger(AccoutServerRequestUtil.class);

    private static HttpComponentsClientHttpRequestFactory clientHttpRequestFactory;

    private static String secrectKey ;
    private static String accoutServerPath;

    public static String registerHuanxinUser(Integer userId) throws IOException, HttpException, ExHttpException {
        String url = accoutServerPath + "huanxin/register";
        HttpClient httpClient = clientHttpRequestFactory.getHttpClient();
        Map map = new HashMap();
        map.put("userId",userId.toString());
        String signRequest = generateRequestSign(map);
        map.put("requestSign",signRequest);

        try {
            return HttpClientTools.getContent(httpClient,url,map,HttpClientTools.REQ_GET);
        } catch (IOException e) {
            logger.error(e);
            throw e;
        } catch (HttpException e) {
            logger.error(e);
            throw e;

        } catch (ExHttpException e) {
            logger.error(e);
            throw e;

        }finally {
            if(httpClient != null){
                httpClient = null;
            }
        }
    }

    public HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        return clientHttpRequestFactory;
    }

    public void setClientHttpRequestFactory(HttpComponentsClientHttpRequestFactory clientHttpRequestFactory) {
        this.clientHttpRequestFactory = clientHttpRequestFactory;
    }

    public void afterPropertiesSet() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        secrectKey = bundle.getString("signSerect");
        accoutServerPath = bundle.getString("accoutServerPath");
        if(secrectKey == null){
            throw new Exception("init AccoutServerRequestUtil errir : the secrectKey hasn't config");
        }
    }

    private static String generateRequestSign(Map<String,String > map) throws UnsupportedEncodingException {
        SortedMap<String, String> sortMap = new TreeMap<String, String>(map);
        sortMap.put("secret", secrectKey);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> parm : sortMap.entrySet()) {
            sb.append(parm.getKey() ).append("=").append(parm.getValue());
            sb.append("&");
        }
        String input = sb.substring(0,sb.length()-1);
        //MD5加密
        return DigestUtils.md5Hex(input.getBytes("utf-8"));

    }
}

package mobi.birdgame.common.util.http;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * http应用
 */
public class HttpClientTools {

    public static final Integer REQ_GET = 1;
    public static final Integer POST = 2;
    public static final Integer PUT = 3;
    public static final Integer DELETE = 4;

    private static Logger logger = LogManager.getLogger(HttpClientTools.class);


    /**
     * @param httpClient http请求客户端
     * @param requestUrl 请求地址
     * @param params     请求参数
     * @param method     请求方法
     * @return 返回的内容体
     * @throws IOException     IO异常
     * @throws HttpException   Http请求异常
     * @throws ExHttpException 个性化Http异常
     */
    public static String getContent(HttpClient httpClient, String requestUrl,
                                    Map<String, String> params, Integer method)
            throws IOException, HttpException, ExHttpException {
        return getContent(httpClient, requestUrl, params, null, method);
    }


    /**
     * @param httpClient http请求客户端
     * @param requestUrl 请求地址
     * @param method     请求方法
     * @return 返回的内容体
     * @throws IOException     IO异常
     * @throws HttpException   Http请求异常
     * @throws ExHttpException 个性化Http异常
     */
    public static String getContent(HttpClient httpClient, String requestUrl, Integer method)
            throws IOException, HttpException, ExHttpException {
        return getContent(httpClient, requestUrl, null, null, method);
    }

    /**
     * @param httpClient http请求客户端
     * @param requestUrl 请求地址
     * @param params     请求参数
     * @param headers    请求头
     * @param method     请求方法
     * @return 返回的内容体
     * @throws IOException     IO异常
     * @throws HttpException   Http请求异常
     * @throws ExHttpException 个性化Http异常
     */
    public static String getContent(HttpClient httpClient, String requestUrl,
                                    Map<String, String> params, Header[] headers, Integer method)
            throws IOException, HttpException, ExHttpException {
        HttpResponse response = null;
        switch (method) {
            case 1: {
                response = executeGet(httpClient, requestUrl, params, headers);
            }
        }

        if (response != null) {
            int code = response.getStatusLine().getStatusCode();
            if (code >= 200 && code <= 303) {
                return EntityUtils.toString(response.getEntity());
            } else if (code >= 400) {
                throw new ExHttpException(code);
            }
        }
        return null;
    }

    /**
     * @param httpClient http客户端
     * @param requestUrl 请求地址
     * @return 请求的反馈信息
     * @throws HttpException
     * @throws IOException
     */
    public static HttpResponse executeGet(HttpClient httpClient, String requestUrl) throws HttpException, IOException {
        return executeGet(httpClient, requestUrl, null, null);
    }

    /**
     * @param httpClient http客户端
     * @param requestUrl 请求地址
     * @param params     请求参数
     * @return 请求的反馈信息
     * @throws HttpException
     * @throws IOException
     */
    public static HttpResponse executeGet(HttpClient httpClient, String requestUrl, Map<String, String> params) throws HttpException, IOException {
        return executeGet(httpClient, requestUrl, params, null);
    }

    /**
     * @param httpClient http客户端
     * @param requestUrl 请求地址
     * @param params     请求参数
     * @param headers    请求头设置
     * @return 请求的反馈信息
     * @throws HttpException
     * @throws IOException
     */
    public static HttpResponse executeGet(HttpClient httpClient, String requestUrl, Map<String, String> params, Header[] headers) throws HttpException, IOException {
        HttpResponse response = null;
        if (httpClient == null) {
            logger.error("HttpClient is null,you must init it before using!");
            throw new HttpException("HttpClient is null,you must init it before using!");
        }

        StringBuffer requestParams = new StringBuffer();
        requestParams.append(requestUrl);
        if (params!= null && !params.isEmpty()) {
            if (requestUrl.indexOf("?") < 0) {
                requestParams.append("?");
            }
            Set<String> keys = params.keySet();
            for (String key : keys) {
                requestParams.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        HttpGet httpGet = new HttpGet(requestParams.substring(0, requestParams.length() - 1).toString());
        if (headers != null && headers.length > 0) {
            httpGet.setHeaders(headers);
        }
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            logger.error(e);
            throw e;
        }finally {
            httpGet.releaseConnection();
        }
        return response;
    }


}
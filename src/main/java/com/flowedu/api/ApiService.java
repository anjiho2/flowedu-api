package com.flowedu.api;

import com.flowedu.define.datasource.HttpDefineType;
import com.flowedu.define.datasource.RequestMethod;
import com.flowedu.domain.RequestApi;
import com.flowedu.error.FlowEduException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public abstract class ApiService {

    protected static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    /**
     * restful url response
     * @param url
     * @param requestMethod
     * @param jsonBody
     * @return
     */
    public RequestApi responseRestfulApi(String url, RequestMethod requestMethod, String jsonBody) {
        RequestApi requestApi = null;
        HttpResponse response = null;
        HttpPost httpPost;
        HttpPut httpPut;
        HttpDelete httpDelete;
        HttpGet httpGet;

        logger.info("call url ------------> " + url);

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            if (requestMethod.equals(RequestMethod.REQUEST_METHOD_POST)) {
                httpPost = new HttpPost(url);
                StringEntity entity = new StringEntity(jsonBody, HttpDefineType.UTF_8.getProperty());
                httpPost.addHeader(HttpDefineType.CONTENT_TYPE.getProperty(), HttpDefineType.APPLICATION_JSON.getProperty());
                httpPost.setEntity(entity);
                response = httpClient.execute(httpPost);
            } else if (requestMethod.equals(RequestMethod.REQUEST_METHOD_PUT)) {
                httpPut = new HttpPut(url);
                response = httpClient.execute(httpPut);
            } else if (requestMethod.equals(RequestMethod.REQUEST_METHOD_DELETE)) {
                httpDelete = new HttpDelete(url);
                response = httpClient.execute(httpDelete);
            } else if (requestMethod.equals(RequestMethod.REQUEST_METHOD_GET)) {
                httpGet = new HttpGet(url);
                response = httpClient.execute(httpGet);
            }
            String requestBody = EntityUtils.toString(response.getEntity(), HttpDefineType.UTF_8.getProperty());
            int httpStatusCode = response.getStatusLine().getStatusCode();

            logger.info("http status code -----------------> " + httpStatusCode);

            if (httpStatusCode != 200) {
                throw new FlowEduException(response.getStatusLine().getStatusCode());
            }
            requestApi = new RequestApi(requestBody, httpStatusCode);
            logger.info("response result -----------------> " + requestApi.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestApi;
    }

    /**
     * <PRE>
     * 1. Comment : HTTP POST 파라미터 전송
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 01. 31
     * </PRE>
     * @param targetUrl
     * @param nameValuePairList
     * @return
     * @throws Exception
     */
    public RequestApi sendPost(String targetUrl, List<NameValuePair>nameValuePairList) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(targetUrl);
        logger.info("call url ------------> " + targetUrl);

        post.setEntity(new UrlEncodedFormEntity(nameValuePairList, HttpDefineType.UTF_8.getProperty()));
        post.setHeader(HttpDefineType.CONTENT_TYPE.getProperty(), "application/x-www-form-urlencoded");

        HttpResponse response = client.execute(post);

        int httpStatusCode = response.getStatusLine().getStatusCode();
        logger.info("http status code -----------------> " + httpStatusCode);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);

        RequestApi requestApi = new RequestApi(body, httpStatusCode);
        logger.info("response result -----------------> " + requestApi.toString());

        return requestApi;
    }
}

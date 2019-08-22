package com.bootdo.api.utils;

import com.bootdo.api.utils.HttpResult;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class APIService {
//public static void main(String[] args) throws Exception {
//	APIService apiService=new APIService();
//	Map<String, Object> mapParam=new HashMap<>();
//	mapParam.put("rows", 1);
//	mapParam.put("page", 30);
//	HttpResult result=apiService.doGet("https://lajifenleiapp.com/sk/", mapParam);
//	System.out.println(result.getBody());
//}
    /**
     * 带参数的GET请求
     *
     * @param url      请求的URL
     * @param mapParam 请求的参数
     * @return
     * @throws URISyntaxException
     */
    public HttpResult doGet(String url, Map<String, Object> mapParam) throws Exception {
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 封装参数
        URIBuilder uriBuilder = new URIBuilder(url);
        if (mapParam != null && !mapParam.isEmpty()) {
            for (String key : mapParam.keySet()) {
                uriBuilder.setParameter(key, mapParam.get(key).toString());
            }
        }

        URI uri = uriBuilder.build();
        // 创建httpget对象
        HttpGet httpGet = new HttpGet(uri);
        // 执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 响应与封装数据
        Integer code = response.getStatusLine().getStatusCode();
        String body = null;
        if (response.getEntity() != null)
            body = EntityUtils.toString(response.getEntity(), "utf-8");

        HttpResult httpResult = new HttpResult(code, body);

        return httpResult;

    }

    /**
     * 不带参数的GET请求
     *
     * @param url 请求的URL
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url) throws Exception {
        return doGet(url, null);
    }

    /**
     * 带参数的POST请求
     *
     * @param url      请求的URL
     * @param mapParam 请求的参数
     * @return
     * @throws Exception
     * @throws ClientProtocolException
     */
    public HttpResult doPost(String url, Map<String, Object> mapParam) throws Exception {
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建httppost对象
        HttpPost httpPost=new HttpPost(url);

        // 封装参数
        List<NameValuePair> parameters=new ArrayList<NameValuePair>();
        if (mapParam != null && !mapParam.isEmpty()) {
            for (String key : mapParam.keySet()) {
                parameters.add(new BasicNameValuePair(key, mapParam.get(key).toString()));
            }

            // 构造一个form表单式的实体
            UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
            //设置参数
            httpPost.setEntity(entity);
        }


        // 执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 响应与封装数据
        Integer code = response.getStatusLine().getStatusCode();
        String body = null;
        if (response.getEntity() != null)
            body = EntityUtils.toString(response.getEntity(), "utf-8");

        HttpResult httpResult = new HttpResult(code, body);

        return httpResult;
    }

    /**
     * 不带参数POST请求
     * @param url 请求的url
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return doGet(url, null);
    }

    public HttpResult upload(String url, File file) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        // File file1=new File("D:/lua/2.jpg");
        multipartEntityBuilder.addBinaryBody("fileupload",file);
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        httpResponse = httpClient.execute(httpPost);
        // 响应与封装数据
        Integer code = httpResponse.getStatusLine().getStatusCode();
        String body = null;

        if (httpResponse.getEntity() != null)
            body = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

        HttpResult httpResult = new HttpResult(code, body==null?"null":body);

        return httpResult;

    }
}

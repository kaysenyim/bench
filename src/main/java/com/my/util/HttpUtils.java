package com.my.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpUtils {
    public static String get(String url) {
        return get(url, new HashMap<>());
    }

    public static String get(String url, Map<String, String> headers) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            if (url.startsWith("https")) {
                httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            } else {
                httpClient = HttpClients.createDefault();
            }
            HttpGet httpGet = new HttpGet(url);
            if (null != headers && headers.size() > 0) {
                Set<String> names = headers.keySet();
                names.forEach(name -> {
                    httpGet.setHeader(name, headers.get(name));
                });
            }

            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                    .setConnectionRequestTimeout(35000)
                    .setSocketTimeout(60000)
                    .build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void getWithoutResult(String url, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        if (url.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        HttpGet httpGet = new HttpGet(url);
        if (null != headers && headers.size() > 0) {
            Set<String> names = headers.keySet();
            names.forEach(name -> {
                httpGet.setHeader(name, headers.get(name));
            });
        }

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                .setConnectionRequestTimeout(35000)
                .setSocketTimeout(60000)
                .build();
        httpGet.setConfig(requestConfig);
        httpClient.execute(httpGet);
    }

    public static String post(String url, Map<String, String> headers, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        if (url.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("X-Forwarded-For", "127.0.0.1");
        httpPost.addHeader("Proxy-Client-IP", "127.0.0.1");
        httpPost.addHeader("WL-Proxy-Client-IP", "127.0.0.1");
        httpPost.addHeader("HTTP_CLIENT_IP", "127.0.0.1");
        httpPost.addHeader("X-Real-IP", "127.0.0.1");
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        if (null != headers && headers.size() > 0) {
            Set<String> names = headers.keySet();
            names.forEach(name -> {
                httpPost.setHeader(name, headers.get(name));
            });
        }
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String post(String url, Map<String, String> headers, String body) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("X-Forwarded-For", "127.0.0.1");
        httpPost.addHeader("Proxy-Client-IP", "127.0.0.1");
        httpPost.addHeader("WL-Proxy-Client-IP", "127.0.0.1");
        httpPost.addHeader("HTTP_CLIENT_IP", "127.0.0.1");
        httpPost.addHeader("X-Real-IP", "127.0.0.1");
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        if (null != headers && headers.size() > 0) {
            Set<String> names = headers.keySet();
            names.forEach(name -> {
                httpPost.setHeader(name, headers.get(name));
            });
        }
        if (StringUtils.isNotBlank(body)) {
            StringEntity entity = new StringEntity(body, "UTF-8");
//            entity.setContentEncoding("UTF-8");
//            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}

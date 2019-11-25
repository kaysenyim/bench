package com.my.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class HttpUtilsTest {
    @Test
    public void post() {
        String url = "https://chainex.in/Digital/exchange_usercoin/list";
        Map<String, String> headers = new HashMap<>();
        headers.put(":authority", "chainex.in");
        headers.put(":method", "POST");
        headers.put(":path", "/Digital/exchange_usercoin/list");
        headers.put(":scheme", "https");
        headers.put("accept", "application/json, text/javascript, */*; q=0.01");
        headers.put("accept-encoding", "gzip, deflate, br");
        headers.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
//        headers.put("content-length", "193");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("cookie", "6nl1ymm29oqc051q=omod6im06r8bd571u7fu6b584g; _ga=GA1.2.1701319867.1569333539; _gid=GA1.2.1515017747.1569333539; __zlcmid=uRiYtmRlTHZx4C");
        headers.put("origin", "https://chainex.in");
        headers.put("referer", "https://chainex.in/digital/exchange_usercoin/index");
        headers.put("sec-fetch-mode", "cors");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("sign", "1510b8f19e3c1b6a41437827e745414e");
        headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        headers.put("x-requested-with", "XMLHttpRequest");
        Map<String, Object> params = new HashMap<>();
        params.put("data", "fW77Hi%2F9q9ijW4Xh6%2FXIs9pvJ6C8nB%2FSmfU2lVW2FdNsyXfY3YZBVgv4RJ2qe5gzQEF0zHsKmb4uclu%2BCQAIuc96i42JyvrThPC%2FXLMgfzDrd%2BUZpU7DDk1KuDy8ps%2BobQu7t9xHlVWLYV7ZWy1kS7MA4AhhX07zBGXukFCZpoE%3D");
        HttpUtils.post(url, headers, params);
        int nThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                while (true) {
                    HttpUtils.post(url, headers, params);
                }
            });
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                return;
            } else {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

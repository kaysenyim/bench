package com.my;

import com.alibaba.fastjson.JSONObject;
import com.my.util.HttpUtils;
import com.my.util.XmlUtils;
import com.my.vo.*;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
//             String fileName = "/Users/kaysenyim/Workspace/bench/doc/demo.xml";
            String fileName = args[0];
            StringBuffer sb = new StringBuffer();
            FileReader in = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            in.close();
            Request request = XmlUtils.toObj(Request.class, sb.toString());
            String url = request.getUrl();
            boolean isJson = false;
            Map<String, String> headers = new HashMap<>();
            List<Header> headerList = request.getHeaders().getHeader();
            String method = request.getMethod();
            for (Header header : headerList) {
                headers.put(header.getName(), header.getValue());
                if ("Content-Type".equalsIgnoreCase(header.getName()) && header.getValue().contains("json")) {
                    isJson = true;
                }
            }
            Map<String, Object> params = new HashMap<>();
            List<Param> paramList = request.getParams().getParam();
            for (Param param : paramList) {
                params.put(param.getName(), param.getValue());
            }

            Thread task = null;
            switch (method) {
                case "get":
                case "GET":
                    task = new Thread(() -> {
                        while (true) {
                            HttpUtils.get(url, headers);
                        }
                    });
                    break;
                case "post":
                case "POST":
                    if (isJson) {
                        task = new Thread(() -> {
                            while (true) {
                                HttpUtils.post(url, headers, JSONObject.toJSONString(params));
                            }
                        });
                    } else {
                        task = new Thread(() -> {
                            while (true) {
                                HttpUtils.post(url, headers, params);
                            }
                        });
                    }
                    break;
                default:
                    System.err.println("method!");
                    return;
            }

            ExecutorService executorService = Executors.newCachedThreadPool();
            int executer = request.getExecuter();
            for (int i = 0; i < executer; i++) {
                executorService.execute(task::run);
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
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}

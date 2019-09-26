package com.my;

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
            // String fileName = "/Users/yimcarson/Workspace/GitHub/bench/doc/demo.xml";
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
            Map<String, String> headers = new HashMap<>();
            List<Header> headerList = request.getHeaders().getHeader();
            for (Header header : headerList) {
                headers.put(header.getName(), header.getValue());
            }
            Map<String, Object> params = new HashMap<>();
            List<Param> paramList = request.getParams().getParam();
            for (Param param : paramList) {
                params.put(param.getName(), param.getValue());
            }
            String result = HttpUtils.post(url, headers, params);
            ExecutorService executorService = Executors.newCachedThreadPool();
            int executer = request.getExecuter();
            for (int i = 0; i < executer; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String result = HttpUtils.post(url, headers, params);
                            System.out.println(result);
                        }
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
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

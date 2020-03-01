package com.my.util;

import com.my.vo.*;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XmlUtilsTest {
    @Test
    public void toObj() {
        try {
            String fileName = "/Users/yimcarson/Workspace/GitHub/bench/doc/demo.xml";
            StringBuffer sb = new StringBuffer();
            FileReader in = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            in.close();
            String xml = sb.toString();
//            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><request><url>https://chainex.in/Digital/exchange_usercoin/list</url><method>POST</method><executer>1</executer><headers><header name=\":authority\" value=\"chainex.in\"/></headers><params><param name=\"data\" value=\"YmG3oebmQPKfi6BTVKzRiyf9OUc7akQttvJspm5TTG7pJY3rL9Ta%2FmFyGxs6tz0wh1n65q1NkuWEndw2k%2BVt0LacADGb%2BgGRRSkKEIM2vEKGGeSt1ymP9Zvu7A7eg3NvwtarK2WimwrvqAmh6%2FRO1QlnqwrOrraIBhdXhROld7o%3D\"/></params></request>";
            Request request = XmlUtils.toObj(Request.class, xml);
            System.out.println(request);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toXml() {
        Headers headers = new Headers();
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header.Builder()
                .name(":authority")
                .value("chainex.in")
                .build());
        headerList.add(new Header.Builder()
                .name(":method")
                .value("POST")
                .build());
        headerList.add(new Header.Builder()
                .name(":path")
                .value("/Digital/exchange_usercoin/list")
                .build());
        headerList.add(new Header.Builder()
                .name(":scheme")
                .value("https")
                .build());
        headerList.add(new Header.Builder()
                .name("accept")
                .value("application/json, text/javascript, */*; q=0.01")
                .build());
        headerList.add(new Header.Builder()
                .name("accept-language")
                .value("zh-CN,zh;q=0.9,en;q=0.8")
                .build());
        headerList.add(new Header.Builder()
                .name("Content-Type")
                .value("application/x-www-form-urlencoded")
                .build());
        headerList.add(new Header.Builder()
                .name("cookie")
                .value("6nl1ymm29oqc051q=omod6im06r8bd571u7fu6b584g; _ga=GA1.2.1701319867.1569333539; _gid=GA1.2.1515017747.1569333539; __zlcmid=uRiYtmRlTHZx4C")
                .build());
        headerList.add(new Header.Builder()
                .name("origin")
                .value("https://chainex.in")
                .build());
        headerList.add(new Header.Builder()
                .name("referer")
                .value("https://chainex.in/digital/exchange_usercoin/index")
                .build());
        headerList.add(new Header.Builder()
                .name("sec-fetch-mode")
                .value("cors")
                .build());
        headerList.add(new Header.Builder()
                .name("sec-fetch-site")
                .value("same-origin")
                .build());
        headerList.add(new Header.Builder()
                .name("sign")
                .value("1510b8f19e3c1b6a41437827e745414e")
                .build());
        headerList.add(new Header.Builder()
                .name("user-agent")
                .value("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .build());
        headerList.add(new Header.Builder()
                .name("x-requested-with")
                .value("XMLHttpRequest")
                .build());
        headers.setHeader(headerList);
        Params params = new Params();
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param.Builder()
                .name("data")
                .value("YmG3oebmQPKfi6BTVKzRiyf9OUc7akQttvJspm5TTG7pJY3rL9Ta%2FmFyGxs6tz0wh1n65q1NkuWEndw2k%2BVt0LacADGb%2BgGRRSkKEIM2vEKGGeSt1ymP9Zvu7A7eg3NvwtarK2WimwrvqAmh6%2FRO1QlnqwrOrraIBhdXhROld7o%3D")
                .build());
        params.setParam(paramList);
        try {
            String xml = XmlUtils.toXml(new Request.Builder()
                    .url("https://chainex.in/Digital/exchange_usercoin/list")
                    .method("POST")
                    .headers(headers)
                    .params(params)
                    .build());
            System.out.println(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
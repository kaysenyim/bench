package com.my.util;
import java.io.PrintWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Set;
import java.util.Date;
public class HttpUtils {
    public static void get(String url, Map<String, String> headers) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("OK");
            } else {
                System.out.printf("%s Response code : %s\n", new Date(), responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(String url, Map<String, String> headers, Map<String, Object> params) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(params);
            out.flush();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("OK");
            } else {
                System.err.printf("%s Response code : %s\n", new Date(), responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(String url, Map<String, String> headers, String body) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(body);
            out.flush();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("OK");
            } else {
                System.err.printf("%s Response code : %s\n", new Date(), responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

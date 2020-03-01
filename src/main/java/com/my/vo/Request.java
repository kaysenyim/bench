package com.my.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
@XmlType(propOrder = {
        "url",
        "method",
        "headers",
        "params"
})
public class Request {
    private String url;

    private String method;

    private Headers headers;

    private Params params;

    private Request(Builder builder) {
        setUrl(builder.url);
        setMethod(builder.method);
        setHeaders(builder.headers);
        setParams(builder.params);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Request(String url, String method, Headers headers, Params params) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.params = params;
    }

    public Request() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"method\":\"")
                .append(method).append('\"');
        sb.append(",\"headers\":")
                .append(headers);
        sb.append(",\"params\":")
                .append(params);
        sb.append('}');
        return sb.toString();
    }

    public static final class Builder {
        private String url;
        private String method;
        private Headers headers;
        private Params params;

        public Builder() {
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder method(String val) {
            method = val;
            return this;
        }

        public Builder headers(Headers val) {
            headers = val;
            return this;
        }

        public Builder params(Params val) {
            params = val;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}

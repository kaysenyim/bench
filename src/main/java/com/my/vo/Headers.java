package com.my.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "headers")
public class Headers {
    private List<Header> header;

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public Headers(List<Header> header) {
        this.header = header;
    }

    public Headers() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"header\":")
                .append(header);
        sb.append('}');
        return sb.toString();
    }
}

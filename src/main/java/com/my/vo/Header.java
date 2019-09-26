package com.my.vo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

public class Header {
    private String name;

    private String value;

    private Header(Builder builder) {
        setName(builder.name);
        setValue(builder.value);
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Header(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Header() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"value\":\"")
                .append(value).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public static final class Builder {
        private String name;
        private String value;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public Header build() {
            return new Header(this);
        }
    }
}

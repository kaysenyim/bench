package com.my.vo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

public class Param {
    private String name;

    private String value;

    private Param(Builder builder) {
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
        this.value = String.format(value, new Date(), new Date());
    }

    public Param(String name, String value) {
        this.name = name;
        this.value = value;
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

    public Param() {
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

        public Param build() {
            return new Param(this);
        }
    }
}

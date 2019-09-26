package com.my.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "params")
public class Params {
    private List<Param> param;

    public List<Param> getParam() {
        return param;
    }

    public void setParam(List<Param> param) {
        this.param = param;
    }

    public Params(List<Param> param) {
        this.param = param;
    }

    public Params() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"param\":")
                .append(param);
        sb.append('}');
        return sb.toString();
    }
}

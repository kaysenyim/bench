package com.my.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "requests")
public class Requests {
    private List<Request> request;

    private int executer;

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    @XmlAttribute(name = "executer")
    public int getExecuter() {
        return executer;
    }

    public void setExecuter(int executer) {
        this.executer = executer;
    }
}

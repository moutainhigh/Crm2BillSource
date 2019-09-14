package com.al.nppm.business.syntomq.model;

import java.io.Serializable;

public class MqObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String implService;
    private String methodName;
    private String parameter;
    private String parameterName;

    public MqObject() {
    }

    public String getImplService() {
        return this.implService;
    }

    public void setImplService(String implService) {
        this.implService = implService;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameter() {
        return this.parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
}
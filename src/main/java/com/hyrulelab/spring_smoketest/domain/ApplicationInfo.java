package com.hyrulelab.spring_smoketest.domain;

import java.util.Map;

public class ApplicationInfo {
    private String[] services;
    private Map<String, String> request;

    public ApplicationInfo(String[] services, Map<String, String> request) {
        this.services = services;
        this.request = request;
    }

    public String[] getServices() {
        return services;
    }

    public Map<String, String> getRequest() {
        return request;
    }

    public void setServices(String[] services, Map<String, String> request) {
        this.services = services;
        this.request = request;
    }
}

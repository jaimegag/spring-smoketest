package com.hyrulelab.spring_smoketest.domain;

import java.util.Map;

public class ApplicationInfo {
    private String[] services;
    private Map<String, String> request;
    private String metadata;
    private String ip;

    public ApplicationInfo(String[] services, Map<String, String> request, String metadata, String ip) {
        this.services = services;
        this.request = request;
        this.metadata = metadata;
        this.ip = ip;
    }

    public String[] getServices() {
        return services;
    }

    public Map<String, String> getRequest() {
        return request;
    }

    public String getMetadata() {
        return metadata;
    }

    public String getIP() {
        return ip;
    }
}

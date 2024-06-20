package com.hyrulelab.spring_smoketest.domain;

import java.util.Map;

public class ApplicationInfo {
    private String[] services;
    private Map<String, String> request;
    private String metadata;

    public ApplicationInfo(String[] services, Map<String, String> request, String metadata) {
        this.services = services;
        this.request = request;
        this.metadata = metadata;
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
}

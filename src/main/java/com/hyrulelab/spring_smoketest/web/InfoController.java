package com.hyrulelab.spring_smoketest.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bindings.Binding;
import org.springframework.cloud.bindings.Bindings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyrulelab.spring_smoketest.domain.ApplicationInfo;

import io.pivotal.cfenv.core.CfEnv;
import io.pivotal.cfenv.core.CfService;

@RestController
public class InfoController {
    private final CfEnv cfEnv;

    @Autowired
    public InfoController() {
        this.cfEnv = new CfEnv();
    }

    @RequestMapping(value = "/request")
    public Map<String, String> requestInfo(HttpServletRequest req) {
        return getRequestInfo(req);
    }

    @RequestMapping(value = "/appinfo")
    public ApplicationInfo info(HttpServletRequest req) {
        return new ApplicationInfo(getServiceNames(), getRequestInfo(req));
    }

    private Map<String, String> getRequestInfo(HttpServletRequest req) {
        HashMap<String, String> result = new HashMap<>();
        result.put("session-id", req.getSession().getId());
        result.put("protocol", req.getProtocol());
        result.put("method", req.getMethod());
        result.put("scheme", req.getScheme());
        result.put("remote-addr", req.getRemoteAddr());
        return result;
    }

    private String[] getServiceNames() {
        List<CfService> services = cfEnv.findAllServices();

        List<String> names = new ArrayList<>();
        for (CfService service : services) {
            names.add(service.getName());
        }

        List<Binding> bindings = new Bindings().getBindings();
        List<String> bindingNames = bindings.stream()
            .map(Binding::getName)
            .collect(Collectors.toList());
        names.addAll(bindingNames);
        
        return names.toArray(new String[0]);
    }
}

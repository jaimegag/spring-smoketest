package com.hyrulelab.spring_smoketest.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
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
        return new ApplicationInfo(getServiceNames(), getRequestInfo(req), getMetadataInfo());
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

    private String getMetadataInfo() {
        String az = "No Metadata info";
        try {
            URI uri = new URI("http://169.254.169.254/latest/meta-data/placement/availability-zone");
            URLConnection url = uri.toURL().openConnection();
            url.setConnectTimeout(1000);
            url.setReadTimeout(3000);
			InputStream is = url.getInputStream();
			StringBuilder sb = new StringBuilder();
			
			int ch;
			while ((ch = is.read()) != -1) {
				sb.append((char) ch);
				System.out.print((char) ch);
			}
			az = sb.toString();
        } catch (URISyntaxException e) {
            System.out.println("URI Syntax - Curling metadata server");
			e.printStackTrace();
        } catch (MalformedURLException e) {
			System.out.println("Malform - Curling metadata server");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOExcept - Curling metadata server");
			e.printStackTrace();
		} finally {
			System.out.println("\n\nFinally - Curling metadata server");
		}
        return az;
    }
}

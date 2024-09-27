package com.xz.controller;

import com.xz.service.RefreshServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
* @since 2023/12/21 14:42
* @author xz
*/
@RestController
public class CloudController {
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    RefreshServiceImpl refreshService;
    
    
    @Value("#{'${spring.profiles.active:default}' + '-' + '${test.config:}' + '-' + '${test.config1:}'}")
    private String config;
    
    @Value("${nacos.server.port:}")
    String text;
    @GetMapping("/pc")
    public Object getProfileConfig() {
        return config;
    }

    @GetMapping("/pts")
    public Object getProperties() {
        return refreshService.text() + ":" + text;
    }
    
    @GetMapping("/pts1")
    public Object getProperties1() {
        return refreshService.aLong();
    }

    @RequestMapping("/instance/list/{serviceId}")
    public List<ServiceInstance> instanceList(@PathVariable String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }
    
//    public static void main(String[] args) {
//        try {
//            System.out.println(new URI(""));
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

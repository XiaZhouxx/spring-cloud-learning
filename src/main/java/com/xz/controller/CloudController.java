package com.xz.controller;

import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
* @since 2023/12/21 14:42
* @author xz
*/
public class CloudController {
    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/instance/list/{serviceId}")
    public List<ServiceInstance> instanceList(@PathVariable String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

    public static void main(String[] args) {
        String str = """
                
                """;
    }
}

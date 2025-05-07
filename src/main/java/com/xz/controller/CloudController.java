package com.xz.controller;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import com.alibaba.cloud.nacos.refresh.NacosConfigRefreshEvent;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xz.service.RefreshServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
* @since 2023/12/21 14:42
* @author xz
*/
@RestController
@RefreshScope
public class CloudController {
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    RefreshServiceImpl refreshService;
    
    /**
     * 控制配置获取的优先级, 多个同名文件在加载时编排顺序(list) 依次获取配置文件 先加载的配置就返回
     * ConfigDataEnvironment.applyToEnvironment() | applyContributor()
     * 优先级：
     * 启动命令中指定的配置项；
     * 操作系统配置项 (System.getProperties())
     * 操作系统环境变量
     * 配置中心中的配置文件；
     * 本地的application.properties(yml)
     * 本地boostrap.properties（yml）
     */
    @Resource
    ConfigurableEnvironment environment;
    
    @Value("#{'${spring.profiles.active:default}' + '-' + '${test.config:}' + '-' + '${test.config1:}'}")
    private String config;
    
    @Value("${nacos.server.port:}")
    String text;
    
    @NacosConfig(dataId = "test.yml", group = "DEFAULT_GROUP", key = "test.val")
    String test;
    
    @GetMapping("/pts2")
    public String getProperties2() {
        return test;
    }
    
    @GetMapping("/pc")
    @SentinelResource
    public Object getProfileConfig(String s) {
        return config;
    }

    @GetMapping("/pts")
    public Object getProperties() {
        return refreshService.text() + ":";
    }
    
    
    
    @GetMapping("/pts1")
    public Object getProperties1() {
        return refreshService.aLong();
    }

    @RequestMapping("/instance/list/{serviceId}")
    public List<ServiceInstance> instanceList(@PathVariable String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }
    
}

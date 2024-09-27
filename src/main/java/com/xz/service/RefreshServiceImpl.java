package com.xz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xz
 * @since 2024/7/19 14:49
 */
@Service
// 如果要支持删除配置也能动态刷新使用refreshScope
@RefreshScope
// ConfigurationProperties针对配置bean进行bind/set值, 如果没有配置值(删除的情况) 那么会导致不会刷新
/**
 * {@link org.springframework.cloud.context.properties.ConfigurationPropertiesRebinder}
 */
@ConfigurationProperties(prefix = "nacos.test")
public class RefreshServiceImpl {
    @Value("${nacos.server.port:}")
    String text;
    
    @Value("#{${nacos.server.aaLong}}")
    HashMap<String, String> aaLong = new HashMap<>();
    
    Long test1;

    public Object text() {
        return text;
    }
    
    public Object aLong() {
        return test1;
    }
    
    public Long getTest1() {
        return test1;
    }
    
    public void setTest1(Long test1) {
        this.test1 = test1;
    }
}

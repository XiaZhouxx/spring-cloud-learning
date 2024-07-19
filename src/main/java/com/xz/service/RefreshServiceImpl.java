package com.xz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author xz
 * @since 2024/7/19 14:49
 */
@Service
@RefreshScope
public class RefreshServiceImpl {
    @Value("${nacos.server.port}")
    String text;

    public Object text() {
        return text;
    }
}

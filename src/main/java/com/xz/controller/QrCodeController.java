package com.xz.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.cloud.nacos.refresh.NacosConfigRefreshEvent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xz
 * @since 2024/9/11 10:12
 */
@RestController
public class QrCodeController implements ApplicationContextAware {
    
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
    
    ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
    
    @RequestMapping("/sort")
    public void reSortEnvironment() {
        MutablePropertySources propertySources = environment.getPropertySources();
        Map map = new LinkedHashMap();
        map.put("test.config", "test");
        OriginTrackedMapPropertySource pts = new OriginTrackedMapPropertySource("test", map);
        // 在某个配置之前添加对应的配置 可以替换他的优先级。当然直接改是不是更快？
        propertySources.addBefore("DEFAULT_GROUP@last-cloud.yml", pts);
        // 不要使用refreshScope的类发布, 直接发布RefreshEvent可能死锁
        context.publishEvent(new NacosConfigRefreshEvent(new Object(), null, "test"));
    }
    @RequestMapping("/qr")
    public void testQrCode(HttpServletResponse response) throws Exception {
        BufferedImage generate = QrCodeUtil.generate("http://www.baidu.com", 500, 500);
        
        ImgUtil.write(generate, "", response.getOutputStream());
    }
}

package com.xz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author xz
 * @since 2023/12/21 11:02
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // 配置文件 -Dspring.config.name=application - StandardConfigDataLocationResolver

        // 类型推断
        var str = "abc";
        // 多行字符格式
        var str2 = """
                abc
                123
                4556
                """;
        // switch表达式
        var str3 = switch (str) {
            case "abc", "efd", "123" -> "abc";
            default -> "default";
        };
        // instanceof模式匹配
        Object obj = Integer.valueOf(1);
        if (obj instanceof Integer s) {
            System.out.println(s);
        }
        // 1. 配置变更
        /**
         * spring boot >= 2.4.0 可以不采用bootstrap.xml(BootstrapApplicationListener比较重的实现) 即可启动并且找到注册中心 减少多余依赖（依赖spring cloud的）
         * 入口： ConfigDataEnvironmentPostProcessor, 还是基于Spring常规的扩展后置处理器
         * ConfigDataImporter.resolveAndLoad() 解析并加载配置,
         * 1. resolve 'ConfigDataLocationResolver'
         * 2. configLoad 'ConfigDataLoader'
         *
         * 通过一个导入的配置项 导入nacod的配置
         * NacosConfigDataLocationResolver 识别前缀为 nacos进行加载
         * spring:
         *   config:
         *     import:
         *       - optional:nacos:${spring.application.name}
         * 并且有些配置可以配置在路径中
         * optional:nacos:${spring.application.name}?group=test&refreshEnabled=true&preference=xxx
         *
         * nacos 最新的配置拉取
         *
         * 1. 解析本地配置文件配置信息获取需要拉取的远程文件配置. NacosConfigDataLocationResolver
         *
         * 2. NacosConfigDataLoader 根据前阶段的基本信息 拉取nacos的配置
         */
        Logger log = LoggerFactory.getLogger(Main.class);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> log.info("custom shutdownHook"), "SpringApplicationShutdownHook"));

        SpringApplication sa = new SpringApplication(Main.class);
//        sa.setDefaultProperties((Map<String, Object>) null);
        // Spring 执行的一些阶段启动处置
//        sa.setApplicationStartup(new ExecutionTimeApplicationStartup());
        ConfigurableApplicationContext run = sa.run(args);
        System.out.println(run.getBean("springBootBanner"));
        /**
         * 3.x 后Feign移除了ribbon依赖, 如果要使用负载均衡 则需要依赖cloud的loadBalancer
         *
         *          <dependency>
         *             <groupId>org.springframework.cloud</groupId>
         *             <artifactId>spring-cloud-starter-loadbalancer</artifactId>
         *         </dependency>
         *
         * 原理和Ribbon类似(可能尽量兼容使用ribbon的习惯), 使用一个LoadBalancerClientFactory(Spring容器类)存放每个
         * 需要负载均衡服务的配置(所以第一次调用可能也会很慢, 同样提供了配置 spring.cloud.loadbalancer.eager-load.clients)
         *
         * Nacos的实现也有略微变化, 以前基于ribbon的ServerList实现, 现在则时基于cloud的ReactorLoadBalancer
         */
    }
}
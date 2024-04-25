package org.springframework.cloud.bootstrap.marker;

/**
 * 基于 spring cloud 启动触发配置加载需要的类
 * 新版本的配置加载已经和Spring cloud脱离，这样不需要另起一个springboot容器
 * 更加解耦
 *
 *  spring.cloud.bootstrap.enabled || exists(org.springframework.cloud.bootstrap.marker.Marker)
 *
 * @author xz
 * @since 2023/12/25 14:18
 */
//public class Marker {
//}

package com.xz.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author xz
 * @since 2024/9/12 09:45
 */
@RestController
public class ShortUrlController {

    @RequestMapping("/generate")
    public String generateUrl() {
        // 生成一个短链接 对应一个长链接 www.xxx.cn/code
        return "";
    }
    
    @RequestMapping("/t/{code}")
    public void redirectUrl(@PathVariable String code, HttpServletResponse response) {
        try {
            // 从redis/数据库中获取短链接code对应的长链接, 然后跳转过去.
            response.sendRedirect("http://" + code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

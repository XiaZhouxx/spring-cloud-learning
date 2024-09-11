package com.xz.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

/**
 * @author xz
 * @since 2024/9/11 10:12
 */
@RestController
public class QrCodeController {
    @RequestMapping("/qr")
    public void testQrCode(HttpServletResponse response) throws Exception {
        BufferedImage generate = QrCodeUtil.generate("http://www.baidu.com", 500, 500);
        
        ImgUtil.write(generate, "", response.getOutputStream());
    }
}

package com.dyz.skeleton.controller;

import com.dyz.skeleton.utils.VerifyCodeUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@Controller
public class VerifyCodeController {
    @Resource
    private Producer producer;

    @GetMapping("/vc.jpg")
    public void getVerifyCode(HttpServletResponse resp) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
        VerifyCodeUtil.save(text);
        BufferedImage image = producer.createImage(text);
        try(ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }


}

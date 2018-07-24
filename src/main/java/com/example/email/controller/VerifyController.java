package com.example.email.controller;

import com.example.email.utils.VerifyUtil;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class VerifyController {
    /**
     * 获取生成的验证码图片
     * @param request
     * @param response
     */
    @GetMapping("/verify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        Object oj[]=VerifyUtil.createImage();
        String code=(String)oj[0];
        code=code.toLowerCase();
        BufferedImage image=(BufferedImage)oj[1];
        long time =System.currentTimeMillis();
        if(session.getAttribute("verifyCode")==null) {
            session.removeAttribute("verifyCode");
            session.removeAttribute("verifyTime");
        }
        session.setAttribute("verifyCode",code);
        session.setAttribute("verifyTime",time);
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            ImageIO.write(image,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/checkVerify")
    public String check(HttpSession session, @RequestParam String code){
        System.out.println(code);
        code=code.toLowerCase();
        String verifyCode=(String)session.getAttribute("verifyCode");
        verifyCode=verifyCode.toLowerCase();
        System.out.println(verifyCode);
        if(!(code.equals(verifyCode))){
            return "验证码错误";

        }
        long time=(long)session.getAttribute("verifyTime");
        time+=30;
        long currentTime=System.currentTimeMillis();
        if(currentTime>time){
            return "已超时";

        }


        return "验证成功";

    }
    @GetMapping("/void")
    public void test(){

    }

}

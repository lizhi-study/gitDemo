package com.powernode.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("text")
public class CheckController {

    //接收浏览器传入的请求，设置路径和请求方式为post请求
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public User fileAdd(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        System.out.println(3412414);
        //获取上传文件的原名称
        String originalFilename = file.getOriginalFilename();

        //添加日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(new Date());
        String fileName= time+originalFilename;

        //获取存放文件的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        realPath = realPath +"/"+fileName;

        //创建文件
        File file1 = new File(realPath);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //测试传入的参数是能不能是数组类型
    @RequestMapping("/t1")
    public String text1(int[] check){
        for (int i = 0; i <check.length;i++){
            System.out.println(check[i]);
        }
        return "text";
    }

    @RequestMapping("t2")
    @ResponseBody
    public User t2(int id ){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setResult(30.3);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "t3",method = RequestMethod.POST)
    public User t3(@RequestBody User user){
        System.out.println(user);
        return user;
    }
}

package com.powernode.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("test")
public class HelloController  {

    /*使用注解接收传入的参数*/
    @RequestMapping("add")
    public String add(){
        System.out.println("欢迎使用");
        return "/index.jsp";
    }

    //注解进行视图传参
@RequestMapping("model")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","嘻嘻嘻嘻");
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }

    //配置视图解析器后的代码
    @RequestMapping("model2")
    public String page2(HttpServletRequest request,HttpServletResponse response){
        request.setAttribute("message","雪国");
        return "index";
    }

    //使用post请求传参
    @RequestMapping(value=("/update"),method = RequestMethod.POST)
    public String update(){
        System.out.println("更新数据");
        return"index";
    }

    //get请求接受用户输入的基本类型参数
    @RequestMapping("/app")
    public String app(int id,String name,double result){
        System.out.println(id);
        System.out.println(name);
        System.out.println(result);
        return "index";
    }

    //传入的形参和实参不一致时可以使用@RequestParam注解
    @RequestMapping("/app1")
    public String app1( @RequestParam("text") int id){
        System.out.println(id);
        return "index";
    }
    @RequestMapping("/app2")
    //传入的形参，与实参数量不一致，使用@RequestParm注解
    public String app2(int id, String name,@RequestParam(value = "result",required = false) double result){
        System.out.println(id);
        System.out.println(name);
        System.out.println(result);
        return "index";
    }

    //实参是一个引用类型，需要传入该对象的属性，并且形参名名称与对象的属性名称一致
  /*  @RequestMapping("app3")
    public String app3(User user){
        System.out.println(user);
        return "index";
    }*/


  @RequestMapping("/app4")
  public String app4(User user){
      System.out.println(user);
      return "index";
  }

  /*  @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //false 准确  true非准确
        format.setLenient(false);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(format,true));
    }*/

}


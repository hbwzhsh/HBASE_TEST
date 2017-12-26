package com.ande.chart.controller;

import javax.servlet.http.HttpServletRequest;

import com.ande.chart.service.EchartsT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Option;
import com.ande.chart.service.EchartsT;

/**
 * 3 控制层代码
 * 2015-6-12下午1:36:51
 *springechart.com.controller.echartsContr
 */
@Controller
@RequestMapping("/echarts")
public class echartsContr {

    EchartsT ech = new EchartsT();

    /**
     * @param res
     * @return
     * 堆栈图
     */
    @RequestMapping("/stackYear")
    public String stackYear(HttpServletRequest res)
    {
        //调用服务层的函数并保存返回值
        Option option=ech.stackYear();
        //通过json装换成字符串
        String opt =JSON.toJSONString(option);
        //将对象传入到jsp页面
        res.setAttribute("option", opt);
        return "ec";
    }

    @RequestMapping("/stackChart")
    public String stackChart(HttpServletRequest res)
    {
        Option option = ech.stackChart();
        String opt = JSON.toJSONString(option);
        res.setAttribute("option", opt);
        return "ec";
    }

}
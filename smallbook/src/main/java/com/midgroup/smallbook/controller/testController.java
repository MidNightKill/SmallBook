package com.midgroup.smallbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:ZZY
 * Date:2019-7-21
 * Target:测试Controller
 */
@Controller
public class testController {
    @RequestMapping(value = "/test")
//    @ResponseBody
    public String test()
    {
        return "index";
    }
}

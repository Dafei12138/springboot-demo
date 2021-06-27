package com.fiction.rabbitmq.controller;

import com.fiction.rabbitmq.sender.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试控制器
 */
@Controller
@RequestMapping
public class TestController {

    @Autowired
    private SendMessage sendMessage;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        for (int i = 0; i < 5; i++) {
            sendMessage.sendMessage("", "confirm_test_queue",  "发送者消息");
        }
        return "success";
    }
}

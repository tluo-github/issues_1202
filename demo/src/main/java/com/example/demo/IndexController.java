package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);



    @RequestMapping(value = "/luotao")
    public String luotao(){
        if (true) {
            throw new RuntimeException("luotao error");
        }
        return "index";
    }


    @RequestMapping(value = "/transa")
    public String transa() throws Exception {
        Thread.sleep(1000);
        return "transa,sleep 1 s";
    }
    @RequestMapping(value = "/transb")
    public String transb() throws Exception {
        Thread.sleep(5000);
        return "transa,sleep 5 s";
    }
    @RequestMapping(value = "/transc")
    public String transc() throws Exception {
        Thread.sleep(10000);
        return "transa,sleep 10 s";
    }

    @RequestMapping(value = "/transd")
    public String transd() throws Exception {
        Thread.sleep(60 * 1000);
        return "transa,sleep 60 s";
    }

    @RequestMapping(value = "/transe")
    public String transE() throws Exception {
        Thread.sleep(5 * 60 * 1000);
        return "transa,sleep 5 m";
    }


}

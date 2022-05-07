package com.benny.jane.controller;

import com.benny.jane.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

// 使用Controller无效（Spring框架）
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello, world!";
    }

    @RequestMapping("/hello/json")
    public Result sayJson(String name) {

        logger.info("/hello/json  " + name);

        return Result.okResult(name);
    }

    @RequestMapping("/cache")
    public ResponseEntity<String> cache(@RequestParam String id) {
        String msg = MessageFormat.format("ID is {1}", id);

        logger.info(msg);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Test-Example-Header", "Value-ResponseEntityBuilderWithHttpHeaders");
        httpHeaders.setCacheControl(CacheControl.maxAge(60 * 5, TimeUnit.SECONDS));

        return ResponseEntity.ok().headers(httpHeaders)
                .body("Response with header using ResponseEntity");
    }

    @RequestMapping("/cache/json")
    public ResponseEntity<Result> cacheJson(@RequestParam String id) {
        String msg = MessageFormat.format("ID is {1}", id);
        Result result = Result.okResult(msg);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Test-Example-Header", "Value-ResponseEntityBuilderWithHttpHeaders");
        httpHeaders.setCacheControl(CacheControl.maxAge(60 * 5, TimeUnit.SECONDS));

        return ResponseEntity.ok().headers(httpHeaders)
                .body(result);
    }
}

package redisBasePackage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    private Logger logger= LoggerFactory.getLogger("TestController");
    @RequestMapping("/urlT0")
    public String testUrlTo(){
        logger.info("日志进来了");
        System.out.println("..");
        return "index";
    }
}

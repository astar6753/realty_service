package com.astar.global.presentation;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController @RequestMapping("/api")
public class TestAPIController {
    
    // #1 
    @GetMapping("/test/timestamp")
    public Map<String,Object> testTimestamp(@RequestParam String ext) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        //타입스탬프
        long aaa = System.currentTimeMillis();
        
        System.out.println("=================================");
        System.out.println(aaa);
        System.out.println("=================================");
        
        StringBuilder sb = new StringBuilder();
        sb.append("str1");
        sb.append(aaa);
        String concat = sb.toString();
        System.out.println("=================================");
        System.out.println(concat);
        System.out.println("=================================");

        StringBuffer sbf = new StringBuffer();
        sbf.append("str1");
        sbf.append(aaa);
        String concat2 = sbf.toString();
        System.out.println("=================================");
        System.out.println(concat2);
        System.out.println("=================================");
        switch(ext.toLowerCase()) {
            case "jpg": case "jpeg": case "png": case "gif": 
                resultMap.put("status",true);
                break;
            default : 
                resultMap.put("status",false);
                resultMap.put("message","file extension is not allowed [jpg,jpeg,png,gif]");
                return resultMap;
        }
        if(!ext.equalsIgnoreCase("jpg")&&!ext.equalsIgnoreCase("jpeg")&&!ext.equalsIgnoreCase("png")&&!ext.equalsIgnoreCase("gif")) {
            resultMap.put("status",false);
            resultMap.put("message","file extension is not allowed [jpg,jpeg,png,gif]");
            
        }
        return resultMap;
    }

    // #2 @Slf4j
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAPIController.class);
    @GetMapping("/test/log")
    public Map<String,Object> testLog() {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        
        int sample1 = 111;
        Integer sample2 = 222;
        resultMap.put("sample1", sample1);
        resultMap.put("sample2", sample2);

        log.trace("=================================================");
        log.trace("1번의 값은 : {} 2번의 값은 : {}", sample1, sample2);
        log.trace("=================================================");
        log.debug("=================================================");
        log.debug("1번의 값은 : {} 2번의 값은 : {}", sample1, sample2);
        log.debug("=================================================");
        log.info("=================================================");
        log.info("1번의 값은 : {} 2번의 값은 : {}", sample1, sample2);
        log.info("=================================================");
        log.warn("=================================================");
        log.warn("1번의 값은 : {} 2번의 값은 : {}", sample1, sample2);
        log.warn("=================================================");
        log.error("=================================================");
        log.error("1번의 값은 : {} 2번의 값은 : {}", sample1, sample2);
        log.error("=================================================");
        LOGGER.info("sajkfljaflioefjwio");
        

        return resultMap;
    }







}

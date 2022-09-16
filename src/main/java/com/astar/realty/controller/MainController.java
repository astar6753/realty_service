package com.astar.realty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    // @Autowired RealtyService r_service;
    // @Autowired RealtyAPIController r_controller;

    @GetMapping({"/", "/index", "/main"})
    public String getMain() {
        return "/index";
    }

    // @GetMapping("/detail")
    // private String getRealtyDetail(@RequestParam Integer seq, Model model) {
    //     model.addAttribute("data", r_service.selectPostInfoBySeq(seq));
    //     model.addAttribute("data", r_controller.selectPostInfoBySeq(seq).getBody());
    // }

    // @GetMapping("/login")
    // public String getUserLogin() {
    //     return "/login";
    // }

    // @GetMapping("/join")
    // public String getUserJoin() {
    //     return "/join";0
    // }

    // value 생략 가능 자동 매핑 배열형태로 다중 인식 가능
    // @GetMapping(consumes = "",headers = "", name = "",params = "",path = "",produces = "")
    // public SomeData getMethodName(@RequestParam String param) {
    //     return new SomeData();
    // }
    
}

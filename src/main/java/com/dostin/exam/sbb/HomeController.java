package com.dostin.exam.sbb;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//  @Controller : 스프링부트한테 이 클래스는 컨트롤러 역할이라고 알려준다.
@Controller
public class HomeController {
    @RequestMapping("/sbb")
    // @ResponseBody : 함수 실행 결과를 body에 그려준다.
    @ResponseBody
    public String showHome() {
        return "첫 시작";
    }
}

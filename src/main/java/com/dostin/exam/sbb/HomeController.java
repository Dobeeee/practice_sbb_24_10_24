package com.dostin.exam.sbb;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//  @Controller : 스프링부트한테 이 클래스는 컨트롤러 역할이라고 알려준다.
@Controller
public class HomeController {
    @RequestMapping("/sbb")
    // @ResponseBody : 함수 실행 결과를 body에 그려준다.
    @ResponseBody
    public String showHome() {
        return "라이브";
    }

    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "3") int a, @RequestParam(defaultValue = "1") int b) {
        return a + b;
    }

    @GetMapping("/plus2")
    @ResponseBody
    public int showPlus2(HttpServletRequest req, HttpServletResponse resp) {
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));
        return a + b;

    }

    @GetMapping("/mbti/{name}")
    @ResponseBody
    public String showMbti(@PathVariable String name) {
        String rs = switch (name) {
          case "홍길동" -> "INFP";
          case "홍길순" -> "ENFP";
          case "임꺽정" -> "ESFJ";
            default -> "모름";
        };
        return rs;
    }
}

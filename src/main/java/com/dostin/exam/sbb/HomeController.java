package com.dostin.exam.sbb;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//  @Controller : 스프링부트한테 이 클래스는 컨트롤러 역할이라고 알려준다.
@Controller
public class HomeController {

    private int increaseNo;
    private List<Article> articles;

    public HomeController() {
        increaseNo = -1;
        articles = new ArrayList<>();
    }
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

    @GetMapping("saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession(@PathVariable String name, @PathVariable String value, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
        return "세션 변수의 %s의 값이 %s(으)로 설정되었습니다.".formatted(name, value);
    }

    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String getSession(@PathVariable String name, HttpSession session) {
        String value = (String) session.getAttribute(name);
        return "세션 변수의 %s의 값이 %s입니다..".formatted(name, value);
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
    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {
        int id = 1;
        Article article = new Article(title, body);
        articles.add(article);

        System.out.println(article);


        return "%d번 게시물이 추가되었습니다.".formatted(article.getId());
    }

    @GetMapping("/article/list")
    @ResponseBody
    public List<Article> getArticles() {
        return articles;

    }


}

@AllArgsConstructor
@Getter
@ToString
class Article {
    private static int lastId;
    private final int id;
    private final String title;
    private final String body;

    static {
        lastId = 0;
    }
    public Article(String title, String body) {
        this(++lastId, title, body);
    }
}
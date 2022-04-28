package k5s.hotmovie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "redirect:" + "https://k5smovie.ga" + "/movies";
    }

}

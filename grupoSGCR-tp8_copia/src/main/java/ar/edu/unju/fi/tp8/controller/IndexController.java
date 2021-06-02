package ar.edu.unju.fi.tp8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @GetMapping("/home")
    public String getIndexPage(){
        return "index";
    }
    
}

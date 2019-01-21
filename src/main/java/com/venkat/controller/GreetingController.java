package com.venkat.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/greet")
public class GreetingController {

    //localhost:8080/greet/hello?name=Venkatram
    @GetMapping("/hello")
    public String greet(@RequestParam(value = "name") String userName){
        return "Hello, " + userName;
    }

    //localhost:8080/greet/hello?name=Venkatram
    @GetMapping("/hello2")
    public String greet2(@RequestParam String name){
        return "Hello, " + name;
    }

    //above both methods are same

    //localhost:8080/greet/hello?name=Venkatram&time=morning
    //localhost:8080/greet/hello?name=Venkatram
    @GetMapping("/hello3")
    public String greet3(@RequestParam String name, @RequestParam(required = false) String time){
        if(time == null){
            time = "day";
        }
        return "Hello" + time + ", " + name;
    }

    @GetMapping("/request-header")
    public String language(@RequestHeader(value = "Accept-Language") String language){
        return language;
    }

}

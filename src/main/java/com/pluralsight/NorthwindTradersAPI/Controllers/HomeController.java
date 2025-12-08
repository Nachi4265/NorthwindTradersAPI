package com.pluralsight.NorthwindTradersAPI.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(path = "/" , method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "World") String name){
        return "Hello " + name + " welcome to home page!";
    }

    @RequestMapping(path = "/coolNumbers" , method = RequestMethod.GET)
    public double coolNumbers(){
        return 3.14;
    }

    @RequestMapping(path = "/hello" , method = RequestMethod.GET)
    public String hello(){
        return "Hello to <strong> you </strong> too!";
    }

    @RequestMapping(path = "/Goodbye" , method = RequestMethod.GET)
    public String goodbye(){
        return "See ya next time!";
    }
}

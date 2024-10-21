package me.study.shorturl;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    //(1) text/plain
    @GetMapping(path = "/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(path = "/bye")
    public String bye(){
        return "bye";
    }

    //(2) JSON


}

package nl.ramonpeek.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WolfController {

    @GetMapping("/test")
    public String test() {
        return "hi";
    }

}

package pl.dicedev.simplyauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/ok")
    public String getOK() {
        return "OK";
    }

}

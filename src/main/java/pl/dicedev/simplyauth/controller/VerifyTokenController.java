package pl.dicedev.simplyauth.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.simplyauth.service.VerifyTokenService;

@RestController
@RequestMapping("/v2/token")
@AllArgsConstructor
public class VerifyTokenController {

    private final VerifyTokenService service;

    @PostMapping("/valid")
    public boolean isValid(
            @RequestHeader("token") String token,
            @RequestParam("right") String right) {
        return service.isValid(token, right);
    }

}

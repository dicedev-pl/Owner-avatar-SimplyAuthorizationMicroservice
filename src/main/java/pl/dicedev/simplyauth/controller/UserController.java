package pl.dicedev.simplyauth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.simplyauth.dto.AuthDto;
import pl.dicedev.simplyauth.dto.AuthUserDto;
import pl.dicedev.simplyauth.dto.CredentialsDto;
import pl.dicedev.simplyauth.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/v2/authentication")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public UUID addNewUser(
            @RequestHeader("user-id") String userId,
            @RequestHeader("token") String token,
            @RequestBody AuthUserDto authUserDto
    ) {
        log.info(userId);
        log.info(token);
        log.info(String.valueOf(authUserDto));
        return userService.addUser(userId, authUserDto, token);
    }

    @PostMapping("/login")
    public AuthDto addNewUser(
            @RequestBody CredentialsDto credentialsDto
    ) {
        log.info(String.valueOf(credentialsDto));
        return userService.login(credentialsDto);
    }

}

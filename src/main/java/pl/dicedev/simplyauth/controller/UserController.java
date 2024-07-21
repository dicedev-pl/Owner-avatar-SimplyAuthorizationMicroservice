package pl.dicedev.simplyauth.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.simplyauth.dto.AuthUserDto;
import pl.dicedev.simplyauth.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/v2/authentication")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public UUID addNewUser(
            @RequestHeader("user-id") String userId,
            @RequestBody AuthUserDto authUserDto
    ) {
        return userService.addUser(userId, authUserDto);
    }

}

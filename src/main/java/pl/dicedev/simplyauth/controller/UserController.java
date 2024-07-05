package pl.dicedev.simplyauth.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.simplyauth.dto.AuthUserDto;
import pl.dicedev.simplyauth.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/v2/authentication")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public boolean addNewUser(
            @RequestHeader("user-id") String userId,
            @RequestBody AuthUserDto authUserDto
            ) {

        String credentials = userId + authUserDto.getPassword();
        byte[] afterDecode = Base64.getDecoder().decode(credentials);
        String hash = new String(afterDecode, StandardCharsets.UTF_8);

        userService.addUser(hash, authUserDto.getUsername());

        return true;
    }

}

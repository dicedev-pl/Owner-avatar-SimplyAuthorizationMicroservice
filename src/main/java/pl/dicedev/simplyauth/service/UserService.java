package pl.dicedev.simplyauth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.simplyauth.dto.AuthUserDto;
import pl.dicedev.simplyauth.repository.UserCredentialsRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserCredentialsRepository userCredentialsRepository;

    public UUID addUser(String pwPrefix, AuthUserDto authUserDto) {
        UserEntity entity = prepareUserEntity(pwPrefix, authUserDto);
        var savedEntity = userCredentialsRepository.save(entity);

        return savedEntity.getId();
    }

    private UserEntity prepareUserEntity(String pwPrefix, AuthUserDto authUserDto) {
        String passwdHash = preparePasswdHash(pwPrefix, authUserDto);

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username(authUserDto.getUsername())
                .password(passwdHash)
                .createAt(Instant.now())
                .rights(authUserDto.getRights())
                .build();
    }

    private String preparePasswdHash(String pwPrefix, AuthUserDto authUserDto) {
        String credentials = pwPrefix + authUserDto.getPassword();
        byte[] afterDecode = Base64.getDecoder().decode(credentials);
        return new String(afterDecode, StandardCharsets.UTF_8);
    }
}

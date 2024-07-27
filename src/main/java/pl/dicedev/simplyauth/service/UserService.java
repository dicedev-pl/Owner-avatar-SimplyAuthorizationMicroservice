package pl.dicedev.simplyauth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.simplyauth.dto.AuthDto;
import pl.dicedev.simplyauth.dto.AuthUserDto;
import pl.dicedev.simplyauth.dto.CredentialsDto;
import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.repository.UserCredentialsRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserCredentialsRepository userCredentialsRepository;

    public UUID addUser(String pwPrefix, AuthUserDto authUserDto, String token) {
        UserEntity entity = prepareUserEntity(pwPrefix, authUserDto);
        var savedEntity = userCredentialsRepository.save(entity);

        return savedEntity.getId();
    }

    public AuthDto login(CredentialsDto credentialsDto) {
        UserEntity userEntity = userCredentialsRepository.findByUsernameAndPassword(credentialsDto.getLogin(), credentialsDto.getPassword());
        if (userEntity == null) return new AuthDto(null);
        String token = buildToken(userEntity);

        return new AuthDto(token);
    }

    private String buildToken(UserEntity userEntity) {
        return "ust:" + prepareUSTRights(userEntity.getRights()) +
                "+xst:" + prepareXSTRights(userEntity.getRights()) +
                "+name:" + userEntity.getUsername() +
                "+rights:" + userEntity.getRights().toString()
                .trim();
    }

    private String prepareUSTRights(Set<Rights> rights) {
        StringBuilder rightToToken = new StringBuilder();
        for (Rights right : rights) {
            rightToToken.append(right.getUst());
            rightToToken.append("-");
        }
        rightToToken.deleteCharAt(rightToToken.length() - 1);
        return rightToToken.toString();
    }

    private String prepareXSTRights(Set<Rights> rights) {
        StringBuilder rightToToken = new StringBuilder();
        for (Rights right : rights) {
            rightToToken.append(right.getXst());
            rightToToken.append("-");
        }
        rightToToken.deleteCharAt(rightToToken.length() - 1);
        return rightToToken.toString();
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

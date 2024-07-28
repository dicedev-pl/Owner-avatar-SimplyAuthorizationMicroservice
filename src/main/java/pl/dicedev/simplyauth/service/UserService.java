package pl.dicedev.simplyauth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.simplyauth.dto.AuthDto;
import pl.dicedev.simplyauth.dto.AuthUserDto;
import pl.dicedev.simplyauth.dto.CredentialsDto;
import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.enums.TokenSeparators;
import pl.dicedev.simplyauth.repository.UserCredentialsRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;
import pl.dicedev.simplyauth.utils.PasswordUtil;
import pl.dicedev.simplyauth.validators.RightsFromToken;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static pl.dicedev.simplyauth.utils.PasswordUtil.preparePasswdHash;

@Service
@AllArgsConstructor
public class UserService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final RightsFromToken rightsFromToken;

    public UUID addUser(String pwPrefix, AuthUserDto authUserDto, String token) {
        Map<Rights, Boolean> rights = rightsFromToken.getValidatedRights(token);
        if (!rights.get(Rights.ADD_USERS)) return UUID.fromString("0");

        UserEntity entity = prepareUserEntity(pwPrefix, authUserDto);
        var savedEntity = userCredentialsRepository.save(entity);

        return savedEntity.getId();
    }

    public AuthDto login(CredentialsDto credentialsDto) {
        String hashedPw = PasswordUtil.preparePasswdHash(credentialsDto.getPassword());
        UserEntity userEntity = userCredentialsRepository.findByUsernameAndPassword(credentialsDto.getLogin(), hashedPw);
        if (userEntity == null) return new AuthDto(null);
        String token = buildToken(userEntity);

        return new AuthDto(token);
    }

    private String buildToken(UserEntity userEntity) {
        return "ust:" + prepareUSTRights(userEntity.getRights()) + TokenSeparators.SECTIONS.getSeparator() +
                "xst:" + prepareXSTRights(userEntity.getRights()) + TokenSeparators.SECTIONS.getSeparator() +
                "name:" + userEntity.getUsername() + TokenSeparators.SECTIONS.getSeparator() +
                "rights:" + userEntity.getRights().toString()
                .trim();
    }

    private String prepareUSTRights(Set<Rights> rights) {
        StringBuilder rightToToken = new StringBuilder();
        for (Rights right : rights) {
            rightToToken.append(right.getUst());
            rightToToken.append(TokenSeparators.DETAIL.getSeparator());
        }
        rightToToken.deleteCharAt(rightToToken.length() - 1);
        return rightToToken.toString();
    }

    private String prepareXSTRights(Set<Rights> rights) {
        StringBuilder rightToToken = new StringBuilder();
        for (Rights right : rights) {
            rightToToken.append(right.getXst());
            rightToToken.append(TokenSeparators.DETAIL.getSeparator());
        }
        rightToToken.deleteCharAt(rightToToken.length() - 1);
        return rightToToken.toString();
    }

    private UserEntity prepareUserEntity(String pwPrefix, AuthUserDto authUserDto) {
        String passwdHash = preparePasswdHash(pwPrefix + authUserDto.getPassword());

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username(authUserDto.getUsername())
                .password(passwdHash)
                .createAt(Instant.now())
                .rights(authUserDto.getRights())
                .build();
    }

}

package pl.dicedev.simplyauth.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.repository.UserCredentialsRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class InitDBByFirstUser implements ApplicationListener<ContextRefreshedEvent> {

    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initUser();
    }

    private void initUser() {
        int countUsers = userCredentialsRepository.findAll().size();

        if (countUsers == 0) {
            UserEntity userEntity = prepareUserEntity("QjQ5NUJFNTBCNDc4RDcyQTIxRDg5MEEwRERCMEI3MjY=");
            userCredentialsRepository.save(userEntity);
        }
    }

    private UserEntity prepareUserEntity(String pw) {
        String passwdHash = preparePasswdHash(pw);

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username("Admin")
                .password(passwdHash)
                .createAt(Instant.now())
                .rights(Set.of(Rights.ADD_USERS))
                .build();
    }

    private String preparePasswdHash(String pw) {
        byte[] afterDecode = Base64.getDecoder().decode(pw);
        return new String(afterDecode, StandardCharsets.UTF_8);
    }
}

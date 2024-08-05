package pl.dicedev.simplyauth.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.repository.UserCredentialsRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;

import java.time.Instant;
import java.util.*;

import static pl.dicedev.simplyauth.utils.PasswordUtil.preparePasswdHash;

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
            UserEntity rootEntity = prepareRootEntity("QjQ5NUJFNTBCNDc4RDcyQTIxRDg5MEEwRERCMEI3MjY=");
            userCredentialsRepository.saveAll(List.of(userEntity, rootEntity));
        }
    }

    private UserEntity prepareRootEntity(String pw) {
        String passwdHash = preparePasswdHash(pw);
        Set<Rights> allRights = EnumSet.allOf(Rights.class);

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username("Root")
                .password(passwdHash)
                .createAt(Instant.now())
                .rights(allRights)
                .build();
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

}

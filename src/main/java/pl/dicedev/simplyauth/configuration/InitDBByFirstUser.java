package pl.dicedev.simplyauth.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.repository.UserCredentialsRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;
import pl.dicedev.simplyauth.utils.PasswordUtil;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
@EnableConfigurationProperties(CustomInitUser.class)
@AllArgsConstructor
public class InitDBByFirstUser implements ApplicationListener<ContextRefreshedEvent> {

    private final UserCredentialsRepository userCredentialsRepository;
    private final CustomInitUser customInitUser;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (customInitUser.isValid()) {
            initCustomUser();
        } else {
            initDefaultUsers();
        }
    }

    private void initCustomUser() {
        Set<Rights> userRights;
        if (customInitUser.hasRights()) {
            userRights = customInitUser.getRights().stream()
                    .map(right -> Rights.valueOf(right.toUpperCase()))
                    .collect(Collectors.toSet());
        } else {
            userRights = EnumSet.allOf(Rights.class);
        }

        UserEntity userEntity =  UserEntity.builder()
                .id(UUID.randomUUID())
                .username(customInitUser.getName())
                .password(PasswordUtil.encodePasswdToMd5(customInitUser.getName(), customInitUser.getPassword()))
                .createAt(Instant.now())
                .rights(userRights)
                .build();

        userCredentialsRepository.save(userEntity);
    }


    private void initDefaultUsers() {
        int countUsers = userCredentialsRepository.findAll().size();

        if (countUsers == 0) {
            UserEntity userEntity = prepareUserEntity("Admin", "AdminMaKota");
            UserEntity rootEntity = prepareRootEntity("RootMaKota");
            userCredentialsRepository.saveAll(List.of(userEntity, rootEntity));
        }
    }

    private UserEntity prepareRootEntity(String password) {
        String passwdHash = PasswordUtil.encodePasswdToMd5("Root", password);
        Set<Rights> allRights = EnumSet.allOf(Rights.class);

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username("Root")
                .password(passwdHash)
                .createAt(Instant.now())
                .rights(allRights)
                .build();
    }

    private UserEntity prepareUserEntity(String name, String password) {
        String passwdHash = PasswordUtil.encodePasswdToMd5(name, password);

        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username(name)
                .password(passwdHash)
                .createAt(Instant.now())
                .rights(Set.of(Rights.ADD_USERS))
                .build();
    }

}

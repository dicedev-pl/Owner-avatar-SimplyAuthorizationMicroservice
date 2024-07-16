package pl.dicedev.simplyauth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.simplyauth.repository.UserCredentialsRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserCredentialsRepository userCredentialsRepository;

    public UUID addUser(String passwdHash, String username) {
        // save to database

        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setUsername(username);
        entity.setPassword(passwdHash);

        var savedEntity = userCredentialsRepository.save(entity);
        System.out.println("User: " + username);
        return savedEntity.getId();
    }
}

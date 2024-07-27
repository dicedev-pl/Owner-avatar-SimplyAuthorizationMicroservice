package pl.dicedev.simplyauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.dicedev.simplyauth.repository.entity.UserEntity;

import java.util.UUID;

public interface UserCredentialsRepository extends MongoRepository<UserEntity, UUID> {

    UserEntity findByUsernameAndPassword(String username, String password);

}

package pl.dicedev.simplyauth.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.dicedev.simplyauth.enums.Rights;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    private UUID id;
    private String username;
    private String password;
    private Set<Rights> rights;
    private Instant createAt;

}

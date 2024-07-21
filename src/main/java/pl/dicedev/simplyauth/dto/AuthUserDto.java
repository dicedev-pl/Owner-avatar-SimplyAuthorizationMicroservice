package pl.dicedev.simplyauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dicedev.simplyauth.enums.Rights;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {

    private String username;
    private String password;
    private Set<Rights> rights;

}

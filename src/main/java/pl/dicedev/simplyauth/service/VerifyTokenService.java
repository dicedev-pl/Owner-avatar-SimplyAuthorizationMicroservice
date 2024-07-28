package pl.dicedev.simplyauth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.validators.RightsFromToken;

import java.util.Map;

@Service
@AllArgsConstructor
public class VerifyTokenService {

    private final RightsFromToken rightsFromToken;

    public boolean isValid(String token, String rightToCheck) {
        Rights right = Rights.findByName(rightToCheck.toUpperCase());

        Map<Rights, Boolean> rights = rightsFromToken.getValidatedRights(token);
        return right != null && rights.get(right);
    }

}

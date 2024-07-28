package pl.dicedev.simplyauth.validators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.dicedev.simplyauth.enums.Rights;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class RightsFromToken {

    private final Map<Rights, RightsValidator> validatorMap;

    public Map<Rights, Boolean> getValidatedRights(String token) {
        Map<Rights, Boolean> mapWithRights = new HashMap<>();

        validatorMap.values()
                .forEach(value -> value.validate(mapWithRights, token));

        return mapWithRights;
    }

}

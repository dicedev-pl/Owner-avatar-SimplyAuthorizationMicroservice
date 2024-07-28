package pl.dicedev.simplyauth.validators;

import org.junit.jupiter.api.Test;
import pl.dicedev.simplyauth.enums.Rights;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RightsValidatorTest {

    private final RightsValidator validator = new RightsValidatorForAddUsers();

    @Test
    void shouldParseTheToken() {
        // given
        String token = "ust:au+xst:create+name:Admin+rights:[ADD_USERS]";
        Map<Rights, Boolean> rights = new HashMap<>();

        // when
        validator.validate(rights, token);

        // then
        assertTrue(rights.get(Rights.ADD_USERS));

    }
}
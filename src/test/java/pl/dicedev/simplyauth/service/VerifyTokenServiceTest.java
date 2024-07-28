package pl.dicedev.simplyauth.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.validators.RightsFromToken;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerifyTokenServiceTest {

    private static VerifyTokenService service;

    @BeforeAll
    static void init(
            @Mock RightsFromToken rightsFromToken
    ) {
        when(rightsFromToken.getValidatedRights(anyString()))
                .thenReturn(
                        Map.of(
                                Rights.USER, false,
                                Rights.ADD_USERS, true
                        )
                );

        service = new VerifyTokenService(rightsFromToken);
    }

    @Test
    void shouldReturnTrueIfFindRightsInToken() {
        // given
        String token = "ust:au+xst:create+name:Admin+rights:[ADD_USERS]";
        String right = "ADD_USERS";

        // when
        boolean result = service.isValid(token, right);

        // then
        assertTrue(result);

    }

    @Test
    void shouldReturnFalseIfNotFindRights() {
        // given
        String token = "ust:au+xst:create+name:Admin+rights:[ADD_USERS]";
        String right = "USER";

        // when
        boolean result = service.isValid(token, right);

        // then
        assertFalse(result);

    }

    @Test
    void shouldReturnFalseIfCheckedRightDoNotExist() {
        // given
        String token = "ust:au+xst:create+name:Admin+rights:[ADD_USERS]";
        String right = "FakeRight";

        // when
        boolean result = service.isValid(token, right);

        // then
        assertFalse(result);

    }
}









package pl.dicedev.simplyauth.validators;

import org.springframework.stereotype.Component;
import pl.dicedev.simplyauth.enums.Rights;

@Component
class RightsValidatorForRemoveReservations extends RightsValidator {

    @Override
    protected Rights getRightsToCheck() {
        return Rights.REMOVE_RESERVATIONS;
    }
}

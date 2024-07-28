package pl.dicedev.simplyauth.validators;

import org.springframework.stereotype.Component;
import pl.dicedev.simplyauth.enums.Rights;

@Component
class RightsValidatorForTrainee extends RightsValidator {

    @Override
    protected Rights getRightsToCheck() {
        return Rights.TRAINEE;
    }
}

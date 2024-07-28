package pl.dicedev.simplyauth.validators;

import org.springframework.stereotype.Component;
import pl.dicedev.simplyauth.enums.Rights;

@Component
class RightsValidatorForOrderFood extends RightsValidator {

    @Override
    protected Rights getRightsToCheck() {
        return Rights.ORDER_FOOD;
    }
}

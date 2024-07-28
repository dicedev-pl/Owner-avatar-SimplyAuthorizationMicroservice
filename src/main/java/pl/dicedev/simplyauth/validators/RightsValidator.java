package pl.dicedev.simplyauth.validators;

import pl.dicedev.simplyauth.enums.Rights;
import pl.dicedev.simplyauth.enums.TokenSeparators;

import java.util.Map;

public abstract class RightsValidator {

    public void validate(Map<Rights, Boolean> havingRight, String token) {
        // token example: ust:au+xst:create+name:Admin+rights:[ADD_USERS]
        String[] splitedToken = token.split(TokenSeparators.SECTIONS.getSeparatorWithEscape());
        Rights rightsToCheck = getRightsToCheck();

        boolean isUst = splitedToken[0].contains(rightsToCheck.getUst());
        boolean isXst = splitedToken[1].contains(rightsToCheck.getXst());
        boolean isName = splitedToken[3].contains(rightsToCheck.name());

        if (isUst && isXst && isName) {
            havingRight.put(rightsToCheck, true);
        } else {
            havingRight.put(rightsToCheck, false);
        }
    }

    protected abstract Rights getRightsToCheck();

}

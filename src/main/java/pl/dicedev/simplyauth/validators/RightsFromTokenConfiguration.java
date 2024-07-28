package pl.dicedev.simplyauth.validators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dicedev.simplyauth.enums.Rights;

import java.util.Map;

@Configuration
class RightsFromTokenConfiguration {

    @Bean
    public Map<Rights, RightsValidator> validatorMap(
            RightsValidatorForUser rightsValidatorForUser,
            RightsValidatorForAddUsers rightsValidatorForAddUsers,
            RightsValidatorForAddReservations rightsValidatorForAddReservations,
            RightsValidatorForOrderFood rightsValidatorForOrderFood,
            RightsValidatorForRemoveReservations rightsValidatorForRemoveReservations,
            RightsValidatorForRemoveUsers rightsValidatorForRemoveUsers,
            RightsValidatorForTrainee rightsValidatorForTrainee
    ) {
        return Map.of(
                Rights.USER, rightsValidatorForUser,
                Rights.ADD_USERS, rightsValidatorForAddUsers,
                Rights.ADD_RESERVATIONS, rightsValidatorForAddReservations,
                Rights.ORDER_FOOD, rightsValidatorForOrderFood,
                Rights.REMOVE_RESERVATIONS, rightsValidatorForRemoveReservations,
                Rights.REMOVE_USERS, rightsValidatorForRemoveUsers,
                Rights.TRAINEE, rightsValidatorForTrainee
        );
    }

}

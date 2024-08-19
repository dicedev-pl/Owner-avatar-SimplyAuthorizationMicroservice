package pl.dicedev.simplyauth.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@ConfigurationProperties(prefix = "dicedev.init.user")
class CustomInitUser {

    private String name;
    private String password;
    private Set<String> rights;

    public boolean isValid() {
        return name != null && password != null;
    }

    public boolean hasRights() {
        return rights != null && !rights.isEmpty();
    }
}
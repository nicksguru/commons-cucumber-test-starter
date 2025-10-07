package guru.nicks.cucumber.world;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Domain-specific (not feature-specific) state shared between scenario steps. Thanks to
 * {@link ScenarioScope @ScenarioScope}, each scenario gets a fresh copy.
 */
@Component
@ScenarioScope
@Data
public class UserWorld {

    private String userId;
    private String username;
    private String password;
    private String email;

    /**
     * Syntactically, i.e. as per password policy.
     */
    private Boolean passwordCompliant;

}

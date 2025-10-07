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
public class NumberWorld {

    private Integer intValue1;
    private Long longValue1;
    private Double doubleValue1;

    private Integer intValue2;
    private Long longValue2;
    private Double doubleValue2;

    private Integer intValue3;
    private Long longValue3;
    private Double doubleValue3;

}

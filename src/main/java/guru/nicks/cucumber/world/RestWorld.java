package guru.nicks.cucumber.world;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Domain-specific (not feature-specific) state shared between scenario steps. Thanks to
 * {@link ScenarioScope @ScenarioScope}, each scenario gets a fresh copy.
 */
@Component
@ScenarioScope
@Data
public class RestWorld {

    /**
     * Can be either a template or a real address. In general, methods like
     * {@link org.springframework.test.web.servlet.request.MockMvcRequestBuilders#get(String, Object...)} accept a
     * template and variables to insert in the template.
     */
    private String urlTemplate;
    private Object[] uriVariables;

    private String requestContentType = "application/json";
    private ResultActions invocationResult;

}

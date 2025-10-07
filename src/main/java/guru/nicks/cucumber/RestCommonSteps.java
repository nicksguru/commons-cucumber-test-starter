package guru.nicks.cucumber;

import guru.nicks.cucumber.world.RestWorld;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("java:S100") // allow underscores in method names
@RequiredArgsConstructor
public class RestCommonSteps {

    // DI
    private final RestWorld restWorld;
    private final MockMvc mockMvc;

    @Given("endpoint {string}")
    public void endpoint(String endpoint) {
        restWorld.setUrlTemplate(endpoint);
    }

    @Given("request content type {string}")
    public void requestContentType(String contentType) {
        restWorld.setRequestContentType(contentType);
    }

    @Given("endpoint variables")
    public void endpointVariables(Collection<String> variables) {
        restWorld.setUriVariables(variables.toArray());
    }

    @When("endpoint is invoked")
    public void endpointIsInvoked() throws Exception {
        // should not be null
        Object[] uriVariables = Optional.ofNullable(restWorld.getUriVariables())
                .orElseGet(() -> new Object[]{});

        restWorld.setInvocationResult(mockMvc.perform(
                post(restWorld.getUrlTemplate(), uriVariables)
                        .contentType(restWorld.getRequestContentType())));
    }

    @Then("HTTP status is {int}")
    public void httpStatusIs(int httpStatus) throws Exception {
        restWorld.getInvocationResult()
                .andExpect(status().is(httpStatus));
    }

}

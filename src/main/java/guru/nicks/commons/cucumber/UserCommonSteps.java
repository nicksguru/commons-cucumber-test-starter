package guru.nicks.commons.cucumber;

import guru.nicks.commons.cucumber.world.UserWorld;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("java:S100") // allow underscores in method names
public class UserCommonSteps {

    // DI
    private final UserWorld userWorld;

    @Given("user ID is {string}")
    public void userIdIs(String userId) {
        userWorld.setUserId(userId);
    }

    @Given("user name is {string}")
    public void userNameIs(String username) {
        userWorld.setUsername(username);
    }

    @Given("user email is {string}")
    public void userEmail(String email) {
        userWorld.setEmail(email);
    }

}

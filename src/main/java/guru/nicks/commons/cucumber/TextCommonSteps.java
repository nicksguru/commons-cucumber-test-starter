package guru.nicks.commons.cucumber;

import guru.nicks.commons.cucumber.world.TextWorld;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import jakarta.annotation.Nullable;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class TextCommonSteps {

    // DI
    private final TextWorld textWorld;

    /**
     * Cucumber has no '{boolean}' parameter type built-in. This method registers the '{booleanValue}' parameter type.
     */
    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }

    @Then("no exception should be thrown")
    public void noExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isNull();
    }

    /**
     * @see #anExceptionShouldNotBeThrown()
     */
    @Then("an exception should be thrown")
    public void anExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isNotNull();
    }

    /**
     * Differs from 'an exception should be thrown' by a single word ('not') which is useful for dynamic step called
     * from example tables, e.g. {@code @Then an exception <exception> be thrown}, and in the examples: 'should' /
     * 'should not'.
     *
     * @see #anExceptionShouldBeThrown()
     */
    @Then("an exception should not be thrown")
    public void anExceptionShouldNotBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isNull();
    }

    /**
     * If the argument is blank, checks that there's NO exception, which is convenient for scenario outline tables.
     *
     * @see #theExceptionShouldBeOfType(String)
     */
    @Then("the exception message should contain {string}")
    public void theExceptionMessageShouldContain(String substring) {
        if (StringUtils.isBlank(substring)) {
            assertThat(textWorld.getLastException())
                    .as("lastException")
                    .isNull();
            return;
        }

        assertThat(textWorld.getLastException())
                .as("lastException")
                .isNotNull();

        assertThat(textWorld.getLastException().getMessage())
                .as("lastException.getMessage()")
                .isNotNull();

        assertThat(textWorld.getLastException().getMessage())
                .as("lastException.getMessage()")
                .contains(substring);
    }

    /**
     * Checks {@link Class#getSimpleName()} of {@link TextWorld#getLastException()}. If the argument is blank, checks
     * that there's NO exception, which is convenient for scenario outline tables.
     */
    @Then("the exception should be of type {string}")
    public void theExceptionShouldBeOfType(String classNameWithoutPackage) {
        Throwable exception = textWorld.getLastException();

        if (StringUtils.isBlank(classNameWithoutPackage)) {
            assertThat(exception)
                    .as("lastException")
                    .isNull();
            return;
        }

        assertThat(exception)
                .as("lastException")
                .isNotNull();

        assertThat(exception.getClass().getSimpleName())
                .as("lastException class (simple name)")
                .isEqualTo(classNameWithoutPackage);
    }

    @Then("if an exception is thrown it should be of type {string}")
    public void ifAnExceptionIsThrownItShouldBeOfType(String classNameWithoutPackage) {
        if (textWorld.getLastException() != null) {
            assertThat(textWorld.getLastException().getClass().getSimpleName())
                    .as("lastException class (simple name)")
                    .isEqualTo(classNameWithoutPackage);
        }
    }

    /**
     * {@code guru.nicks.commons.exception.http.UnauthorizedException} class is not available in this module, therefore the
     * exception is checked by the aforementioned class name.
     */
    @Then("UnauthorizedException should be thrown")
    public void anUnauthorizeExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isNotNull();

        assertThat(textWorld.getLastException().getClass().getName())
                .as("lastException")
                .isEqualTo("guru.nicks.commons.exception.http.UnauthorizedException");
    }

    @Then("IllegalArgumentException should be thrown")
    public void anIllegalArgumentExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Then("NullPointerException should be thrown")
    public void nullPointerExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isInstanceOf(NullPointerException.class);
    }

    @Then("IllegalStateException should be thrown")
    public void anIllegalStateExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isInstanceOf(IllegalStateException.class);
    }

    @Then("UnknownHostException should be thrown")
    public void anUnknownHostExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isInstanceOf(UnknownHostException.class);
    }

    @Then("ValidationException should be thrown")
    public void validationExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isInstanceOf(ValidationException.class);
    }

    @Then("ArithmeticException should be thrown")
    public void anArithmeticExceptionShouldBeThrown() {
        assertThat(textWorld.getLastException())
                .as("lastException")
                .isInstanceOf(ArithmeticException.class);
    }

    @Given("date is {string}")
    public void dateIs(@Nullable String date) {
        textWorld.setDate(Optional.ofNullable(date)
                .filter(StringUtils::isNotBlank)
                .map(Instant::parse)
                .orElse(null));
    }

    @Given("alphabet is {string}")
    public void alphabetIs(String alphabet) {
        textWorld.setAlphabet(alphabet);
    }

    @Given("success should be {string}")
    public void successShouldBe(String value) {
        assertThat(textWorld.isSuccess())
                .isEqualTo(Boolean.parseBoolean(value));
    }

    @Given("input is {string}")
    public void inputIs(String input) {
        textWorld.setInput(input);
    }

    @Then("output should be empty")
    public void outputShouldBeEmpty() {
        assertThat(textWorld.getOutput())
                .as("output")
                .hasSize(0);
    }

    @Then("output should be {string}")
    public void outputIs(String expectedItem) {
        // blank strings come from Gherkin tables (empty columns) and should be ignored
        List<String> expectedItems = Stream.of(expectedItem)
                .filter(StringUtils::isNotBlank)
                .toList();
        assertThat(textWorld.getOutput()).isEqualTo(expectedItems);
    }

    @Then("output should be {string}, {string}")
    public void outputIs(String expectedItem1, String expectedItem2) {
        // blank strings come from Gherkin tables (empty columns) and should be ignored
        List<String> expectedItems = Stream.of(expectedItem1, expectedItem2)
                .filter(StringUtils::isNotBlank)
                .toList();
        assertThat(textWorld.getOutput()).isEqualTo(expectedItems);
    }

    @Then("output should be {string}, {string}, {string}")
    public void outputIs(String expectedItem1, String expectedItem2, String expectedItem3) {
        // blank strings come from Gherkin tables (empty columns) and should be ignored
        List<String> expectedItems = Stream.of(expectedItem1, expectedItem2, expectedItem3)
                .filter(StringUtils::isNotBlank)
                .toList();
        assertThat(textWorld.getOutput()).isEqualTo(expectedItems);
    }

    @Then("output should be {string}, {string}, {string}, {string}")
    public void outputIs(String expectedItem1, String expectedItem2, String expectedItem3, String expectedItem4) {
        // blank strings come from Gherkin tables (empty columns) and should be ignored
        List<String> expectedItems = Stream.of(expectedItem1, expectedItem2, expectedItem3, expectedItem4)
                .filter(StringUtils::isNotBlank)
                .toList();
        assertThat(textWorld.getOutput()).isEqualTo(expectedItems);
    }

    @Then("output should be {string}, {string}, {string}, {string}, {string}")
    public void outputIs(String expectedItem1, String expectedItem2, String expectedItem3, String expectedItem4,
            String expectedItem5) {
        // blank strings come from Gherkin tables (empty columns) and should be ignored
        List<String> expectedItems = Stream.of(expectedItem1, expectedItem2, expectedItem3, expectedItem4,
                        expectedItem5)
                .filter(StringUtils::isNotBlank)
                .toList();
        assertThat(textWorld.getOutput()).isEqualTo(expectedItems);
    }

    @Then("output should be {string}, {string}, {string}, {string}, {string}, {string}")
    public void outputIs(String expectedItem1, String expectedItem2, String expectedItem3, String expectedItem4,
            String expectedItem5, String expectedItem6) {
        // blank strings come from Gherkin tables (empty columns) and should be ignored
        List<String> expectedItems = Stream.of(expectedItem1, expectedItem2, expectedItem3, expectedItem4,
                        expectedItem5, expectedItem6)
                .filter(StringUtils::isNotBlank)
                .toList();
        assertThat(textWorld.getOutput()).isEqualTo(expectedItems);
    }

    @Then("output should be {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void outputIs(String expectedItem1, String expectedItem2, String expectedItem3, String expectedItem4,
            String expectedItem5, String expectedItem6, String expectedItem7) {
        // blank strings come from Gherkin tables (empty columns) and should be ignored
        List<String> expectedItems = Stream.of(expectedItem1, expectedItem2, expectedItem3, expectedItem4,
                        expectedItem5, expectedItem6, expectedItem7)
                .filter(StringUtils::isNotBlank)
                .toList();
        assertThat(textWorld.getOutput()).isEqualTo(expectedItems);
    }

    @Then("parsed date should be {string}")
    public void parsedDateIs(String date) {
        if (StringUtils.isBlank(date)) {
            assertThat(textWorld.getParsedDate()).isNull();
        } else {
            assertThat(textWorld.getParsedDate()).isEqualTo(Instant.parse(date));
        }
    }

    @SneakyThrows
    @And("sleep {long} milliseconds")
    public void sleepMilliseconds(long millis) {
        Thread.sleep(millis);
    }

}

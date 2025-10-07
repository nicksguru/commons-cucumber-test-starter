package guru.nicks.cucumber;

import guru.nicks.cucumber.world.NumberWorld;

import io.cucumber.java.en.Given;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
public class NumberCommonSteps {

    // DI
    private final NumberWorld numberWorld;

    @Given("integer1 is {string}")
    public void integer1Is(@Nullable String value) {
        numberWorld.setIntValue1(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Integer::parseInt)
                        .orElse(null));
    }

    @Given("long1 is {string}")
    public void long1Is(@Nullable String value) {
        numberWorld.setLongValue1(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Long::parseLong)
                        .orElse(null));
    }

    @Given("double1 is {string}")
    public void double1Is(@Nullable String value) {
        numberWorld.setDoubleValue1(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Double::parseDouble)
                        .orElse(null));
    }

    @Given("integer2 is {string}")
    public void integer2Is(@Nullable String value) {
        numberWorld.setIntValue2(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Integer::parseInt)
                        .orElse(null));
    }

    @Given("long2 is {string}")
    public void long2Is(@Nullable String value) {
        numberWorld.setLongValue2(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Long::parseLong)
                        .orElse(null));
    }

    @Given("double2 is {string}")
    public void double2Is(@Nullable String value) {
        numberWorld.setDoubleValue2(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Double::parseDouble)
                        .orElse(null));
    }

    @Given("integer3 is {string}")
    public void integer3Is(@Nullable String value) {
        numberWorld.setIntValue3(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Integer::parseInt)
                        .orElse(null));
    }

    @Given("long3 is {string}")
    public void long3Is(@Nullable String value) {
        numberWorld.setLongValue3(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Long::parseLong)
                        .orElse(null));
    }

    @Given("double3 is {string}")
    public void double3Is(@Nullable String value) {
        numberWorld.setDoubleValue3(
                Optional.ofNullable(value)
                        .filter(StringUtils::isNotBlank)
                        .map(Double::parseDouble)
                        .orElse(null));
    }

}

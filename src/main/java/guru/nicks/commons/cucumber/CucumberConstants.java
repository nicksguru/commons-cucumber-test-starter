package guru.nicks.commons.cucumber;

import lombok.experimental.UtilityClass;
import org.junit.platform.suite.api.IncludeEngines;

/**
 * Cucumber settings, to avoid duplication in Maven modules.
 */
@UtilityClass
public class CucumberConstants {

    /**
     * For {@link IncludeEngines @IncludeEngines}.
     */
    public static final String ENGINE_NAME = "cucumber";

    /**
     * Step definitions must reside in this package (and its subpackages, preferably {@value #ENGINE_NAME}, but testing
     * protected methods may require the same package as that of the method being tested).
     */
    public static final String GLUE_PROPERTY = "guru.nicks";

    /**
     * For {@code @SelectClasspathResource}. Subdirectory under {@code src/test/resources} where feature files reside.
     */
    public static final String FEATURES_CLASSPATH = "src/test/resources/cucumber";

    /**
     * Skip all tests annotated with {@code @disabled} (works in Maven, but not in IDE).
     */
    public static final String FILTER_TAGS_PROPERTY = "not @disabled";

    public static final String PLUGIN_PROPERTY = "pretty,summary"
            + ",html:target/site/cucumber-reports/index.html"
            + ",json:target/site/cucumber-reports/cucumber.json";

}

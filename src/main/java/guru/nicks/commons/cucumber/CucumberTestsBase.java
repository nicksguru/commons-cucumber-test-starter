package guru.nicks.commons.cucumber;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

/**
 * Configures Cucumber tests to be run as a suite. Can be used as a base class in all other modules.
 */
@Suite
@IncludeEngines(CucumberConstants.ENGINE_NAME)
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = CucumberConstants.GLUE_PROPERTY)
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = CucumberConstants.FEATURES_CLASSPATH)
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = CucumberConstants.PLUGIN_PROPERTY)
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = CucumberConstants.FILTER_TAGS_PROPERTY)
public class CucumberTestsBase {
}

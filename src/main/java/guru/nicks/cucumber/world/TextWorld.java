package guru.nicks.cucumber.world;

import io.cucumber.spring.ScenarioScope;
import jakarta.annotation.Nullable;
import jakarta.servlet.ServletException;
import jakarta.validation.ValidationException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * Domain-specific (not feature-specific) state shared between scenario steps. Thanks to
 * {@link ScenarioScope @ScenarioScope}, each scenario gets a fresh copy.
 */
@Component
@ScenarioScope
@Data
public class TextWorld {

    /**
     * General-purpose text.
     */
    private String text;

    /**
     * General-purpose method input and a list of output values.
     */
    private String input;
    private List<String> output;

    private Instant date;
    private Instant parsedDate;
    private String alphabet;

    private boolean success;
    private Throwable lastException;

    /**
     * If the method that caused the exception was called via reflection during tests, it's wrapped in
     * {@link InvocationTargetException} (sometimes more than once!). This method unwraps it (using
     * {@link InvocationTargetException#getTargetException()}) because tests check for specific exceptions and their
     * <p>
     * Also, if the exception is {@link ServletException}, it's unwrapped into its cause because MockMvc does the
     * wrapping, thus hiding the original exception (such as {@link ValidationException} from a controller upon failed
     * DTO validation).
     *
     */
    public void setLastException(Throwable t) {
        if ((t instanceof ServletException se) && (se.getCause() != null)) {
            t = se.getCause();
        }

        while ((t instanceof InvocationTargetException ite) && (ite.getTargetException() != null)) {
            t = ite.getTargetException();
        }

        lastException = t;
    }

    public void setOutput(List<String> output) {
        // don't store null list
        this.output = (output == null)
                ? Collections.emptyList()
                : output;
    }

    /**
     * Doesn't store null/blank strings - in such cases, {@link #getOutput()} becomes an empty list.
     *
     * @param output string
     */
    public void setOutput(@Nullable String output) {
        this.output = StringUtils.isBlank(output)
                ? Collections.emptyList()
                : List.of(output);
    }

}

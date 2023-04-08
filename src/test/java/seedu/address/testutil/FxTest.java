package seedu.address.testutil;

import org.junit.jupiter.api.BeforeAll;

import com.sun.javafx.application.PlatformImpl;

/**
 * Starts up the JavaFX platform to prevent "Toolkit not initialised" errors.
 * Taken from https://stackoverflow.com/questions/45109876
 * /toolkit-not-initialized-exception-when-unit-testing-an-javafx-application.
 */
public abstract class FxTest {
    @BeforeAll
    static void initJfxRuntime() {
        PlatformImpl.startup(() -> {
        });
    }
}

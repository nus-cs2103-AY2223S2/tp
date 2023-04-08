package seedu.address.testutil;

import org.junit.jupiter.api.BeforeAll;

import javafx.application.Platform;

/**
 * Starts up the JavaFX platform to prevent "Toolkit not initialised" errors.
 * Taken from https://stackoverflow.com/questions/45109876
 * /toolkit-not-initialized-exception-when-unit-testing-an-javafx-application.
 */
public abstract class FxTest {
    @BeforeAll
    static void initJfxRuntime() {
        System.setProperty("jdk.gtk.version", "2");
        Platform.startup(() -> {
        });
    }
}

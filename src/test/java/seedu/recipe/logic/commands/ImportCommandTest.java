package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;

/**
 * Validates the Constructor and initial behavior of this Command.
 * As the Command's execute method requires interaction with a File dialog that
 * is not supported by TestFX, it cannot be tested with these unit tests.
 */
public class ImportCommandTest extends ApplicationTest {
    private Stage stage;

    /**
     * Simple implementation of {@code ApplicationTest::start}, to ensure that this test does not start
     * without an invalid Stage.
     * @param stage The Stage instance to use in our test mocks
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.close();
    }

    @Test
    public void testConstructor_nullStage_error() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void testConstructor_validStage_noError() {
        assertDoesNotThrow(() -> new ImportCommand(stage));
    }

    @Test
    public void isDuplicate_initial_isFalse() {
        assertFalse(new ImportCommand(stage).isDuplicate());
    }
}

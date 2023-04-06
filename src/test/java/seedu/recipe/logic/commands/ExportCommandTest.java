package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.stage.Stage;
import seedu.recipe.commons.core.Config;
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.LogicManager;
import seedu.recipe.model.ModelManager;
import seedu.recipe.storage.JsonRecipeBookStorage;
import seedu.recipe.storage.JsonUserPrefsStorage;
import seedu.recipe.storage.Storage;
import seedu.recipe.storage.StorageManager;

/**
 * Validates the Constructor and initial behavior of this Command.
 * As the Command's execute method requires interaction with a File dialog that
 * is not supported by TestFX, it cannot be tested with these unit tests.
 */
public class ExportCommandTest extends ApplicationTest {
    private static final Logic logic;
    private Stage stage;
    static {
        Config config = new Config();

        //Initialise Model
        Storage storage = new StorageManager(new JsonRecipeBookStorage(Paths.get("stubPath")),
                new JsonUserPrefsStorage(config.getUserPrefsFilePath()));
        logic = new LogicManager(new ModelManager(), storage);
    }

    /**
     * Simple implementation of {@code ApplicationTest::start}, to ensure that this test does not start
     * without an invalid Stage.
     * @param stage The Stage instance to use in our test mocks
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
    }

    @Test
    public void testConstructor_nullStage_error() {
        assertThrows(NullPointerException.class, () -> new ExportCommand(null, logic));
    }

    @Test
    public void testConstructor_nullLogic_error() {
        assertThrows(NullPointerException.class, () -> new ExportCommand(stage, null));
    }

    @Test
    public void testConstructor_validParams_noError() {
        assertDoesNotThrow(() -> new ExportCommand(stage, logic));
    }
}

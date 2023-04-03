package seedu.recipe.ui;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

//JavaFX imports
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
//Custom imports
//Commons Package
import seedu.recipe.commons.core.Config;
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.commons.exceptions.DataConversionException;
//Logic Package
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.LogicManager;
//Model Package
import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.util.SampleDataUtil;
//Storage Package
import seedu.recipe.storage.JsonRecipeBookStorage;
import seedu.recipe.storage.JsonUserPrefsStorage;
import seedu.recipe.storage.RecipeBookStorage;
import seedu.recipe.storage.Storage;
import seedu.recipe.storage.StorageManager;
import seedu.recipe.storage.UserPrefsStorage;

/**
 * Acts as the entry point to all UI tests
 * This unit tests the GUI for GUI interactions.
 */
public class UiTest extends ApplicationTest {
    private static final Logic logic;

    static {
        Config config = new Config();

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = new UserPrefs();
        RecipeBookStorage recipeBookStorage = new JsonRecipeBookStorage(userPrefs.getRecipeBookFilePath());
        Storage storage = new StorageManager(recipeBookStorage, userPrefsStorage);
        LogsCenter.init(config);

        Model model;
        try {
            model = new ModelManager(storage
                .readRecipeBook()
                .orElseGet(SampleDataUtil::getSampleRecipeBook), userPrefs);
        } catch (DataConversionException | IOException e) {
            model = new ModelManager(new RecipeBook(), userPrefs);
        }

        logic = new LogicManager(model, storage);
    }

    @Override
    public void start(Stage stage) {
        MainWindow window = new MainWindow(stage, logic);
        window.show();
    }

    @Test
    public void dummyTest() {
        press(KeyCode.F3);
    }
}

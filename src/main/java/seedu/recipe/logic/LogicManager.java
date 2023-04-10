package seedu.recipe.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.recipe.commons.core.GuiSettings;
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.logic.commands.Command;
import seedu.recipe.logic.commands.CommandResult;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.parser.RecipeBookParser;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.Model;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final RecipeBookParser recipeBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        recipeBookParser = new RecipeBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = recipeBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveRecipeBook(model.getRecipeBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public CommandResult execute(Command command) throws CommandException {
        logger.info("----------------[USER COMMAND][" + command.getClass().getSimpleName() + "]");
        CommandResult commandResult;
        commandResult = command.execute(model);
        try {
            storage.saveRecipeBook(model.getRecipeBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return commandResult;
    }

    @Override
    public ReadOnlyRecipeBook getRecipeBook() {
        return model.getRecipeBook();
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return model.getFilteredRecipeList();
    }

    @Override
    public Path getRecipeBookFilePath() {
        return model.getRecipeBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}

package seedu.wife.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.wife.commons.core.GuiSettings;
import seedu.wife.commons.core.LogsCenter;
import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.logic.parser.WifeParser;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.Model;
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.food.Food;
import seedu.wife.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final WifeParser wifeParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        wifeParser = new WifeParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = wifeParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveWife(model.getWife());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyWife getWife() {
        return model.getWife();
    }

    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return model.getFilteredFoodList();
    }

    @Override
    public Path getWifeFilePath() {
        return model.getWifeFilePath();
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

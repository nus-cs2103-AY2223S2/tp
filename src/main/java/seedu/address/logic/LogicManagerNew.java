package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandNew;
import seedu.address.logic.commands.CommandResultNew;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UltronParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelNew;
import seedu.address.model.ReadOnlyUltron;
import seedu.address.model.opening.Opening;
import seedu.address.storage.StorageNew;

/**
 * The main LogicManager of the app.
 */
public class LogicManagerNew implements LogicNew {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final ModelNew model;
    private final StorageNew storage;
    private final UltronParser ultronParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManagerNew(ModelNew model, StorageNew storage) {
        this.model = model;
        this.storage = storage;
        ultronParser = new UltronParser();
    }

    @Override
    public CommandResultNew execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResultNew commandResult;
        CommandNew command = ultronParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveUltron(model.getUltron());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyUltron getUltron() {
        return model.getUltron();
    }

    @Override
    public ObservableList<Opening> getFilteredOpeningList() {
        return model.getFilteredOpeningList();
    }

    @Override
    public Path getUltronFilePath() {
        return model.getUltronFilePath();
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

package seedu.ultron.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.ultron.commons.core.GuiSettings;
import seedu.ultron.commons.core.LogsCenter;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.Command;
import seedu.ultron.logic.commands.CommandResult;
import seedu.ultron.logic.commands.exceptions.CommandException;
import seedu.ultron.logic.parser.UltronParser;
import seedu.ultron.logic.parser.exceptions.ParseException;
import seedu.ultron.model.Model;
import seedu.ultron.model.ReadOnlyUltron;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final UltronParser ultronParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        ultronParser = new UltronParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = ultronParser.parseCommand(commandText);
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
    public Opening getSelectedOpening() {
        return model.getSelectedOpening();
    }

    @Override
    public void setSelectedOpening(Index index) {
        model.setSelectedIndex(index);
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

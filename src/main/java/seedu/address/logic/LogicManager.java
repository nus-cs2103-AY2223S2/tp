package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ElisterParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Filter;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyElister;
import seedu.address.model.StateHistory;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StateHistory stateHistory;
    private final ElisterParser elisterParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        stateHistory = new StateHistory(model);
        elisterParser = new ElisterParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = elisterParser.parseCommand(commandText);
        command.setStateHistory(stateHistory);
        commandResult = command.execute(model);
        stateHistory.offerCommand(command, model, commandResult);

        try {
            storage.saveElister(model.getElister());

            model.getInputHistory().offerCommand(commandText, commandResult);
            storage.saveInputHistory(model.getInputHistory());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyElister getElister() {
        return model.getElister();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Filter> getApplyingFilterList() {
        return model.getApplyingFilterList();
    }

    @Override
    public Path getElisterFilePath() {
        return model.getElisterFilePath();
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

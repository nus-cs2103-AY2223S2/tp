package seedu.connectus.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.connectus.commons.core.GuiSettings;
import seedu.connectus.commons.core.LogsCenter;
import seedu.connectus.logic.commands.Command;
import seedu.connectus.logic.commands.CommandResult;
import seedu.connectus.logic.commands.exceptions.CommandException;
import seedu.connectus.logic.parser.ConnectUsParser;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.Model;
import seedu.connectus.model.ReadOnlyConnectUs;
import seedu.connectus.model.person.Person;
import seedu.connectus.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ConnectUsParser connectUsParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        connectUsParser = new ConnectUsParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = connectUsParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveConnectUs(model.getConnectUs());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyConnectUs getConnectUs() {
        return model.getConnectUs();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }


    @Override
    public Path getConnectUsFilePath() {
        return model.getConnectUsFilePath();
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

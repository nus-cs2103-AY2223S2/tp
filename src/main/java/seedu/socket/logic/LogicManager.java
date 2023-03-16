package seedu.socket.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.socket.commons.core.GuiSettings;
import seedu.socket.commons.core.LogsCenter;
import seedu.socket.logic.commands.Command;
import seedu.socket.logic.commands.CommandResult;
import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.logic.parser.SocketParser;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.Model;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.person.Person;
import seedu.socket.storage.Storage;

/**
 * The main {@code LogicManager} of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final SocketParser socketParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        socketParser = new SocketParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = socketParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveSocket(model.getSocket());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlySocket getSocket() {
        return model.getSocket();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getViewedPerson() {
        return model.getViewedPerson();
    }

    @Override
    public void setViewedPerson(int index) {
        model.updateViewedPerson(getFilteredPersonList().get(index));
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getSocketFilePath();
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

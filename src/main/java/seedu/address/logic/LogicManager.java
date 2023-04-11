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
import seedu.address.logic.parser.PowerConnectParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.model.person.student.Student;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PowerConnectParser powerConnectParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        powerConnectParser = new PowerConnectParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = powerConnectParser.parseCommand(commandText);
        commandResult = command.execute(model);
        try {
            storage.savePC(model.getPcClass());
            storage.saveParents(model.getParents());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    /**
     * Returns the parents.
     *
     * @see Model#getParents()
     */
    @Override
    public ReadOnlyParents getParents() {
        return model.getParents();
    }

    /**
     * Returns the PCClass.
     *
     * @see Model#getPcClass()
     */
    @Override
    public ReadOnlyPcClass getPcClass() {
        return model.getPcClass();
    }

    /**
     * Returns the user prefs' PCClass file path.
     */
    @Override
    public Path getPcClassFilePath() {
        return model.getPcClassFilePath();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Parent> getFilteredParentList() {
        return model.getFilteredParentList();
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

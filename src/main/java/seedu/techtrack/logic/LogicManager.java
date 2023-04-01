package seedu.techtrack.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.techtrack.commons.core.GuiSettings;
import seedu.techtrack.commons.core.LogsCenter;
import seedu.techtrack.logic.commands.Command;
import seedu.techtrack.logic.commands.CommandResult;
import seedu.techtrack.logic.commands.exceptions.CommandException;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;
import seedu.techtrack.logic.parser.RoleBookParser;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final RoleBookParser roleBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        roleBookParser = new RoleBookParser();
    }

    @Override
    public CommandResult<?> execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult<?> commandResult;
        Command command = roleBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveRoleBook(model.getRoleBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyRoleBook getRoleBook() {
        return model.getRoleBook();
    }

    @Override
    public ObservableList<Role> getFilteredRoleList() {
        return model.getFilteredRoleList();
    }

    @Override
    public Path getRoleBookFilePath() {
        return model.getRoleBookFilePath();
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

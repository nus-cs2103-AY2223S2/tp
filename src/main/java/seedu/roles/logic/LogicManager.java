package seedu.roles.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.roles.commons.core.GuiSettings;
import seedu.roles.commons.core.LogsCenter;
import seedu.roles.logic.commands.Command;
import seedu.roles.logic.commands.CommandResult;
import seedu.roles.logic.commands.exceptions.CommandException;
import seedu.roles.logic.commands.exceptions.exceptions.ParseException;
import seedu.roles.logic.parser.RoleBookParser;
import seedu.roles.model.Model;
import seedu.roles.model.ReadOnlyRoleBook;
import seedu.roles.model.job.Role;
import seedu.roles.storage.Storage;

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

package seedu.sprint.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.sprint.commons.core.GuiSettings;
import seedu.sprint.commons.core.LogsCenter;
import seedu.sprint.logic.commands.Command;
import seedu.sprint.logic.commands.CommandResult;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.logic.parser.InternshipBookParser;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.Model;
import seedu.sprint.model.ReadOnlyInternshipBook;
import seedu.sprint.model.application.Application;
import seedu.sprint.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InternshipBookParser internshipBookParser;
    private final CommandHistory commandHistory;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.internshipBookParser = new InternshipBookParser();
        this.commandHistory = new CommandHistory();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        commandHistory.addCommand(commandText);
        Command command = internshipBookParser.parseCommand(commandText);
        commandResult = command.execute(model, commandHistory);

        try {
            storage.saveInternshipBook(model.getInternshipBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return model.getInternshipBook();
    }

    @Override
    public ObservableList<Application> getFilteredApplicationList() {
        return model.getFilteredApplicationList();
    }

    @Override
    public ObservableList<Application> getSortedApplicationList() {
        return model.getSortedApplicationList();
    }

    @Override
    public Path getInternshipBookFilePath() {
        return model.getInternshipBookFilePath();
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

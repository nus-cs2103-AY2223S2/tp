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
import seedu.address.logic.parser.InternEaseParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.statstics.StatsManager;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StatsManager statsManager;
    private final InternEaseParser internEaseParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage, StatsManager statsManager) {
        this.model = model;
        this.storage = storage;
        this.statsManager = statsManager;
        internEaseParser = new InternEaseParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = internEaseParser.parseCommand(commandText);
        commandResult = command.execute(model);
        statsManager.updateAllStatsInformation();

        try {
            if (commandResult.getType() == TaskType.NONE) {
                storage.saveAddressBook(model.getAddressBook());
            } else if (commandResult.getType() == TaskType.TODO) {
                storage.saveTodoList(model.getTodoList());
            } else if (commandResult.getType() == TaskType.NOTE) {
                storage.saveNoteList(model.getNoteList());
            }

        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public StatsManager getStatsManager() {
        return statsManager;
    }

    @Override
    public ObservableList<InternshipApplication> getFilteredInternshipList() {
        return model.getSortedFilteredInternshipList();
    }

    @Override
    public ObservableList<InternshipApplication> getSortedFilteredInternshipList() {
        return model.getSortedFilteredInternshipList();
    }

    @Override
    public ObservableList<InternshipTodo> getFilteredTodoList() {
        return model.getFilteredTodoList();
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return model.getFilteredNoteList();
    }

    @Override
    public InternshipApplication getReminderApplication() {
        model.updateReminder();
        return model.getReminder();
    }
    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public Path getTodoListFilePath() {
        return model.getTodoListFilePath();
    }

    @Override
    public Path getNoteListFilePath() {
        return model.getNoteListFilePath();
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

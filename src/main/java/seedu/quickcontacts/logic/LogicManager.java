package seedu.quickcontacts.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.quickcontacts.commons.core.GuiSettings;
import seedu.quickcontacts.commons.core.LogsCenter;
import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.Command;
import seedu.quickcontacts.logic.commands.CommandResult;
import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.logic.parser.QuickContactsParser;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final QuickContactsParser quickContactsParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        quickContactsParser = new QuickContactsParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = quickContactsParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveQuickBook(model.getQuickBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public AutocompleteResult autocomplete(String commandText) {
        return quickContactsParser.getAutocompleteSuggestion(commandText);
    }

    @Override
    public ReadOnlyQuickBook getQuickBook() {
        return model.getQuickBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return model.getFilteredMeetingList();
    }

    @Override
    public Path getQuickBookFilePath() {
        return model.getQuickBookFilePath();
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

package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ApplicationCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.InternshipBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.application.Application;
//import seedu.address.storage.ApplicationStorage;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class ApplicationLogicManager implements ApplicationLogic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(ApplicationLogicManager.class);

    private final ApplicationModel model;
    //private final ApplicationStorage storage;
    private final Storage storage;
    private final InternshipBookParser internshipBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public ApplicationLogicManager(ApplicationModel model, Storage storage) {
        this.model = model;
        this.storage = storage;
        internshipBookParser = new InternshipBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        ApplicationCommand command = internshipBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getInternshipBook());
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

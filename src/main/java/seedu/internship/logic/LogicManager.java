package seedu.internship.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.logic.commands.Command;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.logic.parser.InternBuddyParser;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.Model;
import seedu.internship.model.ReadOnlyInternBuddy;
import seedu.internship.model.internship.Internship;
import seedu.internship.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InternBuddyParser internBuddyParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        internBuddyParser = new InternBuddyParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = internBuddyParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternBuddy(model.getInternBuddy());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternBuddy getInternBuddy() {
        return model.getInternBuddy();
    }

    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return model.getFilteredInternshipList();
    }

    @Override
    public Path getInternBuddyFilePath() {
        return model.getInternBuddyFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public Internship getSelectedInternship() {
        return model.getSelectedInternship();

    }
    @Override
    public Model getModel() {
        return this.model;
    };
}

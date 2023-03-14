package seedu.fitbook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.logic.commands.Command;
import seedu.fitbook.logic.commands.CommandResult;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.logic.parser.FitBookParser;
import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final FitBookModel model;
    private final Storage storage;
    private final FitBookParser fitBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code FitBookModel} and {@code Storage}.
     */
    public LogicManager(FitBookModel model, Storage storage) {
        this.model = model;
        this.storage = storage;
        fitBookParser = new FitBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = fitBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFitBook(model.getFitBook());
            storage.saveFitBookExerciseRoutine(model.getFitBookExerciseRoutine());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFitBook getFitBook() {
        return model.getFitBook();
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return model.getFilteredClientList();
    }

    @Override
    public Path getFitBookFilePath() {
        return model.getFitBookFilePath();
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
    public ObservableList<Routine> getFilteredRoutineList() {
        return model.getFilteredRoutineList();
    }
}

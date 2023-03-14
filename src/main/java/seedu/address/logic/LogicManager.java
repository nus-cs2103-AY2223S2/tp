package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.EduMateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String FILE_GET_ERROR_MESSAGE = "File not found. Aborting action.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final EduMateParser eduMateParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        eduMateParser = new EduMateParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = eduMateParser.parseCommand(commandText);
        commandResult = command.execute(model);
        Path filePath = commandResult.getFilePath().orElseGet(model::getEduMateFilePath);

        try {
            if (commandResult.isSave()) {
                storage.saveEduMate(model.getEduMate(), filePath);
            } else if (commandResult.isLoad()) {
                // we throw if the file does not exist
                ReadOnlyEduMate readOnlyEduMate =
                        storage.readEduMate(filePath)
                                .orElseThrow(() -> new CommandException(FILE_GET_ERROR_MESSAGE));
                model.setEduMate(readOnlyEduMate);
            }
            storage.saveEduMate(model.getEduMate());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        } catch (DataConversionException dce) {
            String message = String.format(
                    "Data file at %s not in the correct format. Will be starting with an empty EduMate",
                    filePath.toString());
            logger.warning(message);
            throw new CommandException(message, dce);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEduMate getEduMate() {
        return model.getEduMate();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getEduMateFilePath() {
        return model.getEduMateFilePath();
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
    public User getUser() {
        return model.getUser();
    }
}

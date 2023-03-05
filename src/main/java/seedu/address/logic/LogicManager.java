package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.WingmanParser;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.OperationMode;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.item.Identifiable;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 * <p>
 * TODO: remove the magic literals.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Model model;
    private final Storage storage;
    private final WingmanParser parser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     *
     * @param model   the model to use.
     * @param storage the storage to use.
     * @param parser  the parser to use.
     */
    public LogicManager(Model model, Storage storage, WingmanParser parser) {
        this.model = model;
        this.storage = storage;
        this.parser = parser;
    }

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}, using the default {@code WingmanParser}.
     *
     * @param model   the model to use.
     * @param storage the storage to use.
     */
    public LogicManager(Model model, Storage storage) {
        this(model, storage, new WingmanParser());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        final Command command = parser.parse(getOperationMode(), commandText);
        final CommandResult result = command.execute(model);
        this.save();
        return result;
    }

    /**
     * Saves the current state of the application to the storage.
     *
     * @throws CommandException if there was an error during saving.
     */
    private void save() throws CommandException {
        try {
            switch (getOperationMode()) {
            case PILOT:
                storage.savePilotManager(model.getPilotManager());
                break;
            case LOCATION:
                storage.saveLocationManager(model.getLocationManager());
                break;
            case PLANE:
            case FLIGHT:
            case CREW:
                throw new CommandException(
                    "Operation mode not supported yet: " + getOperationMode()
                        + "Check LogicManager.java's save() method."
                );
            default:
                throw new CommandException("Unknown operation mode");
            }
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + e, e);
        }
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
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
    public ObservableList<Identifiable> getFilteredItemList() {
        logger.info("Getting filtered item list: " + model.getItemsList().size() + " "
                        + "items");
        return model.getItemsList();
    }

    @Override
    public OperationMode getOperationMode() {
        return model.getOperationMode();
    }
}

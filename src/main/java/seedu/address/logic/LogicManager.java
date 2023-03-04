package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandGroup;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.OperationMode;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.logic.factories.AddPilotCommandFactory;
import seedu.address.logic.factories.DeletePilotCommandFactory;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 * <p>
 * TODO: remove the magic literals.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    /**
     * The command groups that are available in the application.
     */
    private static final CommandGroup[] COMMAND_GROUPS = new CommandGroup[]{
        new CommandGroup(OperationMode.PILOT, new CommandFactory[]{
            new AddPilotCommandFactory(),
            new DeletePilotCommandFactory(),
            }),
        new CommandGroup(OperationMode.CREW, new CommandFactory[]{}),
        new CommandGroup(OperationMode.PLANE, new CommandFactory[]{}),
        new CommandGroup(OperationMode.FLIGHT, new CommandFactory[]{}),
        new CommandGroup(OperationMode.LOCATION, new CommandFactory[]{}),
        };

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Model model;
    private final Storage storage;


    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        final String[] rawTokens = commandText.trim().split(" ");
        final Deque<String> tokens = new ArrayDeque<>(Arrays.asList(rawTokens));
        final OperationMode operationMode =
            this.model.getUserPrefs().getOperationMode();
        CommandResult result = null;
        for (CommandGroup commandGroup : COMMAND_GROUPS) {
            if (commandGroup.getOperationMode() != operationMode) {
                continue;
            }
            final Command command = commandGroup.parse(tokens);
            result = command.execute(this.model);
            break;
        }
        if (result == null) {
            throw new CommandException("Did not receive a result from the "
                                           + "command. This may be due to"
                                           + " the fact that the command "
                                           + "has not been found.");
        }
        save();
        return result;
    }

    /**
     * Saves the current state of the application to the storage.
     *
     * @throws CommandException if there was an error during saving.
     */
    private void save() throws CommandException {
        try {
            switch (this.model.getUserPrefs().getOperationMode()) {
            case PILOT:
                storage.savePilotManager(model.getPilotManager());
                break;
            case PLANE:
                throw new CommandException("Plane mode not implemented yet");
            case FLIGHT:
                throw new CommandException("Flight mode not implemented yet");
            case CREW:
                throw new CommandException("Crew mode not implemented yet");
            case LOCATION:
                throw new CommandException("Location mode not implemented "
                                               + "yet");
            default:
                throw new CommandException("Unknown operation mode");
            }
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
}

package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.experimental.model.Model;
import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.storage.Storage;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.EditModeParser;
import seedu.address.logic.parser.UiSwitchMode;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Entity;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final EditModeParser editModeParser;
    private boolean isInEditMode;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        editModeParser = new EditModeParser(model);
        isInEditMode = false;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;

        Command command;
        if (!isInEditMode) {
            command = addressBookParser.parseCommand(commandText);
        } else {
            command = editModeParser.parseCommand(commandText);
        }
        commandResult = command.execute(model);
        SetMode(commandResult.getSwitchMode());
        System.out.println(model.getFilteredEntityList().size());
        try {
            storage.saveReroll(model.getReroll());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyReroll getReroll() {
        return model.getReroll();
    }

    @Override
    public ObservableList<Entity> getFilteredEntityList() {
        return model.getFilteredEntityList();
    }

    @Override
    public Path getRerollFilePath() {
        return model.getRerollFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    private void SetMode(UiSwitchMode switchMode) {
        switch (switchMode) {
        case LIST:
            isInEditMode = false;
            break;
        case VIEW:
            isInEditMode = true;
            break;
        case TOGGLE:
            isInEditMode = !isInEditMode;
            break;
        }

    }
}

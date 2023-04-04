package tfifteenfour.clipboard.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import tfifteenfour.clipboard.commons.core.GuiSettings;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.UndoCommand;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.parser.RosterParser;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private static final int stateHistoryBufferSize = 5;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private Model model;
    private final CircularBuffer<Model> stateHistoryBuffer = new CircularBuffer<>(stateHistoryBufferSize);
    private final Storage storage;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
    }

    CommandResult handleUndoCommand(Command command) throws CommandException, ParseException {
        UndoCommand undoCmd = (UndoCommand) command;

        undoCmd.setStateHistoryBuffer(this.stateHistoryBuffer);
        CommandResult commandResult = undoCmd.execute(model);
        model = undoCmd.getPrevModel();

        return commandResult;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = RosterParser.parseCommand(commandText, model);

        // Special case for UndoCommand because restoring the model to a previous state requires actions that are above
        // the model, as opposed to typical commands that behave within the model.
        if (command instanceof UndoCommand) {
            commandResult = handleUndoCommand(command);
        } else {
            Model modelCopy = model.copy();
            commandResult = command.execute(model);
            if (commandResult.isStateModified()) {
                modelCopy.setCommandTextExecuted(commandText);
                modelCopy.setCommandExecuted(command);
                stateHistoryBuffer.add(modelCopy);
            }
        }

        try {
            storage.saveRoster(model.getRoster());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyRoster getRoster() {
        return model.getRoster();
    }

    @Override
    public Path getRosterFilePath() {
        return model.getRosterFilePath();
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
    public Model getModel() {
        return this.model;
    }
}



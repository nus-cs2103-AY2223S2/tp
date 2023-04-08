package tfifteenfour.clipboard.logic.commands;

import tfifteenfour.clipboard.logic.CircularBuffer;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;


/**
 * Represents an Undo command to undo the previous modification to the Model.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    private CircularBuffer<Model> stateHistoryBuffer;
    private Model prevModel;

    public UndoCommand() {
        super(false);
    }
    /**
     * Executes the Undo command.
     *
     * @param model The model to execute the command on.
     * @return The result of the command.
     * @throws CommandException If an error occurs while executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (stateHistoryBuffer.size() > 0) {
            this.prevModel = stateHistoryBuffer.removeLast();
            Command prevCommandExecuted = prevModel.getCommandExecuted();
            if (prevCommandExecuted instanceof UploadCommand) {
                UploadCommand uploadCmd = (UploadCommand) prevCommandExecuted;
                uploadCmd.deleteUploadedFile();
            }
        } else {
            throw new CommandException("Cannot undo any further");
        }
        return new CommandResult(this, String.format("Undid previous command: %s",
                prevModel.getCommandTextExecuted()), false);
    }

    /**
     * Sets the state history buffer for the command.
     *
     * @param stateHistoryBuffer The state history buffer to set.
     */
    public void setStateHistoryBuffer(CircularBuffer<Model> stateHistoryBuffer) {
        this.stateHistoryBuffer = stateHistoryBuffer;
    }

    /**
     * Returns the new model after executing the Undo command.
     *
     * @return The new model.
     */
    public Model getPrevModel() {
        return this.prevModel;
    }
}

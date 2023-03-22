package tfifteenfour.clipboard.logic.commands;

import tfifteenfour.clipboard.logic.CircularBuffer;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

	private CircularBuffer<Model> stateHistoryBuffer;
	private Model newModel;

    @Override
    public CommandResult execute(Model model) throws CommandException {

		if (stateHistoryBuffer.size() > 0) {
			this.newModel = stateHistoryBuffer.removeLast();
			newModel.getFilteredStudentList().forEach(System.out::println);
		} else {
			throw new CommandException("Cannot undo any further");
		}

		return new CommandResult(this, String.format("Undid previous command: %s", model.getPrevStateModifyingCommand()), false);
    }

	public void setStateHistoryBuffer(CircularBuffer<Model> stateHistorBuffer) {
		this.stateHistoryBuffer = stateHistorBuffer;
	}

	public Model getNewModel() {
		return this.newModel;
	}
}

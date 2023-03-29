package seedu.dengue.logic.commands;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;

abstract class UndoRedoCommand extends Command {
    abstract public CommandResult execute(Model model) throws CommandException;
    public int undoOrRedoAtMost(Model model, int numOfSteps, boolean isUndo) {
        int counts;
        if (isUndo) {
            for (counts = 0; counts < numOfSteps; counts++) {
                try {
                    model.undo();
                } catch (CommandException err) {
                    break;
                }
            }
            return counts;
        } else {
            for (counts = 0; counts < numOfSteps; counts++) {
                try {
                    model.redo();
                } catch (CommandException err) {
                    break;
                }
            }
            return counts;
        }
    }
}

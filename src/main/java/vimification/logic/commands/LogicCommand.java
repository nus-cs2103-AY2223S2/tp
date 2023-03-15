package vimification.logic.commands;

import vimification.logic.commands.exceptions.CommandException;
import vimification.model.Model;
import vimification.model.TaskList;
import vimification.model.task.Task;

public abstract class LogicCommand extends Command {
    private TaskList taskList;
    public LogicCommand(TaskList list) { //dependency injection
        this.taskList = list;
    }

    public abstract CommandResult execute(Model model) throws CommandException;
    public abstract CommandResult undo();

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int numOfTask() {
        return taskList.size();
    }

    // Don't think this method will ever be used
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LogicCommand // instanceof handles nulls
                && this.taskList.equals(((LogicCommand) other).taskList));
    }
}

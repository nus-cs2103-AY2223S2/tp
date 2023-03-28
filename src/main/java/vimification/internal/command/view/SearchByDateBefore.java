package vimification.internal.command.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Deadline;
import vimification.model.task.Task;

import java.time.LocalDateTime;

public class SearchByDateBefore extends SearchCommand {
    public static final String COMMAND_WORD = "s -d bef";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": search for tasks that have deadline before (inclusive) the input date.\n"
            + "Parameters: DATETIME \n"
            + "Conditions: Date time must be valid.\n"
            + "Example: " + COMMAND_WORD + " 2023-01-01";

    public SearchByDateBefore(LocalDateTime date) {
        super(task -> task.isDeadline() && ((Deadline)task).isDateBefore(date));
    }

    @Override
    public CommandResult execute(LogicTaskList taskList) throws CommandException {
        ObservableList<Task> viewTaskList =
                FXCollections.observableList(taskList.filter(getPredicate()));
        setViewTaskList(viewTaskList);
        return new CommandResult(SUCCESS_MESSAGE_FORMAT);
    }

}


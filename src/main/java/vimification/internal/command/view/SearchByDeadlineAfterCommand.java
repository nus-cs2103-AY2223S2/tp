package vimification.internal.command.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.LogicTaskList;
import vimification.model.task.Task;

import java.time.LocalDateTime;

public class SearchByDeadlineAfterCommand extends SearchCommand {
    public static final String COMMAND_WORD = "s --after";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": search for tasks that have deadline after (inclusive) the input date.\n"
            + "Parameters: DATETIME \n"
            + "Conditions: Date time must be valid in the format of YYYY-MM-DD.\n"
            + "Example: " + COMMAND_WORD + " 2023-01-01";

    public SearchByDeadlineAfterCommand(LocalDateTime date) {
        super(task -> task.isDateAfter(date));
    }

    // @Override
    // public CommandResult execute(FilteredList<Task> taskList) {
    // ObservableList<Task> viewTaskList =
    // FXCollections.observableList(taskList.filter(getPredicate()));
    // setViewTaskList(viewTaskList);
    // return new CommandResult(SUCCESS_MESSAGE_FORMAT);
    // }

}


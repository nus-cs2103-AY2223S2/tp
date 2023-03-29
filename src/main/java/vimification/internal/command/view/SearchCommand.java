package vimification.internal.command.view;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import vimification.internal.command.CommandResult;
import vimification.model.task.Task;

public abstract class SearchCommand extends ViewCommand {

    public static final String SUCCESS_MESSAGE = "Here are your search results:";

    private final Predicate<Task> pred;

    SearchCommand(Predicate<Task> pred) {
        this.pred = pred;
    }

    Predicate<Task> getPredicate() {
        return pred;
    }

    @Override
    public CommandResult execute(FilteredList<Task> taskList) {
        taskList.setPredicate(pred);
        return new CommandResult(SUCCESS_MESSAGE);
    }
}

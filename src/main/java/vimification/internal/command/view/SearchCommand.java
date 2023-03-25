package vimification.internal.command.view;

import java.util.function.Predicate;

import vimification.model.task.Task;

public abstract class SearchCommand extends ViewCommand {
    public static final String COMMAND_WORD = "search";
    public static final String SUCCESS_MESSAGE_FORMAT = "These are your search results:";

    private final Predicate<Task> pred;

    SearchCommand(Predicate<Task> pred) {
        this.pred = pred;
    }

    protected Predicate<Task> getPredicate() {
        return pred;
    }
}

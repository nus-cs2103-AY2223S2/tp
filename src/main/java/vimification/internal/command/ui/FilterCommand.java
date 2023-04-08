package vimification.internal.command.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.task.Task;
import vimification.ui.MainScreen;

/**
 * Filters the displayed list using some conditions.
 */
public class FilterCommand extends UiCommand {

    private static final String SUCCESS_MESSAGE = "Here are your search results:";

    private final FilterRequest request;

    /**
     * Creates a new {@code FilterCommand} instance.
     *
     * @param request a structure that contains the relevant information for this command
     */
    public FilterCommand(FilterRequest request) {
        this.request = request;
    }

    @Override
    public CommandResult execute(MainScreen mainScreen) {
        List<Predicate<Task>> predicates = new ArrayList<>();
        if (request.getSearchedKeyword() != null) {
            predicates.add(task -> task.containsKeyword(request.getSearchedKeyword()));
        }
        if (request.getSearchedPriority() != null) {
            predicates.add(task -> task.hasPriority(request.getSearchedPriority()));
        }
        if (request.getSearchedStatus() != null) {
            predicates.add(task -> task.hasStatus(request.getSearchedStatus()));
        }
        if (request.getSearchedDeadlineBefore() != null) {
            predicates.add(task -> task.deadlineIsBefore(request.getSearchedDeadlineBefore()));
        }
        if (request.getSearchedDeadlineAfter() != null) {
            predicates.add(task -> task.deadlineIsAfter(request.getSearchedDeadlineAfter()));
        }
        request.getSearchedLabels()
                .forEach(label -> predicates.add(task -> task.containsLabel(label)));

        Predicate<Task> predicate = null;
        switch (request.getMode()) {
        case DEFAULT:
            predicate = predicates.get(0);
            break;
        case AND:
            predicate = predicates.stream().reduce(Predicate::and).orElse(ignore -> true);
            break;
        case OR:
            predicate = predicates.stream().reduce(Predicate::or).orElse(ignore -> false);
            break;
        default:
            throw new CommandException("Should not reach here!");
        }
        mainScreen.getTaskListPanel().searchForTask(predicate);
        return new CommandResult(SUCCESS_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterCommand)) {
            return false;
        }
        FilterCommand otherCommand = (FilterCommand) other;
        return Objects.equals(request, otherCommand.request);
    }
}

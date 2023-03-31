package vimification.internal.command.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import vimification.internal.command.CommandResult;
import vimification.model.task.Task;
import vimification.ui.MainScreen;

public class FilterCommand extends UiCommand {

    private static final String SUCCESS_MESSAGE = "Here are your search results:";

    private final FilterRequest request;

    public FilterCommand(FilterRequest request) {
        this.request = request;
    }

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
            predicates.add(task -> task.isDateBefore(request.getSearchedDeadlineBefore()));
        }
        if (request.getSearchedDeadlineAfter() != null) {
            predicates.add(task -> task.isDateAfter(request.getSearchedDeadlineAfter()));
        }
        request.getSearchedLabels()
                .forEach(label -> predicates.add(task -> task.containsLabel(label)));
        Predicate<Task> predicate = predicates.stream()
                .reduce(request.getMode() == FilterRequest.Mode.OR ? Predicate::or : Predicate::and)
                .orElse(ignore -> true);
        mainScreen.getTaskTabPanel().searchForTask(predicate, 0); // TODO: refactor to pass enum
        return new CommandResult(SUCCESS_MESSAGE);
    }
}

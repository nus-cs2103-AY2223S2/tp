package vimification.internal.command.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import vimification.internal.command.CommandResult;
import vimification.internal.command.logic.SearchRequest;
import vimification.internal.command.view.SearchByDeadlineAfterCommand;
import vimification.internal.command.view.SearchByDeadlineBeforeCommand;
import vimification.ui.MainScreen;
import vimification.model.task.Task;

public class SearchUiCommand extends UiCommand {

    private static final String SUCCESS_MESSAGE = "Here are your search results:";

    private Predicate<? super Task> predicate;
    private SearchRequest request;
    private int targetTabIndex = 0; // Index to jump to after filtering.

    public SearchUiCommand(Predicate<? super Task> predicate, SearchRequest request) {
        this.predicate = predicate;
        this.request = request;
    }

    public CommandResult execute(MainScreen mainScreen) {
        List<Predicate<Task>> predicates = new ArrayList<>();
        Predicate<Task> predicate = predicates.stream()
                .reduce(Predicate::and)
                .orElse(ignore -> true);

        mainScreen.getTaskTabPanel().searchForTask(predicate, targetTabIndex);
        return new CommandResult(SUCCESS_MESSAGE);
    }
}

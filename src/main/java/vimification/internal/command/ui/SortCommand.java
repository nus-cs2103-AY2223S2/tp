package vimification.internal.command.ui;

import java.util.Comparator;

import vimification.internal.command.CommandResult;
import vimification.model.task.Task;
import vimification.ui.MainScreen;

public class SortCommand extends UiCommand {

    private static final String SUCCESS_MESSAGE = "Here are your search results:";

    private final SortRequest request;

    public SortCommand(SortRequest request) {
        this.request = request;
    }

    public CommandResult execute(MainScreen mainScreen) {
        Comparator<Task> comparator = null;
        switch (request.getMode()) {
        case DEADLINE:
            comparator = Comparator.comparing(Task::getDeadline);
            break;
        case PRIORITY:
            comparator = Comparator.comparing(Task::getPriority);
            break;
        case STATUS:
            comparator = Comparator.comparing(Task::getStatus);
            break;
        }
        mainScreen.getTaskTabPanel()
                .getOngoingTaskListPanel()
                .getTaskList()
                .setComparator(comparator);
        return new CommandResult(SUCCESS_MESSAGE);
    }
}

package vimification.internal.command.ui;

import java.util.Comparator;
import java.util.Objects;

import vimification.internal.command.CommandException;
import vimification.internal.command.CommandResult;
import vimification.model.task.Task;
import vimification.ui.MainScreen;

/**
 * Sorts the displayed list using values of a field of entities stored in the list.
 */
public class SortCommand extends UiCommand {

    private static final String SUCCESS_MESSAGE = "Here are your search results:";

    private final SortRequest request;

    /**
     * Creates a new {@code SortCommand} instance.
     *
     * @param request a structure that contains the relevant information for this command
     */
    public SortCommand(SortRequest request) {
        this.request = request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(MainScreen mainScreen) {
        Comparator<Task> comparator = null;
        switch (request.getMode()) {
        case DEADLINE:
            comparator = Comparator.nullsLast(Comparator.comparing(Task::getDeadline));
            break;
        case PRIORITY:
            comparator = Comparator.comparing(Task::getPriority);
            break;
        case STATUS:
            comparator = Comparator.comparing(Task::getStatus);
            break;
        default:
            throw new CommandException("Should not reach here!");
        }
        mainScreen.getTaskListPanel()
                .getUiTaskList()
                .setComparator(comparator);
        return new CommandResult(SUCCESS_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand otherCommand = (SortCommand) other;
        return Objects.equals(request, otherCommand.request);
    }
}

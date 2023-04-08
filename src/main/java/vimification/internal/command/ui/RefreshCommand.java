package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.model.UiTaskList;
import vimification.ui.MainScreen;

/**
 * Refreshes the displayed list by removing all predicates and comparators set on it.
 */
public class RefreshCommand extends UiCommand {

    public static final String SUCCESS_MESSAGE = "Refreshed your task list.";

    /**
     * Creates a new {@code RefreshCommand} instance.
     */
    public RefreshCommand() {}

    @Override
    public CommandResult execute(MainScreen mainScreen) {
        UiTaskList taskList = mainScreen.getTaskListPanel()
                .getUiTaskList();
        taskList.setPredicate(null);
        taskList.setComparator(null);
        mainScreen.getTaskListPanel().refreshTaskDetailPanel();
        return new CommandResult(SUCCESS_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof RefreshCommand;
    }
}

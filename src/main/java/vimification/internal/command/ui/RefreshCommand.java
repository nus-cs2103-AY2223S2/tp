package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.model.UiTaskList;
import vimification.ui.MainScreen;

public class RefreshCommand extends UiCommand {

    public static final String SUCCESS_MESSAGE = "Refreshed your task list.";

    @Override
    public CommandResult execute(MainScreen mainScreen) {
        UiTaskList taskList = mainScreen.getTaskTabPanel()
                .getOngoingTaskListPanel()
                .getTaskList();
        taskList.setPredicate(null);
        taskList.setComparator(null);
        return new CommandResult(SUCCESS_MESSAGE);
    }
}

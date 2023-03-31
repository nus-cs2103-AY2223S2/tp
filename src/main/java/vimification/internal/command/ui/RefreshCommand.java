package vimification.internal.command.ui;

import vimification.internal.command.CommandResult;
import vimification.ui.MainScreen;

public class RefreshCommand extends UiCommand {

    public CommandResult execute(MainScreen mainScreen) {
        mainScreen.getTaskTabPanel()
                .getOngoingTaskListPanel()
                .getTaskList()
                .refresh();
        return new CommandResult("Refreshed Task List.");
    }
}
